package it.unibo.monopoly.view.api;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * Display information related to the {@link BankAccount} of a player.
 */
public interface AccountPanel extends GamePanel {

    /**
     * Displays information related to a {@link BankAccount}.
     * @param ba the {@link BankAccount} to unpack
     */
    void displayBankAccount(BankAccount ba);
}
