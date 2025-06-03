package it.unibo.monopoly.model.transactions.api;

import java.util.List;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * The title deed card of a specific {@link Property}.
 * Each {@link TitleDeed} lists all its 
 * rents, mortgage values and buying costs;
 * as well as encapsulating information about its 
 * ownership.
 * The title deed might be owned by a player or 
 * it might not have been bought yet.
 * Title deeds are managed by a {@link Bank}.
 * For each {@link TitleDeed} there is a {@link Property},
 * managed by the {@link Board} that shares the same {@code name}
 * with this title deed ({@link TitleDeed#getName()})
 */
public interface TitleDeed {

    /**
     * Tells whether this Title deed
     * is owned by a player or not. Meaning 
     * a player bought it from the {@link Bank} or the Title deed
     * was assigned to a player at the beginning of the game.
     * @return {@code true} if a player owns the Title deed and {@code false} if
     * the Title deed has no ownership
     */
    boolean isOwned();

    /**
     * Get the id of the {@link Player} that owns this deed.
     * @return the id 
     * associated with the player that currently holds
     * ownership of this {@link TitleDeed}.
     */
    int getOwnerId();

    /**
     * Sets the passed player as the owner of 
     * this {@link TitleDeed}, if no player already owns 
     * the {@link TitleDeed}.
     * @param ownerId The {@code name} associated with the new owner 
     */
    void setOwner(int ownerId);

    /**
     * If the {@link TitleDeed} previously had
     * an owner, resets ownership information of 
     * the {@link TitleDeed}. After that, the deed 
     * will have no owner.
     */
    void removeOwner();

    /**
     * Returns the name of the group.
     * @return the name of the group this {@link TitleDeed} is part of.
     */
    Group getGroup();

    /**
     * Get the name of the title deed.
     * @return the name of this {@link TitleDeed}
     */
    String getName();

    /**
     * Get the sale price of this title deed.
     * @return the price that has to be paid to buy the {@link TitleDeed}
     * and acquire its ownership
     */
    Integer getSalePrice();

    /**
     * Get the mortgage price.
     * @return the amount of money the user would gain back
     * from selling the {@link TitleDeed}
     */
    Integer getMortgagePrice();

    /**
     * The rent price a player has to pay for stepping onto the 
     * {@link Property} corresponding to this {@link TitleDeed}.
     * The rent price might depend also on the state of the 
     * other title deeds that are part of the same group of 
     * this {@link TitleDeed}
     * @param groupTitleDeeds a {@link Set} composed of the 
     * title deeds that will be checked to determine the final 
     * rent price. 
     * The tile deeds should be part of the same group,
     * a call on {@link #getGroup()} should give the same {@link Group}
     * for each of the deeds that are part of this {@link Set}
     * @param diceThrow the value of the throw used to get some specific rent
     * @return the final rent that should be paid as an {@code Integer}
     */
    Integer getRent(Set<TitleDeed> groupTitleDeeds, int diceThrow);

    /**
     * Get all the options available to pay rent.
     * @return the {@link List} of {@link RentOption} 
     * that may be chosen when having to pay the rent of this 
     * {@link TitleDeed}.
     */
    List<RentOption> getRentOptions();


    /**
     * Get the price to pay to buy a house.
     * @return how much it would cost to build a house 
     * on the {@link Property} associated with this {@link TitleDeed}.
     */
    int getHousePrice();

    /**
     * Get the price to pay to buy a hotel.
     * @return how much it would cost to build a hotel
     * on the {@link Property} associated with this {@link TitleDeed}.
     */
    int getHotelPrice();
}
