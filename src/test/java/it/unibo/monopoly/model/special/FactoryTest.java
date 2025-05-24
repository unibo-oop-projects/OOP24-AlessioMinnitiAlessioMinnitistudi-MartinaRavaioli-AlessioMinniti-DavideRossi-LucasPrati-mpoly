package it.unibo.monopoly.model.special;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.Color;
import java.util.Collection;
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
import it.unibo.monopoly.model.turnation.impl.ParkablePlayer;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.model.turnation.impl.PrisonablePlayer;





/**
 * Tests to verify correct functionality of.
 * class SpecialFactoryImpl
 */
class FactoryTest {

    private static final String PLAYER1_NAME = "Alice";
    private static final String TITLE_DEED_NAME1 = "Bastoni Gran Sasso";
    private static final String TITLE_DEED_NAME2 = "Viale Monterosa";
    private static final int VALID_ID1 = 1;
    private static final Color VALID_COLOR1 = Color.GREEN;



    private SpecialFactory factory = new SpecialFactoryImpl();
    private PawnFactory pF = new PawnFactoryImpl();
    private Position pos0 = new PositionImpl(0);
    private Position pos1 = new PositionImpl(1);
    private Position pos2 = new PositionImpl(2);
    private Position pos3 = new PositionImpl(3);
    private Position pos4 = new PositionImpl(4);
    private Position pos5 = new PositionImpl(5);
    private Position pos6 = new PositionImpl(6);

    private Player p1 = new PrisonablePlayer(new ParkablePlayer(PlayerImpl.of(VALID_ID1, PLAYER1_NAME, VALID_COLOR1)));

    private Set<BankAccount> accounts = Set.of(
        new SimpleBankAccountImpl(VALID_ID1, PLAYER1_NAME)
    );
    private Set<TitleDeed> deeds = Set.of(
        new BaseTitleDeed(Group.PURPLE, TITLE_DEED_NAME1, 50, s -> s / 2, 10),
        new BaseTitleDeed(Group.PURPLE, TITLE_DEED_NAME2, 60, s -> s / 2, 10)

    );
    private List<Pawn> pawns = List.of(
        pF.createAdvanced(VALID_ID1, pos1, VALID_COLOR1, PLAYER1_NAME)
    );
    
    private Bank bank = new BankImpl(accounts, deeds);
    private Board board;


    private List<Tile> tiles;


    @BeforeEach
    void setAll(){
        tiles  = List.of(    
        new PropertyImpl("a", pos0, Group.RED),
        new PropertyImpl("b", pos1, Group.BLUE),
        new PropertyImpl("c", pos2, Group.YELLOW),
        factory.goToPrison(pos3, null),
        factory.parking(pos5),
        factory.prison(pos4),
        factory.start(bank),
        factory.taxes(pos6, bank)
    );
        board = new BoardImpl(tiles, pawns);
    }

    @Test
    void testGoToPrison(){        
        final Special s = factory.goToPrison(pos3, board);        
        final Collection<Integer> dice1 = List.of(1,2);
        final Collection<Integer> dice2 = List.of(1,1);
        s.activateEffect(p1);
        //assertEquals(pos5, board.getPawn(p1.getID()).getPosition());
        assertTrue(p1.isInPrison());
        assertFalse(p1.canExitPrison(dice1, board, p1));
        assertTrue(p1.isInPrison());
        assertTrue(p1.canExitPrison(dice2, board, p1));
        assertFalse(p1.isInPrison());
        //assertEquals(pos6, board.getPawn(p1.getID()).getPosition());
    }

    @Test
    void testStart(){
        final Special s = factory.start(bank);
        s.activateEffect(p1);
        assertEquals(1200, bank.getBankAccount(p1.getName()).getBalance());

    }

    @Test
    void testTaxes(){
        final Special s = factory.taxes(pos1, bank);
        s.activateEffect(p1);
        assertEquals(900, bank.getBankAccount(p1.getName()).getBalance());

    }

    @Test
    void testPark(){
        final Special s = factory.parking(pos2);
        s.activateEffect(p1);
        assertTrue(p1.isParked());
        assertFalse(p1.isParked());
    }


}
