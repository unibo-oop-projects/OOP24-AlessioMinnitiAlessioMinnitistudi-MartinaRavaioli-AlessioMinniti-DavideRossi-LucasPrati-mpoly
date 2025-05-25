package it.unibo.monopoly.model.gameboard.impl;


import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * {@link Special} tile implementation.
*/

public final class SpecialImpl extends TileImpl implements Special {

    private final Effect effect;
    /**
     * contruvtor for special impl.
     * @param name of the special tile
     * @param pos of the special tile
     * @param group of the special tile
     * @param effetto of the special tile
     */
    public SpecialImpl(final String name, final Position pos, final Group group, final Effect effetto) {
        super(name, pos, group);
        this.effect = effetto;
    }

    @Override
    public void activateEffect(final Player player) {
        this.effect.activate(player);

    }

}
