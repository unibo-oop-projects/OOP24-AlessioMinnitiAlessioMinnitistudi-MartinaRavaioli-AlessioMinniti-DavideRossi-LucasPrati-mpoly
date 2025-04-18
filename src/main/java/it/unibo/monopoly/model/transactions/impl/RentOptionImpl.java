package it.unibo.monopoly.model.transactions.impl;

import java.util.Set;
import java.util.function.Predicate;

import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

public record RentOptionImpl(String title,String description,int price,Predicate<Set<TitleDeed>> applicabilityCondition) implements RentOption{

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
    public boolean canBeApplied(Set<TitleDeed> groupDeeds) {
        return applicabilityCondition.test(groupDeeds);
    }

    public static RentOptionImpl baseRentOption(int baseRent) {
        return new RentOptionImpl(BASE_RENT_TITLE, "", baseRent,deeds -> true);
    }
}
