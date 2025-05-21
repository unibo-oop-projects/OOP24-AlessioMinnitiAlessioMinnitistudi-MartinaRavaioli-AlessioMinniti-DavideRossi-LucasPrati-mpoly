package it.unibo.monopoly.view.api;

import it.unibo.monopoly.model.turnation.api.Player;

/**
 * A panel that displays information of a {@link Player}.
 */
public interface PlayerPanel extends GamePanel {

    /**
     * Display the information related to the {@link Player}.
     * @param pl the {@link Player} to unpack
     */
    void displayPlayer(Player pl);
}
