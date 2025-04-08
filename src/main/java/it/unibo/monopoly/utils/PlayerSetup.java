package it.unibo.monopoly.utils;

import java.awt.Color;

/**
 * Player initial setup record.
 * @param name that the player chose for himself
 * @param color the color chosen for the player
 */
public record PlayerSetup(String name, Color color) { } 
