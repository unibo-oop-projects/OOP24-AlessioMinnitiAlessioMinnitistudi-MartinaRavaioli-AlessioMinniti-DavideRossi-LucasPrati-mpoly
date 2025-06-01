package it.unibo.monopoly.model.transactions.impl.rentoption;

import java.util.Set;
import java.util.function.Predicate;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * An implementation of {@link RentOption} that checks for specific conditions 
 * on the passed {@link Property}, when the method {@link #canBeApplied(Set, int, Property)}
 * is called. The check to apply to validate the {@link Property} is passed upon construction.
 * @param title The title of this rent option
 * @param description Additional information about how cost is calculated 
 * and applicability conditions
 * @param price the amount of money to pay if this option is selected
 * @param propertyApplicabilityCondition used to verify whether this rent option 
 * can be chosen or not based on the state of the passed {@link Property} as input.
 */
public record PropertyRentOptionImpl(
    String title,
    String description,
    int price,
    Predicate<Property> propertyApplicabilityCondition
) implements RentOption {

    @Override
    public String getTitle() {
       return this.title();
    }

    @Override
    public String getDescription() {
        return this.description();
    }

    @Override
    public int getPrice() {
        return this.price();
    }

    @Override
    public boolean canBeApplied(final Set<TitleDeed> groupDeeds, final int ownerId, final Property property) {
        return propertyApplicabilityCondition.test(property);
    }
}
