package it.unibo.monopoly.model.transactions.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TitleDeed {

    Optional<String> getOwner();

    void setOwner(String ownerName);

    void removeOwner();

    String getGroup();

    String getName();

    Integer getSalePrice();

    Integer getMortgagePrice();

    Integer getRent(Set<TitleDeed> groupTitleDeeds);

    List<RentOption> getRentOptions();

}
