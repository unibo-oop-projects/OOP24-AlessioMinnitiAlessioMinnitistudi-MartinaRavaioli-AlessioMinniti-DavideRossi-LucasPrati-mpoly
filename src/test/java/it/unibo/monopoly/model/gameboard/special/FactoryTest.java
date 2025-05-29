package it.unibo.monopoly.model.gameboard.special;


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

    private final Set<BankAccount> accounts = Set.of(
        new SimpleBankAccountImpl(VALID_ID1)
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
        final List<Tile> tiles = List.of(
        new PropertyImpl("a", pos0, Group.RED),
        new PropertyImpl("b", pos1, Group.BLUE),
        new PropertyImpl("c", pos2, Group.YELLOW),
        factory.parking(pos5),
        factory.prison(pos4),
        factory.start(bank),
        factory.taxes(pos6, bank)
    );
        board = new BoardImpl(tiles, pawns);
        board.addTile(factory.goToPrison(pos3, board));

    }

    @Test
    void testGoToPrison() {
        final Special s = (Special) board.getTile("GoToJail");
        final Collection<Integer> dice1 = List.of(1, 2);
        final Collection<Integer> dice2 = List.of(1, 1);

        board.movePawn(board.getPawn(p1.getID()), dice1);
        assertEquals(pos3.getPos(), board.getPawn(p1.getID()).getPosition().getPos());

        s.activateEffect(p1);
        assertEquals(pos4.getPos(), board.getPawn(p1.getID()).getPosition().getPos());
        assertTrue(p1.isInPrison());
        assertFalse(p1.canExitPrison(dice1));
        assertTrue(p1.isInPrison());
        assertTrue(p1.canExitPrison(dice2));
        assertFalse(p1.isInPrison());
    }

    @Test
    void testStart() {
        final Special s = factory.start(bank);
        final int expectedBalance = 1200;
        s.activateEffect(p1);
        assertEquals(expectedBalance, bank.getBankAccount(p1.getID()).getBalance());

    }

    @Test
    void testTaxes() {
        final int expectedBalance = 900;
        final Special s = factory.taxes(pos1, bank);
        s.activateEffect(p1);
        assertEquals(expectedBalance, bank.getBankAccount(p1.getID()).getBalance());

    }

    @Test
    void testPark() {
        final Special s = factory.parking(pos2);
        s.activateEffect(p1);
        assertTrue(p1.isParked());
        assertFalse(p1.isParked());
    }


}
