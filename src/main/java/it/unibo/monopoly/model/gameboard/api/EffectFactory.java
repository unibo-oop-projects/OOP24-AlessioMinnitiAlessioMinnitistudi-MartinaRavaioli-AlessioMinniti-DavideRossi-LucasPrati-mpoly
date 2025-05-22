package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.TurnationManager;

public interface EffectFactory {

    Effect depositMoney(int amount);

    Effect withdrawMoney(int amount);

    Effect putInPrison();

    Effect stayInPrison(TurnationManager turnationManager);
}
