package it.unibo.monopoly.model.gameboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.impl.BuildablePropertyImpl;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.gameboard.impl.NormalPropertyImpl;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

class PropertyTest {
    private NormalPropertyImpl normal;
    private BuildablePropertyImpl buildable;
    private final Position position = new PositionImpl(0);

    @BeforeEach
    void setUp() {
        normal = new NormalPropertyImpl("Test Property", position, Group.RED);
        buildable = new BuildablePropertyImpl(normal);
    }

    @Test
    void testIsBuildableShouldBeFalse() {
        assertFalse(normal.isBuildable());
    }

    @Test
    void testGetNHousesShouldBeZero() {
        assertEquals(0, normal.getNHouses());
    }

    @Test
    void testHasHotelShouldBeFalse() {
        assertFalse(normal.hasHotel());
    }

    @Test
    void testUnsupportedOperations() {
        assertThrows(UnsupportedOperationException.class, () -> normal.buildHouse());
        assertThrows(UnsupportedOperationException.class, () -> normal.buildHotel());
        assertThrows(UnsupportedOperationException.class, () -> normal.canBuildHouse());
        assertThrows(UnsupportedOperationException.class, () -> normal.canBuildHotel());
        assertThrows(UnsupportedOperationException.class, () -> normal.deleteHouse());
        assertThrows(UnsupportedOperationException.class, () -> normal.deleteHotel());
        assertThrows(UnsupportedOperationException.class, () -> normal.canDeleteHouse());
    }

    @Test
    void testInitialBuildableValues() {
        assertEquals(0, buildable.getNHouses());
        assertFalse(buildable.hasHotel());
        assertTrue(buildable.isBuildable());
        assertTrue(buildable.canBuildHouse());
        assertFalse(buildable.canBuildHotel());
    }

    @Test
    void testBuildHouseUpToMax() {
        for (int i = 1; i <= 4; i++) {
            assertTrue(buildable.canBuildHouse());
            buildable.buildHouse();
            assertEquals(i, buildable.getNHouses());
        }
        assertFalse(buildable.canBuildHouse());
        assertThrows(IllegalArgumentException.class, () -> buildable.buildHouse());
    }

    @Test
    void testBuildHotel() {
        buildable.setNHouses(4);
        assertTrue(buildable.canBuildHotel());
        buildable.buildHotel();
        assertTrue(buildable.hasHotel());
        assertFalse(buildable.canBuildHotel());
        assertThrows(IllegalArgumentException.class, () -> buildable.buildHotel());
    }
}
