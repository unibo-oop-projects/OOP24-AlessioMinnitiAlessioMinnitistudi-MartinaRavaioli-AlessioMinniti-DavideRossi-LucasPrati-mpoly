package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import it.unibo.monopoly.model.turnation.api.Player;

/**
 * interface for the typer chance and community chest card.
 */
public interface ChanceAndCommunityChest {

    /**
     * execute the associated command.
     * @param player ow which the effect will be executed
     */
    void execute(Player player);

    /**
     * 
     * @return the description of what the card does.
     */
    String getDescription();

}
