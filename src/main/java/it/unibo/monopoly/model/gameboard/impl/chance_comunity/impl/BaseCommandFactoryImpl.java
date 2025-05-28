package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ArgsInterpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public class BaseCommandFactoryImpl implements BaseCommandFactory {

    /**
     * command that moves a player by some steps.
     * @param steps
     * @param player 
     * @param board to move the player pawn
     * @return the command 
     */
    BaseCommand move(Board board){
        return new BaseCommand() {

            private String keyword;
            private int num;

            @Override
            public String getKeyWord() {
                return this.keyword;
            }

            @Override
            public void addIntArg(int arg) {
                this.num = arg;
            }

            @Override
            public void addTileArg(String tile) {
            }

            @Override
            public void execute(Player player) {
                board.movePawn(board.getPawn(player.getID()), Set.of(num));
            }

            @Override
            public void addPlayersArg(List<Player> players) {
            }

        };
    }

    /**
     * a command that moves a player in to a specific tile.
     * @param tile
     * @param player
     * @param board to move the player pawn
     * @return the command
     */
    BaseCommand moveIn(Board board){
        return new BaseCommand() {

            private String keyword;
            private String tile;

            @Override
            public void execute(Player player) {
                board.movePawnInTile(board.getPawn(player.getID()), this.tile);
            }

            @Override
            public String getKeyWord() {
                return this.keyword;
            }

            @Override
            public void addIntArg(int arg) {
            }

            @Override
            public void addTileArg(String tile) {
                this.tile = tile;
            }

            @Override
            public void addPlayersArg(List<Player> players) {
            }
            
        };
    }

    /**
     * a command that withdraw an ammount of money from a player bankaccount.
     * @param ammount
     * @param player
     * @param bank to make the transaction
     * @return the command
     */
    BaseCommand withdraw(Bank bank){
        return new BaseCommand() {

            private String keyword;
            private int num;

            @Override
            public void execute(Player player) {
                bank.withdrawFrom(player.getName(), num);
            }

            @Override
            public String getKeyWord() {
                return this.keyword;
            }

            @Override
            public void addIntArg(int arg) {
                this.num = arg;
            }

            @Override
            public void addTileArg(String tile) {
            }

            @Override
            public void addPlayersArg(List<Player> players) {
            }

        };
    }

    /**
     * a command that deposit an ammount of money in a player bankaccount.
     * @param ammount
     * @param player
     * @param bank to make the transaction
     * @return the command
     */
    BaseCommand deposit(Bank bank){
        return new BaseCommand() {

            private String keyword;
            private int num;

            @Override
            public void execute(Player player) {
                bank.depositTo(player.getName(), num);
            }

            @Override
            public String getKeyWord() {
                return this.keyword;
            }

            @Override
            public void addIntArg(int arg) {
                this.num = arg;
            }

            @Override
            public void addTileArg(String tile) {
            }
            
            @Override
            public void addPlayersArg(List<Player> players) {
            }
        };
    }

    /**
     * each of the players gives an ammount to another player.
     * @param bank to make the transaction
     * @param players
     * @return
     */
    BaseCommand depositFrom(Bank bank){
        return new BaseCommand() {

            private String keyword;
            private int num;
            private List<Player> players;

            @Override
            public void execute(Player player) {
                for (Player player2 : players) {
                    bank.withdrawFrom(player2.getName(), num);
                    bank.depositTo(player.getName(), num);
                }
            }

            @Override
            public String getKeyWord() {
                return this.keyword;
            }

            @Override
            public void addIntArg(int arg) {
                this.num = arg;
            }

            @Override
            public void addTileArg(String tile) {
            }            
            
            @Override
            public void addPlayersArg(List<Player> players) {
                this.players = List.copyOf(players);
            }
        };
    }
    @Override
    public List<BaseCommand> allCommand(Bank bank, Board board) {
        return List.of(
            this.deposit(bank),
            this.move(board),
            this.moveIn(board),
            this.withdraw(bank), 
            depositFrom(bank)
        );
    }

}
