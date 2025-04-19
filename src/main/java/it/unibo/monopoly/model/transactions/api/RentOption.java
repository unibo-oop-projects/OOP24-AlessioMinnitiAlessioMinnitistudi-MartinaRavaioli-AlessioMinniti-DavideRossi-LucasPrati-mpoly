package it.unibo.monopoly.model.transactions.api;

import java.util.Set;

/**
 * An object that encapsulates all information
 * related to rent cost and applicability conditions.
 */
public interface RentOption {

    /**
     * @return the title of the {@link RentOption}.
     */
    String getTitle();

    /**
     * @return the decription of the {@link RentOption}.
     * The description could be blank
     */
    String getDescription();

    /**
     * @return the price of the {@link RentOption}.
     * This is the price the player will have to pay
     * if this rent option is selected
     */
    int getPrice();

    /**
     * Verifies whether this specific {@link RentOption}
     * can be applied. It may apply some check conditions on the 
     * title deeds that are passed as input
     * @param groupDeeds a {@link Set} of {@link TitleDeed} on which
     * some check conditions might be applied
     * @return whether this rent option can be chosen based on the given 
     * information.
     */
    boolean canBeApplied(Set<TitleDeed> groupDeeds);
}
