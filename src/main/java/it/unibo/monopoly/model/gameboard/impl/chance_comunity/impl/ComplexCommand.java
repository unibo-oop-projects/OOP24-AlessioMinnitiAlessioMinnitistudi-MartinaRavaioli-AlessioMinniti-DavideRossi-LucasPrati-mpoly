package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.turnation.api.Player;

public class ComplexCommand implements Command {
    private final List<Command> commands;
    private final String keyword;

    public ComplexCommand(final List<Command> commands, final String desc){
        this.commands = commands;
        this.keyword = desc;
        
    }

    @Override
    public String getKeyWord() {
        return keyword;
    }

    @Override
    public void execute(Player player) {
        for (Command command : commands) {
            command.execute(player);
        }
    }

    @Override
    public String getDesc() {
        String str = "";
        for (Command command : commands) {
            if (commands.indexOf(command) == commands.size()-1) {
                str = str + command.getDesc();
            } else {
                str = str + command.getDesc() + " then\n";
            }
        }
        return str;
    }

}
