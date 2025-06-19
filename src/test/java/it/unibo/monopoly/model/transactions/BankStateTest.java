package it.unibo.monopoly.model.transactions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankState;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;
import it.unibo.monopoly.model.transactions.impl.titledeed.BaseTitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;

class BankStateTest {

    private static final int AMOUNT = 100;
    private static final int ID_1 = 21;
    private static final int ID_2 = 42;
    private static final String TITLE_DEED_NAME1 = "Bastoni Gran Sasso";
    private static final String TITLE_DEED_NAME2 = "Viale Monterosa";
    private static final int DICE_THROW = 12;

    private final Player player1 = PlayerImpl.of(
        ID_1, "a", Color.BLACK);
    private final Set<BankAccount> accounts = Set.of(
        new SimpleBankAccountImpl(ID_1, AMOUNT, e -> e.getBalance() > 0),
        new SimpleBankAccountImpl(ID_2, AMOUNT, e -> e.getBalance() > 0)
    );
    private final Set<TitleDeed> deeds = Set.of(
        new BaseTitleDeed(Group.GREEN, TITLE_DEED_NAME1, 50, s -> s / 2, 10),
        new BaseTitleDeed(Group.GREEN, TITLE_DEED_NAME2, 60, s -> s / 2, 10)
    );
    private Bank bank;
    private BankState bankState;


    @BeforeEach
    void setUp() {
        bank = new BankImpl(accounts, deeds);
        bankState = bank.getBankStateObject();
    }

    @Test
    void playerHasToDoSomeTransactionsBeforePassingTurn() {
        bank.buyTitleDeed(TITLE_DEED_NAME1, ID_1);
        //set that player with ID_2 has to pay rent
        bank.getActionsForTitleDeed(ID_2, TITLE_DEED_NAME1, DICE_THROW);
        assertFalse(bankState.allMandatoryTransactionsCompleted());
        bank.payRent(TITLE_DEED_NAME1, ID_2, DICE_THROW);
        assertTrue(bankState.allMandatoryTransactionsCompleted());
        assertThrows(IllegalStateException.class, 
            () -> bank.payRent(TITLE_DEED_NAME1, ID_2, DICE_THROW)
        );
    }

    @Test
    void playerCannotContinue() {
        assertTrue(bankState.canContinuePlay(player1));
        bank.buyTitleDeed(TITLE_DEED_NAME1, ID_1);
        bank.buyTitleDeed(TITLE_DEED_NAME2, ID_1);
        assertFalse(bankState.canContinuePlay(player1));
    }

    @Test
    void  testResetTransactionData() {
        bank.buyTitleDeed(TITLE_DEED_NAME1, ID_1);
        //set that player with ID_2 has to pay rent
        bank.getActionsForTitleDeed(ID_2, TITLE_DEED_NAME1, DICE_THROW);
        assertFalse(bankState.allMandatoryTransactionsCompleted());
        //simulate player ends its turn by wiping all data of the executed transactions
        bankState.resetTransactionData();
        assertTrue(bankState.allMandatoryTransactionsCompleted());
        bank.getActionsForTitleDeed(ID_2, TITLE_DEED_NAME1, DICE_THROW);
        assertFalse(bankState.allMandatoryTransactionsCompleted());
    }

    @Test
    void releaseDeedsOwnedByPlayer() {
        bank.buyTitleDeed(TITLE_DEED_NAME1, ID_1);
        bankState.releasePlayerDeeds(player1);
        assertFalse(bank.getTitleDeed(TITLE_DEED_NAME1).isOwned());
        assertThrows(IllegalArgumentException.class, 
            () -> bank.getBankAccount(ID_1)
        );
    }
}
