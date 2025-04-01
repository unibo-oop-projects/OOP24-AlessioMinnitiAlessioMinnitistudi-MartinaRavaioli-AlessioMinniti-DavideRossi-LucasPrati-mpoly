package it.unibo.monopoly.model.bankaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void checkInitialBalance() {
        assertEquals(bankAccount.getBalance(), INITIAL_BALANCE);
    }

    @Test
    void depositPositiveAmount() {
        final int previousBalance = bankAccount.getBalance();
        bankAccount.deposit(AMOUNT);
        assertEquals(bankAccount.getBalance(), previousBalance + AMOUNT);
    }

    @Test 
    void depositNegativeAmount() {
        final IllegalArgumentException negativeAmountException = assertThrows(
            IllegalArgumentException.class,
            () -> bankAccount.deposit(-AMOUNT),
            "Depositing a negative amount of money should have thrown an error");
        testException(negativeAmountException);
    }

    @Test
    void withdrawNegativeAmount() {
        final IllegalArgumentException negativeAmountException = assertThrows(
            IllegalArgumentException.class,
            () -> bankAccount.withdraw(-AMOUNT),
            "Withdrawing a negative amount of money should have thrown an error");
        testException(negativeAmountException);
    }

    @Test
    void withdrawSufficientBalance() {
        bankAccount.deposit(AMOUNT * 2);
        final int previousBalance = bankAccount.getBalance();
        bankAccount.withdraw(AMOUNT);
        assertEquals(bankAccount.getBalance(), previousBalance - AMOUNT);
    }

    @Test 
    void withdrawWithInsufficientBalance() {
        final IllegalStateException insufficientBalanceException = assertThrows(
            IllegalStateException.class,
            () -> bankAccount.withdraw(AMOUNT * 100),
            "Withdrawing without having enough money should have thrown an error");
        testException(insufficientBalanceException);
    }

    private void testException(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }

}
