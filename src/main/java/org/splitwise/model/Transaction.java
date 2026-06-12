package org.splitwise.model;

import java.math.BigDecimal;

public class Transaction {
    String transactionId;
    BigDecimal amount;
    String senderId;
    String groupId;
    String splitWiseId;

    public Transaction(String transactionId, BigDecimal amount, String senderId, String groupId, String splitWiseId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.senderId = senderId;
        this.groupId = groupId;
        this.splitWiseId = splitWiseId;
    }
}
