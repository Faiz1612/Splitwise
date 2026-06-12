package org.splitwise.dto;

import java.util.List;

public class BalanceSheet {
    List<BalanceView> incoming;
    List<BalanceView> outgoing;

    public BalanceSheet(List<BalanceView> incoming, List<BalanceView> outgoing) {
        this.incoming = incoming;
        this.outgoing = outgoing;
    }

    public List<BalanceView> getIncoming() {
        return incoming;
    }

    public void setIncoming(List<BalanceView> incoming) {
        this.incoming = incoming;
    }

    public List<BalanceView> getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(List<BalanceView> outgoing) {
        this.outgoing = outgoing;
    }
}
