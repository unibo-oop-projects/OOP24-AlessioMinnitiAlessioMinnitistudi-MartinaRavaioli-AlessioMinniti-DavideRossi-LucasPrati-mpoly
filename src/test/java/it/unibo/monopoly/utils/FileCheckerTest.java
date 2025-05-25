package it.unibo.monopoly.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.utils.impl.FileChecker;


class FileCheckerTest {

    @Test
    void checkPath_nullPath_returnsFalse() {
        assertFalse(FileChecker.checkPath(null));
    }

    @Test
    void checkPath_nonexistentPath_returnsFalse() {
        assertFalse(FileChecker.checkPath("a"));
    }

    @Test
    void checkPath_existingDebugRules_returnsTrue() {
        assertTrue(FileChecker.checkPath("debug/rules/debug_rules.txt"));
    }

    @Test
    void checkPath_existingDebugTiles_returnsTrue() {
        assertTrue(FileChecker.checkPath("debug/cards/debug_tiles.json"));
    }
}