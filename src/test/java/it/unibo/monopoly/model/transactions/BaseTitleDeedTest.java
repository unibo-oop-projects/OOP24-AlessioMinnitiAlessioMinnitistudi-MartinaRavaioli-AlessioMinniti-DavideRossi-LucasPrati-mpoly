package it.unibo.monopoly.model.transactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;

class BaseTitleDeedTest {

    private TitleDeed deed;
    private static final String OWNER_NAME = "Bob";
    private static final String SECOND_OWNER_NAME = "Alice";
    private static final String GROUP_NAME = "viola";
    private static final String TITLE_DEED_NAME = "vicolo corto";
    private static final int SALE_PRICE = 50;
    private static final Function<Integer,Integer> MORTAGE_PRICE_FUNCTION = salePrice -> {
        return salePrice / 10;
    };


    @BeforeEach
    void setUp () {
        deed = new BaseTitleDeed(GROUP_NAME, TITLE_DEED_NAME, SALE_PRICE, MORTAGE_PRICE_FUNCTION);
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
        assertEquals(MORTAGE_PRICE_FUNCTION.apply(SALE_PRICE), deed.getMortgagePrice());
   }

   @Test
   void testGetCorrectRentPrice () {
        throw new UnsupportedOperationException("testGetCorrectRentPrice not yet implemented");
   }

   @Test
   void testGetRentPricePassingTitleDeedsOfDifferentGroup() {
        throw new UnsupportedOperationException("testGetRentPricePassingTitleDeedsOfDifferentGroup not yet implemented");
   }

   @Test
   void testGetAllRentOptions () {
        throw new UnsupportedOperationException("testGetAllRentOptions test not yet implemented");
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
