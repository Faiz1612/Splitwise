package org.splitwise.observer;

import org.splitwise.model.SplitWise;

public interface ExpenseObserver {
    void execute(SplitWise splitWise);
}
