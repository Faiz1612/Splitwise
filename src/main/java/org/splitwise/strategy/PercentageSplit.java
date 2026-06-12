package org.splitwise.strategy;

import org.splitwise.dto.ExpenseRequest;
import org.splitwise.model.Participant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PercentageSplit implements ExpenseDivideStrategy {
    @Override
    public List<Participant> getParticipantsWithDividedSplit(ExpenseRequest request) {
        BigDecimal amount = request.getAmount();
        List<Participant> participants = new ArrayList<>();
        request.getParticipants().forEach(participantRequest -> {
            Participant participant = new Participant();
            int share = Integer.parseInt(participantRequest.getShare());
            BigDecimal shareAmount = (amount.multiply(BigDecimal.valueOf(share))).divide(BigDecimal.valueOf(100));
            participant.setAmount(shareAmount);
            participant.setPaid(false);
            participants.add(participant);
        });

        return participants;
    }
}
