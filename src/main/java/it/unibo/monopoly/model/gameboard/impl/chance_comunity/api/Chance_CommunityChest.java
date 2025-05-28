package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import it.unibo.monopoly.model.turnation.api.Player;

public interface Chance_CommunityChest {

    /**
     * execute the associated command.
     */
    void execute(Player player);

    /**
     * 
     * @return the description of what the card does.
     */
    String getDescription();

}
