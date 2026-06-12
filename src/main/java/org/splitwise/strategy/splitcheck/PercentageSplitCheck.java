package org.splitwise.strategy.splitcheck;

import org.splitwise.dto.ExpenseRequest;
import org.splitwise.strategy.SplitCheckStrategy;

import java.util.concurrent.atomic.AtomicReference;

public class PercentageSplitCheck implements SplitCheckStrategy {
    @Override
    public boolean validSplit(ExpenseRequest request) {
        AtomicReference<Integer> totalPercentage = new AtomicReference<>(0);

        request.getParticipants().forEach(participantRequest -> {
            Integer value = Integer.valueOf(participantRequest.getShare());
            totalPercentage.updateAndGet(v -> v + value);
        });

        return (totalPercentage.get() == 100);
    }
}
