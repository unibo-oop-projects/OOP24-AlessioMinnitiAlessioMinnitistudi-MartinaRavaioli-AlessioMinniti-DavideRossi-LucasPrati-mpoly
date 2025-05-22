package it.unibo.monopoly.model.transactions.impl;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.RentOptionFactory;
import it.unibo.monopoly.model.transactions.api.SpecialPropertyFactory;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * implementation of the special property factory interface.
 */
public final class SpecialPropertyFactoryImpl implements SpecialPropertyFactory {

    private final TurnationManager turnationManager;
    private final RentOptionFactory f = new RentOptionFactoryImpl();

    /**
     * constructor for the factory tha takes the turnation manager.
     * for the rent function of socuìiety tile
     * @param tunrnationmanager
     */
    public SpecialPropertyFactoryImpl(final TurnationManager tunrnationmanager) {
        this.turnationManager = tunrnationmanager;

    }

    @Override
    public TitleDeed station(final Group group, final String name, final int salePrice, 
                                final Function<Integer, Integer> mortgageFunction,
                                final int baseRent) {

        final int startRent = 50;
        final List<RentOption> rentO = f.progressivelyIncreasingPrice(startRent, 2, 4);
        final List<RentOption> rent = rentO.subList(1, rentO.size());
        return new BaseTitleDeed(group, name, salePrice, mortgageFunction, baseRent, rent);
    }

    @Override
    public TitleDeed society(final Group group, final String name, final int salePrice, 
                                final Function<Integer, Integer> mortgageFunction,
                                final int baseRent) {
        final int startRent = 5;
        final List<RentOption> rentO = f.progressivelyIncreasingPrice(startRent, 2, 2);
        final List<RentOption> rent = rentO.subList(1, rentO.size());
        return new TitleDeed() {

            private final TitleDeed titleDeed = new BaseTitleDeed(group, name, salePrice, mortgageFunction, baseRent, rent);
            private final TurnationManager tunrM = turnationManager;

            @Override
            public String getOwner() {
                return titleDeed.getOwner();
            }

            @Override
            public void setOwner(final String ownerName) {
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
            public Integer getRent(final Set<TitleDeed> groupTitleDeeds) {
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
