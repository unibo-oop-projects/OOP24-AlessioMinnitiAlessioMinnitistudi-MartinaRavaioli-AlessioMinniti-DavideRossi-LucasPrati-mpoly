package it.unibo.monopoly.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.view.impl.GUIVendita;

/**
 * test class for the property manager frame.
 */

public final class LauncherVendita {

    /**
     * this private constructor's use is to preent form instancing an object of this class.
     */
    private LauncherVendita() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * this is the launcher of the property manager frame.
     * @param args
    */
    public static void main(final String[] args) throws java.io.IOException {
        final BankAccount ac1 = new SimpleBankAccountImpl("piero");
        final BankAccount ac2 = new SimpleBankAccountImpl("pietro");
        final Player p1 = new PlayerImpl("piero");
        final TitleDeed ti1 = new BaseTitleDeed("blue", "nana", 23, i -> i + 40 , 20);
        final Bank bank = new BankImpl(Set.of(ac1,ac2), Set.of(ti1));
        final int width = 700;
        final int heigth = 500;
        bank.buyTitleDeed("nana", "piero");
        new GUIVendita(p1, width, heigth, bank); 
    }

}
