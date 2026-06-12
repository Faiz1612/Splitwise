package org.splitwise.strategy;

import org.splitwise.dto.ExpenseRequest;
import org.splitwise.model.Participant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExactSplit implements ExpenseDivideStrategy {
    @Override
    public List<Participant> getParticipantsWithDividedSplit(ExpenseRequest request) {
        BigDecimal amount = request.getAmount();
        int size = request.getParticipants().size();
        BigDecimal share = amount.divide(BigDecimal.valueOf(size));
        List<Participant> participants = new ArrayList<>();
        request.getParticipants().forEach(participantRequest -> {
            Participant participant = new Participant();
            participant.setAmount(share);
            participant.setPaid(false);
            participants.add(participant);
        });

        return participants;
    }
}
