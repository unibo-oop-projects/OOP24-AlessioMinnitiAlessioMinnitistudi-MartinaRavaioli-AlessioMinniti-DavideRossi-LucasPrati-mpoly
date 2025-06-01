package it.unibo.monopoly.model.transactions.impl.rentoption;

import java.util.Set;
import java.util.function.Predicate;

import it.unibo.monopoly.model.gameboard.api.BuildableProperty;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

public record PropertyRentOptionImpl(
    String title,
    String description,
    int price,
    Predicate<BuildableProperty> propertyApplicabilityCondition
) implements RentOption{

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
    public boolean canBeApplied(final Set<TitleDeed> groupDeeds, final int ownerId, final BuildableProperty property) {
        return propertyApplicabilityCondition.test(property);
    }
}
