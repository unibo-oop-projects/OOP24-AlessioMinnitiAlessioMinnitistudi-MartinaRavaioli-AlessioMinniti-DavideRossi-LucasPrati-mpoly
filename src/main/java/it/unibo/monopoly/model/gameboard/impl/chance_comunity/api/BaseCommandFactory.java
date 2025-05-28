package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.BaseCommand;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.Player;

public interface BaseCommandFactory {

    /**
     * command that moves a player by some steps.
     * @param steps
     * @param player 
     * @param board to move the player pawn
     * @return the command 
     */
    BaseCommand move(int steps, Player player, Board board);

    /**
     * a command that moves a player in to a specific tile.
     * @param tile
     * @param player
     * @param board to move the player pawn
     * @return the command
     */
    BaseCommand moveIn(Tile tile, Player player, Board board);

    /**
     * a command that withdraw an ammount of money from a player bankaccount.
     * @param ammount
     * @param player
     * @param bank to make the transaction
     * @return the command
     */
    BaseCommand withdraw(int ammount, Player player, Bank bank);

    /**
     * a command that deposit an ammount of money in a player bankaccount.
     * @param ammount
     * @param player
     * @param bank to make the transaction
     * @return the command
     */
    BaseCommand deposit(int ammount, Player player, Bank bank);


}
