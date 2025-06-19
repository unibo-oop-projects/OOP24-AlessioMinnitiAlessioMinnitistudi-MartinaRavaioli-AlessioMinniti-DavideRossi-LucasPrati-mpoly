package it.unibo.monopoly.model.gameboard.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.CardFactory;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.RentOptionFactory;
import it.unibo.monopoly.model.transactions.api.SpecialPropertyFactory;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.rentoption.RentOptionFactoryImpl;
import it.unibo.monopoly.model.transactions.impl.titledeed.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.titledeed.SpecialPropertyFactoryImpl;
import it.unibo.monopoly.model.transactions.impl.titledeed.TitleDeedWithHouses;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * A {@link CardFactory} implementation, for create entities after the deserialization from the file json.
 */
public class CardFactoryImpl implements CardFactory {

    private static final double HOTEL_PERC = 1.5;
    private final SpecialFactory specialFactory = new SpecialFactoryImpl();
    private final SpecialPropertyFactory specialPropertyFactory = new SpecialPropertyFactoryImpl();
    private final RentOptionFactory rentOptionFactory = new RentOptionFactoryImpl();
    private final Board board;
    private final Bank bank;
    private final TurnationManager turnM;

    private final Function<Integer, Integer> houseCost = propertyPrice -> {
        final int min = 50;
        final int max = 300;
        final int cost = propertyPrice / 3;
        return Math.max(min, Math.min(max, cost));
    };
    private final Function<Integer, Integer> hotelCost = propertyPrice -> (int) (houseCost.apply(propertyPrice) * HOTEL_PERC);

    private final List<Tile> tiles = new ArrayList<>();
    private final Set<TitleDeed> deeds = new HashSet<>();

    /**
     * Create a new {@link CardFactoryImpl}.
     * @param board the {@link Board} of the game for handle specific effects
     * @param bank the {@link Bank} of the game for handle specific effects
     * @param turnM the {@link TurnationManager} of the game for handle specific effects
     */
    @SuppressFBWarnings(
    value = "EI2",
    justification = "CardFactoryImpl intentionally holds references to mutable collaborators (Bank and Board) for initial setup."
    )
    public CardFactoryImpl(final Board board, final Bank bank, final TurnationManager turnM) {
        this.board = Objects.requireNonNull(board);
        this.bank = Objects.requireNonNull(bank);
        this.turnM = Objects.requireNonNull(turnM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final List<CardDTO> dtos) {
        for (final var dto : dtos) {
            switch (dto.getType().toUpperCase(Locale.ENGLISH)) {
                case "SPECIAL" -> handleSpecial(dto);
                case "PROPERTY" -> handleProperty(dto);
                default -> throw new IllegalArgumentException("Unknown tile type: " + dto.getType());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tile> getTiles() {
        return List.copyOf(tiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<TitleDeed> getDeeds() {
        return Set.copyOf(deeds);
    }



    private void handleSpecial(final CardDTO dto) {
        final Special tile;
        final Position position = dto.getPosition();

        final String effect = dto.getEffect()
            .orElseThrow(() -> new IllegalArgumentException("Missing 'effect' for SPECIAL card: " + dto.getName()));

        switch (effect) {
            case "JAIL"             -> tile = specialFactory.prison(position);
            case "GO_TO_JAIL"       -> tile = specialFactory.goToPrison(position, board, turnM);
            case "INCOME"           -> tile = specialFactory.start(bank);
            case "TAX"              -> tile = specialFactory.taxes(position, bank);
            case "PARKING"          -> tile = specialFactory.parking(position, turnM);
            case "CHANCE / CHEST"   -> tile = specialFactory.chancesAndCommunityChest(position, board); 
            default -> throw new IllegalArgumentException("Unknown effect type: " + effect);
        }
        tiles.add(tile);
    }


    private void handleProperty(final CardDTO dto) {
        final Position position = dto.getPosition();

        final String name = dto.getName()
            .orElseThrow(() -> new IllegalArgumentException(
                "Missing 'name' for PROPERTY card at position: " + position.getPos()));
        final Group group = dto.getGroup()
            .orElseThrow(() -> new IllegalArgumentException(
                "Missing 'group' for PROPERTY card at position: " + position.getPos()));

        final Property property = new NormalPropertyImpl(name, position, group);
        final TitleDeed deed;

        if (isSpecialProperty(group)) {
            tiles.add(property);
            deed = handleSpecialPropertyTitleDeed(name, group);
            deeds.add(deed);

        } else {
            final BuildablePropertyImpl buildableProperty = new BuildablePropertyImpl(property);
            tiles.add(buildableProperty);

            final int cost = dto.getCost()
                .orElseThrow(() -> new IllegalArgumentException(
                    "Missing 'cost' for PROPERTY card at position: " + position.getPos()));
            final int baseRent = dto.getBaseRent()
                .orElseThrow(() -> new IllegalArgumentException(
                    "Missing 'baseRent' for PROPERTY card at position: " + position.getPos()));

            deed = new BaseTitleDeed(
                group,
                name,
                cost,
                p -> p / 2,
                baseRent,
                List.of(rentOptionFactory.allDeedsOfGroupWithSameOwner(baseRent))
            );

            final TitleDeed deedWithHouses = new TitleDeedWithHouses(
                deed,
                rentOptionFactory.housesAndHotelsOptions(baseRent, 4, true),
                new ImmutableProperty(buildableProperty),
                houseCost,
                hotelCost
            );
            deeds.add(deedWithHouses);
        }
    }


    private boolean isSpecialProperty(final Group group) {
        return group.equals(Group.STATION) || group.equals(Group.SOCIETY);
    }


    private TitleDeed handleSpecialPropertyTitleDeed(final String name, final Group group) {
        if (group.equals(Group.STATION)) {
            return specialPropertyFactory.station(name);

        } else if (group.equals(Group.SOCIETY)) {
            return specialPropertyFactory.society(name);
        }
        // unused exception because group just is validated from  #idSpecialProperty()
        throw new IllegalArgumentException(
            "The provided group '" + group.name() + "' in '" + name + "' is neither a STATION or a SOCIETY"
        );
    }

}
