package org.splitwise.repository;

import org.splitwise.model.SplitWise;
import org.splitwise.model.Transaction;
import org.splitwise.model.User;

public interface UserDb {
    void addSplitWise(String userid, SplitWise splitWise);
    void addTransaction(String userId, Transaction transaction);
    User getUser(String userId);
}
