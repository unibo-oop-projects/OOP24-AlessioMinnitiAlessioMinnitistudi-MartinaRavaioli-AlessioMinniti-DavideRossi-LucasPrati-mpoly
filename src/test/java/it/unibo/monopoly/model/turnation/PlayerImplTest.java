package it.unibo.monopoly.model.turnation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;

class PlayerImplTest {

    private static final String VALID_NAME = "Alice";
    private static final Color VALID_COLOR = Color.GREEN;
    private Player player;

    @BeforeEach
    void setUp() {
        player = PlayerImpl.of(VALID_NAME, VALID_COLOR);
    }

    @Test
    void createPlayerWithValidParameters() {
        assertNotNull(player);
        assertEquals(VALID_NAME, player.getName());
        assertEquals(VALID_COLOR, player.getColor());
    }

    @Test
    void createPlayerWithNullNameThrowsException() {
        final IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> PlayerImpl.of(null, VALID_COLOR),
            "Creating a player with a null name should have thrown an error");
        testExceptionFormat(exception);
    }

    @Test
    void createPlayerWithEmptyNameThrowsException() {
        final IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> PlayerImpl.of("   ", VALID_COLOR),
            "Creating a player with an empty name should have thrown an error");
        testExceptionFormat(exception);
    }

    @Test
    void createPlayerWithNullColorThrowsException() {
        final IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> PlayerImpl.of(VALID_NAME, null),
            "Creating a player with a null color should have thrown an error");
        testExceptionFormat(exception);
    }

    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }
}
