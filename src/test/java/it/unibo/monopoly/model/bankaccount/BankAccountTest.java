package it.unibo.monopoly.model.bankaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.impl.BankAccountImpl;


class BankAccountTest {

    private static final int INITIAL_BALANCE = 0;
    private static final int AMOUNT = 100;
    private BankAccount bankAccount;


    @BeforeEach
    void setUp() {
        bankAccount = new BankAccountImpl(INITIAL_BALANCE);
    }

    @Test
    void createAccountWithNegativeBalance() {
        final IllegalArgumentException negativeAmountException = assertThrows(
            IllegalArgumentException.class,
            () -> bankAccount = new BankAccountImpl(-(INITIAL_BALANCE + 1)),
            "Creating a bankAccount with a negative balance should have thrown an error");
        testExceptionFormat(negativeAmountException);
    }

    @Test
    void checkInitialBalance() {
        assertEquals(bankAccount.getBalance(), INITIAL_BALANCE);
    }

    @Test
    void depositPositiveAmount() {
        bankAccount.deposit(AMOUNT);
        assertEquals(bankAccount.getBalance(), INITIAL_BALANCE + AMOUNT);
        assertTrue(bankAccount.isAccountValid());
    }

    @Test 
    void depositNegativeAmount() {
        final IllegalArgumentException negativeAmountException = assertThrows(
            IllegalArgumentException.class,
            () -> bankAccount.deposit(-AMOUNT),
            "Depositing a negative amount of money should have thrown an error");
        testExceptionFormat(negativeAmountException);
    }

    @Test
    void withdrawNegativeAmount() {
        final IllegalArgumentException negativeAmountException = assertThrows(
            IllegalArgumentException.class,
            () -> bankAccount.withdraw(-AMOUNT),
            "Withdrawing a negative amount of money should have thrown an error");
        testExceptionFormat(negativeAmountException);
    }

    @Test
    void withdrawLessThanBalance() {
        bankAccount.deposit(AMOUNT * 2);
        final int previousBalance = bankAccount.getBalance();
        bankAccount.withdraw(AMOUNT);
        assertEquals(bankAccount.getBalance(), previousBalance - AMOUNT);
        assertTrue(bankAccount.isAccountValid());
    }

    @Test
    void withdrawMoreThanBalance() {
        final int withdrawAmount = bankAccount.getBalance() + 1;
        bankAccount.withdraw(withdrawAmount);
        assertEquals(bankAccount.getBalance(), INITIAL_BALANCE - withdrawAmount);
        assertFalse(bankAccount.isAccountValid());
    }

    @Test 
    void withdrawEqualToBalance() {
        bankAccount.deposit(AMOUNT);
        bankAccount.withdraw(bankAccount.getBalance());
        assertEquals(bankAccount.getBalance(), 0);
        assertFalse(bankAccount.isAccountValid());
    }


    @Test
    void checkAccountInvalidAfterInsufficientDeposit() {
        bankAccount.withdraw(bankAccount.getBalance() + AMOUNT);
        bankAccount.deposit(AMOUNT / 2);
        assertEquals(bankAccount.getBalance(), -AMOUNT + (AMOUNT / 2));
        assertFalse(bankAccount.isAccountValid());
    }

    @Test
    void checkAccountInvalidAfterSufficientDeposit() {
        bankAccount.withdraw(bankAccount.getBalance() + AMOUNT);
        bankAccount.deposit(AMOUNT * 2);
        assertEquals(bankAccount.getBalance(), -AMOUNT + AMOUNT * 2);
        assertTrue(bankAccount.isAccountValid());
    }

    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }

}
