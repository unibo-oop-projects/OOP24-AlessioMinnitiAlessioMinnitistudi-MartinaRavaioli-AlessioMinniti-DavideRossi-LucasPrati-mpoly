package it.unibo.monopoly.model.transactions.impl;

import java.util.Set;
import java.util.function.Predicate;

import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * Unmodifiable implementation of a {@link RentOption}.
 * @param title The title of this rent option
 * @param description Additional information about how cost is calculated 
 * and applicability conditions
 * @param price the amount of money to pay if this option is selected
 * @param applicabilityCondition used to verify whether this rent option 
 * can be chosen or not
 */
public record RentOptionImpl(String title,
                            String description,
                            int price,
                            Predicate<Set<TitleDeed>> applicabilityCondition) implements RentOption {

    private static final String BASE_RENT_TITLE = "Affitto base";


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
    public boolean canBeApplied(final Set<TitleDeed> groupDeeds) {
        return applicabilityCondition.test(groupDeeds);
    }

    /**
     * Creates the standard rent option. The most basic rent 
     * option, which is always applicable
     * @param baseRent the rent of the option
     * @return the created rent option
     */
    public static RentOptionImpl baseRentOption(final int baseRent) {
        return new RentOptionImpl(BASE_RENT_TITLE, "", baseRent, deeds -> true);
    }
}
