package it.unibo.monopoly.model.turnation.api;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

/**
 * turnation manager interface.
*/
public interface TurnationManager {
    /**
     * tells if is over.
     * @return bool
     */
    boolean isOver();
    /**
     * get the next player.
     * @return the next player
     */
    Player getNextPlayer();
    /**
     * get the dices' result.
     * @return multiple int
     * @throws IllegalAccessException
     */
    Collection<Integer> moveByDices() throws IllegalAccessException;
    /**
     * return the id of the current player.
     * @return int
     */
    int getIdCurrPlayer();
    /**
     * return the current player.
     * @return Player
     */
    Player getCurrPlayer();
    /**
     * return list of all the players.
     * @return List of Player
     */
    List<Player> getPlayerList();
    /**
     * add a player.
     * @param p player to add
     */
    void addPlayer(Player p);
    /**
     * set the game over.
     */
    void setOver();
    /**
     * get dices' info.
     * @return Dice
     */
    Dice getDice();
    /**
     * set the dice.
     * @param dice dice
     */
    void setDice(Dice dice);
    /**
     * set all the players.
     * @param plList list of the players
     */
    void setList(List<Player> plList);
    /**
     * tells if the current player is in prison.
     * @return bool
     */
    boolean isCurrentPlayerInPrison();
    /**
     * tells if the player can exit the prison.
     * @return bool
     * @param value result of the dices
     */
    boolean canExitPrison(Collection<Integer> value);

    /**
     * tells if the player can pass the turn.
     * @return bool
     */
    boolean canPassTurn();
    /**
     * tells if the player will die if he pass the turn.
     * @return bool
     */
    boolean playerDiesIfTurnPassed();
    /**
     * get the winner.
     * @return player
     */
    Pair<String, Integer> getWinner();
    /**
     * get the ranking of the players.
     * @return multiple players
     */
    List<Pair<String, Integer>> getRanking();
    /**
     * delete the player.
     * @param player player to delete
     */
    void deletePlayer(Player player);
    /**
     * reset the bank state transactions data.
     */
    void resetBankState();
    /**
     * tells if the current player has already thrown the dices.
     * @return bool
     */
    boolean hasCurrPlayerThrownDices();
}
