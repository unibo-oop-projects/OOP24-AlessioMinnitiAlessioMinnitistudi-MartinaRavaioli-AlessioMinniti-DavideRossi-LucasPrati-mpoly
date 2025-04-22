package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * special tile implementation.
*/
public class SpecialImpl implements Special {

    private Effect effetto;

    public SpecialImpl(Effect effetto){
        this.effetto = effetto;
    } 

    @Override
    public void activateEffect(Player player) {
        this.effetto.activate(player);
    }
    
}
