package it.unibo.monopoly.model.bankaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.Transactions.api.BankAccount;
import it.unibo.monopoly.model.Transactions.impl.BankAccountImpl;

public class BankAccountTest {

    private BankAccount bankAccount;
    private static final int BALANCE = 0;
    private static final int AMOUNT = 100;

    @BeforeEach
    public void setUp(){
        bankAccount = new BankAccountImpl();
    }

    @Test
    public void checkInitialBalance() {
    }

    @Test
    public void depositPositiveAmount(){

    }

    @Test 
    public void depositNegativeAmount() {
        final IllegalArgumentException negativeAmountException = assertThrows(
            IllegalArgumentException.class,
            ()-> bankAccount.deposit(AMOUNT),
            "Depositing a negative amount of money should have thrown an error");
        testException(negativeAmountException);
    }

    @Test
    public void withdrawNegativeAmount() {
        final IllegalArgumentException negativeAmountException = assertThrows(
            IllegalArgumentException.class,
            ()-> bankAccount.withdraw(AMOUNT),
            "Withdrawing a negative amount of money should have thrown an error");
        testException(negativeAmountException);
    }

    @Test
    public void withdrawSufficientBalance() {

    }

    @Test 
    public void withdrawWithInsufficientBalance() {

    }

    private void testException(Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }

}
