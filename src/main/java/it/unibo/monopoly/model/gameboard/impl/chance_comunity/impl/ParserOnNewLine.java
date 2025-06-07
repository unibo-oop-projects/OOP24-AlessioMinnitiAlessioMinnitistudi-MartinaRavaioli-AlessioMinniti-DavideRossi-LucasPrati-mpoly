package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;

/**
 * implementation of a parser on new lilne, returns every new line.
 */
public final class ParserOnNewLine implements Parser {

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
        final int newLine = 10;
        for (int i = index; i < toParseString.length(); i++) {
            final char c = toParseString.charAt(i);
            if (c == newLine) {
                index = i + 1;
                return ret;
            }
            if (i == toParseString.length() -1) {
                index = i + 2;
            }
            ret = ret.concat(String.valueOf(c));
        }
        return ret;

    }

    @Override
    public boolean hasNesxt() {
        return index < toParseString.length();
    }

}
