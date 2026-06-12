package org.splitwise.repository;

import org.splitwise.model.SplitWise;
import org.splitwise.model.Transaction;
import org.splitwise.model.User;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserDb implements UserDb {
    ConcurrentHashMap<String, User> userMap;

    public InMemoryUserDb() {
        this.userMap = new ConcurrentHashMap<>();
    }

    @Override
    public void addSplitWise(String userid, SplitWise splitWise) {
        userMap.get(userid).getSplitWises().add(splitWise);
    }

    @Override
    public void addTransaction(String userId, Transaction transaction) {
        userMap.get(userId).getTransactions().add(transaction);
    }

    @Override
    public User getUser(String userId) {
        return userMap.get(userId);
    }
}
