package it.unibo.monopoly.model.transactions.impl.titledeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.rentoption.RentOptionFactoryImpl;

abstract class AbstractTitleDeed implements TitleDeed {
    private final Group group;
    private final String name;
    private final int salePrice;
    private final Function<Integer, Integer> mortgageFunction; 
    private final List<RentOption> rentOptions;
    private Optional<Integer> owner = Optional.empty();

    /**
     * //TODO fill in.
     * @param group
     * @param name
     * @param salePrice
     * @param mortgageFunction
     * @param baseRent
     */
    AbstractTitleDeed(final Group group, 
                        final String name, 
                        final int salePrice, 
                        final Function<Integer, Integer> mortgageFunction, 
                        final int baseRent) {
        this.group = group;
        this.name = name;
        this.salePrice = salePrice;
        this.mortgageFunction = mortgageFunction;
        this.rentOptions = new ArrayList<>(List.of(new RentOptionFactoryImpl().baseRentOption(baseRent)));
    }

    @Override
    public final int getOwnerId() {
        if (owner.isEmpty()) {
            throw new IllegalStateException("This title deed has no owner");
        }
        return owner.get();
    }

    @Override
    public final void setOwner(final int ownerId) {
        Objects.requireNonNull(ownerId);
        if (owner.isPresent()) {
            throw new IllegalStateException("Cannot set a new owner for" 
                                            + "the title deed because the owner" 
                                            + owner.get() 
                                            + " already owns it"
                                            );
        }
        owner = Optional.of(ownerId);
    }

    @Override
    public final void removeOwner() {
        if (owner.isEmpty()) {
            throw new IllegalStateException("Cannot remove the owner because no owner is set");
        }
        owner = Optional.empty();
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
     * This implementation returns a copy 
     * of all the rent options that could be applied when paying the
     * rent of this specific {@link TitleDeed}.
     */
    @Override
    public List<RentOption> getRentOptions() {
        return List.copyOf(this.rentOptions);
    }
}
