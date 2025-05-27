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
    private final Position position;
    private final String type;

    private final Optional<String> effect;
    private final Optional<Group> group;
    private final Optional<Integer> cost;
    private final Optional<Integer> baseRent;

    @JsonCreator
    public CardDTO(
        @JsonProperty("name") final String name,
        @JsonProperty("position") final int position,
        @JsonProperty("type") final String type,
        @JsonProperty("effect") final Optional<String> effect,
        @JsonProperty("group") final Optional<Group> group,
        @JsonProperty("cost") final Optional<Integer> cost,
        @JsonProperty("baseRent") final Optional<Integer> baseRent
    ) {
        this.name = name;
        this.position = new PositionImpl(position);
        this.type = type;
        this.effect = effect;
        this.group = group;
        this.cost = cost;
        this.baseRent = baseRent;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public Optional<String> getEffect() {
        return effect;
    }

    public Optional<Group> getGroup() {
        return group;
    }

    public Optional<Integer> getCost() {
        return cost;
    }

    public Optional<Integer> getBaseRent() {
        return baseRent;
    }
}
