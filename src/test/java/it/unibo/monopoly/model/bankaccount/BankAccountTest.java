package it.unibo.monopoly.model.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.Transactions.api.BankAccount;
import it.unibo.monopoly.model.Transactions.impl.BankAccountImpl;

public class BankAccountTest {

    private BankAccount bankAccount;


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

    }

    @Test
    public void withdrawNegativeAmount() {

    }

    @Test
    public void withdrawSufficientBalance() {

    }

    @Test 
    public void withdrawWithInsufficientBalance(){

    }

}
