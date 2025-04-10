package it.unibo.monopoly.model.transactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.impl.BankImpl;

/*
 * Tests to verify correct functionality of
 * class Bank.
 */
final class BankTest {

    private static final String INEXSISTENT = "";
    private static final String PLAYER1_NAME = "Alice";
    private static final String PLAYER2_NAME = "Bob";
    private static final String PROPERTY_NAME = "Vicolo corto";
    private static final int ARBITRARY_AMOUNT = 100;
    private Bank bank;


    @BeforeEach
    void setUp() {
        bank = new BankImpl();
    }

    @Test
    void paymentWithInexistentSender() {
        final IllegalArgumentException inexistentSenderException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.executePayment(INEXSISTENT, PLAYER2_NAME, ARBITRARY_AMOUNT)); 
        testExceptionFormat(inexistentSenderException);
    }

    @Test 
    void paymentWithInexistentReceiver() {
        final IllegalArgumentException inexistentReceiverException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.executePayment(PLAYER1_NAME, INEXSISTENT, ARBITRARY_AMOUNT));
        testExceptionFormat(inexistentReceiverException);
    }

    @Test
    void paymentWithZeroOrNegativeAmount() {
        final IllegalArgumentException negativeOrZeroAmountException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.executePayment(PLAYER1_NAME, PLAYER2_NAME, 0));
        testExceptionFormat(negativeOrZeroAmountException);
    }

    @Test
    void paymentViolatesSenderWithdrawConditions() {
        throw new UnsupportedOperationException("paymentViolatesSenderWithdrawConditions test not written");
    }

    @Test
    void paymentSuccessful() {
        final int player1InitialBalance = bank.getBankAccount(PLAYER1_NAME).getBalance();
        final int player2InitialBalance = bank.getBankAccount(PLAYER2_NAME).getBalance();

        bank.executePayment(PLAYER1_NAME, PLAYER2_NAME, ARBITRARY_AMOUNT);

        final int player1FinalBalance = bank.getBankAccount(PLAYER1_NAME).getBalance();
        final int player2FinalBalance = bank.getBankAccount(PLAYER2_NAME).getBalance();

        assertEquals(player1InitialBalance - ARBITRARY_AMOUNT, player1FinalBalance);
        assertEquals(player2InitialBalance + ARBITRARY_AMOUNT, player2FinalBalance);
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

    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }
} 
