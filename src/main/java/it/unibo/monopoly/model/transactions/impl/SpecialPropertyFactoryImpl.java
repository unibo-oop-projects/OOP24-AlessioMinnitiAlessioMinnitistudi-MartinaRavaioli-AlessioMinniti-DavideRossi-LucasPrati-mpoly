package it.unibo.monopoly.model.transactions.impl;

import java.util.List;
import java.util.Set;
import java.util.Collection;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.RentOptionFactory;
import it.unibo.monopoly.model.transactions.api.SpecialPropertyFactory;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * implementation of the special property factory interface.
 */
public final class SpecialPropertyFactoryImpl implements SpecialPropertyFactory {

    private final RentOptionFactory f = new RentOptionFactoryImpl();

    @Override
    public TitleDeed station(final String name) {

        final int startRent = 50;
        final int salePrice = 200;
        final List<RentOption> rentO = f.progressivelyIncreasingPrice(startRent, 2, 4);
        final List<RentOption> rent = rentO.subList(1, rentO.size());
        return new BaseTitleDeed(Group.STATION, name, salePrice, p -> p / 4 * 3, startRent, rent);
    }

    @Override
    public TitleDeed society(final String name) {
        final int salePrice = 120;
        final int startFactor = 5;
        final List<RentOption> rentO = f.progressivelyIncreasingPrice(startFactor, 2, 2);
        final List<RentOption> rent = rentO.subList(1, rentO.size());
        return new TitleDeed() {

            private final TitleDeed titleDeed = new BaseTitleDeed(Group.SOCIETY, name, salePrice,
                                                             p -> p / 4 * 3, startFactor, rent);

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
            public Integer getRent(final Set<TitleDeed> groupTitleDeeds, final Collection<Integer> dices) {
                return titleDeed.getRent(groupTitleDeeds, dices) * dices.stream().mapToInt(Integer::intValue).sum();
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
