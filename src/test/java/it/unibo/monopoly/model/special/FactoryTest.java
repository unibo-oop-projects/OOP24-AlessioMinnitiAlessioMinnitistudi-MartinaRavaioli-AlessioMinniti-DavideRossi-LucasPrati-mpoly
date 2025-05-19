package it.unibo.monopoly.model.special;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.Color;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.gameboard.impl.SpecialFactoryImpl;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;



/*
 * Tests to verify correct functionality of
 * class Bank.
 */
public class FactoryTest {
    
    private static final int VALID_ID = 1;
    private static final String VALID_NAME = "Alice";
    private static final Color VALID_COLOR = Color.GREEN;
    private Player player;
    private Special special;
    private final SpecialFactory factory = new SpecialFactoryImpl(null, null, null);
    private final Position pos = new PositionImpl(13);

    @BeforeEach
    void create(){
        player = PlayerImpl.of(VALID_ID, VALID_NAME, VALID_COLOR);        
    }

    @Test
    void testPrison(){
        special = factory.prison(pos);
    }

}
