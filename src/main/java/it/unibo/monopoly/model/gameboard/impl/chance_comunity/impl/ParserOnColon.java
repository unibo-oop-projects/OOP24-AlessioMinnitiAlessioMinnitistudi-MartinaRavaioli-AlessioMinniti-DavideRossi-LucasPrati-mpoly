package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;

/**
 * implementation of parser on colon, return the srting before every colon.
 */
public final class ParserOnColon implements Parser {

    private final String toParseString;
    private int index;

    /**
     * constructor.
     * @param str the string to parse on
     */
    public ParserOnColon(final String str) {
        this.toParseString = str; 
    }

    @Override
    public String next() {
        final int colon = 58;
        String ret = ""; 
        for (int i = index; i < toParseString.length(); i++) {
            final char c = toParseString.charAt(i);
            if (c == colon) {
                index = i + 2;
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
        return index < toParseString.length() - 1;
    }

}
