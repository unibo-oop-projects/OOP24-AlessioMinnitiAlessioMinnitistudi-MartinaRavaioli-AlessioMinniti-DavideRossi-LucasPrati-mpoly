package it.unibo.monopoly.model.transactions.api;

/**
 * An abstract factory of {@link PropertyAction}.
 */
public interface PropertyActionFactory {

    /**
     * Creates an action that buys the requested {@link TitleDeed} for the
     * requested player. Calls {@link Bank#buyTitleDeed(String, int)}.
     * @param currentPlayerId the id associated with the player that will be the new owner of the
     * {@link TitleDeed}
     * @param titleDeedName the name of the {@link TitleDeed} to buy
     * @return a {@link PropertyAction} command that encapsulates the descripted action.
     */
    PropertyAction createBuy(int currentPlayerId, String titleDeedName);

    /**
     * Creates an action that sells the requested {@link TitleDeed}, refunding the 
     * {@link BankAccount} of the player who owned the deed. Calls {@link Bank#sellTitleDeed(String)}.
     * @param titleDeedName the name of the {@link TitleDeed} to sell
     * @return a {@link PropertyAction} command that encapsulates the descripted action.
     */
    PropertyAction createSell(String titleDeedName);

    /**
     * Creates an action that pays the rent requested for the specified user, for stepping
     * onto the specified {@link TitleDeed}. The payment of the rent corresponds to a transfer of
     * money from the payer {@link BankAccount} to the {@link BankAccount} of the
     * user who owns the {@link TitleDeed}. Calls {@link Bank#payRent(String, int)}.
     * @param titleDeedName the name of the {@link TitleDeed} to pay the rent for
     * @param currentPlayerId the id of the player who has to pay the rent
     * @param diceThrow the result of the dice throw that brought the player's {@link Pawn} on the
     * {@link Property} this {@link TitleDeed} is associated to. This parameter is necessary as some {@link TitleDeed}
     * vary the {@code rent amount} based on this parameter.
     * @return a {@link PropertyAction} command that encapsulates the descripted action.
     */
    PropertyAction createPayRent(String titleDeedName, int currentPlayerId, int diceThrow);
    /**
     * create an action that buys an house in the requested titleDeed for the requested player.
     * @param titleDeedName the name of the titleDeed to buy the house
     * @return the PropertyAction command that encapsulates the descripted action
     */
    PropertyAction createBuyHouse(String titleDeedName);
    /**
     * create an action that buys the hotel in the requested titleDeed for the requested player.
     * @param titleDeedName the name of the titleDeed to buy the house
     * @return the PropertyAction command that encapsulates the descripted action
     */
    PropertyAction createBuyHotel(String titleDeedName);
}
