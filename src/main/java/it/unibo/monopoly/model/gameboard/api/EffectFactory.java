package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.transactions.api.Bank;

public interface EffectFactory {

    Effect depositMoney(int amount, Bank bank);

    Effect withdrawMoney(int amount, Bank bank);

    Effect putInPrison(Board board);

    Effect park();

    Effect still();
}
