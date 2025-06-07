package it.unibo.monopoly.model.comunityCestAndChance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.PawnFactory;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.gameboard.impl.PawnFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.PropertyImpl;
import it.unibo.monopoly.model.gameboard.impl.SpecialFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ArgsInterpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseInterpreterInt;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Interpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ArgsInterpreterImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.BaseCommandFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.BaseInterpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ComplexInterpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnColon;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;
import it.unibo.monopoly.model.turnation.api.Dice;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.DiceImpl;
import it.unibo.monopoly.model.turnation.impl.ParkablePlayer;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.model.turnation.impl.PrisonablePlayer;
import it.unibo.monopoly.model.turnation.impl.TurnationManagerImpl;

/**
 * test for classes Base interpreter and complex interpreter.
 */
public final class BaseAndComplexInterpreterTest {

    private static final String PLAYER1_NAME = "Alice";
    private static final String PLAYER2_NAME = "Marta";
    private static final String PLAYER3_NAME = "Roberto";
    private static final String TITLE_DEED_NAME1 = "Bastoni Gran Sasso";
    private static final String TITLE_DEED_NAME2 = "Viale Monterosa";
    private static final int VALID_ID1 = 1;
    private static final int VALID_ID2 = 2;
    private static final int VALID_ID3 = 3;
    private static final Color VALID_COLOR1 = Color.GREEN;
    private static final Color VALID_COLOR2 = Color.PINK;
    private static final Color VALID_COLOR3 = Color.BLACK;
    private static final Predicate<BankAccount> VALID_PREDICATE = e -> true;

    private static final int VALID_SALE_PRICE1 = 60;
    private static final int VALID_SALE_PRICE2 = 50;
    private static final int VALID_BASE_RENT = 10;

    private static final int PO0 = 0;
    private static final int PO1 = 1;
    private static final int PO2 = 2;
    private static final int PO3 = 3;
    private static final int PO4 = 4;
    private static final int PO5 = 5;
    private static final int PO6 = 6;

    private Bank bank;
    private Board board;
    private TurnationManager turnM; 
    private final BaseCommandFactory bcf = new BaseCommandFactoryImpl();
    private List<BaseCommand> commands;
    private BaseInterpreterInt baseInt;
    private Interpreter complexInt;
    private ArgsInterpreter argsInt;

    private final SpecialFactory factory = new SpecialFactoryImpl();
    private final PawnFactory pF = new PawnFactoryImpl();
    private final Position pos0 = new PositionImpl(PO0);
    private final Position pos1 = new PositionImpl(PO1);
    private final Position pos2 = new PositionImpl(PO2);
    private final Position pos3 = new PositionImpl(PO3);
    private final Position pos4 = new PositionImpl(PO4);
    private final Position pos5 = new PositionImpl(PO5);
    private final Position pos6 = new PositionImpl(PO6);

    private final Player p = new ParkablePlayer(PlayerImpl.of(VALID_ID1, PLAYER1_NAME, VALID_COLOR1));
    private final Player p1 = new PrisonablePlayer(p);
    private final Player p2 = PlayerImpl.of(VALID_ID2, PLAYER2_NAME, VALID_COLOR2);
    private final Player p3 = PlayerImpl.of(VALID_ID3, PLAYER3_NAME, VALID_COLOR3);


    private final Set<BankAccount> accounts = Set.of(
        new SimpleBankAccountImpl(VALID_ID1, VALID_PREDICATE), 
        new SimpleBankAccountImpl(VALID_ID2, VALID_PREDICATE), 
        new SimpleBankAccountImpl(VALID_ID3, VALID_PREDICATE)
    );
    private final Set<TitleDeed> deeds = Set.of(
        new BaseTitleDeed(Group.PURPLE, TITLE_DEED_NAME1, VALID_SALE_PRICE1, s -> s / 2, VALID_BASE_RENT),
        new BaseTitleDeed(Group.PURPLE, TITLE_DEED_NAME2, VALID_SALE_PRICE2, s -> s / 2, VALID_BASE_RENT)

    );
    private final List<Pawn> pawns = List.of(
        pF.createBasic(VALID_ID1, pos0, VALID_COLOR1)
    );

