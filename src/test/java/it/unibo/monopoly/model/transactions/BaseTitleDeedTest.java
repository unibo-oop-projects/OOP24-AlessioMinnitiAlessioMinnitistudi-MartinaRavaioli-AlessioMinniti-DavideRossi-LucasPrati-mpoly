package it.unibo.monopoly.model.transactions;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.RentOptionImpl;

class BaseTitleDeedTest {

    private static final int OWNER_ID = 1;
    private static final int SECOND_OWNER_ID = 2;
    private static final Group GROUP_NAME = Group.GREEN;
    private static final String TITLE_DEED_NAME = "vicolo corto";
    private static final int SALE_PRICE = 50;
    private static final Function<Integer, Integer> MORTGAGE_PRICE_FUNCTION = salePrice -> {
        return salePrice / 10;
    };
    private static final int BASE_RENT_PRICE = 2;
    private TitleDeed deed;



    @BeforeEach
    void setUp() {
        deed = new BaseTitleDeed(GROUP_NAME, TITLE_DEED_NAME, SALE_PRICE, MORTGAGE_PRICE_FUNCTION, BASE_RENT_PRICE);
    }


    @Test
    void testIsOwnedFalseIfNoOwnerIsSet() {
        assertFalse(deed.isOwned());
    }

    @Test
    void testGetOwnerThrowsExceptionIfNoOwnerIsSet() {
        final IllegalStateException noOwnerException = assertThrows(
            IllegalStateException.class, 
            deed::getOwnerId
        );
        testExceptionFormat(noOwnerException);
    }

    @Test
    void testSetOwnerSuccessful() {
        deed.setOwner(OWNER_ID);
        assertTrue(deed.isOwned());
        assertEquals(OWNER_ID, deed.getOwnerId());
    }

    @Test
    void setOwnerWhenOwnerAlreadySetThrowsException() {
        deed.setOwner(OWNER_ID);
        final IllegalStateException ownerAlreadySetException = assertThrows(
            IllegalStateException.class, 
            () -> deed.setOwner(SECOND_OWNER_ID)
        );
        testExceptionFormat(ownerAlreadySetException);
    }

    @Test
    void removeOwnerWhenNoOwnerIsSetThrowsException() {
        final IllegalStateException noOwnerSetException = assertThrows(
            IllegalStateException.class,
            deed::removeOwner
        );
        testExceptionFormat(noOwnerSetException);
    }

    @Test
    void removeOwnerSuccessful() {
        deed.setOwner(OWNER_ID);
        assertTrue(deed.isOwned());
        assertEquals(OWNER_ID, deed.getOwnerId());
        deed.removeOwner();
        assertFalse(deed.isOwned());
    }

   @Test
   void testGetGroup() {
        assertEquals(GROUP_NAME, deed.getGroup());
   }

   @Test
   void testGetTitleDeedName() {
        assertEquals(TITLE_DEED_NAME, deed.getName());
   } 

   @Test
   void testGetTitleDeedSalePrice() {
        assertEquals(SALE_PRICE, deed.getSalePrice());
   }

   @Test
   void testGetTitleDeedMortgagePrice() {
        assertEquals(MORTGAGE_PRICE_FUNCTION.apply(SALE_PRICE), deed.getMortgagePrice());
   }

   @Test
   void testGetCorrectRentPrice() {
        deed.setOwner(OWNER_ID);
        assertEquals(BASE_RENT_PRICE, deed.getRent(Set.of()));
        final RentOption allPropertiesOwned = new RentOptionImpl("Si possiede tutte le proprietà del gruppo", 
                                                                "", 
                                                                BASE_RENT_PRICE * 2, 
                                                                (deeds, o) -> deeds.stream()
                                                                                .allMatch(d -> d.isOwned() 
                                                                                                && o.equals(d.getOwnerId())
                                                                                                )
                                                                );
        final TitleDeed shortStreetDeed = new BaseTitleDeed(GROUP_NAME,
                                        "vicolo corto", 
                                        SALE_PRICE, 
                                        MORTGAGE_PRICE_FUNCTION, 
                                        BASE_RENT_PRICE, 
                                        List.of(allPropertiesOwned)
                                    );
        final TitleDeed longStreetDeed = new BaseTitleDeed(GROUP_NAME, 
                                        "vicolo lungo",
                                        SALE_PRICE, 
                                        MORTGAGE_PRICE_FUNCTION, 
                                        BASE_RENT_PRICE, 
                                        List.of(allPropertiesOwned)
                                        );
        shortStreetDeed.setOwner(OWNER_ID);
        longStreetDeed.setOwner(OWNER_ID);
        assertEquals(BASE_RENT_PRICE * 2, shortStreetDeed.getRent(Set.of(longStreetDeed)));
    }

   @Test
   void testGetRentPricePassingTitleDeedsOfDifferentGroup() {

        final TitleDeed differentGroupTitleDeed = new BaseTitleDeed(Group.BLUE, 
                                                        "via dante", 
                                                        SALE_PRICE, 
                                                        MORTGAGE_PRICE_FUNCTION,
                                                        BASE_RENT_PRICE
                                                    );


        final IllegalArgumentException titleDeedsOfDifferentGroup = assertThrows(
            IllegalArgumentException.class,
            () -> deed.getRent(Set.of(differentGroupTitleDeed))
        );
        testExceptionFormat(titleDeedsOfDifferentGroup);
   }

   @Test
   void testGetAllRentOptions() {
        final List<RentOption> rentOptions = deed.getRentOptions();
        assertFalse(rentOptions.isEmpty());

        for (final RentOption rentOption : rentOptions) {
            assertNotNull(rentOption.getTitle());
            assertFalse(rentOption.getTitle().isBlank());
            assertNotNull(rentOption.getDescription());

            assertTrue(rentOption.getPrice() > 0);
        }
   }

    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }
}
