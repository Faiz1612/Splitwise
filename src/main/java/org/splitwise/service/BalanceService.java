package org.splitwise.service;

import org.splitwise.dto.BalanceSheet;
import org.splitwise.dto.BalanceView;
import org.splitwise.model.Balance;
import org.splitwise.repository.BalanceDb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceService {
    BalanceDb balanceDb;
    public BalanceSheet getBalanceView(String userId) {
        List<Balance> balances = balanceDb.getAllBalances(userId);
        Map<String, BigDecimal> expenseMap = new HashMap<>();
        balances.forEach(balance -> {
            if(!balance.isSettled()) {
                expenseMap.putIfAbsent(balance.getReceiverId(), BigDecimal.ZERO).add(balance.getAmount());
            }
        });

        List<BalanceView> incoming = new ArrayList<>();
        List<BalanceView> outgoing = new ArrayList<>();

        expenseMap.forEach((k,v) -> {
            if(v.compareTo(BigDecimal.ZERO) < 0) {
                incoming.add(new BalanceView(k, v));
            } else if(v.compareTo(BigDecimal.ZERO) > 0) {
                outgoing.add(new BalanceView(k, v));
            }
        });

        return new BalanceSheet(incoming, outgoing);
    }
}
