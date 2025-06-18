package it.unibo.monopoly.model.transactions.impl.rentoption;

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

    private static final double HOTEL_BOOST = 1.3;

    private PropertyRentOptionImpl housesOption(final int nHouse, final int baseRent) {
        return new PropertyRentOptionImpl(nHouse + " case",
        "Un giocatore possiede almeno " + nHouse + " case sul terreno",
        pricePerHouses(baseRent, nHouse),
        property -> property != null && property.isBuildable() && property.getNHouses() >= nHouse);
    }

    private PropertyRentOptionImpl hotelOption(final int baseRent, final int maxNumberOfHouses) {
        return new PropertyRentOptionImpl("Hotel",
        "Un giocatore ha piazzato un hotel sul terreno",
        priceWithHotel(baseRent, maxNumberOfHouses),
        property -> property != null && property.isBuildable() && property.hasHotel());
    }

    private int pricePerHouses(final int baseRent, final int nHouse) {
        return (int) Math.round(baseRent * Math.pow(nHouse + 1, 2));
    }

    private int priceWithHotel(final int baseRent, final int maxNumberOfHouses) {
        return (int) Math.round(pricePerHouses(baseRent, maxNumberOfHouses + 1) * HOTEL_BOOST);
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

    @Override
    public List<RentOption> housesAndHotelsOptions(final int baseRent, final int nHouses, final boolean withHotel) {
        final List<RentOption> options = Stream.iterate(1, r -> r + 1)
                                                    .limit(nHouses)
                                                    .map(p -> housesOption(p, baseRent))
                                                    .collect(Collectors.toList());
        if (withHotel) {
            options.add(hotelOption(baseRent, nHouses));
        }
        return options;
    }
}
