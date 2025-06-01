package it.unibo.monopoly.model.comunityCestAndChance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnColon;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnHyphen;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnNewLine;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnComma;
import it.unibo.monopoly.utils.api.UseFileTxt;
import it.unibo.monopoly.utils.impl.UseFileTxtImpl;

public class ParserOnSpaceTest {

        private final UseFileTxt f = new UseFileTxtImpl();
        private final String fi = f.loadTextResource("cards//commandTest.txt");
        private final static String COLON_TEST = "ciao: io sono: world: -: come stai";
        private final static String SPACES_TEST = "ciao, io, sono, world, -, come, stai";

    @Test
    void parserOnHyphenTest() {
        final Parser p = new ParserOnHyphen(fi);
        assertTrue(p.hasNesxt()); 
        assertEquals("ciao\n" + //
                    "io sono\n" + //
                    "world\n", p.next());
        assertTrue(p.hasNesxt());
        assertEquals(p.next(), "come stai");
    }

    @Test 
    void parserOnNewLineTest(){
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
    void parseOnColonTest(){
        final Parser p = new ParserOnColon(COLON_TEST);
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
    void parseOnCommaTest(){
        final Parser p = new ParserOnComma(SPACES_TEST);
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
    }
}
