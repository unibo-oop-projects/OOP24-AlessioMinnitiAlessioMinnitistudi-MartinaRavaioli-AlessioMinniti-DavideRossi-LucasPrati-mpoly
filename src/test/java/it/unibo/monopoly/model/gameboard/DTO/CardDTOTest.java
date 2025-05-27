package it.unibo.monopoly.model.gameboard.DTO;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import it.unibo.monopoly.model.gameboard.impl.CardDTO;
import it.unibo.monopoly.model.gameboard.impl.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardDTOTest {

    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
    }

    @Test
    void testDeserializeFullPropertyCard() throws Exception {
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

        assertEquals("Boardwalk",dto.getName().orElseThrow(), "Name should be present in PROPERTY card");
        assertEquals(39, dto.getPosition().getPos());
        assertEquals("PROPERTY", dto.getType());

        assertTrue(dto.getGroup().isPresent());
        assertEquals(Group.BLUE, dto.getGroup().get());

        assertTrue(dto.getCost().isPresent());
        assertEquals(400, dto.getCost().get());

        assertTrue(dto.getBaseRent().isPresent());
        assertEquals(50, dto.getBaseRent().get());

        assertFalse(dto.getEffect().isPresent(), "Effect should be empty for PROPERTY type");
    }

    @Test
    void testDeserializeMinimalSpecialCard() throws Exception {
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

        assertEquals(30, dto.getPosition().getPos());
        assertEquals("SPECIAL", dto.getType());

        assertTrue(dto.getEffect().isPresent());
        assertEquals("GO_TO_JAIL", dto.getEffect().get());

        assertFalse(dto.getGroup().isPresent());
        assertFalse(dto.getCost().isPresent());
        assertFalse(dto.getBaseRent().isPresent());
    }

    @Test
    void testDeserializeEmptyOptionals() throws Exception {
        final String json = """
            {
              "position": 0,
              "type": "SPECIAL"
            }
            """;

        final CardDTO dto = mapper.readValue(json, CardDTO.class);

        assertTrue(dto.getName().isEmpty(), "Name should be empty if not provided");
        assertEquals(0, dto.getPosition().getPos());
        assertEquals("SPECIAL", dto.getType());

        assertFalse(dto.getEffect().isPresent());
        assertFalse(dto.getGroup().isPresent());
        assertFalse(dto.getCost().isPresent());
        assertFalse(dto.getBaseRent().isPresent());
    }
}
