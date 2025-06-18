package it.unibo.monopoly.model.transactions.api;

import java.util.Set;

import it.unibo.monopoly.model.gameboard.api.Property;

/**
 * An object that encapsulates all information
 * related to one of the possible rent options that
 * might be chosen when having to pay for a {@link TitleDeed}.
 * Each {@link TitleDeed} has a finite {@code collection} of rent options. When a player's 
 * pawn steps on a Property the {@link TitleDeed} associated with that property is retrieved
 * and the title deed executes a query on its rent options to determine the final price to pay. 
 * Taking the original cardboard game as a reference, the collection of rent options 
 * corresponds to the list of available rents written on a title deed card. Intuitively, a single 
 * row of that list corresponds to a {@link RentOption} object in this system. 
 * A rent option contains a title and eventually a description, 
 * a price and its applicability conditions.
 */
public interface RentOption {

    /**
     * Get the title of the rent option.
     * @return the title of the {@link RentOption}.
     */
    String getTitle();

    /**
     * Get a brief description about the rent option.
     * @return the decription of the {@link RentOption}.
     * The description could be blank
     */
    String getDescription();

    /**
     * Get the price of this rent option.
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
     * @param ownerId the ownerid of the {@link TitleDeed} this {@code rent option} is associated to
     * @param property some options may be applied based on the state of the {@link BuildableProperty}
     * associated with the {@link TitleDeed}.
     * @return whether this rent option can be chosen based on the given 
     * information.
     */
    boolean canBeApplied(Set<TitleDeed> groupDeeds, int ownerId, Property property);
}
