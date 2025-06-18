package it.unibo.monopoly.model.turnation;

import java.awt.Color;
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
    private final List<Player> players = List.of(
        new ParkablePlayer(PlayerImpl.of(1, "a", Color.RED)),
        new ParkablePlayer(PlayerImpl.of(2, "b", Color.GREEN)),
        new ParkablePlayer(PlayerImpl.of(3, "c", Color.BLUE))
    );

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
            turnManager.moveByDices();
            assertEquals(players.get(1).getID(), turnManager.getNextPlayer().getID(), "Next should be p2");
            turnManager.moveByDices();
            assertEquals(players.get(2).getID(), turnManager.getNextPlayer().getID(), "Next should be p3");
            turnManager.moveByDices();
            assertEquals(players.get(0).getID(), turnManager.getNextPlayer().getID(), "Cycle back to p1");
        } catch (final IllegalAccessException e) {
            LOGGER.error(ERROR, e);
        }
    }

    @Test
    void testGetIdCurrPlayer() {
        try {
            assertEquals(1, turnManager.getIdCurrPlayer(), "Initial player ID should be 1");
            turnManager.moveByDices();
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

    public List<Player> getPlayers() {
        return players;
    }

}