    @BeforeEach
    void setAll() {
        bank = new BankImpl(accounts, deeds);
        final Dice d = new DiceImpl(1);
        turnM = new TurnationManagerImpl(List.of(p1, p2, p3), d);
        final List<Tile> tiles = List.of(
        new PropertyImpl(TITLE_DEED_NAME1, pos0, Group.RED),
        new PropertyImpl(TITLE_DEED_NAME2, pos1, Group.BLUE),
        new PropertyImpl("c", pos2, Group.YELLOW),
        factory.parking(pos5, turnM),
        factory.prison(pos4),
        factory.taxes(pos6, bank)
    );
        board = new BoardImpl(tiles, pawns);
        board.addTile(factory.goToPrison(pos3, board, turnM));
        commands = bcf.allCommand(bank, board);
        complexInt = new ComplexInterpreter(board, bank);
        baseInt = new BaseInterpreter(commands);
        argsInt = new ArgsInterpreterImpl();
    }

    @Test
    void test0() {
        final Parser parOnColon = new ParserOnColon("deposit: 50");
        final int ammount = 50;
        parOnColon.hasNesxt();
        final BaseCommand c = baseInt.interpret(parOnColon.next(), board, turnM);
        parOnColon.hasNesxt();
        argsInt.interpret(parOnColon.next(), c, board, turnM);
        assertEquals("deposit " + ammount, c.getDesc());
    }

    @Test
    void test1() {
        final int num = 3;
        final Parser parOnColon = new ParserOnColon("move of steps: " + num);
        parOnColon.hasNesxt();
        final BaseCommand c = baseInt.interpret(parOnColon.next(), board, turnM);
        parOnColon.hasNesxt();
        argsInt.interpret(parOnColon.next(), c, board, turnM);
        assertEquals("move of " + num + " steps", c.getDesc());
    }

    @Test
    void test2() {
        final String s = "Jail / Just Visiting";
        final Parser parOnColon = new ParserOnColon("move in tile: " + s);
        parOnColon.hasNesxt();
        final BaseCommand c = baseInt.interpret(parOnColon.next(), board, turnM);
        parOnColon.hasNesxt();
        argsInt.interpret(parOnColon.next(), c, board, turnM);
        assertEquals("move in " + s, c.getDesc());

    }

    @Test
    void test3() {
        final int ammount = 50;
        final Parser parOnColon = new ParserOnColon("withdraw: " + ammount);
        parOnColon.hasNesxt();
        final BaseCommand c = baseInt.interpret(parOnColon.next(), board, turnM);
        parOnColon.hasNesxt();
        argsInt.interpret(parOnColon.next(), c, board, turnM);
        assertEquals("withdraw " + ammount, c.getDesc());
    }

    @Test
    void test4() {
        final int ammount = 50;
        final Parser parOnColon = new ParserOnColon("deposit from: all, " + ammount);
        parOnColon.hasNesxt();
        final BaseCommand c = baseInt.interpret(parOnColon.next(), board, turnM);
        parOnColon.hasNesxt();
        argsInt.interpret(parOnColon.next(), c, board, turnM);
        assertEquals("deposit " + ammount + " from all players", c.getDesc());
    }

    @Test
    void test5() {
        final String s1 = TITLE_DEED_NAME1;
        final Parser parOnColon = new ParserOnColon("buy if not owned: " + s1);
        parOnColon.hasNesxt();
        final BaseCommand c = baseInt.interpret(parOnColon.next(), board, turnM);
        parOnColon.hasNesxt();
        argsInt.interpret(parOnColon.next(), c, board, turnM);
        assertEquals("buy " + s1 + " if not owned", c.getDesc());
    }

    @Test
    void complex1() {
        final String s1 = TITLE_DEED_NAME1;
        final String s2 = TITLE_DEED_NAME2;
        final String s = "buy if not owned: " + s1 + "\n" + "move in tile: " + s2;
        final Command c = complexInt.interpret(s, board, turnM);
        assertEquals("buy " + s1 + " if not owned" + " then\n" + "move in " + s2, c.getDesc());
    }
}
