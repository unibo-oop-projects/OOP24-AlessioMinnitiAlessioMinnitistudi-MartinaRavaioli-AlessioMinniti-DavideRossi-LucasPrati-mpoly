package it.unibo.monopoly.controller.impl;

import java.util.function.Predicate;

import it.unibo.monopoly.controller.api.GameboardLogic;

/**
    * board logic implementation.
*/
public class GameboardLogicImpl implements GameboardLogic {

    @Override
    public final boolean isBoardTile(final int i, final int j, final int size) {
        final Predicate<Integer> isBorder = x -> x == 0 || x == size - 1;
        return isBorder.test(i) || isBorder.test(j);
    }

    @Override
    public final int isCard(final int i, final int j, final int size) {
        if ((j == size / 2 - 1) && (i == size / 2)) {
            return 0;
        } else if ((j == size / 2 + 1) && (i == size / 2)) {
            return 1;
        } else {
            return -1;
        }
    }
}
