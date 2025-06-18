package it.unibo.monopoly.model.gameboard.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

import java.util.Optional;

/**
 * Unified DTO for both SPECIAL and PROPERTY tiles.
 * Used only for deserialization from JSON.
 */
public final class CardDTO {

    private final String name;
    private final int position;
    private final String type;

    private final String effect;
    private final Group group;
    private final Integer cost;
    private final Integer baseRent;

    /**
     * Create a Data Transfert Object for deserialize the file JSON.
     * @param name the card name
     * @param position the position of the card in the board
     * @param type define if the card is a {@link Property} or a {@link Special}
     * @param effect define the effect of a {@link Special} card
     * @param group the card's {@link Group} 
     * @param cost the price of the card
     * @param baseRent an initial amount of rent
     */
    @JsonCreator
    public CardDTO(
        @JsonProperty("name") final Optional<String> name,
        @JsonProperty("position") final int position,
        @JsonProperty("type") final String type,
        @JsonProperty("effect") final Optional<String> effect,
        @JsonProperty("group") final Optional<Group> group,
        @JsonProperty("cost") final Optional<Integer> cost,
        @JsonProperty("baseRent") final Optional<Integer> baseRent
    ) {
        this.name = name.orElse(null);
        this.position = position;
        this.type = type;
        this.effect = effect.orElse(null);
        this.group = group.orElse(null);
        this.cost = cost.orElse(null);
        this.baseRent = baseRent.orElse(null);
    }

    /**
     * Get the name of the card.
     * @return the an {@link Optional} <{@link String}> with represents the name of the card
     */
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    /**
     * Get the position of the card.
     * @return the {@link Position} of the card
     */
    public Position getPosition() {
        return new PositionImpl(position);
    }

    /**
     * Get the type of the card.
     * @return a {@link Sting} which represents the type of the card
     */
    public String getType() {
        return type;
    }

    /**
     * Get a {@link String} that represents the card's effect.
     * @return an {@link Optional} <{@link String}> with the card's effect
     */
    public Optional<String> getEffect() {
        return Optional.ofNullable(effect);
    }

    /**
     * Get the {@link Group} of the card.
     * @return an {@link Optional} value of the card's {@link Group}
     */
    public Optional<Group> getGroup() {
        return Optional.ofNullable(group);
    }

    /**
     * Get the price of the card.
     * @return an {@link Optional} <{@link Integer}> with represents the card's price
     */
    public Optional<Integer> getCost() {
        return Optional.ofNullable(cost);
    }

    /**
     * Get the initial amount of the card's rent.
     * @return an {@link Optional} <{@link Integer}> which represents the card's initial amount of rent
     */
    public Optional<Integer> getBaseRent() {
        return Optional.ofNullable(baseRent);
    }
}
