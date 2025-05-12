package it.unibo.monopoly.model.gameboard.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.unibo.monopoly.model.gameboard.impl.PropertyImpl;
import it.unibo.monopoly.model.gameboard.impl.SpecialImpl;

/**
 * Marker interface for a board tile.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"  // This matches "type": "PROPERTY"/"SPECIAL" in the JSON
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PropertyImpl.class, name = "PROPERTY"),
    @JsonSubTypes.Type(value = SpecialImpl.class, name = "SPECIAL")
})
public interface Tile {
}
