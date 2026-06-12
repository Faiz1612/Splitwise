package org.splitwise.model;

import java.util.List;

public class User {
    String name;
    String userId;
    List<SplitWise> splitWises;
    List<Transaction> transactions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<SplitWise> getSplitWises() {
        return splitWises;
    }

    public void setSplitWises(List<SplitWise> splitWises) {
        this.splitWises = splitWises;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
