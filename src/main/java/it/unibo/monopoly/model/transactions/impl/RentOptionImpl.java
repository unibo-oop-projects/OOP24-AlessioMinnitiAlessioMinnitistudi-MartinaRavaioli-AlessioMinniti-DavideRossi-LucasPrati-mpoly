package it.unibo.monopoly.model.transactions.impl;

import it.unibo.monopoly.model.transactions.api.RentOption;

public record RentOptionImpl(String title,String description,int price) implements RentOption{

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

}
