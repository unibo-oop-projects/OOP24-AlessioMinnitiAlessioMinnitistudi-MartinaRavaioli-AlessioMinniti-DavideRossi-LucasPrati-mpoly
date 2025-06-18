package it.unibo.monopoly.model.transactions.impl;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.PropertyAction;
import it.unibo.monopoly.model.transactions.api.PropertyActionFactory;
import it.unibo.monopoly.model.transactions.api.PropertyActionsEnum;

/**
 * Implementation of interface {@link PropertyActionFactory} that creates 
 * {@link PropertyAction} objects for the {@link Bank} class passed upon
 * construction.
 */
final class PropertyActionFactoryImpl implements PropertyActionFactory {

    @Override
    public PropertyAction createBuy(final int currentPlayerId, final String titleDeedName) {
        return new PropertyAction() {

            @Override
            public PropertyActionsEnum getType() {
                return PropertyActionsEnum.BUY;
            }

            @Override
            public String getDescription() {
                return "Buy " + titleDeedName;
            }

            @Override
            public void executePropertyAction(final Board board, final Bank bank) {
                bank.buyTitleDeed(titleDeedName, currentPlayerId);
            }
        };
    }

    @Override
    public PropertyAction createSell(final String titleDeedName) {
        return new PropertyAction() {

            @Override
            public PropertyActionsEnum getType() {
                return PropertyActionsEnum.SELL;
            }

            @Override
            public String getDescription() {
                return "Sell " + titleDeedName;
            }

            @Override
            public void executePropertyAction(final Board board, final Bank bank) {
                bank.sellTitleDeed(titleDeedName);
            }
        };
    }

    @Override
    public PropertyAction createPayRent(final String titleDeedName, final int currentPlayerId, final int diceThrow) {
        if (diceThrow <= 0) {
            throw new IllegalArgumentException("The dice throw cannot be lower than 1");
        }
        return new PropertyAction() {

            @Override
            public PropertyActionsEnum getType() {
                return PropertyActionsEnum.PAYRENT;
            }

            @Override
            public String getDescription() {
                return "Pay rent for " + titleDeedName;
            }

            @Override
            public void executePropertyAction(final Board board, final Bank bank) {
                bank.payRent(titleDeedName, currentPlayerId, diceThrow);
            }
        };
    }

    @Override
    public PropertyAction createBuyHouse(final String titleDeedName) {
        return new PropertyAction() {

            @Override
            public PropertyActionsEnum getType() {
                return PropertyActionsEnum.BUYHOUSE;
            }

            @Override
            public String getDescription() {
                return "buy house for " + titleDeedName;
            }

            @Override
            public void executePropertyAction(final Board board, final Bank bank) {
                bank.buyHouse(titleDeedName);
            }
        };
    }

    @Override
    public PropertyAction createBuyHotel(final String titleDeedName) {
        return new PropertyAction() {

            @Override
            public PropertyActionsEnum getType() {
                return PropertyActionsEnum.BUYHOTEL;
            }

            @Override
            public String getDescription() {
                return "buy hotel for " + titleDeedName;
            }

            @Override
            public void executePropertyAction(final Board board, final Bank bank) {
                bank.buyHotel(titleDeedName);
            }
        };
    }

    @Override
    public PropertyAction createSellHouse(final String titleDeedName) {
        return new PropertyAction() {

            @Override
            public PropertyActionsEnum getType() {
                return PropertyActionsEnum.SELLHOUSE;
            }

            @Override
            public String getDescription() {
                return "sell house of " + titleDeedName;
            }

            @Override
            public void executePropertyAction(final Board board, final Bank bank) {
                bank.sellHouse(titleDeedName);
            }
        };
    }

    @Override
    public PropertyAction createSellHotel(final String titleDeedName) {
        return new PropertyAction() {

            @Override
            public PropertyActionsEnum getType() {
                return PropertyActionsEnum.SELLHOTEL;
            }

            @Override
            public String getDescription() {
                return "sell hotel of " + titleDeedName;
            }

            @Override
            public void executePropertyAction(final Board board, final Bank bank) {
                bank.sellHotel(titleDeedName);
            }
        };
    }

}
