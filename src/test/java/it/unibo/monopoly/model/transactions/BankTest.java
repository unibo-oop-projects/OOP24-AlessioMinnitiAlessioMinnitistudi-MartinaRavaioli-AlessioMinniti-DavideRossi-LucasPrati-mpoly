package it.unibo.monopoly.model.transactions;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.ImmutableTitleDeedCopy;
import it.unibo.monopoly.model.transactions.impl.bankaccount.ImmutableBankAccountCopy;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;

/*
 * Tests to verify correct functionality of
 * class Bank.
 */
class BankTest {

    private static final int AMOUNT = 100;
    private static final int ID_1 = 21;
    private static final int ID_2 = 42;
    private static final int DICE_THROW = 12;
    private static final String TITLE_DEED_NAME1 = "Bastoni Gran Sasso";
    private static final String TITLE_DEED_NAME2 = "Viale Monterosa";

    private final Set<BankAccount> accounts = Set.of(
        new SimpleBankAccountImpl(ID_1, AMOUNT, e -> true),
        new SimpleBankAccountImpl(ID_2, AMOUNT, e -> true)
    );
    private final Set<TitleDeed> deeds = Set.of(
        new BaseTitleDeed(Group.GREEN, TITLE_DEED_NAME1, 50, s -> s / 2, 10),
        new BaseTitleDeed(Group.GREEN, TITLE_DEED_NAME2, 60, s -> s / 2, 10)
    );
    private Bank bank;


    @BeforeEach
    void setUp() {
        bank = new BankImpl(accounts, deeds);
    }


    @Test
    void accountsIsEmpty() {
        final IllegalArgumentException emptyList = assertThrows(
            IllegalArgumentException.class, 
            () -> bank = new BankImpl(Set.of(), deeds) 
        );
        testExceptionFormat(emptyList);
    }
 
