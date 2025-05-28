package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Chance_CommunityChest;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.turnation.api.Player;

public class Chance_CommunityChestCard implements Chance_CommunityChest{

    private String description;
    private Command command; 

    public Chance_CommunityChestCard(String desc, Command comm) {
        this.description = desc; 
        this.command = comm;
    } 

    @Override
    public void execute(Player player) {
        this.command.execute(player);
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}
