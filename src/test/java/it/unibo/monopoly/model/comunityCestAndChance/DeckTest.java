package it.unibo.monopoly.model.comunityCestAndChance;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.PawnFactory;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.gameboard.impl.PawnFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.PropertyImpl;
import it.unibo.monopoly.model.gameboard.impl.SpecialFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ChancheAndCommunityChestDeck;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.DeckCreator;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ChanceAndCommunityChestCard;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.DeckCreatorImpl;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;
import it.unibo.monopoly.model.turnation.api.Dice;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.DiceImpl;
import it.unibo.monopoly.model.turnation.impl.ParkablePlayer;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.model.turnation.impl.PrisonablePlayer;
import it.unibo.monopoly.model.turnation.impl.TurnationManagerImpl;

public class DeckTest {

    
    private static final String PLAYER1_NAME = "Alice";
    private static final String PLAYER2_NAME = "Marta";
    private static final String PLAYER3_NAME = "Roberto";
    private static final String TITLE_DEED_NAME1 = "Bastoni Gran Sasso";
    private static final String TITLE_DEED_NAME2 = "Viale Monterosa";
    private static final int VALID_ID1 = 1;
    private static final int VALID_ID2 = 2;
    private static final int VALID_ID3 = 3;
    private static final Color VALID_COLOR1 = Color.GREEN;
    private static final Color VALID_COLOR2 = Color.PINK;
    private static final Color VALID_COLOR3 = Color.BLACK;
    private static final Predicate<BankAccount> VALID_PREDICATE = e -> true;

    private static final int VALID_SALE_PRICE1 = 60;
    private static final int VALID_SALE_PRICE2 = 50;
    private static final int VALID_BASE_RENT = 10;
    
    private static final String VALID_TYPE = "chances";

    private static final int PO0 = 0;
    private static final int PO1 = 1;
    private static final int PO2 = 2;
    private static final int PO3 = 3;
    private static final int PO4 = 4;
    private static final int PO5 = 5;
    private static final int PO6 = 6;

    
    private Bank bank;
    private Board board;
    private TurnationManager turnM;
    private DeckCreator creator = new DeckCreatorImpl();
    private ChancheAndCommunityChestDeck deck;

    private final SpecialFactory factory = new SpecialFactoryImpl();
    private final PawnFactory pF = new PawnFactoryImpl();
    private final Position pos0 = new PositionImpl(PO0);
    private final Position pos1 = new PositionImpl(PO1);
    private final Position pos2 = new PositionImpl(PO2);
    private final Position pos3 = new PositionImpl(PO3);
    private final Position pos4 = new PositionImpl(PO4);
    private final Position pos5 = new PositionImpl(PO5);
    private final Position pos6 = new PositionImpl(PO6);

    private final Player p = new ParkablePlayer(PlayerImpl.of(VALID_ID1, PLAYER1_NAME, VALID_COLOR1));
    private final Player p1 = new PrisonablePlayer(p);
    private final Player p2 = PlayerImpl.of(VALID_ID2, PLAYER2_NAME, VALID_COLOR2);
    private final Player p3 = PlayerImpl.of(VALID_ID3, PLAYER3_NAME, VALID_COLOR3);


    private final Set<BankAccount> accounts = Set.of(
        new SimpleBankAccountImpl(VALID_ID1, VALID_PREDICATE), 
        new SimpleBankAccountImpl(VALID_ID2, VALID_PREDICATE), 
        new SimpleBankAccountImpl(VALID_ID3, VALID_PREDICATE)
    );
    private final Set<TitleDeed> deeds = Set.of(
        new BaseTitleDeed(Group.PURPLE, TITLE_DEED_NAME1, VALID_SALE_PRICE1, s -> s / 2, VALID_BASE_RENT),
        new BaseTitleDeed(Group.PURPLE, TITLE_DEED_NAME2, VALID_SALE_PRICE2, s -> s / 2, VALID_BASE_RENT)

    );
    private final List<Pawn> pawns = List.of(
        pF.createBasic(VALID_ID1, pos0, VALID_COLOR1)
    );

    
    @BeforeEach
    void setAll() {
        bank = new BankImpl(accounts, deeds);
        Dice d = new DiceImpl(1);
        turnM = new TurnationManagerImpl(List.of(p1, p2, p3), d);
        final List<Tile> tiles = List.of(
        new PropertyImpl(TITLE_DEED_NAME1, pos0, Group.RED),
        new PropertyImpl(TITLE_DEED_NAME2, pos1, Group.BLUE),
        new PropertyImpl("c", pos2, Group.YELLOW),
        factory.parking(pos5),
        factory.prison(pos4),
        factory.taxes(pos6, bank)
    );
        board = new BoardImpl(tiles, pawns);
        board.addTile(factory.goToPrison(pos3, board));
        creator = new DeckCreatorImpl();
        
    }

    @Test
    void testDeck(){
        try {
            deck = creator.createDeck("cards//DeckCardTest.txt", VALID_TYPE, board, bank, turnM); 
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        final ChanceAndCommunityChestCard c1 = deck.drawInOrder();
        final ChanceAndCommunityChestCard c2 = deck.drawInOrder();
        final ChanceAndCommunityChestCard c3 = deck.drawInOrder();

        assertEquals("deposit 50", c1.getDescription());
        assertEquals("move in Jail / Just Visiting" + " then\n" + "buy Jail / Just Visiting if not owned" , c2.getDescription());
        assertEquals("withdraw 50", c3.getDescription());
    }

}
