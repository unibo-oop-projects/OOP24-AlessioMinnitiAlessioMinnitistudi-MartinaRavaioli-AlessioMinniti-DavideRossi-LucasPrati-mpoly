package it.unibo.monopoly.model.transactions.api;

import java.util.List;

/**
 * Factory interface for {@link RentOption} objects 
 * and {@code collections} of rent option objects.
 */
public interface RentOptionFactory {

    /**
     * @param startRent the standard rent fare. This implementation returns 
     * a rent option whose price is double the rent fare passed as input.
     * @return A {@link RentOption} that can be applied only when
     * all the deeds passed to the {@code canBeApplied} method 
     * of the rent option have the same owner. The price of this option is 
     * double the {@code startRent} passed as input.
     */
    RentOption allDeedsOfGroupWithSameOwner(int startRent);

    /**
     * @param startRent the standard rent fare. The produced rent option
     * objects' price will be a result of a transformation function applied
     * to this {@code startRent}. The first {@link RentOption} of the {@link List}
     * will have {@code startRent} as its price.
     * @param multiplyFactor each {@link RentOption} in the {@code list} 
     * will have a price that is the same as the one of the previous 
     * rent option of the list, but multiplied by the provided {@code multiplyFactor}
     * @param nOptions the number of options to generate in the {@link List}
     * @return A {@link List} of options whose price increases progressively following this formula:
     * {@code rentPrice = previousOptionInTheList.getPrice() * multiplyFactor}
     * When calling {@link RentOption#canBeApplied(java.util.Set, int)} on one of these rent options it
     * will check how many {@code titledeeds} the {@code owner} passed as input possesses. If this number 
     * is greater than an arbitrary number required by the {@code rent option} than the method will return {@code true}.
     * The arbitrary number specified earlier progressively increases through the list. 
     * So for instance:
     * <ul>
     *      <li>
     *          Calling {@link RentOption#canBeApplied(java.util.Set, int)} on the first {@link RentOption} of the list
     *          will return {@code true} if the passed {@code owner} has ownership of at least one {@link TitleDeed}
     *      </li>
     *      <li>
     *          Calling {@link RentOption#canBeApplied(java.util.Set, int)} on the second {@link RentOption} of the list
     *          will return {@code true} if the passed {@code owner} has ownership of at least two {@link TitleDeed}
     *      </li>
     *      <li>
     *          Calling {@link RentOption#canBeApplied(java.util.Set, int)} on the third {@link RentOption} of the list
     *          will return {@code true} if the passed {@code owner} has ownership of at least three {@link TitleDeed}
     *      </li>
     * </ul> 
     * And so on based on the number of elements in the list.
     */
    List<RentOption> progressivelyIncreasingPrice(int startRent, int multiplyFactor, int nOptions);

    /**
     * Creates the standard rent option. The most basic rent 
     * option, which is always applicable
     * @param baseRent the rent of the option
     * @return the created rent option
     */
    RentOption baseRentOption(int baseRent);

    /**
     * Generates a list consisting of {@code nHouses + nHotels} {@link RentOption}.
     * Each one requires a progressively increasing number of houses on the property to be applied,
     * same goes for hotels. 
     * The {@link RentOption} that check for hotels come after the ones that check for houses and are
     * pricier. The {@code rentAmount} is assigned using a tailored mathematical function. 
     * @param baseRent the starting rent used for the calculation. It should correspond to the {@link #baseRentOption(int)}
     * {@code baseRent}
     * @param nHouses number of rent options checking for houses, with progressively increasing house number
     * @param withHotel specifies whether to include in the list the hotel option or not
     * @return a {@link List} of {@link RentOption} ordered based on number of houses on the {@link Property}
     * required for application (and as a consequence by increasing price), with eventually the hotel option
     * as the last one.
     */
    List<RentOption> housesAndHotelsOptions(int baseRent, int nHouses, boolean withHotel);
}
