package it.unibo.monopoly.model.transactions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * Immutable view for a {@link TitleDeed} object.
 * The class wraps a copy of the object allowing for getter operations, but not 
 * setter operations.
 */
public final class ImmutableTitleDeedCopy implements TitleDeed {

    private final TitleDeed deed;


    /**
     * Creates a new {@link ImmutableTitleDeedCopy}.
     * @param deed the account to wrap and to regulate access to
     */
    public ImmutableTitleDeedCopy(final TitleDeed deed) {
        final List<RentOption> rentOptionCopy = new ArrayList<>(deed.getRentOptions());
        rentOptionCopy.removeFirst();
        this.deed = new BaseTitleDeed(deed.getGroup(), 
                                    deed.getName(),
                                    deed.getSalePrice(), 
                                    d -> deed.getMortgagePrice(), 
                                    deed.getRentOptions().getFirst().getPrice(),
                                    rentOptionCopy);
        if (deed.isOwned()) {
            this.deed.setOwner(deed.getOwner());
        }
    }

    @Override
    public String getOwner() {
        return this.deed.getOwner();
    }

    @Override
    public void setOwner(final String ownerName) {
        throw new UnsupportedOperationException("setOwner operation is not supported on an ImmutableTitleDeedView");
    }

    @Override
    public void removeOwner() {
        throw new UnsupportedOperationException("removeOwner operation is not supported on an ImmutableTitleDeedView");
    }

    @Override
    public Group getGroup() {
        return this.deed.getGroup();
    }

    @Override
    public String getName() {
        return this.deed.getName();
    }

    @Override
    public Integer getSalePrice() {
        return this.deed.getSalePrice();
    }

    @Override
    public Integer getMortgagePrice() {
        return this.deed.getMortgagePrice();
    }

    @Override
    public Integer getRent(final Set<TitleDeed> groupTitleDeeds) {
        return this.deed.getRent(groupTitleDeeds);
    }

    @Override
    public List<RentOption> getRentOptions() {
        return this.deed.getRentOptions();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((deed == null) ? 0 : deed.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ImmutableTitleDeedCopy other = (ImmutableTitleDeedCopy) obj;
        if (deed == null) {
            if (other.deed != null) {
                return false;
            }
        } else if (!deed.equals(other.deed)) {
            return false;
        }
        return true;
    }

    @Override
    public int housePrice() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'housePrice'");
    }

    @Override
    public int houseNum() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'houseNum'");
    }

    @Override
    public boolean isOwned() {
        return this.deed.isOwned();
    }

}
