package org.splitwise.repository;

import org.splitwise.model.SplitWise;

import java.util.concurrent.ConcurrentHashMap;

public class InMemorySplitWiseDb implements SplitWiseDb {
    ConcurrentHashMap<String, SplitWise> splitWiseHashMap;

    public InMemorySplitWiseDb() {
        this.splitWiseHashMap = new ConcurrentHashMap<>();
    }

    @Override
    public SplitWise createSplitWise(SplitWise splitWise) {
        splitWiseHashMap.put(splitWise.getId(), splitWise);
        return splitWise;
    }

    @Override
    public SplitWise getSplitWise(String id) {
        return splitWiseHashMap.get(id);
    }
}
