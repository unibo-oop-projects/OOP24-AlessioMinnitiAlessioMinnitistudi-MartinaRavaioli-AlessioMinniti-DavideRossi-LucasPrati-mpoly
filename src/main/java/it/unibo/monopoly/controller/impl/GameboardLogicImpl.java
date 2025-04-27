package it.unibo.monopoly.controller.impl;

import it.unibo.monopoly.controller.api.GameboardLogic;

public class GameboardLogicImpl implements GameboardLogic{

    @Override
    public boolean isBoardTile(int i, int j, int size) {
        if((i == 0 || i == size) && (j == 0 || j == size)){
            return true;
        }

        return false;
    }
    
}
