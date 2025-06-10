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
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.gameboard.impl.CardDTO;
import it.unibo.monopoly.model.gameboard.impl.CardFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.PawnFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.BaseCommandFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ComplexCommand;
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
 * test for classes complex command and base command factory.
 */
class BaseAndComplexCommandFactoryTest {

    private static final String PLAYER1_NAME = "Alice";
    private static final String PLAYER2_NAME = "Marta";
    private static final String PLAYER3_NAME = "Roberto";
    private static final int VALID_ID1 = 1;
    private static final int VALID_ID2 = 2;
    private static final int VALID_ID3 = 3;
    private static final Color VALID_COLOR1 = Color.GREEN;
    private static final Color VALID_COLOR2 = Color.PINK;
    private static final Color VALID_COLOR3 = Color.BLACK;

    private final BaseCommandFactory bcf = new BaseCommandFactoryImpl();
    private List<BaseCommand> commands;

    private BankImpl bank;
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

        final TurnationManager turnM;
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

        commands = bcf.allCommand(bank, board);
    }

    @Test
    void test0() {
        final int ammount = 50;
        final int indice = 0;
        final BaseCommand c = commands.get(indice);
        c.addIntArg(ammount);
        c.execute(p1);
        final int finalAmmount = 1050;
        assertEquals("deposit " + ammount, c.getDesc());
        assertEquals(finalAmmount, bank.getBankAccount(p1.getID()).getBalance());
    }
    @Test
    void test1() {
        final int ammount = 2;
        final int indice = 1;
        final BaseCommand c = commands.get(indice);
        final int prevPos = board.getPawn(p1.getID()).getPosition().getPos();
        c.addIntArg(ammount);
        c.execute(p1);
        assertEquals("move of " + ammount + " steps", c.getDesc());
        assertEquals(ammount, board.getPawn(p1.getID()).getPosition().getPos() - prevPos);
    }

    @Test
    void test2() {
        final String s = "Jail / Just Visiting";
        final int indice = 2;
        final BaseCommand c = commands.get(indice);
        c.addTileArg(s);
        c.execute(p1);
        assertEquals("move in " + s, c.getDesc());
        assertEquals(board.getTile(s).getPosition().getPos(), 
                        board.getPawn(p1.getID()).getPosition().getPos());

    }
    @Test
    void test3() {
        final int ammount = 50;
        final int indice = 3;
        final BaseCommand c = commands.get(indice);
        c.addIntArg(ammount);
        c.execute(p1);
        final int finalAmmount = 950;
        assertEquals("withdraw " + ammount, c.getDesc());
        assertEquals(finalAmmount, bank.getBankAccount(p1.getID()).getBalance());
    }

    @Test
    void test4() {
        final int ammount = 50;
        final int indice = 4;
        final BaseCommand c = commands.get(indice);
        c.addPlayersArg(List.of(p2, p3));
        c.addIntArg(ammount);
        c.execute(p1);
        final int finalAmmount1 = 1100;
        final int finalAmmount2 = 950;
        assertEquals("deposit " + ammount + " from all players", c.getDesc());
        assertEquals(finalAmmount1, bank.getBankAccount(p1.getID()).getBalance());
        assertEquals(finalAmmount2, bank.getBankAccount(p2.getID()).getBalance());
        assertEquals(finalAmmount2, bank.getBankAccount(p3.getID()).getBalance());
    }

    @Test
    void test5() {
        final String s1 = "Baltic Avenue";
        final String s2 = "Mediterranean Avenue";
        bank.getBankStateObject().resetTransactionData();
        final int indice = 5;
        final BaseCommand c = commands.get(indice);
        c.addTileArg(s1);
        c.execute(p1);
        assertEquals("buy " + s1 + " if not owned otherwise pay it's rent", c.getDesc());
        assertEquals(p1.getID(), bank.getTitleDeed(s1).getOwnerId());
        bank.getBankStateObject().resetTransactionData();
        bank.buyTitleDeed(s2, p1.getID());
        c.addTileArg(s2);
        c.execute(p2);
        assertEquals(p1.getID(), bank.getTitleDeed(s2).getOwnerId());
    }

    @Test
    void complex1() {
        final String s1 = "Baltic Avenue";
        final String s2 = "Mediterranean Avenue";
        final int indice1 = 5;
        bank.getBankStateObject().resetTransactionData();
        final BaseCommand c1 = commands.get(indice1);
        c1.addTileArg(s1);
        final int indice2 = 2;
        final BaseCommand c2 = commands.get(indice2);
        c2.addTileArg(s2);
        final List<Command> li = List.of(c1, c2);
        final Command c = new ComplexCommand(li, s2);
        c.execute(p1);
        assertEquals("buy " + s1 + " if not owned" + " then\n" + "move in " + s2, c.getDesc());
        assertEquals(p1.getID(), bank.getTitleDeed(s1).getOwnerId());
        assertEquals(board.getTile(s2).getPosition().getPos(), board.getPawn(p1.getID()).getPosition().getPos());
    }

}
