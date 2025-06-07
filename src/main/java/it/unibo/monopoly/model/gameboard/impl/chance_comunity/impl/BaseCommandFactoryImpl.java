package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.view.impl.MainViewImpl;

/**
 * implementation of base command factory.
 */
public final class BaseCommandFactoryImpl implements BaseCommandFactory {

    private BaseCommand move(final Board board) {
        return new BaseCommand() {

            private static final String KEY = "move of steps";
            private int num;

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public String getDesc() {
                return "move of " + num + " steps";
            }

            @Override
            public void addIntArg(final int arg) {

                    this.num = arg;
            }

            @Override
            public void addTileArg(final String tile) {
            }

            @Override
            public void execute(final Player player) {
                board.movePawn(player.getID(), Set.of(num));
            }

            @Override
            public void addPlayersArg(final List<Player> players) {
            }

        };
    }

    private BaseCommand moveIn(final Board board) {
        return new BaseCommand() {

            private static final String KEY = "move in tile";
            private String tile;

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public void execute(final Player player) {
                board.movePawnInTile(player.getID(), this.tile);
            }

            @Override
            public String getDesc() {
                return "move in " + tile;
            }

            @Override
            public void addIntArg(final int arg) {
            }

            @Override
            public void addTileArg(final String tile) {
                if (tile != null) {
                    this.tile = tile;
                }
            }

            @Override
            public void addPlayersArg(final List<Player> players) {
            }
        };
    }

    private BaseCommand withdraw(final Bank bank) {
        return new BaseCommand() {

            private static final String KEY = "withdraw";
            private int num;

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public void execute(final Player player) {
                bank.withdrawFrom(player.getID(), num);
            }

            @Override
            public String getDesc() {
                return "withdraw " + num;
            }

            @Override
            public void addIntArg(final int arg) {
                if (arg != -1) {
                    this.num = arg;
                }
            }

            @Override
            public void addTileArg(final String tile) {
            }

            @Override
            public void addPlayersArg(final List<Player> players) {
            }

        };
    }

    private BaseCommand deposit(final Bank bank) {
        return new BaseCommand() {

            private static final String KEY = "deposit";
            private int num;

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public void execute(final Player player) {
                bank.depositTo(player.getID(), num);
            }

            @Override
            public String getDesc() {
                return "deposit " + num;
            }

            @Override
            public void addIntArg(final int arg) {
                if (arg != -1) {
                    this.num = arg;
                }
            }

            @Override
            public void addTileArg(final String tile) {
            }

            @Override
            public void addPlayersArg(final List<Player> players) {
            }
        };
    }

    private BaseCommand depositFrom(final Bank bank) {
        return new BaseCommand() {

            private static final String KEY = "deposit from";
            private int num;
            private List<Player> players = new LinkedList<>();

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public void execute(final Player player) {
                for (final Player player2 : players) {
                    bank.withdrawFrom(player2.getID(), num);
                    bank.depositTo(player.getID(), num);
                }
            }

            @Override
            public String getDesc() {
                return "deposit " + num + " from all players";
            }

            @Override
            public void addIntArg(final int arg) {
                if (arg != -1) {
                    this.num = arg;
                }

            }

            @Override
            public void addTileArg(final String tile) {
            }

            @Override
            public void addPlayersArg(final List<Player> players) {
                if (players != null) {
                    this.players = List.copyOf(players);
                }
            }
        };
    }

    private BaseCommand buyIfNotOwned(final Bank bank, final Board board, final MainViewImpl view) {
        return new BaseCommand() {

            private static final String KEY = "buy if not owned";
            private String tile;

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public void execute(final Player player) {
                final Tile t = board.getTile(tile);
                if (t instanceof Property && !bank.getTitleDeed(tile).isOwned()) {
                    bank.buyTitleDeed(tile, player.getID());
                    view.callBuyProperty((Property) t);
                }
            }

            @Override
            public String getDesc() {
                return "buy " + tile + " if not owned";
            }

            @Override
            public void addIntArg(final int arg) {
            }

            @Override
            public void addTileArg(final String tile) {
                if (tile != null) {
                    this.tile = tile;
                }
            }

            @Override
            public void addPlayersArg(final List<Player> players) {
            }

        };
    }

    @Override
    public BaseCommand still() {
        return new BaseCommand() {
            private static final String KEY = "still";

            @Override
            public String getKeyWord() {
                return KEY;
            }
            @Override
            public void execute(final Player player) {
            }

            @Override
            public String getDesc() {
                return "";
            }

            @Override
            public void addIntArg(final int arg) {
            }

            @Override
            public void addTileArg(final String tile) {
            }

            @Override
            public void addPlayersArg(final List<Player> players) {
            }

        };
    }

    @Override
    public List<BaseCommand> allCommand(final Bank bank, final Board board, final MainViewImpl view) {
        return List.of(
            this.deposit(bank),
            this.move(board),
            this.moveIn(board),
            this.withdraw(bank), 
            this.depositFrom(bank),
            this.buyIfNotOwned(bank, board, view)
        );
    }

}
