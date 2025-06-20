package it.unibo.monopoly.model.turnation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;
import it.unibo.monopoly.model.transactions.impl.titledeed.BaseTitleDeed;
import it.unibo.monopoly.model.turnation.api.Dice;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.DiceImpl;
import it.unibo.monopoly.model.turnation.impl.ParkablePlayer;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PrisonablePlayer;
import it.unibo.monopoly.model.turnation.impl.TurnationManagerImpl;

class TurnationManagerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnationManagerTest.class);
    private static final String ERROR = "ERROR";
    private static final int AMOUNT = 1000;
    private static final int ID_1 = 21;
    private static final int ID_2 = 42;
    private static final String TITLE_DEED_NAME1 = "Bastoni Gran Sasso";
    private static final String TITLE_DEED_NAME2 = "Viale Monterosa";
    private static final int PROPERTY_SALE_PRICE = 50;
    private static final int BASE_RENT = 10;
    private static final int NDICES = 2;
    private TurnationManager turnManager;
    private final List<Player> players = new ArrayList<>(List.of(
        new ParkablePlayer(new PrisonablePlayer(PlayerImpl.of(1, "a", Color.RED))),
        new ParkablePlayer(new PrisonablePlayer(PlayerImpl.of(2, "b", Color.GREEN))),
        new ParkablePlayer(new PrisonablePlayer(PlayerImpl.of(3, "c", Color.BLUE)))
    ));

    private final Set<BankAccount> accounts = Set.of(
        new SimpleBankAccountImpl(ID_1, AMOUNT, e -> true),
        new SimpleBankAccountImpl(ID_2, AMOUNT, e -> true)
    );
    private final Set<TitleDeed> deeds = Set.of(
        new BaseTitleDeed(Group.GREEN, TITLE_DEED_NAME1, PROPERTY_SALE_PRICE, s -> s / 2, BASE_RENT),
        new BaseTitleDeed(Group.GREEN, TITLE_DEED_NAME2, PROPERTY_SALE_PRICE, s -> s / 2, BASE_RENT)
    );

    @BeforeEach
    void setUp() {
        final Bank bank = new BankImpl(accounts, deeds);
        final Dice dice = new DiceImpl(NDICES);
        turnManager = new TurnationManagerImpl(players, dice, bank.getBankStateObject());
    }

    @Test
    void testInitialCurrentPlayer() {
        assertEquals(players.get(0).getID(), turnManager.getCurrPlayer().getID());
    }

    @Test
    void testGetNextPlayerCycles() {
        try {
            turnManager.throwDices();
            assertEquals(players.get(1).getID(), turnManager.getNextPlayer().getID(), "Next should be p2");
            turnManager.throwDices();
            assertEquals(players.get(2).getID(), turnManager.getNextPlayer().getID(), "Next should be p3");
            turnManager.throwDices();
            assertEquals(players.get(0).getID(), turnManager.getNextPlayer().getID(), "Cycle back to p1");
        } catch (final IllegalAccessException e) {
            LOGGER.error(ERROR, e);
        }
    }

    @Test
    void testGetIdCurrPlayer() {
        try {
            assertEquals(1, turnManager.getIdCurrPlayer(), "Initial player ID should be 1");
            turnManager.throwDices();
            turnManager.getNextPlayer();
            assertEquals(2, turnManager.getIdCurrPlayer(), "Next player ID should be 2");
        } catch (final IllegalAccessException e) {
            LOGGER.error(ERROR, e);
        }
    }

    @Test
    void testGetPlayerListIsUnmodifiable() {
        final List<Player> playersTest = turnManager.getPlayerList();
        assertEquals(players.size(), playersTest.size());
        assertThrows(NullPointerException.class, () -> playersTest.add(PlayerImpl.of(4, null, null)));
    }

    @Test
    void testAddPlayer() {
        final Player p4 = PlayerImpl.of(4, "d", Color.YELLOW);
        turnManager.addPlayer(p4);
        assertTrue(turnManager.getPlayerList().contains(p4), "New player should be in the list");
    }

    @Test
    void testPlayerParkedCannotThrowDices() {
        try {
            players.get(0).park();
            final var result = turnManager.throwDices();
            assertEquals("the player can't throw dices because is parked", result.getRight());
        } catch (final IllegalAccessException e) {
            LOGGER.error(ERROR, e);
        }

    }

    @Test
    void testThrowDicesTwiceThrowsException() throws IllegalAccessException {
        turnManager.throwDices(); // primo lancio OK
        assertThrows(IllegalAccessException.class, turnManager :: throwDices);
    }

    @Test
    void testCanPassTurnWhenParked() {
        players.get(0).park();
        assertTrue(turnManager.canPassTurn(), "Player should be able to pass turn when parked");
    }

    @Test
    void testPlayerPutInPrisonAndExit() {
        try {
            players.get(0).putInPrison();
            assertTrue(turnManager.isCurrentPlayerInPrison(), "Player should be in prison");
            final var result = turnManager.throwDices();
            assertTrue(result.getRight().contains("you are still in prison") 
                        || result.getRight().contains("you escaped the prison"));
        } catch (final IllegalAccessException e) {
            LOGGER.error(ERROR, e);
        }
    }

    @Test
    void testMoveByDicesWhileInPrisonUpdatesTurns() {
        try {
            players.get(0).putInPrison();
            final int turnsLeft = turnManager.currentPlayerTurnsLeftInPrison();
            turnManager.throwDices();  // Should decrement turns
            assertEquals(turnsLeft - 1, 
                        turnManager.currentPlayerTurnsLeftInPrison(),
                        "Turns left in prison should decrease after failed attempt");
        } catch (final IllegalAccessException e) {
            LOGGER.error(ERROR, e);
        }
    }

    @Test
    void testNextPlayerAfterParkPassesProperly() {
        players.get(0).park();
        assertEquals(players.get(1).getID(), 
                    turnManager.getNextPlayer().getID(),
                    "Next should skip parked player after passing turn");
    }
}
