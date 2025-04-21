package it.unibo.monopoly.model.transactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;

import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;
import it.unibo.monopoly.model.transactions.impl.bankaccount.WithdrawCheckBankAccount;

/*
 * Tests to verify correct functionality of
 * class Bank.
 */
class BankTest {

    private static final int AMOUNT = 100;
    private static final String PLAYER1_NAME = "Alice";
    private static final String PLAYER2_NAME = "Bob";
    private static final String TITLE_DEED_NAME1 = "Bastoni Gran Sasso";
    private static final String TITLE_DEED_NAME2 = "Viale Monterosa";

    private final List<BankAccount> accounts = List.of(
        new WithdrawCheckBankAccount(new SimpleBankAccountImpl(AMOUNT, PLAYER1_NAME),
                                    (b, a) -> a <= b.getBalance()
                                    ),
        new WithdrawCheckBankAccount(new SimpleBankAccountImpl(AMOUNT, PLAYER2_NAME),
                                    (b, a) -> a <= b.getBalance()
                                    )
    );
    private final List<TitleDeed> deeds = List.of(
        new BaseTitleDeed("viola", TITLE_DEED_NAME1, 50, s -> s / 2, 10),
        new BaseTitleDeed("viola", TITLE_DEED_NAME2, 60, s -> s / 2, 10)

    );
    private Bank bank;


    @BeforeEach
    void setUp() {
        //bank = new BankImpl();
    }

