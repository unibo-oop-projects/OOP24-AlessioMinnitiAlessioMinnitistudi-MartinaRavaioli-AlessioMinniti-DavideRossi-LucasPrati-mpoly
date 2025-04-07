package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;

public interface Pawn extends Player{
    void move(int steps);
}
