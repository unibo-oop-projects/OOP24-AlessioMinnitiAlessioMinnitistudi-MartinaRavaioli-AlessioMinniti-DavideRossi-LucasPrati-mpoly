package it.unibo.monopoly.model.comunityCestAndChance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnSpaces;

public class ParserOnSpaceTest {

    private static final String test1 = "hello";
    private static final String test2 = "hello\n world";
    private static final String test3 = "hello\n fucking\n world"; 


    @Test
    void parserTest1() {
        final ParserOnSpaces p = new ParserOnSpaces(test1);
        assertEquals("hello", p.next());
    }

    @Test
    void parserTest2() {
        final ParserOnSpaces p = new ParserOnSpaces(test2);
        assertEquals("hello", p.next());
        assertEquals("world", p.next());
    }

    @Test
    void parserTest3() {
        final ParserOnSpaces p = new ParserOnSpaces(test3);
        assertEquals("hello", p.next());
        assertEquals("fucking", p.next());
        assertEquals("world", p.next());
    }
}
