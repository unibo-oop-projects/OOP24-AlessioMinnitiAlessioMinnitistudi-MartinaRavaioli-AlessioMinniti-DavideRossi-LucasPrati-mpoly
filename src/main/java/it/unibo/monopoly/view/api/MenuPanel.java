package it.unibo.monopoly.view.api;

/**
 * interface of {@link SwingMenuPanel}.
 */
public interface MenuPanel extends GamePanel {

    /**
     * Display the result of the increment/decrement of the number of players.
     * @param num the number of player to display
     * @param reachMinPlayers whether the number of players is equal to the minimum allowed
     * @param reachMaxPlayers whether the number of players is equal to the maximum allowed
     */
    void refreshNumPlayers(int num, boolean reachMinPlayers, boolean reachMaxPlayers);
}
