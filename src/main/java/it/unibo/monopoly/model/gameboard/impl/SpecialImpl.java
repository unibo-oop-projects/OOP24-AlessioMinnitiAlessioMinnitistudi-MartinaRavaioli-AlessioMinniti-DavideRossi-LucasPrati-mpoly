package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * special tile implementation.
*/

public class SpecialImpl extends TileImpl implements Special {

    private Effect effetto;
    /**
    * get the Group.
    * @param name
    * @param pos
    * @param group
    */
    public SpecialImpl(final String name, final Position pos, final Group group) {
        super(name, pos, group);
    }
    /**
    * get the Group.
    * @return Effect
    */

    @Override
    public void activateEffect(Player player) {
        this.effetto.activate(player);
    }

}
