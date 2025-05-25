package it.unibo.monopoly.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.utils.api.UseFileTxt;
import it.unibo.monopoly.utils.impl.UseFileTxtImpl;

class UseFileTxtImplTest {
    
    private final String rulesPath = "debug/rules/debug_rules.txt";
    private final UseFileTxt txtLoader = new UseFileTxtImpl();

    @Test
    void loadTextResource_existingPath_returnsContent() {
        String content = txtLoader.loadTextResource(rulesPath);
        testMessageFormat(content);
    }

    @Test
    void loadTextResource_nonexistentPath_returnsErrorMessage() {
        String msg = txtLoader.loadTextResource("nonexistent");
        testMessageFormat(msg);
        assertTrue(msg.contains("Resource not found"),
                   "The message of error should show why the loading failed");
    }

    @Test
    void loadTextResource_nullPath_throwsNullPointerException() {
        final NullPointerException exception = assertThrows(
            NullPointerException.class,
            () -> txtLoader.loadTextResource(null)
        );
        testMessageFormat(exception.getMessage());
    }


    private void testMessageFormat(final String message) {
        assertNotNull(message);
        assertFalse(message.isBlank());
    }

}
