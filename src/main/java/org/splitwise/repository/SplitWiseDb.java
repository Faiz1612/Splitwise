package org.splitwise.repository;

import org.splitwise.model.SplitWise;

public interface SplitWiseDb {
    SplitWise createSplitWise(SplitWise splitWise);
    SplitWise getSplitWise(String id);
}
