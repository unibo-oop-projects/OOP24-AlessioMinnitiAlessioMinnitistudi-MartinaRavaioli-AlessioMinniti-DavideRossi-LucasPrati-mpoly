package it.unibo.monopoly.model.transactions.impl.titledeed;

import java.util.List;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

abstract class TitleDeedDecorator implements TitleDeed {

    private final TitleDeed decorated;

    protected TitleDeedDecorator(final TitleDeed decorated) {
        this.decorated = decorated;
    }

    protected TitleDeed getDecorated() {
        return this.decorated;
    }

    @Override
    public boolean isOwned() {
        return decorated.isOwned();
    }

    @Override
    public int getOwnerId() {
        return decorated.getOwnerId();
    }

    @Override
    public void setOwner(final int ownerId) {
        decorated.setOwner(ownerId);
    }

    @Override
    public void removeOwner() {
        decorated.removeOwner();
    }

    @Override
    public Group getGroup() {
        return decorated.getGroup();
    }

    @Override
    public String getName() {
        return decorated.getName();
    }

    @Override
    public Integer getSalePrice() {
        return decorated.getSalePrice();
    }

    @Override
    public Integer getMortgagePrice() {
        return decorated.getMortgagePrice();
    }

    @Override
    public Integer getRent(final Set<TitleDeed> groupTitleDeeds, final int diceThrow) {
        return decorated.getRent(groupTitleDeeds, diceThrow);
    }

    @Override
    public List<RentOption> getRentOptions() {
        return decorated.getRentOptions();
    }

    @Override
    public int getHousePrice() {
        return decorated.getHousePrice();
    }

    @Override
    public int getHotelPrice() {
        return decorated.getHotelPrice();
    }
}
