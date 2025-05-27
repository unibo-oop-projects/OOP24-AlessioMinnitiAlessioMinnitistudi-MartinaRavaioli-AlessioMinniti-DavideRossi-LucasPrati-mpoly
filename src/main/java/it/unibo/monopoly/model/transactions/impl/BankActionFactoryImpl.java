package it.unibo.monopoly.model.transactions.impl;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAction;
import it.unibo.monopoly.model.transactions.api.BankActionFactory;

/**
 * Implementation of interface {@link BankActionFactory} that creates 
 * {@link BankAction} objects for the {@link Bank} class passed upon
 * construction.
 */
//TODO refactor visibility of classes in package 
public class BankActionFactoryImpl implements BankActionFactory {

    private final Bank bank;

    /**
     * Creates a new {@link BankActionFactory} that creates {@link BankAction} commands
     * to call on the {@link Bank} object passed as input.
     * @param bank the {@link Bank} object the {@link BankAction} objects will point to
     */
    @SuppressFBWarnings(
        value = { "EI_EXPOSE_REP2" },
        justification = 
        """
        This factory is intended for the creation of command objects 
        that act on a specific Bakn object. It does not modify in any way 
        the state of the stored object. For this use case, storing a copy would not 
        be a valid option because the BankAction objects created would not call methods 
        on the orginal Bank instance passed, but rather on its copy
        """
    )
    public BankActionFactoryImpl(final Bank bank) {
        this.bank = bank;
    }

    @Override
    public BankAction createBuy(final int currentPlayerId, final String titleDeedName) {
        return new BankAction() {

            @Override
            public String getName() {
                return "Buy " + titleDeedName;
            }

            @Override
            public void executeTransaction() {
                bank.buyTitleDeed(titleDeedName, currentPlayerId);
            }
            
        };
    }

    @Override
    public BankAction createSell(final String titleDeedName) {
        return new BankAction() {

            @Override
            public String getName() {
                return "Sell " + titleDeedName;
            }

            @Override
            public void executeTransaction() {
                bank.sellTitleDeed(titleDeedName);
            }
            
        };
    }

    @Override
    public BankAction createPayRent(final String titleDeedName, final int currentPlayerId, final int diceThrow) {
        if(diceThrow <= 0) {
            throw new IllegalArgumentException("The dice throw cannot be lower than 1");
        }
        return new BankAction() {

            @Override
            public String getName() {
                return "Pay rent for " + titleDeedName;
            }

            @Override
            public void executeTransaction() {
                //TODO refactor dice throw to integer
                bank.payRent(titleDeedName, currentPlayerId, List.of(diceThrow));
            }
            
        };
    }

}
