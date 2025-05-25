package it.unibo.monopoly.model.transactions.api;

/**
 * An object that stores information regarding 
 * all transactions invoked on a {@link Bank} object during the turn of a player. 
 * This ledger is used to write and retrieve information about the transactions 
 * executed during the the turn of a player; meaning the method invocations the user called
 * on a {@link Bank} object (for example, {@link Bank#buyTitleDeed(String, int)},{@link Bank#payRent(String, int, java.util.Collection)}). 
 * Implementations of the {@link Bank} interface can harness this ledger to apply specific domain logic checks. 
 * For instance, in the original version of the game the method {@link Bank#payRent(String, int, java.util.Collection)} can only be
 * called once per turn, and the player cannot end its turn if it still has to pay a rent.
 * This ledger enables to track all payment operations with flexibility and room for expandability. 
 */
public interface TransactionLedger {

    /**
     * Reset the registered transactions
     */
    void reset();

    /**
     * Register a new type of transaction that the player can execute in its turn.
     * @param string
     * @param mandatory mark the transaction as mandatory, meaning the player has to execute it
     * in order to be able to conclude its turn. A transaction that is mandatory can be marked as executed 
     * by calling the method {@link #markExecution(String)} and passing the corresponding name. As long as a mandatory transaction
     * is marked as not executed the method {@link #checkAllMandatoryTransactionsCompleted()} will return {@code false}
     * @param nTimes number of times the transaction can or has to be executed, depending if {@code mandatory} is true or false
     */
    void registerTransaction(String name, boolean mandatory, int nTimes);

    /**
     * Register the execution of a transaction in the ledger.
     * @param name the name of the transaction to mark the execution of
     * @throws IllegalStateException if marking the execution would violated 
     * the ledger's policy. This may happen mainly if you're trying to mark the execution
     * of an action that has already been executed for its maximum number of times.
     */
    void markExecution(String name);

    /**
     * Deleted a previously registered execution of a transaction in the ledger.
     * @param name the name of the transaction to unmark the execution of
     * @throws IllegalArgumentException if no type of transaction with this name 
     * has been registered in the ledger
     * @throws IllegalStateException if execution of the specified transaction has never 
     * been marked by the ledger. Unmarking the execution would result in a negative value for
     * the {@code numberExecutions} of the specified transaction.
     */
    void unmarkExecution(String name);

    /**
     * Check if all the transactions that are marked as mandatory have been
     * executed, for the number of times that was specifcied upon registration of the transaction.
     * @return {@code true} if all mandatory transactions were completed, {@code false} otherwise
     */
    boolean checkAllMandatoryTransactionsCompleted();

}
