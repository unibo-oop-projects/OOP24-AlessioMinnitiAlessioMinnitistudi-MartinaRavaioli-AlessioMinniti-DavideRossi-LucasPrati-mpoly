package it.unibo.monopoly.model.gameboard.api;

import java.util.List;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.impl.CardDTO;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * A factory for create the {@link Tile}s' specializations like {@link Special} and {@link Property}.
 * <p>
 * Also usefull for create {@link TitleDeed}s from the {@link Property}.
 */
public interface CardFactory {
    
    /**
     * Parses a card DTO and creates corresponding domain objects.
     * @param dtos the card list of DTOs from JSON
     * @throws IllegalArgumentException if the parsing does not match anything expected or failed
     */
    void parse(final List<CardDTO> dtos);

    /**
     * Get all the {@link Tile}s parsed.
     * @return the {@link List} of {@link Tile}s pased from the file Json
     */
    List<Tile> getTiles();

    /**
     * Get all the {@link TitleDeed}s parsed.
     * @return the {@link List} of {@link TitleDeed}s pased from the file Json
     */
    Set<TitleDeed> getDeeds();
}
