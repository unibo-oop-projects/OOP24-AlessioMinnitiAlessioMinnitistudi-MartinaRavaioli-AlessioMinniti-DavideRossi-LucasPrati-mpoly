package it.unibo.monopoly.model.transactions.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.unibo.monopoly.model.transactions.api.TransactionLedger;

final class TransactionLedgerImpl implements TransactionLedger {

    private final Set<TransactionLedgerEntry> allowedTransactionTypes = new HashSet<>();
    private final Map<String,Integer> executions = new HashMap<>();

    @Override
    public void reset() {
        allowedTransactionTypes.clear();
        executions.clear();
    }

    @Override
    public void registerTransaction(final String name, final int minimumExecutions, final int maximumExecutions) {
        if (allowedTransactionTypes.stream().anyMatch(t -> name.equals(t.name()))) {
            throw new IllegalStateException("A transaction type with this name is already present in the ledger");
        }
        allowedTransactionTypes.add(new TransactionLedgerEntry(name, minimumExecutions, maximumExecutions));
        executions.put(name, 0);
    }

    @Override
    public void registerTransaction(final String name, final int minimumExecutions) {
        if (allowedTransactionTypes.stream().anyMatch(t -> name.equals(t.name()))) {
            throw new IllegalStateException("A transaction type with this name is already present in the ledger");
        }
        allowedTransactionTypes.add(new TransactionLedgerEntry(name, minimumExecutions, -1));
        executions.put(name, 0);
    }

    @Override
    public void removeIfPresent(final String name) {
        allowedTransactionTypes.removeIf(e -> name.equals(e.name()));
        if (executions.containsKey(name)) {
            executions.remove(name);
        }
    }

    @Override
    public void markExecution(final String name) {
        if (allowedTransactionTypes.stream().noneMatch(t -> name.equals(t.name()))) {
            throw new IllegalArgumentException("No transaction with this name exists in the ledger. Register the transaction type by calling the method registerTransaction before asking to mark its execution");
        }
        final TransactionLedgerEntry transaction = allowedTransactionTypes.stream().filter(t -> name.equals(t.name())).findFirst().get();
        if (transaction.maximumExecutions() > 0 && executions.get(name) >= transaction.maximumExecutions()) {
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
        return allowedTransactionTypes
        .stream()
        .allMatch(t -> {
            final int executionTimes = executions.containsKey(t.name()) ? executions.get(t.name()) : 0;
            return executionTimes >= t.minimumExecutions();
        });
    }

    private static record TransactionLedgerEntry(String name, int minimumExecutions, int maximumExecutions) {}
}
