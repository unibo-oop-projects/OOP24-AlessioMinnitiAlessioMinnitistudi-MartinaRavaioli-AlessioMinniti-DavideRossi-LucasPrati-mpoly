package it.unibo.model.Special.API;

import it.unibo.model.Card.API.Card;
import it.unibo.model.Effect.API.Effect;

public interface Special extends Card {
    Effect getEffect();
}
