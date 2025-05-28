package it.unibo.monopoly.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.utils.impl.FileChecker;


class FileCheckerTest {

    @Test
    void checkNullPath() {
        assertFalse(FileChecker.checkPath(null));
    }

    @Test
    void checkNonExistentPath() {
        assertFalse(FileChecker.checkPath("nonexistent"));
    }

    @Test
    void checkExistingDebugRules() {
        assertTrue(FileChecker.checkPath("debug/rules/debug_rules.txt"));
    }

    @Test
    void checkExistingDebugCards() {
        assertTrue(FileChecker.checkPath("debug/cards/debug_cards.json"));
    }

}
