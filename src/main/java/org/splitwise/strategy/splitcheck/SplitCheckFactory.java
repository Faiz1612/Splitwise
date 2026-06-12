package org.splitwise.strategy.splitcheck;

import org.splitwise.enums.SplitType;
import org.splitwise.strategy.SplitCheckStrategy;

import java.util.HashMap;
import java.util.Map;

public class SplitCheckFactory {
    Map<SplitType, SplitCheckStrategy> splitCheckStrategyMap;

    public SplitCheckFactory() {
        this.splitCheckStrategyMap = new HashMap<>();
        splitCheckStrategyMap.put(SplitType.EXACT, new ExactSplitCheck());
        splitCheckStrategyMap.put(SplitType.PERCENTAGE, new PercentageSplitCheck());
    }

    public SplitCheckStrategy getSplitCheckStrategy(SplitType splitType) {
        return splitCheckStrategyMap.get(splitType);
    }
}