    @Test
    void checkGetBankAccountOfInexistentPlayerThrowsException() {
        final IllegalArgumentException inexistentPlayerException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.getBankAccount(-1)
        );
        testExceptionFormat(inexistentPlayerException);
    }

    @Test
    void checkGetBankAccountOfPlayerGivesCorrectAccount() {
        final BankAccount account = bank.getBankAccount(ID_1);
        assertEquals(accounts
                    .stream()
                    .filter(a -> ID_1 == a.getID())
                    .map(a -> new ImmutableBankAccountCopy(a))
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
                    .map(d -> new ImmutableTitleDeedCopy(d))
                    .toList()
                    .getFirst(), deed);
    }

    @Test 
    void payRentInexistentPlayer() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.payRent(TITLE_DEED_NAME1, -1, DICE_THROW)
        );
        testExceptionFormat(inexistentPropertyException);
    }

    @Test
    void payRentInexistentProperty() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.payRent("", ID_1, DICE_THROW)
        );
        testExceptionFormat(inexistentPropertyException); 
    }

    @Test
    void payRentOfPropertyWithNoOwner() {
        final IllegalStateException propertyHasNoOwnerException = assertThrows(
            IllegalStateException.class,
            () -> bank.payRent(TITLE_DEED_NAME1, ID_1, DICE_THROW)
        );
        testExceptionFormat(propertyHasNoOwnerException); 
    }

    @Test
    void payRentForPropertyPossessedByThePayer() {
        bank.getBankStateObject().resetTransactionData();
        bank.buyTitleDeed(TITLE_DEED_NAME1, ID_1);
        assertTrue(bank.getTitleDeed(TITLE_DEED_NAME1).isOwned());
        assertEquals(ID_1, bank.getTitleDeed(TITLE_DEED_NAME1).getOwnerId());
        final IllegalStateException propertyPossessedByPlayerException = assertThrows(
            IllegalStateException.class,
            () -> bank.payRent(TITLE_DEED_NAME1, ID_1, DICE_THROW)
        );
        testExceptionFormat(propertyPossessedByPlayerException); 
    }

    @Test
    void payRentSuccessful() {
        bank.getBankStateObject().resetTransactionData();
        bank.buyTitleDeed(TITLE_DEED_NAME1, ID_2);
        final int rent = bank.getTitleDeed(TITLE_DEED_NAME1)
                                                .getRent(Sets.filter(Sets.newHashSet(deeds), 
                                                        d -> !TITLE_DEED_NAME1.equals(d.getName())),
                                                DICE_THROW
                                                );
        final int initialBalancePl1 = bank.getBankAccount(ID_1).getBalance();
        final int initialBalancePl2 = bank.getBankAccount(ID_2).getBalance();
        bank.getBankStateObject().resetTransactionData();
        bank.getApplicableActionsForTitleDeed(ID_1, TITLE_DEED_NAME1, DICE_THROW);
        bank.payRent(TITLE_DEED_NAME1, ID_1, DICE_THROW);
        assertEquals(initialBalancePl1 - rent, bank.getBankAccount(ID_1).getBalance());
        assertEquals(initialBalancePl2 + rent, bank.getBankAccount(ID_2).getBalance());
    }

    @Test
    void buyInexistentProperty() {
        final IllegalArgumentException inexistentPropertyException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.buyTitleDeed("", ID_1)
        );
        testExceptionFormat(inexistentPropertyException);
    }

    @Test
    void buyPropertyForInexistentPlayer() {
        final IllegalArgumentException inexistentPlayerException = assertThrows(
            IllegalArgumentException.class,
            () -> bank.buyTitleDeed(TITLE_DEED_NAME1, -1)
        );
        testExceptionFormat(inexistentPlayerException);
    }

    @Test
    void buyAlreadyBoughtProperty() {
        bank.getBankStateObject().resetTransactionData();
        bank.buyTitleDeed(TITLE_DEED_NAME1, ID_1);
        assertTrue(bank.getTitleDeed(TITLE_DEED_NAME1).isOwned());
        assertEquals(ID_1, bank.getTitleDeed(TITLE_DEED_NAME1).getOwnerId());
        bank.getBankStateObject().resetTransactionData();
        final IllegalStateException alreadyBoughtPropertyException = assertThrows(
            IllegalStateException.class,
            () -> bank.buyTitleDeed(TITLE_DEED_NAME1, ID_1)
        );
        testExceptionFormat(alreadyBoughtPropertyException);
    }

    @Test 
    void buyingPropertySuccessful() {
        final int previousBalance = bank.getBankAccount(ID_1).getBalance();
        bank.getBankStateObject().resetTransactionData();
        bank.buyTitleDeed(TITLE_DEED_NAME1, ID_1);
        assertTrue(bank.getTitleDeed(TITLE_DEED_NAME1).isOwned());
        assertEquals(ID_1, bank.getTitleDeed(TITLE_DEED_NAME1).getOwnerId());
        final int expectedBalanceAfterPurchase = previousBalance - bank.getTitleDeed(TITLE_DEED_NAME1).getSalePrice();
        assertEquals(expectedBalanceAfterPurchase, bank.getBankAccount(ID_1).getBalance());
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
        final IllegalStateException propertyNotOwned = assertThrows(
            IllegalStateException.class,
            () -> bank.sellTitleDeed(TITLE_DEED_NAME1));
        testExceptionFormat(propertyNotOwned);
    }

    @Test
    void sellPropertySuccessful() {
        final int previousBalance = bank.getBankAccount(ID_1).getBalance();
        bank.getBankStateObject().resetTransactionData();
        bank.buyTitleDeed(TITLE_DEED_NAME1, ID_1);
        assertTrue(bank.getTitleDeed(TITLE_DEED_NAME1).isOwned());
        assertEquals(ID_1, bank.getTitleDeed(TITLE_DEED_NAME1).getOwnerId());
        final int expectedBalanceAfterPurchase = previousBalance - bank.getTitleDeed(TITLE_DEED_NAME1).getSalePrice();
        assertEquals(expectedBalanceAfterPurchase, bank.getBankAccount(ID_1).getBalance());
        bank.sellTitleDeed(TITLE_DEED_NAME1);
        final int expectedBalanceAfterSale = expectedBalanceAfterPurchase + bank.getTitleDeed(TITLE_DEED_NAME1)
                                                                                .getMortgagePrice();
        assertFalse(bank.getTitleDeed(TITLE_DEED_NAME1).isOwned());
        assertEquals(expectedBalanceAfterSale, bank.getBankAccount(ID_1).getBalance());
    }

    @Test
    void testGetTitleDeedsByOwner() {
        bank.getBankStateObject().resetTransactionData();
        bank.buyTitleDeed(TITLE_DEED_NAME1, ID_1);
        final Set<TitleDeed> deeds = bank.getTitleDeedsByOwner(ID_1);
        assertFalse(deeds.isEmpty());
        assertTrue(deeds.stream().allMatch(d -> d.isOwned() && ID_1 == d.getOwnerId()));
    }

    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }
} 
