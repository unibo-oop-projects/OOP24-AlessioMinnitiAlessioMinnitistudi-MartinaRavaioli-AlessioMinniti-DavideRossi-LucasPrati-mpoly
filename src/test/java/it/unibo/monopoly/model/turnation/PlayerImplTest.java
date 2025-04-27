package it.unibo.monopoly.model.turnation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;

class PlayerImplTest {

    private static final int VALID_ID = 1;
    private static final String VALID_NAME = "Alice";
    private static final Color VALID_COLOR = Color.GREEN;
    private Player player;

    @BeforeEach
    void setUp() {
        player = PlayerImpl.of(VALID_ID, VALID_NAME, VALID_COLOR);
    }

    @Test
    void createPlayerWithValidParameters() {
        assertNotNull(player);
        assertEquals(VALID_ID, player.getID());
        assertEquals(VALID_NAME, player.getName());
        assertEquals(VALID_COLOR, player.getColor());
    }

    @Test
    void createPlayerWithNullNameThrowsException() {
        assertThrows(
            NullPointerException.class,
            () -> PlayerImpl.of(VALID_ID, null, VALID_COLOR),
            "Creating a player with a null name should throw NullPointerException"
        );
    }

    @Test
    void createPlayerWithBlankNameDefaultsToPlayerPlusId() {
        final Player p = PlayerImpl.of(VALID_ID, "   ", VALID_COLOR);
        assertEquals("Player " + VALID_ID, p.getName(),
            "Blank name must be replaced with \"Player <id>\"");
        assertEquals(VALID_ID, p.getID());
    }

    @Test
    void createPlayerWithNullColorThrowsException() {
        assertThrows(
            NullPointerException.class,
            () -> PlayerImpl.of(VALID_ID, VALID_NAME, null),
            "Creating a player with a null color should throw NullPointerException"
        );
    }
}
