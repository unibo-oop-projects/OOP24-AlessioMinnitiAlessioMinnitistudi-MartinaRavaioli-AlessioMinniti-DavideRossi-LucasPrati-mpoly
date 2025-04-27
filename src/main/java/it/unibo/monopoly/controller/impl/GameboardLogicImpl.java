package it.unibo.monopoly.controller.impl;

import java.util.function.Predicate;

import it.unibo.monopoly.controller.api.GameboardLogic;

public class GameboardLogicImpl implements GameboardLogic{

    @Override
    public boolean isBoardTile(int i, int j, int size) {
        Predicate<Integer> isBorder = x -> x == 0 || x == size - 1;
        return isBorder.test(i) || isBorder.test(j);
    }
    
}
