package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;

/**
 * implementation of parser on colon, return the srting before every colon.
 */
public final class ParserOnColon implements Parser {

    private final int colon = 58;
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
        String ret = ""; 
        for (int i = index; i < toParseString.length(); i++) {
            index = i + 2;
            char c = toParseString.charAt(i);
            if (c == colon) {
                return ret;
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
