package org.splitwise.model;

import java.math.BigDecimal;

public class Balance {
    String receiverId;
    BigDecimal amount;
    boolean settled;
    String expenseId;

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isSettled() {
        return settled;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }

    public Balance(String receiverId, BigDecimal amount, boolean paid, String expenseId) {
        this.receiverId = receiverId;
        this.amount = amount;
        this.expenseId = expenseId;
        this.settled = paid;
    }
}
