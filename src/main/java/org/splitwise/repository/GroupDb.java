package org.splitwise.repository;

import org.splitwise.model.Group;

public interface GroupDb {
    Group createGroup(Group group);
    Group getGroup(String groupId);
}
