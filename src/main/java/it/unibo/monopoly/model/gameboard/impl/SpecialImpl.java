package it.unibo.monopoly.model.gameboard.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * {@link Special} tile implementation.
*/
public class SpecialImpl extends TileImpl implements Special {

    private final String effect;

    /**
    * get the type.
    * @param name
    * @param position
    * @param type
    */
    @JsonCreator
    public SpecialImpl(
        @JsonProperty("name") final String name,
        @JsonProperty("position") final Position position,
        @JsonProperty("effect") final String effect
    ){
        super(name, position, Type.SPECIAL);
        this.effect = effect;
    }
    /**
    * get the effect.
    * @return Effect
    */
    @Override
    public final Effect getEffect() { 
        return null; 
    }

    public String debugEffectTODELETE() {
        return effect;
    }
}
