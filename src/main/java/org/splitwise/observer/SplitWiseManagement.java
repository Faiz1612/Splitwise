package org.splitwise.observer;

import org.splitwise.model.SplitWise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SplitWiseManagement {
    ConcurrentHashMap<String, List<ExpenseObserver>> observers;

    public void addObserver(String splitWiseId, ExpenseObserver expenseObserver) {
        observers.computeIfAbsent(splitWiseId, _ -> new ArrayList<>()).add(expenseObserver);
    }

    public void removeObserver(String splitWise, ExpenseObserver expenseObserver) {
        observers.get(splitWise).remove(expenseObserver);
    }

    public void notifyObservers(SplitWise splitWise) {
        observers.get(splitWise.getId()).forEach(observer -> {
            observer.execute(splitWise);
        });
    }
}
