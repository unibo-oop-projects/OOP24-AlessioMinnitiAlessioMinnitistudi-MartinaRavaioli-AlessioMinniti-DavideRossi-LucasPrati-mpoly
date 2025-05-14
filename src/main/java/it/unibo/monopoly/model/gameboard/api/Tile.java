package it.unibo.monopoly.model.gameboard.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import it.unibo.monopoly.model.gameboard.impl.PropertyImpl;
import it.unibo.monopoly.model.gameboard.impl.SpecialImpl;
import it.unibo.monopoly.model.gameboard.impl.Type;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * tile interface.
*/
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SpecialImpl.class, name = "SPECIAL"),
    @JsonSubTypes.Type(value = PropertyImpl.class, name = "PROPERTY")
})
public interface Tile {

    /**
    * get the type.
    * @return Type
    */
    Type getType();

    /**
    * set the type.
    * @param type
    */
    void setType(Type type);

    /**
    * get the position.
    * @return position
    */
    Position getPosition();

    /**
    * get the name.
    * @return String
    */
    String getName();

}
