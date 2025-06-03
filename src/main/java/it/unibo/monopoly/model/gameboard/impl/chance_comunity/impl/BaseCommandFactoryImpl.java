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

            private String desc;
            private int num;
            private String keyword = "move of steps";


            @Override
            public String getKeyWord() {
                return this.keyword;
            }

            @Override
            public String getDesc() {
                desc = "move of " + num + " steps";
                return this.desc;
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

            private String desc;
            private String tile;
            private String keyword = "move in tile";

            @Override
            public String getKeyWord() {
                return this.keyword;
            }

            @Override
            public void execute(Player player) {
                board.movePawnInTile(board.getPawn(player.getID()), this.tile);
            }

            @Override
            public String getDesc() {
                return this.desc;
            }

            @Override
            public void addIntArg(int arg) {
            }

            @Override
            public void addTileArg(String tile) {
                if (tile != null) {
                    this.tile = tile;
                    desc = "move in " + tile;
                }
            }

            @Override
            public void addPlayersArg(List<Player> players) {
            }
            
        };
    }

    private BaseCommand withdraw(Bank bank){
        return new BaseCommand() {

            private String desc;
            private int num;
            private String keyword = "withdraw";

            @Override
            public String getKeyWord() {
                return this.keyword;
            }

            @Override
            public void execute(Player player) {
                bank.withdrawFrom(player.getID(), num);
            }

            @Override
            public String getDesc() {
                return this.desc;
            }

            @Override
            public void addIntArg(int arg) {
                if (arg != -1) {
                    this.num = arg;
                    desc = "withdraw " + num;
                }
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

            private String desc;
            private int num;
            private String keyword = "deposit";

            @Override
            public String getKeyWord() {
                return this.keyword;
            }

            @Override
            public void execute(Player player) {
                bank.depositTo(player.getID(), num);
            }

            @Override
            public String getDesc() {
                desc = "deposit " + num;
                return this.desc;
            }

            @Override
            public void addIntArg(int arg) {
                if (arg != -1) {
                    this.num = arg;
                }
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

            private String desc;
            private int num;
            private List<Player> players;
            private String keyword = "deposit from";

            @Override
            public String getKeyWord() {
                return this.keyword;
            }

            @Override
            public void execute(Player player) {
                for (Player player2 : players) {
                    bank.withdrawFrom(player2.getID(), num);
                    bank.depositTo(player.getID(), num);
                }
            }

            @Override
            public String getDesc() {
                return this.desc;
            }

            @Override
            public void addIntArg(int arg) {
                if (arg != -1) {
                    this.num = arg;
                    desc = "deposit " + num + " from all players";
                }
                
            }

            @Override
            public void addTileArg(String tile) {
            }            
            
            @Override
            public void addPlayersArg(List<Player> players) {
                if (players != null) {
                    this.players = List.copyOf(players);
                }
            }
        };
    }
    
    private BaseCommand buyIfNotOwned(Bank bank, Board board){
        return new BaseCommand() {
            private String desc;
            private String tile;
            private String keyword = "buy if not owned";

            @Override
            public String getKeyWord() {
                return this.keyword;
            }

            @Override
            public void execute(Player player) {
                TitleDeed t = bank.getTitleDeed(tile);
                if (!t.isOwned()) {
                    bank.buyTitleDeed(tile, player.getID());
                }                
            }

            @Override
            public String getDesc() {
                return this.desc;
            }

            @Override
            public void addIntArg(int arg) {
            }

            @Override
            public void addTileArg(String tile) {
                if (tile != null) {
                    this.tile = tile;
                    desc = "buy " + tile + " if not owned";
                }
            }

            @Override
            public void addPlayersArg(List<Player> players) {
            }
            
        };
    }

    public BaseCommand still(){
        return new BaseCommand() {
            private String keyword = "still";

            @Override
            public String getKeyWord() {
                return this.keyword;
            }
            @Override
            public void execute(Player player) {
            }

            @Override
            public String getDesc() {
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
