package org.splitwise.observer;

import org.splitwise.model.SplitWise;
import org.splitwise.repository.UserDb;

public class SplitWiseUserObserver implements ExpenseObserver {
    String userId;
    UserDb userDb;

    public SplitWiseUserObserver(String userId) {
        this.userId = userId;
    }

    @Override
    public void execute(SplitWise splitWise) {
        // notify and add this splitwise to user
        userDb.addSplitWise(userId, splitWise);
    }
}
