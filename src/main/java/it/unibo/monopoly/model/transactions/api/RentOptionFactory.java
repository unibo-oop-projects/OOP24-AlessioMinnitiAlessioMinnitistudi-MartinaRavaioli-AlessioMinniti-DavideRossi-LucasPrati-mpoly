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
     * When calling {@link RentOption#canBeApplied(java.util.Set, String)} on one of these rent options it
     * will check how many {@code titledeeds} the {@code owner} passed as input possesses. If this number 
     * is greater than an arbitrary number required by the {@code rent option} than the method will return {@code true}.
     * The arbitrary number specified earlier progressively increases through the list. 
     * So for instance:
     * <ul>
     *      <li>
     *          Calling {@link RentOption#canBeApplied(java.util.Set, String)} on the first {@link RentOption} of the list
     *          will return {@code true} if the passed {@code owner} has ownership of at least one {@link TitleDeed}
     *      </li>
     *      <li>
     *          Calling {@link RentOption#canBeApplied(java.util.Set, String)} on the second {@link RentOption} of the list
     *          will return {@code true} if the passed {@code owner} has ownership of at least two {@link TitleDeed}
     *      </li>
     *      <li>
     *          Calling {@link RentOption#canBeApplied(java.util.Set, String)} on the third {@link RentOption} of the list
     *          will return {@code true} if the passed {@code owner} has ownership of at least three {@link TitleDeed}
     *      </li>
     * </ul> 
     * And so on based on the number of elements in the list.
     */
    List<RentOption> progressivelyIncreasingPrice(int startRent, int multiplyFactor, int nOptions);

}
