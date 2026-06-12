package org.splitwise.repository;

import org.splitwise.model.Group;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryGroupDb implements GroupDb {
    ConcurrentHashMap<String, Group> groupsDb;

    public InMemoryGroupDb() {
        this.groupsDb = new ConcurrentHashMap<>();
    }

    @Override
    public Group createGroup(Group group) {
        groupsDb.put(group.getId(), group);
        return group;
    }

    @Override
    public Group getGroup(String groupId) {
        return groupsDb.get(groupId);
    }
}
