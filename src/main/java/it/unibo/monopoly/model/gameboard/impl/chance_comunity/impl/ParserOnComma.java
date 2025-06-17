package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.Parser;

/**
 * implementation of a parser on comma, return the string before every comma.
 */
public final class ParserOnComma implements Parser {

    private final String toParseString;
    private String returnString;
    private int index;

    /**
     * constructor.
     * @param str the string to parse on
     */
    public ParserOnComma(final String str) {
        this.toParseString = str; 
    }

    @Override
    public String next() {
        return returnString;

    }

    @Override
    public boolean hasNesxt() {
        if (index >= toParseString.length()) {
            return false;
        }
        final int comma = 44;
        String ret = ""; 
        for (int i = index; i < toParseString.length(); i++) {
            final char c = toParseString.charAt(i);
            if (c == comma) {
                index = i + 2;
                returnString = ret;
                return true;
            }
            ret = ret.concat(String.valueOf(c));
        }
        returnString = ret;
        index = toParseString.length();

        return true;
    }

}
