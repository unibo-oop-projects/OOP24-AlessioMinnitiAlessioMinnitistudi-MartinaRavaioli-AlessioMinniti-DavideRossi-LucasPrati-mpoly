package it.unibo.monopoly.model.transactions.api;

import java.util.Set;

import it.unibo.monopoly.model.transactions.impl.RentOptionImpl;

/**
 * An object that encapsulates all information
 * related to rent cost and applicability conditions.
 */
public interface RentOption {

    /**
     *Default Title for the default rent option.
     */
    String BASE_RENT_TITLE = "Affitto base";

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
     * some check conditions might be applied. The title deeds of the Set 
     * should be all part of the same group, meaning that a call to {@code getGroup} on the title
     * deeds should return the same value.
     * @return whether this rent option can be chosen based on the given 
     * information.
     */
    boolean canBeApplied(Set<TitleDeed> groupDeeds);

    /**
     * Creates the standard rent option. The most basic rent 
     * option, which is always applicable
     * @param baseRent the rent of the option
     * @return the created rent option
     */
    static RentOption baseRentOption(final int baseRent) {
        return new RentOptionImpl(BASE_RENT_TITLE, "", baseRent, deeds -> true);
    }
}
