package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;

/**
 * implementation of a parser on new lilne, returns every new line.
 */
public final class ParserOnNewLine implements Parser {

    private final int newLine = 10;
    private final String toParseString;
    private int index;

    /**
     * constructor.
     * @param str the string to parse on
     */
    public ParserOnNewLine(final String str) {
        this.toParseString = str; 
    }

    @Override
    public String next() {
        String ret = ""; 
        for (int i = index; i < toParseString.length(); i++) {
            index = i + 1;
            if (toParseString.charAt(i) == newLine) {
                return ret;
            }
            ret = ret + toParseString.charAt(i);
        }
        return ret;

    }

    @Override
    public boolean hasNesxt() {
        return index < toParseString.length();
    }

}
