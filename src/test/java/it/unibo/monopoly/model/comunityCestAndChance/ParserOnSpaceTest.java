package it.unibo.monopoly.model.comunityCestAndChance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnHyphen;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ParserOnSpaces;

public class ParserOnSpaceTest {

    
    private static FileReader file ;



    @Test
    void parserOnHyphenTest() {
        try {
            file = new FileReader("command.txt");
            
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        final ParserOnHyphen p = new ParserOnHyphen(file);
        if (p.hasNesxt()) {   
            assertEquals("ciao \r\n" + //
                        "io sono \r\n" + //
                        "world", p.next());   
        }
    }

    @Test
    void parserTest2() {
    }

    @Test
    void parserTest3() {
    }
}
