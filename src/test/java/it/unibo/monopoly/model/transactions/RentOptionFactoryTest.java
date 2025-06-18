package it.unibo.monopoly.model.transactions;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.RentOptionFactory;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.rentoption.RentOptionFactoryImpl;
import it.unibo.monopoly.model.transactions.impl.titledeed.BaseTitleDeed;

class RentOptionFactoryTest {

    private static final int START_RENT = 5;
    private static final int OWNER_ID = 1;
    private static final int MULTIPLY_FACTOR = 2;
    private static final int N_STATIONS = 4;
    private static final Group GROUP_TYPE = Group.STATION;
    private static final int DICE = 1;
    private RentOptionFactory rentOptionFactory;

    @BeforeEach
    void setUp() {
        rentOptionFactory = new RentOptionFactoryImpl();
    }

    @Test
    void allTitleDeedsOfAGroupOwned() {
        final RentOption doubledPrice = rentOptionFactory.allDeedsOfGroupWithSameOwner(START_RENT);
        final List<TitleDeed> deeds = List.of(
            new BaseTitleDeed(Group.GREEN, "vicolo corto", START_RENT * 10, s -> s / 2, START_RENT, List.of(doubledPrice)),
            new BaseTitleDeed(Group.GREEN, "vicolo stretto", START_RENT * 10, s -> s / 2, START_RENT, List.of(doubledPrice))
        );
        deeds.get(0).setOwner(OWNER_ID);
        assertFalse(doubledPrice.canBeApplied(Set.copyOf(deeds), OWNER_ID, null));
        assertEquals(START_RENT, deeds.get(0).getRent(Set.copyOf(deeds), DICE));
        deeds.get(deeds.size() - 1).setOwner(OWNER_ID);
        assertTrue(doubledPrice.canBeApplied(Set.copyOf(deeds), OWNER_ID, null));
        assertEquals(START_RENT * 2, deeds.get(0).getRent(Set.copyOf(deeds), DICE));
    }

    @Test
    void priceIncreasesProgressivelyBasedOnOwnedTitleDeeds() {
        final List<RentOption> options = rentOptionFactory.progressivelyIncreasingPrice(START_RENT, MULTIPLY_FACTOR, N_STATIONS);
        final List<TitleDeed> deeds = List.of(
            new BaseTitleDeed(GROUP_TYPE, "stazione nord", START_RENT * 10, s -> s / 2, START_RENT, options),
            new BaseTitleDeed(GROUP_TYPE, "stazione sud", START_RENT * 10, s -> s / 2, START_RENT, options),
            new BaseTitleDeed(GROUP_TYPE, "stazione est", START_RENT * 10, s -> s / 2, START_RENT, options),
            new BaseTitleDeed(GROUP_TYPE, "stazione ovest", START_RENT * 10, s -> s / 2, START_RENT, options)
        );

        assertTrue(Stream.iterate(Pair.of(0, START_RENT), p -> Pair.of(p.getLeft() + 1, p.getRight() * MULTIPLY_FACTOR))
                    .limit(options.size())
                    .map(p -> Pair.of(options.get(p.getLeft()), p.getRight()))
                    .allMatch(p -> p.getRight().equals(p.getLeft().getPrice())));

        for (int i = 0; i < deeds.size(); i++) {
            final TitleDeed deed = deeds.get(i);
            deed.setOwner(OWNER_ID);
            for (int j = 0; j < options.size(); j++) {
                if (j <= i) {
                    assertTrue(options.get(j).canBeApplied(Set.copyOf(deeds), OWNER_ID, null));
                } else {
                    assertFalse(options.get(j).canBeApplied(Set.copyOf(deeds), OWNER_ID, null));
                }
            }
        }
    }
}
