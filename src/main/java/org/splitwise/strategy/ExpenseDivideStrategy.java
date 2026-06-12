package org.splitwise.strategy;

import org.splitwise.dto.ExpenseRequest;
import org.splitwise.model.Participant;
import java.util.List;

public interface ExpenseDivideStrategy {
    List<Participant> getParticipantsWithDividedSplit(ExpenseRequest request);
}
