package it.unibo.monopoly.model.transactions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;


/**
 * Standard implementation of the TitleDeed interface 
 * that encapsulates all its information, handles the 
 * concept of player ownership and exposes a finite number 
 * of {@link RentOption} to choose the rent to pay.
 */
public class BaseTitleDeed implements TitleDeed {

    private static final int HPRICE = 55;
    private final Group group;
    private final String name;
    private final int salePrice;
    private final Function<Integer, Integer> mortgageFunction; 
    private final List<RentOption> rentOptions;
    private Optional<String> owner = Optional.empty();

    /**
     * Creates a new {@link BaseTitleDeed} that has only one standard rent fee.
     * <p>
     * The {@code mortageFunction} is a default {@code 50%} of the original {@code salePrice}.
     * 
     * @param type The type of group of title deeds this deed is part of
     * @param name The name of the deed
     * @param salePrice The price to pay to buy the deed
     * @param baseRent The standard rent fee
     */
    @JsonCreator
    public BaseTitleDeed(
        @JsonProperty("group") final Group group,
        @JsonProperty("name") final String name,
        @JsonProperty("price") final int salePrice,
        @JsonProperty("rent") final int baseRent
    ) {
        this(group, name, salePrice, p -> p / 2, baseRent);
    }

    /**
     * Creates a new {@link BaseTitleDeed} that 
     * has only one standard rent fee.
     * @param group The Group of title deeds this deed is part of
     * @param name The name of the deed
     * @param salePrice The price to pay to buy the deed
     * @param mortgageFunction The 
     * @param baseRent The standard rent fee
     */
    public BaseTitleDeed(final Group group, 
                        final String name, 
                        final int salePrice, 
                        final Function<Integer, Integer> mortgageFunction, 
                        final int baseRent) {
        this.group = group;
        this.name = name;
        this.salePrice = salePrice;
        this.mortgageFunction = mortgageFunction;
        this.rentOptions = new ArrayList<>(List.of(RentOption.baseRentOption(baseRent)));
    }

    /**
     * Creates a new {@link BaseTitleDeed}
     * with a standard rent fee and a list of additional
     * rent options.
     * @param group The Group of title deeds this deed is part of
     * @param name The name of the deed
     * @param salePrice The price to pay to buy the deed
     * @param mortgageFunction The 
     * @param baseRent The standard rent fee
     * @param additionalRentOptions The other rent options
     * that could be applied when having to pay the rent
     */
    public BaseTitleDeed(final Group group, 
                        final String name, 
                        final int salePrice, 
                        final Function<Integer, Integer> mortgageFunction, 
                        final int baseRent, 
                        final List<RentOption> additionalRentOptions) {
        this(group, name, salePrice, mortgageFunction, baseRent);
        this.rentOptions.addAll(additionalRentOptions);
    }

    @Override
    public final String getOwner() {
        if (owner.isEmpty()) {
            throw new IllegalStateException("This title deed has no owner");
        }
        return owner.get();
    }

    @Override
    public final void setOwner(final String ownerName) {
        Objects.requireNonNull(ownerName);
        if (owner.isPresent()) {
            throw new IllegalStateException("Cannot set a new owner for" 
                                            + "the title deed because the owner" 
                                            + owner.get() 
                                            + " already owns it"
                                            );
        }
        owner = Optional.of(ownerName);
    }

    @Override
    public final void removeOwner() {
        if (owner.isEmpty()) {
            throw new IllegalStateException("Cannot remove the owner because no owner is set");
        }
        owner = Optional.empty();
    }

    @Override
    public final Group getGroup() {
        return this.group;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final Integer getSalePrice() {
        return this.salePrice;
    }

    /**
     * This implementation applies the {@code mortageFunction}
     * to the {@code salePrice} to calculate the final mortgage value.
     */
    @Override
    public Integer getMortgagePrice() {
        return this.mortgageFunction.apply(this.salePrice);
    }

    /**
     * This implementation verifies that the {@link Set} {@code groupTitleDeeds}
     * contains only title deeds whose {@code group} is the same as the one 
     * of this title deed. Then it checks which rent options are applicable, 
     * selects the priciest and returns its value. 
     */
    @Override
    public Integer getRent(final Set<TitleDeed> groupTitleDeeds) {
        if (!groupTitleDeeds.stream().allMatch(d -> d.getGroup().equals(this.group))) {
            throw new IllegalArgumentException("The list of title deeds contains deeds"
                                                + "that are not part of the group "
                                                + this.group
                                                + ", the group of this title deed");
        }

        if (this.owner.isEmpty()) {
            throw new IllegalStateException("This title deed has not owner yet so it makes no sense to request the rent of it." 
            + "Besides, some calculations to determine the final rent require the title deed to have a owner to work");
        }

        return this.rentOptions.stream()
                                .filter(op -> op.canBeApplied(groupTitleDeeds, this.owner.get()))
                                .mapToInt(RentOption::getPrice)
                                .max()
                                .getAsInt();
    }

    /**
     * This implementation returns a copy 
     * of all the rent options that could be applied when paying the
     * rent of this specific {@link TitleDeed}.
     */
    @Override
    public List<RentOption> getRentOptions() {
        return List.copyOf(this.rentOptions);
    }

    /**
     * This implementation returns the {@code name} and {@code group} of
     * the {@link BaseTitleDeed}.
     */
    @Override
    public String toString() {
        return "Name: " + this.name + "\n Group: " + this.group; 
    }

    /**
     * Default IDE generated implementation of the hashCode method
     * based on the parameters {@code name} and {@code group}.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * Default IDE generated implementation of the equals method 
     * based on the parameters {@code name} and {@code Group}.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseTitleDeed other = (BaseTitleDeed) obj;
        if (group == null) {
            if (other.group != null) {
                return false;
            }
        } else if (!group.equals(other.group)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
    /* PLACE HOLDER FOR ATUAL METHOD */
    /**
     * place holder. 
     * @return price of houses
     */
    @Override
    public int housePrice() {
        return HPRICE;
    }
    /* PLACE HOLDER FOR ATUAL METHOD */
    /**
     * place holder. 
     * @return number of houses
     */
    @Override
    public int houseNum() {
        return 0;
    }

    /**
     * This is implementation checks whether there is a value in
     * the {@link Optional} {@code owner} or if it is empty, and
     * returns that as a boolean.
     */
    @Override
    public boolean isOwned() {
        return owner.isPresent();
    }

}
