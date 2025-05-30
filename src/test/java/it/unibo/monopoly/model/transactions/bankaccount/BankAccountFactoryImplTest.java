package it.unibo.monopoly.model.transactions.bankaccount;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;
import it.unibo.monopoly.model.transactions.impl.bankaccount.BankAccountFactoryImpl;

/**
 * Unit tests for {@link BankAccountFactoryImpl}.
 */
class BankAccountFactoryImplTest {

    private static final int VALID_INITIAL_BALANCE = 1_000;
    private static final int NEGATIVE_INITIAL_BALANCE = -1;
    private static final int PLAYER_ID = 42;

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
    void createSimpleReturnsNonNullBankAccount() {
        final BankAccount account = factory.createSimple(PLAYER_ID);
        assertNotNull(account, "createSimple should never return null");
        assertEquals(VALID_INITIAL_BALANCE, account.getBalance(),
            "Simple account should have been initialized with factory's balance");
        assertTrue(account.canContinue(), "Simple account should always be valid for continuation");
    }

    @Test
    void createSimpleReturnsNewInstancesEachTime() {
        final BankAccount acc1 = factory.createSimple(PLAYER_ID);
        final BankAccount acc2 = factory.createSimple(PLAYER_ID + 1);
        assertNotSame(acc1, acc2, "Each call should produce a distinct BankAccount instance");
    }

    @Test
    void createWithCheckReturnsBankAccountHonoringPredicate() {
        final Predicate<BankAccount> alwaysFalse = b -> false;
        final BankAccount account = factory.createWithCheck(PLAYER_ID, alwaysFalse);
        assertEquals(VALID_INITIAL_BALANCE, account.getBalance(),
            "Account should still have correct initial balance");
        assertFalse(account.canContinue(), "Account should report predicate result (false)");
    }

    @Test
    void createWithCheckReturnsNonNullAccount() {
        final Predicate<BankAccount> alwaysTrue = b -> true;
        final BankAccount account = factory.createWithCheck(PLAYER_ID, alwaysTrue);
        assertNotNull(account, "createWithCheck must never return null");
        assertTrue(account.canContinue(), "Account should be valid if predicate returns true");
    }

    @Test
    void createWithCheckRejectsNullPredicate() {
        final NullPointerException ex = assertThrows(
            NullPointerException.class,
            () -> factory.createWithCheck(PLAYER_ID, null),
            "Passing null predicate must throw NullPointerException"
        );
        assertTrue(ex.getMessage().contains("Check cannot be null"),
            "Exception message should describe missing check");
    }

    @Test
    void createWithCheckAssignsCorrectId() {
        final int testId = 99;
        final BankAccount account = factory.createWithCheck(testId, b -> true);
        assertEquals(testId, account.getID(), "BankAccount should preserve ID passed to factory");
    }

    private void assertExceptionMessageNotEmpty(final Exception ex) {
        assertNotNull(ex.getMessage(), "Exception message must not be null");
        assertFalse(ex.getMessage().isBlank(), "Exception message must not be blank");
    }
}
