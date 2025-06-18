package it.unibo.monopoly.model.transactions.impl.titledeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.impl.ImmutableProperty;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * An implementation of {@link TitleDeed} that offers support also for the houses and 
 * hotels part of the game that concerns the property contract. This {@link TitleDeed}
 * can be used to lookup prices to buy a hotel or house, and the rent is calculated based
 * on the number of houses and hotels that are present on the referenced {@link Property}. 
 * This {@link TitleDeed} references a {@link Property} whose name ({@link Property#getName()})
 * is the same as the {@link TitleDeed#getName()} of this deed.
 */
public final class TitleDeedWithHouses extends TitleDeedDecorator {

    private final Property referencedProperty;
    private final List<RentOption> propertyBasedRentOptions;
    private final Function<Integer, Integer> housePriceFunction;
    private final Function<Integer, Integer> hotelPriceFunction;

    /**
     * Creates a new {@link TitleDeedWithHouses}
     * bound to a corresponding {@link Property} located in {@link Board}.
     * @param decorated the {@link TitleDeed} to decorate.
     * @param propertyBasedRentOptions The other rent options
     * that could be applied when having to pay the rent
     * @param attachedProperty the {@link Property} to associated to this
     * deed. The deed will use it to determine the rent. {@link TitleDeed#getName()} 
     * called on this deed and {@link Property#getName()} should return the same value
     * @param houseFunction a function used to calculate the price to buy a house 
     * based on the sale price
     * @param hotelFunction a function used to calculate the price to buy a hotel 
     * based on the sale price
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = """
                It was thought that storing a reference to the property was a better solution for this implementation.
                The title deed needs to know the current state of the property to execute the getRent function. Receiving 
                this information as an input when the method is called could lead to potential errors if the data passed is
                incorrect. Safety is ensured by asking upon construction for an ImmutableProperty: an implementation of Property
                that doesn't allow the modification of its state.
         """
    )
    public TitleDeedWithHouses(
                        final TitleDeed decorated,
                        final List<RentOption> propertyBasedRentOptions,
                        final ImmutableProperty attachedProperty,
                        final Function<Integer, Integer> houseFunction,
                        final Function<Integer, Integer> hotelFunction) {
        super(decorated);
        Objects.requireNonNull(hotelFunction);
        Objects.requireNonNull(houseFunction);
        Objects.requireNonNull(attachedProperty);
        if (!decorated.getName().equals(attachedProperty.getName())) {
            throw new IllegalStateException("This title deed and the associated property"
            + "must have the same name for reference purposes");
        }
        this.propertyBasedRentOptions = new ArrayList<>(propertyBasedRentOptions);
        this.referencedProperty = attachedProperty;
        this.housePriceFunction = houseFunction;
        this.hotelPriceFunction = hotelFunction;
    }

    @Override
    public Integer getRent(final Set<TitleDeed> groupTitleDeeds, final int diceThrow) {
        return IntStream.concat(
            IntStream.of(getDecorated().getRent(groupTitleDeeds, diceThrow)), 
            propertyBasedRentOptions.stream()
            .filter(op -> op.canBeApplied(groupTitleDeeds, diceThrow, referencedProperty))
            .mapToInt(RentOption::getPrice))
            .max()
            .getAsInt();
    }

    @Override
    public List<RentOption> getRentOptions() {
        final List<RentOption> output = new ArrayList<>();
        output.addAll(getDecorated().getRentOptions());
        output.addAll(propertyBasedRentOptions);
        return output;
    }

    @Override
    public int getHousePrice() {
        return housePriceFunction.apply(getSalePrice());
    }

    @Override
    public int getHotelPrice() {
        return hotelPriceFunction.apply(getSalePrice());
    }
}
