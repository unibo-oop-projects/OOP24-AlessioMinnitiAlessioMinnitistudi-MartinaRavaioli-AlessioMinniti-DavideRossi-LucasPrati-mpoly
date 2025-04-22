package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;

/**
 * special tile implementation.
*/
public class SpecialImpl implements Special {

    private Effect effetto;

    public SpecialImpl(Effect effetto){
        this.effetto = effetto;
    } 

    @Override
    public void activateEffect() {
        this.effetto.activate();
    }
    
}
