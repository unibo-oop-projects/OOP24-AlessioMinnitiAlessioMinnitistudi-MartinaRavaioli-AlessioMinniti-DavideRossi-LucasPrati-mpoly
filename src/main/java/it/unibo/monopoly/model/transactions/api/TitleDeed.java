package it.unibo.monopoly.model.transactions.api;

public interface TitleDeed {

    String getOwner();

    void setOwner(String ownerName);

    void removeOwner();

    String getGroup();

    String getName();

    Integer getSalePrice();

    Integer getMortgagePrice();

}
