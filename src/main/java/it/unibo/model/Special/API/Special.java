package it.unibo.model.Special.API;

import it.unibo.model.Effect.API.Effect;
import it.unibo.model.gameBoard.api.Card;

public interface Special extends Card {
    Effect getEffect();
}
