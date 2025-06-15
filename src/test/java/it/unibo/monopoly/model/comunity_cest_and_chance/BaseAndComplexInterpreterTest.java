package it.unibo.monopoly.model.comunity_cest_and_chance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.CardFactory;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.PawnFactory;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.ArgsInterpreter;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.BaseInterpreterInt;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.Command;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.Interpreter;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.Parser;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.gameboard.impl.CardDTO;
import it.unibo.monopoly.model.gameboard.impl.CardFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.PawnFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ArgsInterpreterImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.BaseInterpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ComplexInterpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnColon;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.bankaccount.BankAccountFactoryImpl;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.DiceImpl;
import it.unibo.monopoly.model.turnation.impl.ParkablePlayer;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.model.turnation.impl.PrisonablePlayer;
import it.unibo.monopoly.model.turnation.impl.TurnationManagerImpl;
import it.unibo.monopoly.utils.api.UseFileJson;
import it.unibo.monopoly.utils.impl.Configuration;
import it.unibo.monopoly.utils.impl.UseFileJsonImpl;

/**
 * test for classes Base interpreter and complex interpreter.
 */
class BaseAndComplexInterpreterTest {

    private static final String PLAYER1_NAME = "Alice";
    private static final String PLAYER2_NAME = "Marta";
    private static final String PLAYER3_NAME = "Roberto";
    private static final int VALID_ID1 = 1;
    private static final int VALID_ID2 = 2;
    private static final int VALID_ID3 = 3;
    private static final Color VALID_COLOR1 = Color.GREEN;
    private static final Color VALID_COLOR2 = Color.PINK;
    private static final Color VALID_COLOR3 = Color.BLACK;

    private BaseInterpreterInt baseInt;
    private Interpreter complexInt;
    private ArgsInterpreter argsInt;

    private TurnationManager turnM;
    private Board board;

    private final Player p = new ParkablePlayer(PlayerImpl.of(VALID_ID1, PLAYER1_NAME, VALID_COLOR1));
    private final Player p1 = new PrisonablePlayer(p);
    private final Player p2 = PlayerImpl.of(VALID_ID2, PLAYER2_NAME, VALID_COLOR2);
    private final Player p3 = PlayerImpl.of(VALID_ID3, PLAYER3_NAME, VALID_COLOR3);

    @BeforeEach
    void setAll() {
        final String confFil = "configuration/config.yml";
        final Configuration config = Configuration.configureFromFile(confFil);
        final List<Player> players = new ArrayList<>();
        final List<Pawn> pawns = new ArrayList<>();
        final List<Tile> tiles = new ArrayList<>();
        final Set<TitleDeed> titleDeeds = new HashSet<>();
        final Set<BankAccount> accounts = new HashSet<>();

        final PawnFactory pawnFactory = new PawnFactoryImpl();
        final UseFileJson importFileJson = new UseFileJsonImpl();

        final BankImpl bank;
        // create a id for each Player (his Pawn and BankAccount must have the same id)
        int id = 1;
        // create a Player, his Pawn and his BankAccount according to the type chosen
        final int inBal = 1000;
        final BankAccountFactory bankAccountFactory = new BankAccountFactoryImpl(inBal);
        final Map<Color, String> playersSetup = Map.of(Color.BLUE, p1.getName(), 
                                                        Color.RED, p2.getName(), 
                                                        Color.GREEN, p3.getName());
        for (final var p : playersSetup.entrySet()) {
            final String name = p.getValue();
            final Color color = p.getKey();
            players.add(new ParkablePlayer(new PrisonablePlayer(PlayerImpl.of(id, name, color))));
            accounts.add(bankAccountFactory.createWithCheck(id,
                                                                  account -> account.getBalance() > 0));
            pawns.add(pawnFactory.createBasic(id, new PositionImpl(0), color));
            id++;
        }

        // creation of Bank, Board and TurnationManager
        board = new BoardImpl(List.of(), pawns);
        bank = new BankImpl(accounts, Set.of());
        turnM = new TurnationManagerImpl(
            players,
            new DiceImpl(
                config.getNumDice(),
                config.getSidesPerDie()
            ),
            bank.getBankStateObject()
        );

        // import from json
        final List<CardDTO> dtos = importFileJson.loadJsonList(config.getCardsPath(), CardDTO.class);
        final CardFactory cardFactory = new CardFactoryImpl(board, bank, turnM); 
        cardFactory.parse(dtos);
        // populate elements
        titleDeeds.addAll(cardFactory.getDeeds());
        tiles.addAll(cardFactory.getTiles());

        // Add tiles to the board and titleDeeds to the Bank
        tiles.stream().forEach(board::addTile);
        titleDeeds.stream().forEach(bank::addTitleDeed);

        complexInt = new ComplexInterpreter(board, bank, turnM);
        baseInt = new BaseInterpreter(board, bank, turnM);
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
        final String s1 = "Baltic Avenue";
        final Parser parOnColon = new ParserOnColon("buy if not owned: " + s1);
        parOnColon.hasNesxt();
        final BaseCommand c = baseInt.interpret(parOnColon.next(), board, turnM);
        parOnColon.hasNesxt();
        argsInt.interpret(parOnColon.next(), c, board, turnM);
        assertEquals("buy " + s1 + " if not owned otherwise pay it's rent", c.getDesc());
    }

    @Test
    void complex1() {
        final String s1 = "Baltic Avenue";
        final String s2 = "Mediterranean Avenue";
        final String s = "buy if not owned: " + s1 + "\n" + "move in tile: " + s2;
        final Command c = complexInt.interpret(s, board, turnM);
        c.execute(p, c.getKeyWord());
        assertEquals("buy " + s1 + " if not owned otherwise pay it's rent" + " then\n" + "move in " + s2, c.getDesc());
    }
}
