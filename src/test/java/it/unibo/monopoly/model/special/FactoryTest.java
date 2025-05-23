package it.unibo.monopoly.model.special;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.awt.Color;
import java.util.List;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.PawnFactory;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.gameboard.impl.PawnFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.PropertyImpl;
import it.unibo.monopoly.model.gameboard.impl.SpecialFactoryImpl;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;





/**
 * Tests to verify correct functionality of.
 * class SpecialFactoryImpl
 */
public class FactoryTest {

    private static final int AMOUNT = 100;
    private static final String PLAYER1_NAME = "Alice";
    private static final String PLAYER2_NAME = "Bob";
    private static final String TITLE_DEED_NAME1 = "Bastoni Gran Sasso";
    private static final String TITLE_DEED_NAME2 = "Viale Monterosa";
    private static final int VALID_ID1 = 1;
    private static final Color VALID_COLOR1 = Color.GREEN;
    private static final int VALID_ID2 = 2;
    private static final Color VALID_COLOR2 = Color.RED;

    private final Player p1 = PlayerImpl.of(VALID_ID1, PLAYER1_NAME, VALID_COLOR1);


    private final SpecialFactory factory = new SpecialFactoryImpl();
    private final PawnFactory pF = new PawnFactoryImpl();
    private final Position pos0 = new PositionImpl(0);
    private final Position pos1 = new PositionImpl(1);
    private final Position pos2 = new PositionImpl(2);
    private final Position pos3 = new PositionImpl(3);
    private final Position pos4 = new PositionImpl(4);
    private final Position pos5 = new PositionImpl(5);
    private final Position pos6 = new PositionImpl(6);

    private final Set<BankAccount> accounts = Set.of(
        new SimpleBankAccountImpl(AMOUNT, PLAYER1_NAME),
        new SimpleBankAccountImpl(AMOUNT, PLAYER2_NAME)
    );
    private final Set<TitleDeed> deeds = Set.of(
        new BaseTitleDeed(Group.PURPLE, TITLE_DEED_NAME1, 50, s -> s / 2, 10),
        new BaseTitleDeed(Group.PURPLE, TITLE_DEED_NAME2, 60, s -> s / 2, 10)

    );
    private final List<Pawn> pawns = List.of(
        pF.createAdvanced(VALID_ID1, pos1, VALID_COLOR1, PLAYER1_NAME),
        pF.createAdvanced(VALID_ID2, pos2, VALID_COLOR2, PLAYER2_NAME)
    );
    
    private Bank bank = new BankImpl(accounts, deeds);
    private Board board;


    private final List<Tile> tiles = List.of(    
        new PropertyImpl("a", pos0, Group.RED),
        new PropertyImpl("b", pos1, Group.BLUE),
        new PropertyImpl("c", pos2, Group.YELLOW),
        factory.goToPrison(pos3, board),
        factory.parking(pos4),
        factory.prison(pos5),
        factory.start(bank),
        factory.taxes(pos6, bank)
    );


    @BeforeAll
    void setAll(){
        board = new BoardImpl(tiles, pawns);
    }

    @Test
    void testGoToPrison(){

    }

    @Test
    void testStart(){
        final Special s = factory.start(bank);
        s.activateEffect(p1);
        assertEquals(300, bank.getBankAccount(p1.getName()));

    }

    @Test
    void testTaxes(){
        final Special s = factory.taxes(pos1, bank);
        s.activateEffect(p1);
        assertEquals(0, bank.getBankAccount(p1.getName()));

    }

    @Test
    void testPark(){
        final Special s = factory.parking(pos2);
        s.activateEffect(p1);
        assertTrue(p1.isParked());
        assertFalse(p1.isParked());
    }


}
