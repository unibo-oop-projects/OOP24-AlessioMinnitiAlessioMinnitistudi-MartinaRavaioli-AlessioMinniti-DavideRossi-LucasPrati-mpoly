package it.unibo.monopoly.model.transactions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.gameboard.impl.Type;


/**
 * Standard implementation of the TitleDeed interface 
 * that encapsulates all its information, handles the 
 * concept of player ownership and exposes a finite number 
 * of {@link RentOption} to choose the rent to pay.
 */
public class BaseTitleDeed implements TitleDeed {

    private final Type type;
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
        @JsonProperty("type") final Type type,
        @JsonProperty("name") final String name,
        @JsonProperty("price") final int salePrice,
        @JsonProperty("rent") final int baseRent
    ) {
        this(type, name, salePrice, p -> p / 2, baseRent);
    }

    /**
     * Creates a new {@link BaseTitleDeed} that 
     * has only one standard rent fee.
     * @param type The type of group of title deeds this deed is part of
     * @param name The name of the deed
     * @param salePrice The price to pay to buy the deed
     * @param mortgageFunction The 
     * @param baseRent The standard rent fee
     */
    public BaseTitleDeed(final Type type, 
                        final String name, 
                        final int salePrice, 
                        final Function<Integer, Integer> mortgageFunction, 
                        final int baseRent) {
        this.type = type;
        this.name = name;
        this.salePrice = salePrice;
        this.mortgageFunction = mortgageFunction;
        this.rentOptions = new ArrayList<>(List.of(RentOption.baseRentOption(baseRent)));
    }

    /**
     * Creates a new {@link BaseTitleDeed}
     * with a standard rent fee and a list of additional
     * rent options.
     * @param type The type of group of title deeds this deed is part of
     * @param name The name of the deed
     * @param salePrice The price to pay to buy the deed
     * @param mortgageFunction The 
     * @param baseRent The standard rent fee
     * @param additionalRentOptions The other rent options
     * that could be applied when having to pay the rent
     */
    public BaseTitleDeed(final Type type, 
                        final String name, 
                        final int salePrice, 
                        final Function<Integer, Integer> mortgageFunction, 
                        final int baseRent, 
                        final List<RentOption> additionalRentOptions) {
        this(type, name, salePrice, mortgageFunction, baseRent);
        this.rentOptions.addAll(additionalRentOptions);
    }

    @Override
    public final Optional<String> getOwner() {
        return owner;
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
    public final Type getType() {
        return this.type;
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
        if (!groupTitleDeeds.stream().allMatch(d -> d.getType().equals(this.type))) {
            throw new IllegalArgumentException("The list of title deeds contains deeds"
                                                + "that are not part of the group "
                                                + this.type
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
        return "Name: " + this.name + "\n Group: " + this.type; 
    }

    /**
     * Default IDE generated implementation of the hashCode method
     * based on the parameters {@code name} and {@code group}.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * Default IDE generated implementation of the equals method 
     * based on the parameters {@code name} and {@code type}.
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
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
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
}
