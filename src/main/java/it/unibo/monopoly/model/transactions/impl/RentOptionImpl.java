package it.unibo.monopoly.model.transactions.impl;

import it.unibo.monopoly.model.transactions.api.RentOption;

public record RentOptionImpl(String title,String description,int price) implements RentOption{

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

    public static RentOptionImpl baseRentOption(int baseRent) {
        return new RentOptionImpl(BASE_RENT_TITLE, "", baseRent);
    }
}
