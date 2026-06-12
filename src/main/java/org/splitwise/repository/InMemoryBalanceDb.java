package org.splitwise.repository;

import org.splitwise.model.Balance;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBalanceDb implements BalanceDb {
    ConcurrentHashMap<String, List<Balance>> balancesDb;

    public InMemoryBalanceDb() {
        this.balancesDb = new ConcurrentHashMap<>();
    }


    @Override
    public void addBalance(String userId, Balance balance) {
        balancesDb.get(userId).add(balance);
    }

    @Override
    public List<Balance> getAllBalances(String userId) {
        return balancesDb.get(userId);
    }
}
