package it.unibo.monopoly.model.gameboard.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import it.unibo.monopoly.model.gameboard.impl.CardDTO;

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
              "group": "DARK_BLUE",
              "cost": 400,
              "houseCost": 200,
              "mortgage": 200,
              "rents": [50, 200, 600, 1400, 1700, 2000]
            }
            """;

        final CardDTO dto = mapper.readValue(json, CardDTO.class);

        assertEquals("Boardwalk", dto.getName());
        assertEquals(39, dto.getPosition());
        assertEquals("PROPERTY", dto.getType());

        assertTrue(dto.getGroup().isPresent());
        assertEquals("DARK_BLUE", dto.getGroup().get());

        assertTrue(dto.getCost().isPresent());
        assertEquals(400, dto.getCost().get());

        assertTrue(dto.getRents().isPresent());
        assertEquals(List.of(50, 200, 600, 1400, 1700, 2000), dto.getRents().get());

        assertTrue(dto.getMortgage().isPresent());
        assertFalse(dto.getEffect().isPresent());
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

        assertEquals("Go to Jail", dto.getName());
        assertEquals(30, dto.getPosition());
        assertEquals("SPECIAL", dto.getType());

        assertTrue(dto.getEffect().isPresent());
        assertEquals("GO_TO_JAIL", dto.getEffect().get());

        assertFalse(dto.getGroup().isPresent());
        assertFalse(dto.getCost().isPresent());
        assertFalse(dto.getRents().isPresent());
    }
}
