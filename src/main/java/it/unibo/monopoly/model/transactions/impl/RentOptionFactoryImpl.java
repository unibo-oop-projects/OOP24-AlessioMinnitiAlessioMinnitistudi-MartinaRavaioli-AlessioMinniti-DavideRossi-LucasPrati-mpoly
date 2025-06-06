package it.unibo.monopoly.model.transactions.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.RentOptionFactory;

/**
 * Implementation of the {@link RentOptionFactory} interface.
 */
public final class RentOptionFactoryImpl implements RentOptionFactory {

    /**
     * Creates a new {@link RentOptionFactoryImpl}.
     */
    public RentOptionFactoryImpl() { 
        //intentional empty constructor
    }

    @Override
    public RentOption allDeedsOfGroupWithSameOwner(final int startRent) {
        return new RentOptionImpl("All properties owned", 
        "If a player owns all the properties of a specific group rent is doubled",
        startRent * 2,
        (deeds, o) -> deeds
                        .stream()
                        .allMatch(d -> d.isOwned() && o.equals(d.getOwnerId())));
    }

    @Override
    public List<RentOption> progressivelyIncreasingPrice(final int startRent, final int multiplyFactor, final int nStations) {
        return Stream.iterate(Pair.of(1, startRent), r -> Pair.of(r.getLeft() + 1, r.getRight() * multiplyFactor))
                    .limit(nStations)
                    .map(p -> new RentOptionImpl("Own " + p.getLeft() + " properties of the same group", 
                    "",
                    p.getRight(),
                    (deeds, owner) -> deeds.stream()
                                            .filter(d -> d.isOwned() && owner.equals(d.getOwnerId()))
                                            .count() >= p.getLeft()))
                    .collect(Collectors.toList());
    }

    @Override
    public RentOption baseRentOption(final int baseRent) {
        return new RentOptionImpl("Base rent", "", baseRent, (deeds, ownerId) -> true);
    }
}
