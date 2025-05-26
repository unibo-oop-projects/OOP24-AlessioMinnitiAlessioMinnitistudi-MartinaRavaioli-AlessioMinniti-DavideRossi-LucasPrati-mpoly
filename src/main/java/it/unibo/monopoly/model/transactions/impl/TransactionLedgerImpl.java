package it.unibo.monopoly.model.transactions.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.unibo.monopoly.model.transactions.api.TransactionLedger;

public class TransactionLedgerImpl implements TransactionLedger {

    private final Set<TransactionLedgerEntry> allowedTransactionTypes = new HashSet<>();
    private final Map<String,Integer> executions = new HashMap<>();

    @Override
    public void reset() {
        allowedTransactionTypes.clear();
        executions.clear();
    }

    @Override
    public void registerTransaction(final String name, final boolean mandatory, final int nTimes) {
        if (allowedTransactionTypes.stream().anyMatch(t -> name.equals(t.name()))) {
            throw new IllegalStateException("A transaction type with this name is already present in the ledger");
        }
        allowedTransactionTypes.add(new TransactionLedgerEntry(name, mandatory, nTimes));
        executions.put(name, 0);
    }

    @Override
    public void markExecution(final String name) {
        if (allowedTransactionTypes.stream().noneMatch(t -> name.equals(t.name()))) {
            throw new IllegalArgumentException("No transaction with this name exists in the ledger. Register the transaction type by calling the method registerTransaction before asking to mark its execution");
        }
        final TransactionLedgerEntry transaction = allowedTransactionTypes.stream().filter(t -> name.equals(t.name())).findFirst().get();
        if (executions.get(name) >= transaction.nTimes()) {
            throw new IllegalStateException("The player has already executed the transaction" + name + "for the maximum number of times per turn");
        }
        executions.put(name, executions.get(name) + 1);
    }

    @Override
    public void unmarkExecution(final String name) {
        if (allowedTransactionTypes.stream().noneMatch(t -> name.equals(t.name()))) {
            throw new IllegalArgumentException("No transaction with this name exists in the ledger. Register the transaction type by calling the method registerTransaction before asking to mark its execution");
        }
        if (executions.get(name) <= 0) {
            throw new IllegalStateException("The player has never asked to execute the transaction" + name + ". Therefore it is not possible to unmark it");
        }
        executions.put(name, executions.get(name) - 1);
    }

    @Override
    public boolean checkAllMandatoryTransactionsCompleted() {
        /*filter all the transactions types that are mandatory and that
        * were not executed nTimes (which for transactions that are mandatory is the minimum
        * and maximum number of times of executions required to continue playing). Then count if
        * if there are any; if that is the case it means that there are still some transactions that are marked
        * as mandatory but have not been done enough times yet.
        */
        //TODO check spelling and meaning of this comment
        return allowedTransactionTypes
        .stream()
        .filter(t -> t.mandatory())
        .noneMatch(t -> executions.get(t.name()) < t.nTimes());
    }

    private static record TransactionLedgerEntry(String name, boolean mandatory, int nTimes) {}
}
