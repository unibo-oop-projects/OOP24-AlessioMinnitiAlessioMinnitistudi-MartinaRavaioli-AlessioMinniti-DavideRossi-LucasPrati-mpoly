package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * implementation of complex command.
 * xCommands that are composed by base commands and need to execute each of them
 */
public final class ComplexCommand implements Command {
    private final List<Command> commands;
    private final String keyword;

    /**
     * constructor.
     * @param commands a list of the base commands supported by the game
     * @param desc
     */
    public ComplexCommand(final List<Command> commands, final String desc) {
        this.commands = commands;
        this.keyword = desc;
    }

    @Override
    public String getKeyWord() {
        return keyword;
    }

    @Override
    public void execute(final Player player) {
        for (final Command command : commands) {
            command.execute(player);
        }
    }

    @Override
    public String getDesc() {
        String str = "";
        final StringBuilder s = new StringBuilder(str);
        for (final Command command : commands) {
            if (commands.indexOf(command) == commands.size() - 1) {
                s.append(command.getDesc());
            } else {
                s.append(command.getDesc());
                s.append(" then\n");
            }
        }
        return s.toString();
    }

}
