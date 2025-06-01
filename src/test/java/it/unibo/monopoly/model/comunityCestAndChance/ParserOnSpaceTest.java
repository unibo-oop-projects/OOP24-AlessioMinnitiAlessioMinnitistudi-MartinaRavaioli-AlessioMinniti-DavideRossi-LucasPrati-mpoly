package it.unibo.monopoly.model.comunityCestAndChance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnHyphen;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnNewLine;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnSpaces;
import it.unibo.monopoly.utils.api.UseFileTxt;
import it.unibo.monopoly.utils.impl.UseFileTxtImpl;

public class ParserOnSpaceTest {

        private final UseFileTxt f = new UseFileTxtImpl();
        private final String fi = f.loadTextResource("cards//command.txt");

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

}
