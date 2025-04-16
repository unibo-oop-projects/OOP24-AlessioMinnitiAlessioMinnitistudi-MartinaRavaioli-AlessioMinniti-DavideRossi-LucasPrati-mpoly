package it.unibo.monopoly.model.transactions.impl;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import it.unibo.monopoly.model.transactions.api.TitleDeed;

public class BaseTitleDeed implements TitleDeed{

    private final String group;
    private final String name;
    private final int salePrice;
    private final Function<Integer,Integer> mortageFunction; 
    private Optional<String> owner = Optional.empty();

    public BaseTitleDeed(String group, String name, int salePrice, Function<Integer, Integer> mortageFunction) {
        this.group = group;
        this.name = name;
        this.salePrice = salePrice;
        this.mortageFunction = mortageFunction;
    }

    @Override
    public Optional<String> getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String ownerName) {

        Objects.requireNonNull(ownerName);

        if(owner.isPresent()) {
            throw new IllegalStateException("Cannot set a new owner for the title deed because the owner" + owner.get() + " already owns it");
        }

        owner = Optional.of(ownerName);
    }

    @Override
    public void removeOwner() {
        if(owner.isEmpty()) {
            throw new IllegalStateException("Cannot remove the owner because no owner is set");
        }
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getSalePrice() {
        return this.salePrice;
    }

    @Override
    public Integer getMortgagePrice() {
        return this.mortageFunction.apply(this.salePrice);
    }

}
