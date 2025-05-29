package it.unibo.monopoly.controller.impl;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoly.controller.api.GUIVenditaLogic;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * this is the implementation of the logic behind the property manager GUI. 
 */
public final class GUIVenditaLogicImpl implements  GUIVenditaLogic, Serializable {
    static final int NUM = 0;
    private static final long serialVersionUID = -6218820567019985015L;

    /**
     * constructor.
     */
    // public GUIVenditaLogicImpl() { }

    @Override
    public List<TitleDeed> getProperties(final Player player, final Bank bank) {
        if (bank.getTitleDeedsByOwner(player.getName()).isEmpty()) {
            return List.of();
        }
        return bank.getTitleDeedsByOwner(player.getName()).stream().toList();
    }

    @Override
    public BankAccount getPlayerBalance(final Player player, final Bank bank) {
        return bank.getBankAccount(player.getName());
    }

    @Override
    public Color getPropertyColor(final TitleDeed selectedProperty) {
        return selectedProperty.getGroup().getColor();
    }

    @Override
    public boolean sellProperty(final List<TitleDeed> properties, final TitleDeed selectedProperty, final Bank bank) {
        bank.sellTitleDeed(selectedProperty.getName());
        return true;
    }

    @Override
    public TitleDeed getProperty(final List<TitleDeed> properties, final Object selectedValue) {
        final Optional<TitleDeed> selectedPropertyO = properties.stream()
                                                                .filter(p -> p.getName().equals(selectedValue))
                                                                .findAny();
        TitleDeed selectedProperty = new BaseTitleDeed(null, "null", NUM, null, NUM); 
        if (selectedPropertyO.isPresent()) {
            selectedProperty = selectedPropertyO.get();
        }
        return selectedProperty;
    }

}
