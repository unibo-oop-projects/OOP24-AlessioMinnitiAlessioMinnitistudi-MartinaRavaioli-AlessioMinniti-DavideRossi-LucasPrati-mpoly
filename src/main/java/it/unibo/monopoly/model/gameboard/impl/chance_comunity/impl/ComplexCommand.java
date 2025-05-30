package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.turnation.api.Player;

public class ComplexCommand implements Command {
    private final List<Command> commands;
    private final String desc;

    public ComplexCommand(List<Command> commands, String desc){
        this.commands = commands;
        this.desc = desc;
    }

    @Override
    public String getKeyWord() {
        return desc;
    }

    @Override
    public void execute(Player player) {
        for (Command command : commands) {
            command.execute(player);
        }
    }

}
