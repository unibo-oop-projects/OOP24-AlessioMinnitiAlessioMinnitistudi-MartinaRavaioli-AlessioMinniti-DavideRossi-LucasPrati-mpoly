package it.unibo.monopoly.utils.impl;


import java.awt.Font;
import java.awt.GraphicsEnvironment;

import java.util.Arrays;


/**
 * Utility class for common operation with {@link Font}.
 */
public final class FontUtils {

    private static final int FONT_STYLE = Font.BOLD;

    private FontUtils() { /* Prevent instantiation */ }

    /**
     * Checks whether the given font name is available in the local graphics environment.
     * The comparison is case-insensitive.
     * @param fontName the name of the font to check
     * @return {@code true} if the font is available; {@code false} otherwise
     */
    public static boolean isValidFontName(final String fontName) {
        return  fontName != null
                && Arrays.stream(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
                            .anyMatch(name -> name.equalsIgnoreCase(fontName));
    }

    /**
     * Create a new {@link Font} according to the provided data.
     * @param name the name of the font
     * @param size the size of the font
     * @return a new {@link Font} according to the provided data
     * @throws IllegalArgumentException if the name of the font is not available
     */
    public static Font createFont(final String name, final int size) {
        if(!isValidFontName(name)) {
            throw new IllegalArgumentException("The given font name is not available in the local graphics environment");
        }
        return new Font(name, FONT_STYLE, size);
    }
}
