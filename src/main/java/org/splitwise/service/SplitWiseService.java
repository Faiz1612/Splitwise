package org.splitwise.service;

import org.splitwise.dto.ExpenseRequest;
import org.splitwise.dto.GroupRequest;
import org.splitwise.model.*;
import org.splitwise.observer.SplitWiseManagement;
import org.splitwise.observer.SplitWiseUserObserver;
import org.splitwise.repository.BalanceDb;
import org.splitwise.repository.GroupDb;
import org.splitwise.repository.SplitWiseDb;
import org.splitwise.repository.UserDb;
import org.splitwise.strategy.ExpenseSplitFactory;
import org.splitwise.strategy.splitcheck.SplitCheckFactory;
import org.splitwise.strategy.SplitCheckStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

public class SplitWiseService {
    GroupDb groupDb;
    SplitCheckFactory splitCheckFactory;
    SplitWiseDb splitWiseDb;
    ExpenseSplitFactory expenseSplitFactory;
    ExecutorService executorService;
    SplitWiseManagement splitWiseManagement;
    BalanceDb balanceDb;

    public SplitWiseService(GroupDb groupDb, SplitCheckFactory splitCheckFactory, SplitWiseDb splitWiseDb,
                            ExpenseSplitFactory expenseSplitFactory, ExecutorService executorService,
                            SplitWiseManagement splitWiseManagement, UserDb userDb, BalanceDb balanceDb) {
        this.groupDb = groupDb;
        this.splitCheckFactory = splitCheckFactory;
        this.splitWiseDb = splitWiseDb;
        this.expenseSplitFactory = expenseSplitFactory;
        this.executorService = executorService;
        this.splitWiseManagement = splitWiseManagement;
        this.userDb = userDb;
        this.balanceDb = balanceDb;
    }

    UserDb userDb;

    public Group createGroup(String ownerId, GroupRequest request) {
        List<Member> members = new ArrayList<>();
        Group group = new Group(request.getName(), null, ownerId);
        request.getMemberIds().forEach(id -> members.add(new Member(group.getId(), id)));

        group.setMemberIds(members);
        return groupDb.createGroup(group);
    }

    public SplitWise createSplitWise(ExpenseRequest expenseRequest, String ownerId) {
        SplitCheckStrategy strategy = splitCheckFactory.getSplitCheckStrategy(expenseRequest.getType());
        if (!strategy.validSplit(expenseRequest)) {
            throw new RuntimeException("invalid split");
        }
        List<Participant> participants = expenseSplitFactory.getSplitDivideStrategy(expenseRequest.getType())
                .getParticipantsWithDividedSplit(expenseRequest);
        SplitWise splitWise = new SplitWise(expenseRequest.getName(), expenseRequest.getAmount(), participants, expenseRequest.getGroupId(), ownerId);

        executorService.execute(() -> {
            for (int i = 0; i < participants.size(); i++) {
                splitWiseManagement.addObserver(splitWise.getId(), new SplitWiseUserObserver(participants.get(i).getUserId()));
            }
        });

        splitWiseDb.createSplitWise(splitWise);
        executorService.execute(() -> {
            for (int i = 0; i < participants.size(); i++) {
                splitWiseManagement.notifyObservers(splitWise);
            }
        });

        executorService.execute(() -> {
            participants.forEach(participant -> {
                if (participant.getUserId().equals(ownerId)) {
                    // for owner payment already done, so mark this
                    participant.setPaid(true);
                    Transaction transaction = new Transaction(UUID.randomUUID().toString(), participant.getAmount(), ownerId, expenseRequest.getGroupId(), splitWise.getId());
                    userDb.addTransaction(ownerId, transaction);
                }
            });
        });

        participants.forEach(participant -> {
            if(!participant.getUserId().equals(ownerId)) {
                Balance balance = new Balance(ownerId, participant.getAmount(), false, splitWise.getId());
                Balance reverseBalance = new Balance(participant.getUserId(), participant.getAmount().multiply(BigDecimal.valueOf(-1L)),
                        false, splitWise.getId());
                balanceDb.addBalance(participant.getUserId(), balance);
                balanceDb.addBalance(ownerId, balance);
            }
        });
        return splitWise;
    }

    public boolean clearExpense(String splitwiseId, String userId, BigDecimal amount) {
        SplitWise splitWise = splitWiseDb.getSplitWise(splitwiseId);
        // check if already paid;
        splitWise.getParticipantIds().forEach(participant -> {
            if (participant.getUserId().equals(userId) && participant.isPaid()) {
                throw new RuntimeException("already paid");
            }
        });

        // since question focus on splitwise, so keeping payment thing minmial, otherwise will implement strategy here
        try {
            Thread.sleep(100);
            // simulting payment
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        splitWise.getParticipantIds().forEach(participant -> {
            if (participant.getUserId().equals(userId)) {
                participant.setPaid(true);
            }
        });

        Transaction transaction = new Transaction(UUID.randomUUID().toString(), amount, userId, splitWise.getGroupId(), splitwiseId);
        userDb.addTransaction(userId, transaction);
        List<Balance> balances = balanceDb.getAllBalances(userId);
        balances.forEach(balance -> {
            if(balance.getExpenseId().equals(splitwiseId)) {
                balance.setSettled(true);
            }
        });

        List<Balance> reverseBalances = balanceDb.getAllBalances(splitWise.getOwnerId());
        reverseBalances.forEach(balance -> {
            if(balance.getExpenseId().equals(splitwiseId) && balance.getReceiverId().equals(userId)) {
                balance.setSettled(true);
            }
        });
        return true;
    }

    public List<Transaction> getTransactionHistory(String userId) {
        return userDb.getUser(userId).getTransactions();
    }

    public List<SplitWise> getSplitWiseHistory(String userId) {
        return userDb.getUser(userId).getSplitWises();
    }
}
