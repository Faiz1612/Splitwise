package org.splitwise.model;

public class Member {
    public Member(String groupId, String id) {
        this.groupId = groupId;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    String id;
    String groupId;
}
