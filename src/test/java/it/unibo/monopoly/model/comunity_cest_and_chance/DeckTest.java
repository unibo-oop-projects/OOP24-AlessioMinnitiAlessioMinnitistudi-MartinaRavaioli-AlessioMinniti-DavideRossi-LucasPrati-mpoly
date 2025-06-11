package it.unibo.monopoly.model.comunity_cest_and_chance;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.CardFactory;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.PawnFactory;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.gameboard.impl.CardDTO;
import it.unibo.monopoly.model.gameboard.impl.CardFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.PawnFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ChancheAndCommunityChestDeck;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.DeckCreator;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ChanceAndCommunityChestCard;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ChancheAndCommunityChestDeckImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.DeckCreatorImpl;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.bankaccount.BankAccountFactoryImpl;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.DiceImpl;
import it.unibo.monopoly.model.turnation.impl.ParkablePlayer;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.model.turnation.impl.PrisonablePlayer;
import it.unibo.monopoly.model.turnation.impl.TurnationManagerImpl;
import it.unibo.monopoly.utils.api.UseFileJson;
import it.unibo.monopoly.utils.impl.Configuration;
import it.unibo.monopoly.utils.impl.UseFileJsonImpl;

/**
 * test for class Deck.
 */
class DeckTest {


    private static final String PLAYER1_NAME = "Alice";
    private static final String PLAYER2_NAME = "Marta";
    private static final String PLAYER3_NAME = "Roberto";
    private static final int VALID_ID1 = 1;
    private static final int VALID_ID2 = 2;
    private static final int VALID_ID3 = 3;
    private static final Color VALID_COLOR1 = Color.GREEN;
    private static final Color VALID_COLOR2 = Color.PINK;
    private static final Color VALID_COLOR3 = Color.BLACK;

    private BankImpl bank;
    private Board board;
    private TurnationManager turnM;
    private final DeckCreator creator = new DeckCreatorImpl();

    private final Player p = new ParkablePlayer(PlayerImpl.of(VALID_ID1, PLAYER1_NAME, VALID_COLOR1));
    private final Player p1 = new PrisonablePlayer(p);
    private final Player p2 = PlayerImpl.of(VALID_ID2, PLAYER2_NAME, VALID_COLOR2);
    private final Player p3 = PlayerImpl.of(VALID_ID3, PLAYER3_NAME, VALID_COLOR3);

    @BeforeEach
    void setAll() {
        final String confFil = "configuration/config.yml";
        final Configuration config = Configuration.configureFromFile(confFil);
        final List<Player> players = new ArrayList<>();
        final List<Pawn> pawns = new ArrayList<>();
        final List<Tile> tiles = new ArrayList<>();
        final Set<TitleDeed> titleDeeds = new HashSet<>();
        final Set<BankAccount> accounts = new HashSet<>();

        final PawnFactory pawnFactory = new PawnFactoryImpl();
        final UseFileJson importFileJson = new UseFileJsonImpl();

        // create a id for each Player (his Pawn and BankAccount must have the same id)
        int id = 1;
        // create a Player, his Pawn and his BankAccount according to the type chosen
        final int inBal = 100;
        final BankAccountFactory bankAccountFactory = new BankAccountFactoryImpl(inBal);
        final Map<Color, String> playersSetup = Map.of(Color.BLUE, p1.getName(), 
                                                        Color.RED, p2.getName(), 
                                                        Color.GREEN, p3.getName());
        for (final var p : playersSetup.entrySet()) {
            final String name = p.getValue();
            final Color color = p.getKey();
            players.add(new ParkablePlayer(new PrisonablePlayer(PlayerImpl.of(id, name, color))));
            accounts.add(bankAccountFactory.createWithCheck(id,
                                                                  account -> account.getBalance() > 0));
            pawns.add(pawnFactory.createBasic(id, new PositionImpl(0), color));
            id++;
        }

        // creation of Bank, Board and TurnationManager
        board = new BoardImpl(List.of(), pawns);
        bank = new BankImpl(accounts, Set.of());
        turnM = new TurnationManagerImpl(
            players,
            new DiceImpl(
                config.getNumDice(),
                config.getSidesPerDie()
            ),
            bank.getBankStateObject()
        );

        // import from json
        final List<CardDTO> dtos = importFileJson.loadJsonList(config.getCardsPath(), CardDTO.class);
        final CardFactory cardFactory = new CardFactoryImpl(board, bank, turnM); 
        cardFactory.parse(dtos);
        // populate elements
        titleDeeds.addAll(cardFactory.getDeeds());
        tiles.addAll(cardFactory.getTiles());

        // Add tiles to the board and titleDeeds to the Bank
        tiles.stream().forEach(board::addTile);
        titleDeeds.stream().forEach(bank::addTitleDeed);
    }

    boolean isThere(final String desc, final List<String> descs) {
        boolean isthere = false;
        for (final String string : descs) {
            if (desc.equals(string)) {
                isthere = true;
            }
        }
        return isthere;
    }
    @Test
    void testDeck() {
        try {
            final List<String> descs = List.of("deposit 50", 
                                                    "move in Jail / Just Visiting" 
                                                    + " then\n" 
                                                    + "buy Jail / Just Visiting if not owned otherwise pay it's rent",
                                                    "withdraw 50");
            creator.createDeck("debug//cards//DeckCardTest.txt", board, bank, turnM);
            final ChanceAndCommunityChestCard c1 = board.draw();
            c1.execute(p);
            final ChanceAndCommunityChestCard c2 = board.draw();
            c2.execute(p);
            final ChanceAndCommunityChestCard c3 = board.draw();
            c3.execute(p);

            assertTrue(isThere(c1.getDescription(), descs));
            assertTrue(descs.contains(c2.getDescription()));
            assertTrue(descs.contains(c3.getDescription()));
        } catch (final FileNotFoundException e) { 
            final ChancheAndCommunityChestDeck deck = new ChancheAndCommunityChestDeckImpl(List.of());
            assertEquals("", deck.draw().getDescription());
        }

    }

}
