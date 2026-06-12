package org.splitwise.model;

import java.math.BigDecimal;

public class Participant {
    String userId;
    String groupId;
    String splitWiseId;
    boolean paid;
    BigDecimal amount;

    public Participant(String userId, String groupId, String splitWiseId, boolean paid, BigDecimal amount) {
        this.userId = userId;
        this.groupId = groupId;
        this.splitWiseId = splitWiseId;
        this.paid = paid;
        this.amount = amount;
    }

    public Participant() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSplitWiseId() {
        return splitWiseId;
    }

    public void setSplitWiseId(String splitWiseId) {
        this.splitWiseId = splitWiseId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
