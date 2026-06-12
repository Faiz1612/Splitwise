package org.splitwise.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Group {
    public Group(String name, List<Member> memberIds, String ownerId) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.memberIds = memberIds;
        this.ownerId = ownerId;
        this.createdAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Member> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<Member> memberIds) {
        this.memberIds = memberIds;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    String name;
    String id;
    List<Member> memberIds;
    String ownerId;
    LocalDateTime createdAt;
}
