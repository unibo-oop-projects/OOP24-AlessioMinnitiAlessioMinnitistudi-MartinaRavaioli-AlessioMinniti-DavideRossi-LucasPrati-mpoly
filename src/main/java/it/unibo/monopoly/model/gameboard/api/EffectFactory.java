package it.unibo.monopoly.model.gameboard.api;


public interface EffectFactory {

    Effect depositMoney(int amount);

    Effect withdrawMoney(int amount);

    Effect putInPrison();

    Effect park();

}
