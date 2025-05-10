package it.unibo.monopoly.model.gameboard.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import it.unibo.monopoly.model.gameboard.api.SpecialPropertyFactory;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public class SpecialPropertyFactoryImpl implements SpecialPropertyFactory{

    private final TurnationManager turnationManager;

    public SpecialPropertyFactoryImpl(TurnationManager tunrnationmanager){
        this.turnationManager = tunrnationmanager;

    }

    
    @Override
    public TitleDeed Station(String group, String name, int salePrice, Function<Integer, Integer> mortgageFunction,
                                int baseRent,List<RentOption> additionalRentOptions) {

        return new BaseTitleDeed(group, name, salePrice, mortgageFunction, baseRent, additionalRentOptions);
        
    }

    @Override
    public TitleDeed Society(String group, String name, int salePrice, Function<Integer, Integer> mortgageFunction,
            int baseRent,List<RentOption> additionalRentOptions) {
        return new TitleDeed() {

            private final TitleDeed titleDeed = new BaseTitleDeed(group, name, salePrice, mortgageFunction, baseRent, additionalRentOptions);
            private final TurnationManager tunrM = turnationManager;
    
            @Override
            public Optional<String> getOwner() {
                return titleDeed.getOwner();
            }
    
            @Override
            public void setOwner(String ownerName) {
                titleDeed.setOwner(ownerName);
            }
    
            @Override
            public void removeOwner() {
                titleDeed.removeOwner();
            }
    
            @Override
            public String getGroup() {
                return titleDeed.getGroup();
            }
    
            @Override
            public String getName() {
                return titleDeed.getName();
            }
    
            @Override
            public Integer getSalePrice() {
                return titleDeed.getSalePrice();
            }
    
            @Override
            public Integer getMortgagePrice() {
                return titleDeed.getMortgagePrice();
            }
    
            @Override
            public Integer getRent(Set<TitleDeed> groupTitleDeeds) {
                return titleDeed.getRent(groupTitleDeeds) * (tunrM.moveByDices().getLeft()+tunrM.moveByDices().getRight());
            }
    
            @Override
            public List<RentOption> getRentOptions() {
                return titleDeed.getRentOptions();
            }
                
            };
    
    }

}
