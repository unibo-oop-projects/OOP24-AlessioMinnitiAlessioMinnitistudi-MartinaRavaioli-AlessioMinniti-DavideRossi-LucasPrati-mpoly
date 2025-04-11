package it.unibo.monopoly.model.transactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.impl.BankAccountImpl;
import it.unibo.monopoly.model.transactions.impl.BankImpl;

/*
 * Tests to verify correct functionality of
 * class Bank.
 */
final class BankTest {

    private static final int ARBITRARY_AMOUNT = 100;
    private static final String INEXSISTENT = "";
    private static final String PLAYER1_NAME = "Alice";
    private static final BankAccount PLAYER1_ACCOUNT = new BankAccountImpl(ARBITRARY_AMOUNT);
    private static final String PROPERTY_NAME = "Vicolo corto";
    

    //rimepire con property valide
    private static final Property VALID_PROPERTY = null;
    private static final Property INEXISTENT_PROPERTY = null;
    private Bank bank;


    @BeforeEach
    void setUp() {
        bank = new BankImpl();
    }

    @Test 
    void payRentInexistentPlayer() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.payRent(INEXSISTENT, VALID_PROPERTY)
        );
        testExceptionFormat(inexistentPropertyException);   
    }

    @Test
    void payRentInexistentProperty() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.payRent(PLAYER1_NAME, INEXISTENT_PROPERTY)
        );
        testExceptionFormat(inexistentPropertyException); 
    }

    @Test
    void payRentOfPropertyWithNoOwner() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.payRent(PLAYER1_NAME, VALID_PROPERTY)
        );
        testExceptionFormat(inexistentPropertyException); 
    }

    @Test
    void payRentForPropertyPossessedByThePayer() {
        bank.buyProperty(PROPERTY_NAME, PLAYER1_NAME);
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.payRent(PLAYER1_NAME, VALID_PROPERTY)
        );
        testExceptionFormat(inexistentPropertyException); 
    }

    @Test 
    void payRentViolatesWithdrawConditionsOfThePayerBankAccount() {
        throw new UnsupportedOperationException("payRentViolatesWithdrawConditionsOfThePayerBankAccount test not yet implemented");
    }

    @Test
    void payRentSuccessful() {
        throw new UnsupportedOperationException("payRentViolatesWithdrawConditionsOfThePayerBankAccount test not yet implemented");
    }

    @Test
    void buyInexistentProperty() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.buyProperty(INEXSISTENT, PLAYER1_NAME)
        );
        testExceptionFormat(inexistentPropertyException);
    }

    @Test
    void buyPropertyForInexistentPlayer() {
        final IllegalArgumentException inexistentPlayerException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.buyProperty(PROPERTY_NAME, INEXSISTENT)
        );
        testExceptionFormat(inexistentPlayerException);
    }

    @Test
    void buyAlreadyBoughtProperty() {
        bank.buyProperty(PROPERTY_NAME, PLAYER1_NAME);
        final IllegalStateException alreadyBoughtPropertyException = assertThrows(
            IllegalStateException.class,
            () -> bank.buyProperty(PROPERTY_NAME, PLAYER1_NAME)
        );
        testExceptionFormat(alreadyBoughtPropertyException);
    }

    @Test
    void buyingPropertyViolatesBuyerWithdrawConditions() {
        throw new UnsupportedOperationException("buyingPropertyViolatesBuyerWithdrawConditions test not yet implemented");
    }

    @Test 
    void buyingPropertySuccessful() {
        throw new UnsupportedOperationException("buyingPropertySuccessful test not yet implemented");
    }

    @Test
    void sellInexistentProperty() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.sellProperty(INEXSISTENT)
        );
        testExceptionFormat(inexistentPropertyException);
    }

    @Test
    void sellPropertyWithNoOwner() {
        throw new UnsupportedOperationException("sellPropertyWithNoOwner test not yet implemented");
    }

    @Test
    void sellPropertySuccessful() {
        throw new UnsupportedOperationException("sellPropertySuccessful test not yet implemented");
    }

    @Test
    void getBankAccountOfInexistentPlayer() {
        final IllegalArgumentException inexistentPlayerException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.getBankAccount(INEXSISTENT)
        );
        testExceptionFormat(inexistentPlayerException);
    }

    @Test
    void getBankAccountOfPlayer() {
        final BankAccount account = bank.getBankAccount(PLAYER1_NAME);
        assertEquals(PLAYER1_ACCOUNT, account);
    }

    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }
} 
