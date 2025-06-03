package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import java.util.List;

import it.unibo.monopoly.model.turnation.api.Player;

/**
 * interface for base command that need arguments to be added
 */
public interface BaseCommand extends Command {

    /**
     * add the argumet for a command that requires an int.
     * @param arg
     */
    void addIntArg(int arg);

    /**
     * add the argumet for a command that requires a tile.
     * @param tile
     */
    void addTileArg(String tile);

    /**
     * add the argumet for a command that requires a list of players.
     * @param players
     */
    void addPlayersArg(List<Player> players);
}
