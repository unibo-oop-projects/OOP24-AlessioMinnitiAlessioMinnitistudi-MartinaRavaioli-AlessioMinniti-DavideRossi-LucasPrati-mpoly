package it.unibo.monopoly.model.transactions.api;

/**
 * An object encapsulating a call to a method of a {@link Bank} object. 
 * A {@link BankAction} has a name that briefly describes the action
 * and a method {@link BankAction#executeTransaction()} that when invoked
 * executes the incapsulated action on a Bank object.
 */
public interface BankAction {

    /**
     * @return a {@code String} that describes the functionality of the action.
     */
    String getName();

    /**
     *    
     * Executes the incapsulated action. 
     * When invoked, it triggers the execution of a method of the
     * {@link Bank} interface (such as {@link Bank#buyTitleDeed(String, int)}, {@link Bank#payRent(String, int)})  
     */
    void executeTransaction();
}
