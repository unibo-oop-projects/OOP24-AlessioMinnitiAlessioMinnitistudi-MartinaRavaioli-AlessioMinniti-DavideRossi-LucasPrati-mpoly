package it.unibo.monopoly.model.transactions.api;

/**
 * An object that stores information regarding 
 * all transactions invoked on a {@link Bank} object during the turn of a player. 
 * This ledger is used to write and retrieve information about the transactions 
 * executed during the the turn of a player; meaning the method invocations the user called
 * on a {@link Bank} object (for example, {@link Bank#buyTitleDeed(String, int)},
 * {@link Bank#payRent(String, int, int)}). 
 * Implementations of the {@link Bank} interface can harness this ledger to apply specific domain logic checks. 
 * For instance, in the original version of the game the method {@link Bank#payRent(String, int, int)} 
 * can only be called once per turn, and the player cannot end its turn if it still has to pay a rent.
 * This ledger enables to track all payment operations with flexibility and room for expandability. 
 */
public interface TransactionLedger {

    /**
     * Reset the registered transaction types.
     */
    void reset();

    /**
     * Register a new type of transaction that the player can execute in its turn.
     * @param name the name of the type of transaction to register
     * @param minimumExecutions mark the transaction as mandatory to execute for a minimum number of times before the
     * player can end its turn. A transaction that is mandatory can be marked as executed 
     * by calling the method {@link #markExecution(String)} and passing the corresponding name. 
     * As long as a mandatory transaction hasn't been executed {@code minimumExecutions} times 
     * the method {@link #checkAllMandatoryTransactionsCompleted()} will return {@code false}
     * @param maximumExecutions number of maximum times the transaction type can be executed
     */
    void registerTransaction(PropertyActionsEnum name, int minimumExecutions, int maximumExecutions);


    /**
     * Register a new type of transaction that the player can execute in its turn.
     * This type of transaction has no maximum number of execution times.
     * @param name the name of the type of transaction to register
     * @param minimumExecutions mark the transaction as mandatory to execute for a minimum number of times before the
     * player can end its turn. A transaction that is mandatory can be marked as executed 
     * by calling the method {@link #markExecution(String)} and passing the corresponding name. 
     * As long as a mandatory transaction hasn't been executed {@code minimumExecutions} times 
     * the method {@link #checkAllMandatoryTransactionsCompleted()} will return {@code false}
     */
    void registerTransaction(PropertyActionsEnum name, int minimumExecutions);

    /**
     * Removes tracking information regarding a specific transaction if present in the ledger.
     * Otherwise it does nothing. 
     * @param name the name of the transaction to wipe data of
     */
    void removeIfPresent(PropertyActionsEnum name);

    /**
     * Register the execution of a transaction in the ledger.
     * @param name the name of the transaction to mark the execution of
     */
    void markExecution(PropertyActionsEnum name);

    /**
     * Deleted a previously registered execution of a transaction in the ledger.
     * @param name the name of the transaction to unmark the execution of
     */
    void unmarkExecution(PropertyActionsEnum name);

    /**
     * Check if all the transactions that are marked as mandatory have been
     * executed, for the number of times that was specifcied upon registration of the 
     * transaction (parameter {@code minimumExecutions}).
     * @return {@code true} if all mandatory transactions were completed, {@code false} otherwise
     */
    boolean checkAllMandatoryTransactionsCompleted();
}
