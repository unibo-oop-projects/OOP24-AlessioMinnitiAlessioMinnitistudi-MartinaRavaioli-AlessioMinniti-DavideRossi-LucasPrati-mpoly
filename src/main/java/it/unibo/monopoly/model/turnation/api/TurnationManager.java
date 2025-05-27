package it.unibo.monopoly.model.turnation.api;

import java.util.Collection;
import java.util.List;

/**
 * turnation manager interface.
*/
public interface TurnationManager {
    /**
     * @return if is over
     */
    boolean isOver();
    /**
     * @return the next player
     */
    Player getNextPlayer();
    /**
     * @return the dices' result
     */
    Collection<Integer> moveByDices();
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
     * @param p
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
     * @param dice
     */
    void setDice(Dice dice);
    /**
     * set all the players.
     * @param plList
     */
    void setList(List<Player> plList);
    /**
     * tells if the current player is in prison.
     * @param plList
     */
    boolean isCurrentPlayerInPrison();
    
}
