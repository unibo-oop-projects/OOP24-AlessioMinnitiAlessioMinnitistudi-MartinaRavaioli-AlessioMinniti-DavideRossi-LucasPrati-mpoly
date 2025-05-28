package it.unibo.monopoly.model.transactions.api;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * An object used by {@link TurnationManager} to check on the 
 * finantial situation of the players and manage the state of the {@link Bank}. {@link TurnationManager}, in order to arbitrate
 * the game, may have the necessity to retrieve information related to the finantial situation of a player
 * (ask if a player is bankrupt, rank the players based on their properties, ask if a player has executed
 * all mandatory payments...). Also, {@link TurnationManager} may need to be able to command specific operations
 * (for instance when a player gets eliminated all the {@link TitleDeed} that were owned become available
 * for purchase).
 * This interface describes all operations that are necessary for {@link TurnationManager} to
 * pilot the game flow.
*/
public interface BankState {

    /**
     * Checks if a player's finantial situation is valid for the
     * continuation of the game. 
     * @param player The player whose {@link BankAccount} to check
     * @return {@code true} if the player's {@link BankAccount} is in a
     * state that is valid for the continuation of the game, false otherwise
     */
    boolean canContinuePlay(Player player);

    /**
     * Checks if the {@link Player} that is currently playing its turn
     * has performed all transactions that are redeemed as mandatory.
     * For instance, if a player has to pay the rent for stepping onto 
     * a property owned by another player this method will return {@code false}
     * until the player does so. 
     * Generally speaking, the only type of transaction that is considered mandatory
     * is the payment of the rent, in the original version of Monopoly. However, this
     * implementation enables the modification of the game easily. 
     * @return {@code true} if the player has performed all the transactions 
     * that he was meant to in its turn and can therefore end its turn, {@code false}
     * otherwise.
     */
    boolean allMandatoryTransactionsCompleted();

    /**
     * Ranks the players based on the state of their {@link BankAccount}
     * and the {@link TitleDeed} they own.
     * The ranking algorithm is given to the bank on construction
     * and it is responsible for calculating the total monetary 
     * value of a player.
     * In the default version of the algorithm the total monetary value
     * is given by summing the {@code balance} of the player's {@link BankAccount}
     * and the {@code mortgageValue} of each {@link TitleDeed} owned.
     * However different versions of the algorithm may give more importance
     * to a value more than another or may simply do different calculations,
     * resulting in a different ranking
     * @return a {@link List} of {@link Pair} where the first element is 
     * the player's id and the second is its monetary value. 
     * The values of the list are sorted in ascending order based on monetary
     * value.
     */
    List<Pair<Integer, Integer>> rankPlayers();

    /**
     * Wipes all internal data the {@link Bank} object has stored to track the execution
     * of transaction requests. 
     * When calling transactional methods {@link #payRent(String, int, Collection)},
     * {@link #sellTitleDeed(String)},{@link #buyTitleDeed(String, int)} the {@link Bank} keeps 
     * track of these method requests. This is done to keep track of the payments invoked by the
     * player that is currently playing, which is necessary to implement logic regarding the player's turn.
     * (for instance, you cannot end the turn unless you call payRent, you can only call sellTitleDeed once per turn...).
     * Calling {@link #resetTransactionData()} should be done when the player ends its turn, signaling the {@link Bank}
     * that the current player has ended its turn and that information related to method requests is no longer
     * necessary. It prepares the {@link Bank} to be ready to keep track of the transaction requests of a new player.
     */
    void resetTransactionData();

    /**
     * Revoke ownership of all {@link TitleDeed} possessed
     * by the given {@link Player} and delete its {@link BankAccount}.
     * This action does something similar to {@link Bank#sellTitleDeed(String)}, meaning
     * that {@link TitleDeed#removeOwner()} is called on all the {@code deeds} of the player, and they can
     * be bought again. However no money is deposited to the player's {@link BankAccount}, so it's a force removal.
     * Additionally, it removes the {@link BankAccount} with the same {@code id} as the one of the player
     * (meaning it wipes its {@link BankAccount} from the system). The player will no longer be able to 
     * call transaction methods after this.
     * This is a dangerous method, and should only be called by {@link TurnationManager} when a player gets eliminated. 
     * After the call the player will no longer be able to play the game in its full functionality, which may result in
     * bugs if not invoked properly
     * @param pl The player to eliminate, the {@code id} parameter {@link Player#getID()} will be retrieved and used
     * to perform all cancellation operations.
     */
    void deletePlayer(Player pl);
}
