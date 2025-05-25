package it.unibo.monopoly.utils;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.utils.api.UseConfigurationFile;
import it.unibo.monopoly.utils.impl.Configuration;
import it.unibo.monopoly.utils.impl.UseConfigurationFileImpl;


class UseConfigurationFileImplTest {

    private static final String PATH_CONFIGURATION = "debug/configuration/debug_config.yml";
    private final UseConfigurationFile configLoader = new UseConfigurationFileImpl();

    @Test
    void loadConfigurationFile_nullPath_throwsNullPointerException() {
        final NullPointerException exception = assertThrows(
            NullPointerException.class,
            () -> configLoader.loadConfigurationFile(null)
        );
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }

    @Test
    void loadConfigurationFile_nonexistentPath_returnsDefaultConfig() {
        Configuration config = configLoader.loadConfigurationFile("nonexistent");
        assertTrue(Configuration.Builder.isDefault(config),
                   "The default configuration should be the default-one");
        assertTrue(config.isConsistent());
    }

    @Test
    void loadConfigurationFile_validFile_returnsParsedConfig() {
        Configuration config = configLoader.loadConfigurationFile(PATH_CONFIGURATION);
        assertFalse(Configuration.Builder.isDefault(config),
                   "The configuration should not be the default-one."
                   + "Maybe the file does not have a consistent configuration or is equals to the default-one");
    }
}