package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;

import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.Command;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * implementation of complex command.
 * xCommands that are composed by base commands and need to execute each of them
 */
public final class ComplexCommand implements Command {
    private final List<Pair<BaseCommand, String>> commands;
    private final String keyword;

    /**
     * constructor.
     * @param commands a list of the base commands supported by the game
     * @param desc
     */
    public ComplexCommand(final List<Pair<BaseCommand, String>> commands, final String desc) {
        this.commands = List.copyOf(commands);
        this.keyword = desc;
    }

    @Override
    public String getKeyWord() {
        return keyword;
    }

    @Override
    public void execute(final Player player, final String args) {
        for (final Pair<BaseCommand, String> command : commands) {
            command.getX().execute(player, command.gety());
        }
    }

    @Override
    public String getDesc() {
        final String str = "";
        final StringBuilder s = new StringBuilder(str);
        for (final Pair<BaseCommand, String> commandP : commands) {
            final Command command = commandP.getX();
            if (commands.indexOf(commandP) == commands.size() - 1) {
                s.append(command.getDesc());
            } else {
                s.append(command.getDesc()).append(" then\n");
            }
        }
        return s.toString();
    }

}
