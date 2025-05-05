package it.unibo.monopoly.controller.impl;

import java.awt.Color;
import java.util.function.Predicate;

import it.unibo.monopoly.controller.api.GameboardLogic;
import it.unibo.monopoly.model.gameboard.impl.Type;

public class GameboardLogicImpl implements GameboardLogic{

    @Override
    public boolean isBoardTile(int i, int j, int size) {
        Predicate<Integer> isBorder = x -> x == 0 || x == size - 1;
        return isBorder.test(i) || isBorder.test(j);
    }

    @Override
    public Color getTileColor(Type type) {
        switch (type) {
            case Type.RED -> {
                return Color.RED;
            }
            case Type.BLUE -> {
                return Color.BLUE;
            }
            case Type.GREEN -> {
                return Color.GREEN;
            }
            case Type.YELLOW -> {
                return Color.YELLOW;
            }
            case Type.PURPLE -> {
                return Color.MAGENTA;
            }
            case Type.ORANGE -> {
                return Color.ORANGE;
            }
            case Type.CYAN -> {
                return Color.CYAN;
            }
            case Type.BLACK -> {
                return Color.BLACK;
            }
            default -> {
                return Color.BLACK;
            }
        }
    }
    
}
