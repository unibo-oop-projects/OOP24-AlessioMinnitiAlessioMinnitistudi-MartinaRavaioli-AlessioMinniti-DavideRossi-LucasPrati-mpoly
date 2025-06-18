package it.unibo.monopoly.model.transactions.bankaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.model.transactions.impl.bankaccount.BankAccountFactoryImpl;

/**
 * Unit tests for {@link BankAccountFactoryImpl}.
 */
class BankAccountFactoryImplTest {

    private static final int VALID_INITIAL_BALANCE = 1_000;
    private static final int NEGATIVE_INITIAL_BALANCE = -1;
    private static final int PLAYER_ID = 42;
    private static final BankAccountType INFINITY = BankAccountType.INFINITY;
    private static final BankAccountType CLASSIC = BankAccountType.CLASSIC;

    private BankAccountFactory factory;

    @BeforeEach
    void setUp() {
        factory = new BankAccountFactoryImpl(VALID_INITIAL_BALANCE);
    }


    @Test
    void constructorRejectsNegativeInitialBalance() {
        final IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> new BankAccountFactoryImpl(NEGATIVE_INITIAL_BALANCE),
            "Constructor should throw if initialBalance is negative"
        );
        assertExceptionMessageNotEmpty(ex);
    }


    @Test
    void createInfinityReturnsNonNullBankAccount() {
        final BankAccount account = factory.createBankAccountByType(PLAYER_ID, INFINITY);
        assertNotNull(account, "createSimple should never return null");
        assertEquals(VALID_INITIAL_BALANCE, account.getBalance(),
            "Simple account should have been initialized with factory's balance");
        assertTrue(account.canContinue(), "Simple account should always be valid for continuation");
    }


    @Test
    void createClassicReturnsNonNullAccount() {
        final BankAccount account = factory.createBankAccountByType(PLAYER_ID, CLASSIC);
        assertNotNull(account, "createWithCheck must never return null");
        assertTrue(account.canContinue(), "Account should be valid if predicate returns true");
    }


    @Test
    void createAccountReturnsNewInstancesEachTime() {
        final BankAccount acc1 = factory.createBankAccountByType(PLAYER_ID, INFINITY);
        final BankAccount acc2 = factory.createBankAccountByType(PLAYER_ID + 1, INFINITY);
        assertNotSame(acc1, acc2, "Each call should produce a distinct BankAccount instance");
    }


    @Test
    void createAccountAssignsCorrectId() {
        final int testId = 99;
        final BankAccount account = factory.createBankAccountByType(testId, CLASSIC);
        assertEquals(testId, account.getID(), "BankAccount should preserve ID passed to factory");
    }



    private void assertExceptionMessageNotEmpty(final Exception ex) {
        assertNotNull(ex.getMessage(), "Exception message must not be null");
        assertFalse(ex.getMessage().isBlank(), "Exception message must not be blank");
    }
}
