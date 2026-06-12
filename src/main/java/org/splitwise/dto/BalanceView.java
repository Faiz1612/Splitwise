package org.splitwise.dto;

import java.math.BigDecimal;

public class BalanceView {
    String id;
    BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BalanceView(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }
}
