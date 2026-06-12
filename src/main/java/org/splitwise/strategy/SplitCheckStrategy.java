package org.splitwise.strategy;

import org.splitwise.dto.ExpenseRequest;

public interface SplitCheckStrategy {
    boolean validSplit(ExpenseRequest request);
}
