package it.unibo.monopoly.model.gameboard.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

/**
 * Unified DTO for both SPECIAL and PROPERTY tiles.
 * Used only for deserialization from JSON.
 */
public final class CardDTO {

    private final String name;
    private final int position;
    private final String type;

    private final Optional<String> effect;
    private final Optional<String> group;
    private final Optional<Integer> cost;
    private final Optional<Integer> houseCost;
    private final Optional<Integer> mortgage;
    private final Optional<List<Integer>> rents;

    @JsonCreator
    public CardDTO(
        @JsonProperty("name") final String name,
        @JsonProperty("position") final int position,
        @JsonProperty("type") final String type,
        @JsonProperty("effect") final Optional<String> effect,
        @JsonProperty("group") final Optional<String> group,
        @JsonProperty("cost") final Optional<Integer> cost,
        @JsonProperty("houseCost") final Optional<Integer> houseCost,
        @JsonProperty("mortgage") final Optional<Integer> mortgage,
        @JsonProperty("rents") final Optional<List<Integer>> rents
    ) {
        this.name = name;
        this.position = position;
        this.type = type;
        this.effect = effect;
        this.group = group;
        this.cost = cost;
        this.houseCost = houseCost;
        this.mortgage = mortgage;
        this.rents = rents;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public Optional<String> getEffect() {
        return effect;
    }

    public Optional<String> getGroup() {
        return group;
    }

    public Optional<Integer> getCost() {
        return cost;
    }

    public Optional<Integer> getHouseCost() {
        return houseCost;
    }

    public Optional<Integer> getMortgage() {
        return mortgage;
    }

    public Optional<List<Integer>> getRents() {
        return rents;
    }
}
