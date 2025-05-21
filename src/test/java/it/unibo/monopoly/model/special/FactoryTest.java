package it.unibo.monopoly.model.special;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.Color;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.gameboard.impl.SpecialFactoryImpl;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;
import it.unibo.monopoly.model.transactions.impl.bankaccount.WithdrawCheckBankAccount;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.model.turnation.impl.TurnationManagerImpl;



/*
 * Tests to verify correct functionality of
 * class Bank.
 */
public class FactoryTest {
    
    
    private static final int AMOUNT = 100;
    private static final String PLAYER1_NAME = "Alice";
    private static final String PLAYER2_NAME = "Bob";
    private static final String TITLE_DEED_NAME1 = "Bastoni Gran Sasso";
    private static final String TITLE_DEED_NAME2 = "Viale Monterosa";
    private static final int VALID_ID = 1;
    private static final String VALID_NAME = "Alice";
    private static final Color VALID_COLOR = Color.GREEN;

    private final SpecialFactory factory = new SpecialFactoryImpl();
    private final Position pos = new PositionImpl(13);
    private final Set<BankAccount> accounts = Set.of(
        new WithdrawCheckBankAccount(new SimpleBankAccountImpl(AMOUNT, PLAYER1_NAME),
                                    (b, a) -> a <= b.getBalance()
                                    ),
        new WithdrawCheckBankAccount(new SimpleBankAccountImpl(AMOUNT, PLAYER2_NAME),
                                    (b, a) -> a <= b.getBalance()
                                    )
    );
    private final Set<TitleDeed> deeds = Set.of(
        new BaseTitleDeed(Group.PURPLE, TITLE_DEED_NAME1, 50, s -> s / 2, 10),
        new BaseTitleDeed(Group.PURPLE, TITLE_DEED_NAME2, 60, s -> s / 2, 10)

    );

    private Player player;
    private Special special;
    private Bank bank = new BankImpl(accounts, deeds);;
    private TurnationManager turnM;
    private Board board;

    @BeforeAll
    void setAll(){

        turnM = new TurnationManagerImpl(null, null);
        board = new BoardImpl(null, null);

    }

    @BeforeEach
    void create(){
        player = PlayerImpl.of(VALID_ID, VALID_NAME, VALID_COLOR);        
    }

    @Test
    void testPrison(){
        
    }

}
