package it.unibo.monopoly.model.comunity_cest_and_chance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnColon;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnHyphen;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnNewLine;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnComma;
import it.unibo.monopoly.utils.api.UseFileTxt;
import it.unibo.monopoly.utils.impl.UseFileTxtImpl;

/**
 * test for parsers.
 */
class ParserOnSpaceTest {

        private final UseFileTxt f = new UseFileTxtImpl();
        private final String fi = f.loadTextResource("cards//commandTest.txt");

    @Test
    void parserOnHyphenTest() {
        final Parser p = new ParserOnHyphen(fi);
        assertTrue(p.hasNesxt()); 
        assertEquals("ciao\n"
                    + "io sono\n"
                    + "world\n", p.next());
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "come stai");
    }

    @Test 
    void parserOnNewLineTest() {
        final Parser p = new ParserOnNewLine(fi);
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "ciao");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "io sono");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "world");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "-");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "come stai");
    }

    @Test
    void parseOnColonTest() {
        final String colon = "ciao: io sono: world: -: come stai";
        final Parser p = new ParserOnColon(colon);
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "ciao");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "io sono");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "world");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "-");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "come stai");
    }

    @Test
    void parseOnCommaTest() {
        final String commas = "ciao, io, sono, world, -, come, stai";
        final Parser p = new ParserOnComma(commas);
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "ciao");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "io");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "sono");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "world");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "-");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "come");
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "stai");
        final String commas2 = "50";
        final ParserOnComma pars = new ParserOnComma(commas2);   
        assertTrue(pars.hasNesxt());
        assertEquals(pars.next(), "50");
        assertFalse(pars.hasNesxt());
    }
}
