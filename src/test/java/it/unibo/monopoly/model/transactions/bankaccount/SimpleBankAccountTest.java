package it.unibo.monopoly.model.transactions.bankaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;


class SimpleBankAccountTest {

    private static final int ID = 21;
    private static final int INITIAL_BALANCE = 0;
    private static final int AMOUNT = 100;
    private BankAccount bankAccount;


    @BeforeEach
    void setUp() {
        bankAccount = new SimpleBankAccountImpl(ID, INITIAL_BALANCE);
    }

    @Test
    void createAccountWithNegativeBalance() {
        final IllegalArgumentException negativeAmountException = assertThrows(
            IllegalArgumentException.class,
            () -> bankAccount = new SimpleBankAccountImpl(ID, -(INITIAL_BALANCE + 1)),
            "Creating a bankAccount with a negative balance should have thrown an error");
        testExceptionFormat(negativeAmountException);
    }

    @Test
    void checkOwnerIdIsCorrect() {
        assertEquals(bankAccount.getID(), ID);
    }

    @Test
    void checkInitialBalance() {
        assertEquals(bankAccount.getBalance(), INITIAL_BALANCE);
    }

    @Test
    void depositPositiveAmount() {
        bankAccount.deposit(AMOUNT);
        assertEquals(bankAccount.getBalance(), INITIAL_BALANCE + AMOUNT);
        assertTrue(bankAccount.canContinue());
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
        assertTrue(bankAccount.canContinue());
    }

    @Test
    void checkAccountInvalidAfterSufficientDeposit() {
        bankAccount.withdraw(bankAccount.getBalance() + AMOUNT);
        bankAccount.deposit(AMOUNT * 2);
        assertEquals(bankAccount.getBalance(), -AMOUNT + AMOUNT * 2);
        assertTrue(bankAccount.canContinue());
    }

    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }
}
