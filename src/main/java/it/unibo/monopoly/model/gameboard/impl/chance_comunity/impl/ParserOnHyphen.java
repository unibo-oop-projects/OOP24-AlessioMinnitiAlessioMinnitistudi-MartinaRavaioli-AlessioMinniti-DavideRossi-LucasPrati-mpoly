package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.io.FileReader;
import java.io.IOException;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;

public class ParserOnHyphen implements Parser {

    private final FileReader file;
    private String retString;

    public ParserOnHyphen(FileReader file){
        this.file = file;
    } 

    @Override
    public boolean hasNesxt() {
        try {
            if (this.file ) {
                // TODO how to check file is not null, loook up Buffered file reader as stated in professors slides
            }
            String rStr = "";
            int c = file.read();
            if (c != -1) {
                while (c != 45 && c != -1) {
                    rStr = rStr + c;
                }
                return true;
            }else{
                return false;
            }
            
        } catch (IOException e) {
            System.out.println("not able to find the carachter");
            return false;
        }
        
    }

    @Override
    public String next() {
        return retString;
    }

}
