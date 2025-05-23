package it.unibo.monopoly.model.transactions.bankaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        testExceptionFormat(ex);
    }

    @Test
    void createSimpleReturnsNonNullBankAccount() {
        final BankAccount account = factory.createSimple(PLAYER_ID);
        assertNotNull(account, "createSimple should never return null");
        assertEquals(VALID_INITIAL_BALANCE, account.getBalance(),
            "Simple account should have been initialized with factory's balance");
        // simple accounts always can continue
        assertTrue(account.canContinue());
    }



    @Test
    void createWithCheckWrapsInCheckValidityBankAccount() {
        final Predicate<BankAccount> alwaysTrue = b -> true;
        final BankAccount wrapped = factory.createWithCheck(PLAYER_ID, alwaysTrue);
        assertNotNull(wrapped, "createWithCheck should never return null");
        // we expect a CheckValidityBankAccount wrapper
        assertEquals("it.unibo.monopoly.model.transactions.impl.bankaccount.CheckValidityBankAccount",
            wrapped.getClass().getName(),
            "Should return a CheckValidityBankAccount");
    }

    @Test
    void createWithCheckHonorsPredicate() {
        final Predicate<BankAccount> alwaysFalse = b -> false;
        final BankAccount account = factory.createWithCheck(PLAYER_ID, alwaysFalse);
        // underlying balance still valid
        assertEquals(VALID_INITIAL_BALANCE, account.getBalance());
        // but canContinue is governed by predicate
        assertFalse(account.canContinue(),
            "Wrapped account should report false when predicate fails");
    }

    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage(), "Exception message must not be null");
        assertFalse(exception.getMessage().isBlank(), "Exception message must not be blank");
    }
}
