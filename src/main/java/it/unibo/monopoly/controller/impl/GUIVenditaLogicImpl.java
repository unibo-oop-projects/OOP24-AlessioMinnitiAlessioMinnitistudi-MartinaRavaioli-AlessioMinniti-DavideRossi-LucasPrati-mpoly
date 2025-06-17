package it.unibo.monopoly.controller.impl;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoly.controller.api.GUIVenditaLogic;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.titledeed.BaseTitleDeed;
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
        if (bank.getTitleDeedsByOwner(player.getID()).isEmpty()) {
            return List.of();
        }
        return bank.getTitleDeedsByOwner(player.getID()).stream().toList();
    }

    @Override
    public BankAccount getPlayerBalance(final Player player, final Bank bank) {
        return bank.getBankAccount(player.getID());
    }

    @Override
    public Color getPropertyColor(final TitleDeed selectedProperty) {
        return selectedProperty.getGroup().getColor();
    }

    @Override
    public boolean sellProperty(final TitleDeed selectedProperty, final Bank bank) {
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

    @Override
    public Property getBuildableProperty(final TitleDeed selectedProperty, final Board board) {
        final Tile tile = board.getTile(selectedProperty.getName());
        if (tile instanceof Property) {
            return (Property) tile;
        } else {
            return null;
        }
    }

    @Override
    public boolean sellHouse(final Property selectedBuildableProberty, final Bank bank, final Board board) {
        try {
            bank.sellHouse(selectedBuildableProberty.getName());
            board.deleteHouseInProperty(selectedBuildableProberty);
            return true;
        } catch (final IllegalAccessException e) {
            return false;
        }

    }

    @Override
    public boolean sellHotel(final Property selectedBuildableProberty, final Bank bank, final Board board) {
         try {
            bank.sellHotel(selectedBuildableProberty.getName());
            board.deleteHotelInProperty(selectedBuildableProberty);
            return true;
        } catch (final IllegalAccessException e) {
            return false;
        }
    }

}
