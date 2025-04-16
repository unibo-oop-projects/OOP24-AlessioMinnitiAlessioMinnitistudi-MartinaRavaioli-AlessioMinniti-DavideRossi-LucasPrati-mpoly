package it.unibo.monopoly.model.transactions.api;

import java.util.Optional;

public interface TitleDeed {

    Optional<String> getOwner();

    void setOwner(String ownerName);

    void removeOwner();

    String getGroup();

    String getName();

    Integer getSalePrice();

    Integer getMortgagePrice();

}
