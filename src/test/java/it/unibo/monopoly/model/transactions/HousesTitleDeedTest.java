package it.unibo.monopoly.model.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.impl.BuildablePropertyImpl;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.gameboard.impl.ImmutableProperty;
import it.unibo.monopoly.model.gameboard.impl.NormalPropertyImpl;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.rentoption.RentOptionFactoryImpl;
import it.unibo.monopoly.model.transactions.impl.titledeed.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.titledeed.TitleDeedWithHouses;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
/**
 * test house title deeds
 */
public class HousesTitleDeedTest {
        private static final Group GROUP_NAME = Group.GREEN;
    private static final String TITLE_DEED_NAME = "vicolo corto";
    private static final int SALE_PRICE = 50;
    private static final int DICE_THROW = 1;
    private static final Function<Integer, Integer> MORTGAGE_PRICE_FUNCTION = salePrice -> {
        return salePrice / 10;
    };
    private static final String FOUR_HOUSES_OPTION = "4 case";
    private static final String THREE_HOUSES_OPTION = "3 case";
    private static final int BASE_RENT_PRICE = 2;
    private static final Function<Integer, Integer> HOUSE_PRICE = d -> 50;
    private static final Function<Integer, Integer> HOTEL_PRICE = d -> 100;
    private BuildablePropertyImpl referencedProperty;
    private ImmutableProperty property;
    private TitleDeed deed;
    private TitleDeed decorated;
    private List<RentOption> housesOptions;

    @BeforeEach
    void setUp() {
        referencedProperty = new BuildablePropertyImpl(
        new NormalPropertyImpl(TITLE_DEED_NAME, new PositionImpl(4), GROUP_NAME));
        property = new ImmutableProperty(referencedProperty);
        housesOptions = new RentOptionFactoryImpl().housesAndHotelsOptions(BASE_RENT_PRICE, 4, true);
        decorated = new BaseTitleDeed(GROUP_NAME, TITLE_DEED_NAME, SALE_PRICE, MORTGAGE_PRICE_FUNCTION, BASE_RENT_PRICE);
        deed = new TitleDeedWithHouses(decorated,
        housesOptions,
        property,
        HOUSE_PRICE,
        HOTEL_PRICE);
    }

    @Test
    void applyHouseCostFunction() {
        final int houseCost = deed.getHousePrice();
        assertEquals(HOUSE_PRICE.apply(SALE_PRICE), houseCost);
    }

    @Test
    void applyHotelCostFunction() {
        final int hotelCost = deed.getHotelPrice();
        assertEquals(HOTEL_PRICE.apply(SALE_PRICE), hotelCost);
    }

    @Test
    void rentOptionsRetrievedCorrectly() {
        final List<RentOption> options = deed.getRentOptions();
        assertEquals(decorated.getRentOptions().size() + housesOptions.size(), options.size());
        final List<RentOption> test = new ArrayList<>(decorated.getRentOptions());
        test.addAll(housesOptions);
        assertEquals(test, options);
    }

    @Test
    void rentAfterPlacingHouse() {
        deed.setOwner(1);
        referencedProperty.buildHouse();
        final int rent = deed.getRent(Set.of(), DICE_THROW);
        assertEquals(housesOptions.getFirst().getPrice(), rent);
    }

    @Test 
    void rentAfterPlacingHotel() {
        deed.setOwner(1);
        referencedProperty.buildHouse();
        referencedProperty.buildHouse();
        referencedProperty.buildHouse();
        referencedProperty.buildHouse();
        referencedProperty.buildHotel();
        final int rent = deed.getRent(Set.of(), DICE_THROW);
        assertEquals(housesOptions.getLast().getPrice(), rent);
    }

    @Test 
    void rentWithNoHouses() {
        deed.setOwner(1);
        final int rent = deed.getRent(Set.of(), DICE_THROW);
        assertEquals(decorated.getRent(Set.of(), DICE_THROW), rent);
    }

    @Test
    void rentAfterRemovingHotel() throws IllegalAccessException {
        deed.setOwner(1);
        referencedProperty.buildHouse();
        referencedProperty.buildHouse();
        referencedProperty.buildHouse();
        referencedProperty.buildHouse();
        referencedProperty.buildHotel();
        referencedProperty.deleteHotel();
        final int rent = deed.getRent(Set.of(), DICE_THROW);
        final RentOption fourHouses = housesOptions.stream().filter(r -> FOUR_HOUSES_OPTION.equals(r.getTitle())).findFirst().get();
        assertEquals(fourHouses.getPrice(), rent);
    }

    @Test
    void rentAfterRemovingHouse() throws IllegalAccessException {
        deed.setOwner(1);
        referencedProperty.buildHouse();
        referencedProperty.buildHouse();
        referencedProperty.buildHouse();
        referencedProperty.buildHouse();
        referencedProperty.deleteHouse();
        final int rent = deed.getRent(Set.of(), DICE_THROW);
        final RentOption threeHouses = housesOptions.stream().filter(r -> THREE_HOUSES_OPTION.equals(r.getTitle())).findFirst().get();
        assertEquals(threeHouses.getPrice(), rent);
    }
}
