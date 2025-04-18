package it.unibo.monopoly.model.transactions.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.Sets;

import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

public class BaseTitleDeed implements TitleDeed{

    private final String group;
    private final String name;
    private final int salePrice;
    private final Function<Integer,Integer> mortageFunction; 
    private Optional<String> owner = Optional.empty();
    private final List<RentOption> rentOptions;

    public BaseTitleDeed(String group, String name, int salePrice, Function<Integer, Integer> mortageFunction, int baseRent) {
        this.group = group;
        this.name = name;
        this.salePrice = salePrice;
        this.mortageFunction = mortageFunction;
        this.rentOptions = new ArrayList<>(List.of(RentOptionImpl.baseRentOption(baseRent)));
    }

    public BaseTitleDeed(String group, String name, int salePrice, Function<Integer, Integer> mortageFunction, int baseRent, List<RentOption> additionalRentOptions) {
        this(group, name, salePrice, mortageFunction, baseRent);
        this.rentOptions.addAll(additionalRentOptions);
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

    @Override
    public Integer getRent(Set<TitleDeed> groupTitleDeeds) {
        if(!groupTitleDeeds.stream().allMatch(d -> d.getGroup().equals(this.group))) {
            throw new IllegalArgumentException("The list of title deeds contains deeds that are not part of the group " + this.group + ", the group of this title deed");
        }

        Set<TitleDeed> allDeedsOfGroup = Sets.union(Set.of(this), groupTitleDeeds);

        return this.rentOptions.stream().filter(op -> op.canBeApplied(allDeedsOfGroup)).mapToInt(op-> op.getPrice()).max().getAsInt();
    }

    @Override
    public List<RentOption> getRentOptions() {
        return List.copyOf(this.rentOptions);
    }
}
