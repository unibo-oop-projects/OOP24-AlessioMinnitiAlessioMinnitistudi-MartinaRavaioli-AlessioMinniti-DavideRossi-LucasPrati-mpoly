package it.unibo.monopoly.controller.impl;

import java.util.Map;
import java.util.function.Predicate;

import it.unibo.monopoly.controller.api.GameboardLogic;

/**
    * board logic implementation.
*/
public class GameboardLogicImpl implements GameboardLogic {

    public GameboardLogicImpl() { }

    @Override
    public final boolean isBoardTile(final int i, final int j, final int size) {
        final Predicate<Integer> isBorder = x -> x == 0 || x == size - 1;
        return isBorder.test(i) || isBorder.test(j);
    }

    @Override
    public final int tileCard(final int i, final int j, final int size) {
        final Map<String, Integer> cards = Map.of(
        (size / 2) + "," + (size / 2 - 1), 0,
        (size / 2) + "," + (size / 2 + 1), 1
        );
        return cards.getOrDefault(i + "," + j, -1);
    }

    @Override
    public final int getSize(final int numTiles) {
        return numTiles / 4 + 1;
    }

}
