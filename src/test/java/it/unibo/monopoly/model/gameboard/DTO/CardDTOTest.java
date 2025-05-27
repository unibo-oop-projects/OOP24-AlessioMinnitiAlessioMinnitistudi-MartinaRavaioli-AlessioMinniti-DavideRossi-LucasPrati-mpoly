package it.unibo.monopoly.model.gameboard.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import it.unibo.monopoly.model.gameboard.impl.CardDTO;
import it.unibo.monopoly.model.gameboard.impl.Group;

class CardDTOTest {

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
    }

    @Test
    void testDeserializeFullPropertyCard() throws Exception {
        final int expectedPos = 39;
        final int expectedCost = 400;
        final int expectedBaseRent = 50;
        final String json = """
            {
              "name": "Boardwalk",
              "position": 39, 
              "type": "PROPERTY",
              "group": "BLUE",
              "cost": 400,
              "baseRent": 50
            }
            """;

        final CardDTO dto = mapper.readValue(json, CardDTO.class);

        assertEquals("Boardwalk", dto.getName().orElseThrow(), "Name should be present in PROPERTY card");
        assertEquals(expectedPos, dto.getPosition().getPos());
        assertEquals("PROPERTY", dto.getType());

        assertTrue(dto.getGroup().isPresent());
        assertEquals(Group.BLUE, dto.getGroup().get());

        assertTrue(dto.getCost().isPresent());
        assertEquals(expectedCost, dto.getCost().get());

        assertTrue(dto.getBaseRent().isPresent());
        assertEquals(expectedBaseRent, dto.getBaseRent().get());

        assertFalse(dto.getEffect().isPresent(), "Effect should be empty for PROPERTY type");
    }

    @Test
    void testDeserializeMinimalSpecialCard() throws Exception {
        final int expectedPos = 30;
        final String json = """
            {
              "name": "Go to Jail",
              "position": 30,
              "type": "SPECIAL",
              "effect": "GO_TO_JAIL"
            }
            """;

        final CardDTO dto = mapper.readValue(json, CardDTO.class);

        // In SPECIAL cards, name may be ignored or missing
        assertTrue(dto.getName().isPresent(), "Name can still be present for SPECIAL cards");
        assertEquals("Go to Jail", dto.getName().get());

        assertEquals(expectedPos, dto.getPosition().getPos());
        assertEquals("SPECIAL", dto.getType());

        assertTrue(dto.getEffect().isPresent());
        assertEquals("GO_TO_JAIL", dto.getEffect().get());

        assertFalse(dto.getGroup().isPresent());
        assertFalse(dto.getCost().isPresent());
        assertFalse(dto.getBaseRent().isPresent());
    }

    @Test
    void testDeserializeEmptyOptionals() throws Exception {
        final int expectedPos = 0;
        final String json = """
            {
              "position": 0,
              "type": "SPECIAL"
            }
            """;

        final CardDTO dto = mapper.readValue(json, CardDTO.class);

        assertTrue(dto.getName().isEmpty(), "Name should be empty if not provided");
        assertEquals(expectedPos, dto.getPosition().getPos());
        assertEquals("SPECIAL", dto.getType());

        assertFalse(dto.getEffect().isPresent());
        assertFalse(dto.getGroup().isPresent());
        assertFalse(dto.getCost().isPresent());
        assertFalse(dto.getBaseRent().isPresent());
    }
}
