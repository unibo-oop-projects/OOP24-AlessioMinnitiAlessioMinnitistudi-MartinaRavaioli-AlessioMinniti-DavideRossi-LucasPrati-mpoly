package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.ArgsInterpreter;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.BaseCommandFactory;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.PropertyAction;
import it.unibo.monopoly.model.transactions.api.PropertyActionsEnum;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * implementation of base command factory.
 */
public final class BaseCommandFactoryImpl implements BaseCommandFactory {

    private final ArgsInterpreter argsInt = new ArgsInterpreterImpl(); 

    private BaseCommand move(final Board board, final TurnationManager turnM) {
        return new BaseCommand() {

            private static final String KEY = "move of steps";
            private int num;

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public String getDesc() {
                return "move of " + num + " steps then ignore the effect of the tile," 
                    + "\nyou won't have to pay rent but you can neither buy the property." 
                    + "\nif you pass the start point in doing so, the 200$ will be added";
            }

            @Override
            public void addIntArg(final int arg) {

                    this.num = arg;
            }

            @Override
            public void addTileArg(final String tile) {
            }

            @Override
            public void execute(final Player player, final String args) {
                final ParserOnComma p = new ParserOnComma(args);
                while (p.hasNesxt()) {
                    argsInt.interpret(p.next(), this, board, turnM);
                }

                final int delta = board.movePawn(player.getID(), Set.of(num));
                if (delta < 0) {
                    final Special start = (Special) board.getTile("Start");
                    start.activateEffect(player);
                }
            }

            @Override
            public void addPlayersArg(final List<Player> players) {
            }

        };
    }

    private BaseCommand moveIn(final Board board, final TurnationManager turnM) {
        return new BaseCommand() {

            private static final String KEY = "move in tile";
            private String tile;

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public void execute(final Player player, final String args) {
                final ParserOnComma p = new ParserOnComma(args);
                while (p.hasNesxt()) {
                    argsInt.interpret(p.next(), this, board, turnM);
                }

                int delta = board.getPawn(player.getID()).getPosition().getPos();
                board.movePawnInTile(player.getID(), this.tile);
                delta = delta - board.getPawn(player.getID()).getPosition().getPos();
                if (delta > 0) {
                    final Special start = (Special) board.getTile("Start");
                    start.activateEffect(player);
                }
            }

            @Override
            public String getDesc() {
                return "move in " + tile 
                    + ".\nif you pass the start point in doing so, the 200$ will be added";
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

    private BaseCommand withdraw(final Bank bank, final Board board, final TurnationManager turnM) {
        return new BaseCommand() {

            private static final String KEY = "withdraw";
            private int num;

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public void execute(final Player player, final String args) {
                final ParserOnComma p = new ParserOnComma(args);
                while (p.hasNesxt()) {
                    argsInt.interpret(p.next(), this, board, turnM);
                }
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

    private BaseCommand deposit(final Bank bank, final Board board, final TurnationManager turnM) {
        return new BaseCommand() {

            private static final String KEY = "deposit";
            private int num;

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public void execute(final Player player, final String args) {
                final ParserOnComma p = new ParserOnComma(args);
                while (p.hasNesxt()) {
                    argsInt.interpret(p.next(), this, board, turnM);
                }
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

    private BaseCommand depositFrom(final Bank bank, final Board board, final TurnationManager turnM) {
        return new BaseCommand() {

            private static final String KEY = "deposit from";
            private int num;
            private List<Player> players = new LinkedList<>();

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public void execute(final Player player, final String args) {
                final ParserOnComma p = new ParserOnComma(args);
                while (p.hasNesxt()) {
                    argsInt.interpret(p.next(), this, board, turnM);
                }
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

    private BaseCommand buyIfNotOwned(final Bank bank, final Board board, final TurnationManager turnM) {
        return new BaseCommand() {

            private static final String KEY = "buy if not owned";
            private String tile;

            @Override
            public String getKeyWord() {
                return KEY;
            }

            @Override
            public void execute(final Player player, final String args) {
                final ParserOnComma pc = new ParserOnComma(args);
                while (pc.hasNesxt()) {
                    argsInt.interpret(pc.next(), this, board, turnM);
                }
                final Tile t = board.getTile(tile);

                if (t instanceof Property) {
                    bank.getBankStateObject().resetTransactionData();
                    final Set<PropertyAction> actions = bank.getApplicableActionsForTitleDeed(player.getID(), tile, 10);
                    final Optional<PropertyAction> buy = actions.stream()
                                .filter(p -> PropertyActionsEnum.BUY.equals(p.getType())).findAny();
                    final Optional<PropertyAction> pay = actions.stream()
                                .filter(p -> PropertyActionsEnum.PAYRENT.equals(p.getType())).findAny();
                    if (buy.isPresent()) {
                        buy.get().executePropertyAction(board, bank);
                    } else if (pay.isPresent()) {
                        pay.get().executePropertyAction(board, bank);
                    }
                }
            }

            @Override
            public String getDesc() {
                return "buy " + tile + " if not owned otherwise pay it's rent";
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
            public void execute(final Player player, final String args) {
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
    public List<BaseCommand> allCommand(final Bank bank, final Board board, final TurnationManager turnM) {
        return List.of(
            this.deposit(bank, board, turnM),
            this.move(board, turnM),
            this.moveIn(board, turnM),
            this.withdraw(bank, board, turnM), 
            this.depositFrom(bank, board, turnM),
            this.buyIfNotOwned(bank, board, turnM)
        );
    }

}
