package it.unibo.monopoly.model.transactions.api;

import java.util.List;
import java.util.Set;

public interface RentOption {

    String getTitle();

    String getDescription();

    int getPrice();

    boolean canBeApplied(Set<TitleDeed> groupDeeds);
}
