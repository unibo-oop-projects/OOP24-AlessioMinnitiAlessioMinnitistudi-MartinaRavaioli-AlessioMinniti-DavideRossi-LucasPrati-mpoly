package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ChanceAndCommunityChest;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.turnation.api.Player;

public class ChanceAndCommunityChestCard implements ChanceAndCommunityChest{

    private Command command; 

    public ChanceAndCommunityChestCard(Command comm) { 
        this.command = comm;
    } 

    @Override
    public void execute(Player player) {
        this.command.execute(player);
    }

    @Override
    public String getDescription() {
        return this.command.getDesc();
    }

}
