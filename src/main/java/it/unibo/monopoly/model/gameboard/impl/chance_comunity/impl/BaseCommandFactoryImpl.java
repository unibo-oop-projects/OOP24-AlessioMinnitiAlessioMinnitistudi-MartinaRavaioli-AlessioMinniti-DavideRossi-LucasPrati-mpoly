package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;

public class BaseCommandFactoryImpl implements BaseCommandFactory {

    private BaseCommand move(Board board){
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

    private BaseCommand moveIn(Board board){
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

    private BaseCommand withdraw(Bank bank){
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

    private BaseCommand deposit(Bank bank){
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

    private BaseCommand depositFrom(Bank bank){
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
    
    private BaseCommand buyIfNotOwned(Bank bank, Board board){
        return new BaseCommand() {
            private String keyword;
            private String tile;

            @Override
            public void execute(Player player) {
                TitleDeed t = bank.getTitleDeed(tile);
                if (!t.isOwned()) {
                    bank.buyTitleDeed(tile, player.getName());
                }                
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

    public BaseCommand still(){
        return new BaseCommand() {

            @Override
            public void execute(Player player) {
            }

            @Override
            public String getKeyWord() {
                return "";
            }

            @Override
            public void addIntArg(int arg) {
            }

            @Override
            public void addTileArg(String tile) {
            }

            @Override
            public void addPlayersArg(List<Player> players) {
            }

        };
    }

    public List<BaseCommand> allCommand(Bank bank, Board board) {
        return List.of(
            this.deposit(bank),
            this.move(board),
            this.moveIn(board),
            this.withdraw(bank), 
            this.depositFrom(bank),
            this.buyIfNotOwned(bank, board)
        );
    }

}
