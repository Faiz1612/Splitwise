package org.splitwise.strategy.splitcheck;

import org.splitwise.dto.ExpenseRequest;
import org.splitwise.strategy.SplitCheckStrategy;

import java.math.BigDecimal;

public class ExactSplitCheck implements SplitCheckStrategy {
    @Override
    public boolean validSplit(ExpenseRequest request) {
        BigDecimal totalAmount = BigDecimal.valueOf(0);

        request.getParticipants().forEach(participantRequest -> {
            BigDecimal value = BigDecimal.valueOf(Double.parseDouble(participantRequest.getShare()));
            totalAmount.add(value);
        });

        return (totalAmount.equals(request.getAmount()));
    }
}
