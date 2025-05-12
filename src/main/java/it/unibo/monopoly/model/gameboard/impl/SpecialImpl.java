package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * special tile implementation.
*/
public class SpecialImpl implements Special {

    private Position position; 
    private Effect effetto;

    public SpecialImpl(Effect effetto, Position position){
        this.position = position;
        this.effetto = effetto;
    } 

    @Override
    public void activateEffect(Player player) {
        this.effetto.activate(player);
    }


    @Override
    public final Position getPosition() {
        return this.position;
    }
}
