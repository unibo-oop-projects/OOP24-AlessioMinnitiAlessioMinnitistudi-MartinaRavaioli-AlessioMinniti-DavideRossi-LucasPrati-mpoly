package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.ChanceAndCommunityChest;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.Command;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * implementation of the chances and community chest card.
 */
public final class ChanceAndCommunityChestCard implements ChanceAndCommunityChest {

    private final Command command; 

    /**
     * constructor.
     * @param comm the command that will be executed 
     * once this card has been drawn
     */
    public ChanceAndCommunityChestCard(final Command comm) { 
        this.command = comm;
    } 

    @Override
    public void execute(final Player player) {
        this.command.execute(player, this.command.getKeyWord());
    }

    @Override
    public String getDescription() {
        return this.command.getDesc();
    }

}
