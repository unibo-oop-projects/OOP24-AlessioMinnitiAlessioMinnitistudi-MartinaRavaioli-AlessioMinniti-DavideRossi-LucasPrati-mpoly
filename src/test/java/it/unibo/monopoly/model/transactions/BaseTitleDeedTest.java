package it.unibo.monopoly.model.transactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;

class BaseTitleDeedTest {

    private TitleDeed deed;
    private static final String OWNER_NAME = "Bob";
    private static final String SECOND_OWNER_NAME = "Alice";
    private static final String GROUP_NAME = "viola";
    private static final String TITLE_DEED_NAME = "vicolo corto";
    private static final int SALE_PRICE = 50;
    private static final Function<Integer,Integer> MORTGAGE_PRICE_FUNCTION = salePrice -> {
        return salePrice / 10;
    };
    private static final int BASE_RENT_PRICE = 2;


    @BeforeEach
    void setUp () {
        deed = new BaseTitleDeed(GROUP_NAME, TITLE_DEED_NAME, SALE_PRICE, MORTGAGE_PRICE_FUNCTION,BASE_RENT_PRICE);
    }


    @Test
    void testGetOwnerReturnsNullIfNoOwnerIsSet () {
        assertNull(deed.getOwner());
    }

    @Test
    void testSetOwnerSuccessful () {
        deed.setOwner(OWNER_NAME);
        assertEquals(OWNER_NAME, deed.getOwner());
    }

    @Test
    void setOwnerWhenOwnerAlreadySetThrowsException () {
        deed.setOwner(OWNER_NAME);
        
        final IllegalStateException ownerAlreadySetException = assertThrows(
            IllegalStateException.class, 
            () -> deed.setOwner(SECOND_OWNER_NAME)
        );

        testExceptionFormat(ownerAlreadySetException);
    }

    @Test
    void removeOwnerWhenNoOwnerIsSetThrowsException () {
        final IllegalStateException noOwnerSetException = assertThrows(
            IllegalStateException.class,
            ()->deed.removeOwner()
        );
        testExceptionFormat(noOwnerSetException);
    }

    @Test
    void removeOwnerSuccessful () {
        deed.setOwner(OWNER_NAME);
        assertEquals(OWNER_NAME, deed.getOwner());
        deed.removeOwner();
        assertNull(deed.getOwner());
    }

    //change to robust group object
   @Test
   void testGetGroup () {
        assertEquals(GROUP_NAME, deed.getGroup());
   }
   
   @Test
   void testGetTitleDeedName () {
        assertEquals(TITLE_DEED_NAME, deed.getName());
   } 

   @Test
   void testGetTitleDeedSalePrice () {
        assertEquals(SALE_PRICE, deed.getSalePrice());
   }

   @Test
   void testGetTitleDeedMortgagePrice () {
        assertEquals(MORTGAGE_PRICE_FUNCTION.apply(SALE_PRICE), deed.getMortgagePrice());
   }

   @Test
   void testGetCorrectRentPrice () {
        throw new UnsupportedOperationException("testGetCorrectRentPrice not yet implemented");
   }

   @Test
   void testGetRentPricePassingTitleDeedsOfDifferentGroup() {

        final TitleDeed differentGroupTitleDeed = new BaseTitleDeed("marrone", "via dante", SALE_PRICE, MORTGAGE_PRICE_FUNCTION,BASE_RENT_PRICE);


        final IllegalArgumentException titleDeedsOfDifferentGroup = assertThrows(
            IllegalArgumentException.class,
            ()->deed.getRent(Set.of(differentGroupTitleDeed))
        );
        testExceptionFormat(titleDeedsOfDifferentGroup);
   }

   @Test
   void testGetAllRentOptions () {
        List<RentOption> rentOptions = deed.getRentOptions();
        assertTrue(rentOptions.size() >= 1);

        for (RentOption rentOption : rentOptions) {
            assertNotNull(rentOption.getTitle());
            assertFalse(rentOption.getTitle().isBlank());   
            assertNotNull(rentOption.getDescription());
            assertFalse(rentOption.getDescription().isBlank());   
            assertTrue(rentOption.getPrice() > 0);
        }
   }

   /*
   Verify if such control can be implemented with the enums implementation
   @Test
   void testGetRentPriceNotPassingAllTitleDeedsOfGroup () {

   }
    */

    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }
}