    @Test
    void checkGetBankAccountOfInexistentPlayerThrowsException() {
        final IllegalArgumentException inexistentPlayerException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.getBankAccount("")
        );
        testExceptionFormat(inexistentPlayerException);
    }

    @Test
    void checkGetBankAccountOfPlayerGivesCorrectAccount() {
        final BankAccount account = bank.getBankAccount(PLAYER1_NAME);
        assertEquals(accounts
                    .stream()
                    .filter(a -> PLAYER1_NAME.equals(a.getOwner()))
                    .toList()
                    .getFirst(), account);
    }

    @Test
    void checkGetTitleDeedOfInexistentDeedThrowsException() {
        final IllegalArgumentException inexsistentDeedException = assertThrows(
            IllegalArgumentException.class, 
            () -> bank.getTitleDeed(""));
        testExceptionFormat(inexsistentDeedException);
    }

    @Test
    void checkGetTitleDeedGivesCorrectDeed() {
        final TitleDeed deed = bank.getTitleDeed(TITLE_DEED_NAME1);
        assertEquals(deeds
                    .stream()
                    .filter(d -> TITLE_DEED_NAME1.equals(d.getName()))
                    .toList()
                    .getFirst(), deed);
    }

    @Test 
    void payRentInexistentPlayer() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.payRent(TITLE_DEED_NAME1, "")
        );
        testExceptionFormat(inexistentPropertyException);
    }

    @Test
    void payRentInexistentProperty() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.payRent("", PLAYER1_NAME)
        );
        testExceptionFormat(inexistentPropertyException); 
    }

    @Test
    void payRentOfPropertyWithNoOwner() {
        final IllegalArgumentException propertyHasNoOwnerException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.payRent(TITLE_DEED_NAME1, PLAYER1_NAME)
        );
        testExceptionFormat(propertyHasNoOwnerException); 
    }

    @Test
    void payRentForPropertyPossessedByThePayer() {
        bank.buyTitleDeed(TITLE_DEED_NAME1, PLAYER1_NAME);
        assertEquals(PLAYER1_NAME, bank.getTitleDeed(TITLE_DEED_NAME1).getOwner().get());
        final IllegalArgumentException propertyPossessedByPlayerException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.payRent(TITLE_DEED_NAME1, PLAYER1_NAME)
        );
        testExceptionFormat(propertyPossessedByPlayerException); 
    }

    @Test 
    void payRentViolatesWithdrawConditionsOfThePayerBankAccount() {
        bank.buyTitleDeed(TITLE_DEED_NAME1, PLAYER2_NAME);
        final int rent = bank.getTitleDeed(TITLE_DEED_NAME1)
                            .getRent(Sets.filter(Sets.newHashSet(deeds), 
                                            d -> !TITLE_DEED_NAME1.equals(d.getName())
                                        )
                                    );
        final int pl1Balance = bank.getBankAccount(PLAYER1_NAME).getBalance();

        for (int i = 0; i < (pl1Balance / rent) - 1; i++) {
            bank.payRent(TITLE_DEED_NAME1, PLAYER1_NAME);
        }

        final IllegalStateException withDrawConditionsViolated = assertThrows(
            IllegalStateException.class,
            () -> bank.payRent(TITLE_DEED_NAME1, PLAYER1_NAME));
        testExceptionFormat(withDrawConditionsViolated);
    }

    @Test
    void payRentSuccessful() {
        final int initialBalancePl1 = bank.getBankAccount(PLAYER1_NAME).getBalance();
        final int initialBalancePl2 = bank.getBankAccount(PLAYER2_NAME).getBalance();
        final int rent = bank.getTitleDeed(TITLE_DEED_NAME1)
                                .getRent(Sets.filter(Sets.newHashSet(deeds), 
                                        d -> !TITLE_DEED_NAME1.equals(d.getName()))
                                );
        bank.buyTitleDeed(TITLE_DEED_NAME1, PLAYER2_NAME);
        bank.payRent(TITLE_DEED_NAME1, PLAYER1_NAME);
        assertEquals(initialBalancePl1 - rent, bank.getBankAccount(PLAYER1_NAME).getBalance());
        assertEquals(initialBalancePl2 + rent, bank.getBankAccount(PLAYER2_NAME).getBalance());
    }

    @Test
    void buyInexistentProperty() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.buyTitleDeed("", PLAYER1_NAME)
        );
        testExceptionFormat(inexistentPropertyException);
    }

    @Test
    void buyPropertyForInexistentPlayer() {
        final IllegalArgumentException inexistentPlayerException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.buyTitleDeed(TITLE_DEED_NAME1, "")
        );
        testExceptionFormat(inexistentPlayerException);
    }

    @Test
    void buyAlreadyBoughtProperty() {
        bank.buyTitleDeed(TITLE_DEED_NAME1, PLAYER1_NAME);
        assertEquals(PLAYER1_NAME, bank.getTitleDeed(TITLE_DEED_NAME1).getOwner().get());
        final IllegalStateException alreadyBoughtPropertyException = assertThrows(
            IllegalStateException.class,
            () -> bank.buyTitleDeed(TITLE_DEED_NAME1, PLAYER1_NAME)
        );
        testExceptionFormat(alreadyBoughtPropertyException);
    }

    @Test
    void buyingPropertyViolatesBuyerBankAccountWithdrawConditions() {
        bank.buyTitleDeed(TITLE_DEED_NAME1, PLAYER1_NAME);
        final IllegalStateException withdrawConditionsViolated = assertThrows(
            IllegalStateException.class, 
            () -> bank.buyTitleDeed(TITLE_DEED_NAME2, PLAYER1_NAME));
        testExceptionFormat(withdrawConditionsViolated);
    }

    @Test 
    void buyingPropertySuccessful() {
        final int previousBalance = bank.getBankAccount(PLAYER1_NAME).getBalance();
        bank.buyTitleDeed(TITLE_DEED_NAME1, PLAYER1_NAME);
        assertEquals(PLAYER1_NAME, bank.getTitleDeed(TITLE_DEED_NAME1).getOwner().get());
        final int expectedBalanceAfterPurchase = previousBalance - bank.getTitleDeed(TITLE_DEED_NAME1).getSalePrice();
        assertEquals(expectedBalanceAfterPurchase, bank.getBankAccount(PLAYER1_NAME).getBalance());
    }

    @Test
    void sellInexistentProperty() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.sellTitleDeed("")
        );
        testExceptionFormat(inexistentPropertyException);
    }

    @Test
    void sellPropertyWithNoOwner() {
        final IllegalArgumentException propertyNotOwned = assertThrows(
            IllegalArgumentException.class,
            () -> bank.sellTitleDeed(TITLE_DEED_NAME1));
        testExceptionFormat(propertyNotOwned);
    }

    @Test
    void sellPropertySuccessful() {
        final int previousBalance = bank.getBankAccount(PLAYER1_NAME).getBalance();
        bank.buyTitleDeed(TITLE_DEED_NAME1, PLAYER1_NAME);
        assertEquals(PLAYER1_NAME, bank.getTitleDeed(TITLE_DEED_NAME1).getOwner().get());
        final int expectedBalanceAfterPurchase = previousBalance - bank.getTitleDeed(TITLE_DEED_NAME1).getSalePrice();
        assertEquals(expectedBalanceAfterPurchase, bank.getBankAccount(PLAYER1_NAME).getBalance());
        bank.sellTitleDeed(TITLE_DEED_NAME1);
        final int expectedBalanceAfterSale = expectedBalanceAfterPurchase + bank.getTitleDeed(TITLE_DEED_NAME1)
                                                                                .getMortgagePrice();
        assertTrue(bank.getTitleDeed(TITLE_DEED_NAME1).getOwner().isEmpty());
        assertEquals(expectedBalanceAfterSale, bank.getBankAccount(PLAYER1_NAME).getBalance());
    }

    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }
} 
