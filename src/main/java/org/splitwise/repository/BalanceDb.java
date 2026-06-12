package org.splitwise.repository;

import org.splitwise.model.Balance;

import java.util.List;

public interface BalanceDb {
    void addBalance(String userId, Balance balance);
    List<Balance> getAllBalances(String userId);
}
