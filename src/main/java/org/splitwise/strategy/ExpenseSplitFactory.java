package org.splitwise.strategy;

import org.splitwise.enums.SplitType;

import java.util.HashMap;
import java.util.Map;

public class ExpenseSplitFactory {
    Map<SplitType, ExpenseDivideStrategy> splitCheckStrategyMap;

    public ExpenseSplitFactory() {
        this.splitCheckStrategyMap = new HashMap<>();
        splitCheckStrategyMap.put(SplitType.EXACT, new PercentageSplit());
        splitCheckStrategyMap.put(SplitType.PERCENTAGE, new ExactSplit());
    }

    public ExpenseDivideStrategy getSplitDivideStrategy(SplitType splitType) {
        return splitCheckStrategyMap.get(splitType);
    }
}
