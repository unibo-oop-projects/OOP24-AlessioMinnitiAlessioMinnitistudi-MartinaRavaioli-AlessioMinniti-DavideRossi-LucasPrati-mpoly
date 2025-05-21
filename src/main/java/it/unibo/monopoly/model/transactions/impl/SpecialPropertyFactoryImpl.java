package it.unibo.monopoly.model.transactions.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.RentOptionFactory;
import it.unibo.monopoly.model.transactions.api.SpecialPropertyFactory;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public class SpecialPropertyFactoryImpl implements SpecialPropertyFactory{

    private final TurnationManager turnationManager;
    private final RentOptionFactory f = new RentOptionFactoryImpl();

    public SpecialPropertyFactoryImpl(TurnationManager tunrnationmanager){
        this.turnationManager = tunrnationmanager;

    }

    
    @Override
    public TitleDeed Station(Group group, String name, int salePrice, Function<Integer, Integer> mortgageFunction,
                                int baseRent) {

        List<RentOption> rentO = f.progressivelyIncreasingPrice(50, 2, 4);
        List<RentOption> rent = rentO.subList(1, rentO.size());
        return new BaseTitleDeed(group, name, salePrice, mortgageFunction, baseRent, rent);
        
    }

    @Override
    public TitleDeed Society(Group group, String name, int salePrice, Function<Integer, Integer> mortgageFunction,
            int baseRent) {
        List<RentOption> rentO = f.progressivelyIncreasingPrice(5, 2, 2);
        List<RentOption> rent = rentO.subList(1, rentO.size());
        return new TitleDeed() {

            private final TitleDeed titleDeed = new BaseTitleDeed(group, name, salePrice, mortgageFunction, baseRent,rent);
            private final TurnationManager tunrM = turnationManager;
            
    
            @Override
            public String getOwner() {
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
            public Group getGroup() {
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
                return titleDeed.getRent(groupTitleDeeds) * (tunrM.moveByDices().stream().mapToInt(Integer::intValue).sum());
            }
    
            @Override
            public List<RentOption> getRentOptions() {
                return titleDeed.getRentOptions();
            }

            @Override
            public boolean isOwned() {
                return titleDeed.isOwned();
            }

            @Override
            public int housePrice() {
                return titleDeed.housePrice();
            }

            @Override
            public int houseNum() {
                return titleDeed.houseNum();
            }
                
            };
    
    }

}
