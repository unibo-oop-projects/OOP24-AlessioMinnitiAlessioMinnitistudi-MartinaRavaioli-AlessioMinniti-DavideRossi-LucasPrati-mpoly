package it.unibo.monopoly.utils;

import java.awt.Color;

/**
 * Represents the initial setup data for a player.
 * Used as a Data Transfer Object (DTO) between the View and the Model.
 *
 * @param name  the name chosen by the player
 * @param color the color assigned to the player
 */
public record PlayerSetup(String name, Color color) { } 
