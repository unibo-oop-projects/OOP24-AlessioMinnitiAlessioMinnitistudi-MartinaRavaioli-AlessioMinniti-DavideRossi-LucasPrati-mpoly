package it.unibo.monopoly.model.transactions.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.impl.Type;

/**
 * The title deed card of a specific {@link Property}.
 * Each {@link TitleDeed} lists all its 
 * rents, mortgage values and buying costs;
 * as well as encapsulating information about its 
 * ownership.
 * The title deed might be owned by a player or 
 * it might not have been bought yet
 */
public interface TitleDeed {


    /**
     * @return {@link Optional} containing the name 
     * associated with the player that currently holds
     * ownership of this {@link TitleDeed}; or an empty optional 
     * if no player owns the property.
     */
    Optional<String> getOwner();

    /**
     * Sets the passed player as the owner of 
     * this {@link TitleDeed}, if no player already owns 
     * the {@link TitleDeed}.
     * @param ownerName The {@code name} associated with the new owner 
     */
    void setOwner(String ownerName);

    /**
     * If the {@link TitleDeed} previously had
     * an owner, resets ownership information of 
     * the {@link TitleDeed}. After that, the deed 
     * will have no owner and subsequent calls to 
     * {@link #getOwner()} will return an empty {@link Optional}
     */
    void removeOwner();

    /**
     * @return the name of the group this {@link TitleDeed} is part of.
     */
    Type getType();

    /**
     * @return the name of this {@link TitleDeed}
     */
    String getName();

    /**
     * @return the price that has to be paid to buy the {@link TitleDeed}
     * and acquire its ownership
     */
    Integer getSalePrice();

    /**
     * @return the amount of money the user would gain back
     * from selling the {@link TitleDeed}
     */
    Integer getMortgagePrice();

    /**
     * The rent price a player has to pay for stepping onto the 
     * {@link Property} associated with this {@link TitleDeed}.
     * The rent price might depend also on the state of the 
     * other title deeds that are part of the same group of 
     * this {@link TitleDeed}
     * @param groupTitleDeeds a {@link Set} composed of the 
     * title deeds that will be checked to determine the final 
     * rent price. 
     * The tile deeds should be part of the same group,
     * a call on {@link #getType()} should give the same {@code String}
     * for each of the deeds that are part of this {@link Set}
     * @return the final rent that should be paid as an {@code Integer}
     */
    Integer getRent(Set<TitleDeed> groupTitleDeeds);

    /**
     * @return the {@link List} of {@link RentOption} 
     * that may be chosen when having to pay the rent of this 
     * {@link TitleDeed}.
     */
    List<RentOption> getRentOptions();

    /**
     * place holder. 
     * @return price of houses
     */
    int housePrice();

    /**
     * place holder. 
     * @return number of houses
     */
    int houseNum();

}
