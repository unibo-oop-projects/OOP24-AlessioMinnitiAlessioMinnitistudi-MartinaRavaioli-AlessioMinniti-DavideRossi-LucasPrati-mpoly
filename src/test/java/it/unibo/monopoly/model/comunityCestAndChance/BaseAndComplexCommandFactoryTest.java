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
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.BaseCommandFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ComplexCommand;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.ParkablePlayer;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.model.turnation.impl.PrisonablePlayer;

public class BaseAndComplexCommandFactoryTest {

    
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
    private final BaseCommandFactory bcf = new BaseCommandFactoryImpl();
    private List<BaseCommand> commands;

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
    private int indice = 0;

    
    @BeforeEach
    void setAll() {
        bank = new BankImpl(accounts, deeds);
        final List<Tile> tiles = List.of(
        new PropertyImpl(TITLE_DEED_NAME1, pos0, Group.RED),
        new PropertyImpl(TITLE_DEED_NAME2, pos1, Group.BLUE),
        new PropertyImpl("c", pos2, Group.YELLOW),
        factory.parking(pos5),
        factory.prison(pos4),
        factory.taxes(pos6, bank)
    );
        board = new BoardImpl(tiles, pawns);
        board.addTile(factory.goToPrison(pos3, board));
        commands = bcf.allCommand(bank, board);
    }

    @Test
    void Test0(){
        final int ammount = 50;
        BaseCommand c = commands.get(indice);
        indice += 1;
        c.addIntArg(ammount);
        c.execute(p1);
        final int finalAmmount = 1050;
        assertEquals("deposit " + ammount, c.getDesc());
        assertEquals(finalAmmount, bank.getBankAccount(p1.getID()).getBalance());
    }
    @Test
    void Test1(){
        final int ammount = 2;
        indice = 1 ;
        BaseCommand c = commands.get(indice);
        
        c.addIntArg(ammount);
        c.execute(p1);
        assertEquals("move of " + ammount + " steps", c.getDesc());
        assertEquals(pos2.getPos(), board.getPawn(p1.getID()).getPosition().getPos());
    }
    
    @Test
    void Test2(){
        final String s = "Jail / Just Visiting";
        indice = 2;
        BaseCommand c = commands.get(indice);
        c.addTileArg(s);
        c.execute(p1);
        assertEquals("move in " + s, c.getDesc());
        assertEquals(pos4.getPos(), board.getPawn(p1.getID()).getPosition().getPos());
        
    }
    @Test
    void Test3(){
        final int ammount = 50;
        indice = 3;
        BaseCommand c = commands.get(indice);
        c.addIntArg(ammount);
        c.execute(p1);
        final int finalAmmount = 950;
        assertEquals("withdraw " + ammount, c.getDesc());
        assertEquals(finalAmmount, bank.getBankAccount(p1.getID()).getBalance());
    }

    @Test
    void Test4(){
        final int ammount = 50;
        indice = 4;
        BaseCommand c = commands.get(indice);
        c.addPlayersArg(List.of(p2,p3));
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
    void Test5(){
        final String s1 = TITLE_DEED_NAME1;
        final String s2 = TITLE_DEED_NAME2;
        bank.getBankStateObject().resetTransactionData();
        indice = 5;
        BaseCommand c = commands.get(indice);
        c.addTileArg(s1);
        c.execute(p1);
        assertEquals("buy " + s1 + " if not owned", c.getDesc());
        assertEquals(p1.getID(), bank.getTitleDeed(s1).getOwnerId());
        bank.buyTitleDeed(s2, p2.getID());
        c.addTileArg(s2);
        c.execute(p1);
        assertEquals(p2.getID(), bank.getTitleDeed(s2).getOwnerId());
    }

    @Test
    void complex1(){
        final String s1 = TITLE_DEED_NAME1;
        final String s2 = TITLE_DEED_NAME2;
        indice = 5;
        bank.getBankStateObject().resetTransactionData();
        BaseCommand c1 = commands.get(indice);
        c1.addTileArg(s1);
        indice = 2;
        BaseCommand c2 = commands.get(indice);
        c2.addTileArg(s2);
        List<Command> li = List.of(c1, c2);
        Command c = new ComplexCommand(li, s2);
        c.execute(p1);
        assertEquals("buy " + s1 + " if not owned" + " then\n" + "move in " + s2, c.getDesc());
        assertEquals(p1.getID(), bank.getTitleDeed(s1).getOwnerId());
        assertEquals(pos1.getPos(), board.getPawn(p1.getID()).getPosition().getPos());
        
    }
    
}
