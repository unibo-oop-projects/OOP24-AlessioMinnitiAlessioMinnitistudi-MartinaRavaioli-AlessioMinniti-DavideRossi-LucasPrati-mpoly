package it.unibo.monopoly.model.turnation;

import java.awt.Color;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.turnation.api.Dice;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.DiceImpl;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.TurnationManagerImpl;

public class TurnationManagerTest {
    private TurnationManager turnManager;
    private Dice dice;
    private List<Player> players = List.of(PlayerImpl.of(1, "a", Color.RED),PlayerImpl.of(2, "b", Color.GREEN),PlayerImpl.of(3, "c", Color.BLUE));
    
    @BeforeEach
    void setUp() {
        dice = new DiceImpl(2);
        turnManager = new TurnationManagerImpl(players, dice);
    }
    
    @Test
    void testInitialCurrentPlayer() {
        assertEquals(players.get(0), turnManager.getCurrPlayer(), "Initial player should be the first one");
    }

    @Test
    void testGetNextPlayerCycles() {
        assertEquals(players.get(1), turnManager.getNextPlayer(), "Next should be p2");
        assertEquals(players.get(2), turnManager.getNextPlayer(), "Next should be p3");
        assertEquals(players.get(0), turnManager.getNextPlayer(), "Cycle back to p1");
    }

    @Test
    void testGetIdCurrPlayer() {
        assertEquals(1, turnManager.getIdCurrPlayer(), "Initial player ID should be 1");
        turnManager.getNextPlayer();
        assertEquals(2, turnManager.getIdCurrPlayer(), "Next player ID should be 2");
    }

    @Test
    void testGetPlayerListIsUnmodifiable() {
        List<Player> playersTest = turnManager.getPlayerList();
        assertEquals(players.size(), playersTest.size());
        final UnsupportedOperationException unmodifiableListError = assertThrows (
            UnsupportedOperationException.class,
            () -> playersTest.add(PlayerImpl.of(4, null, null)));
        testExceptionFormat(unmodifiableListError);
    }

    @Test
    void testAddPlayer() {
        Player p4 = PlayerImpl.of(4, "d", Color.YELLOW);
        turnManager.addPlayer(p4);
        assertTrue(turnManager.getPlayerList().contains(p4), "New player should be in the list");
    }

    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }
}
