package org.splitwise.dto;

import org.splitwise.enums.SplitType;
import java.math.BigDecimal;
import java.util.List;

public class ExpenseRequest {
    String name;
    List<ParticipantRequest> participants;
    BigDecimal amount;
    SplitType type;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    String groupId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParticipantRequest> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantRequest> participants) {
        this.participants = participants;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public SplitType getType() {
        return type;
    }

    public void setType(SplitType type) {
        this.type = type;
    }
}
