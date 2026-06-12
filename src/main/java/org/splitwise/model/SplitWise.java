package org.splitwise.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class SplitWise {
    String id;
    String description;
    BigDecimal totalAmount;
    List<Participant> participantIds;
    String ownerId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    String groupId;

    public SplitWise(String description, BigDecimal totalAmount, List<Participant> participantIds, String groupId, String ownerId) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.totalAmount = totalAmount;
        this.participantIds = participantIds;
        this.groupId = groupId;
        this.ownerId = ownerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Participant> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(List<Participant> participantIds) {
        this.participantIds = participantIds;
    }
}
