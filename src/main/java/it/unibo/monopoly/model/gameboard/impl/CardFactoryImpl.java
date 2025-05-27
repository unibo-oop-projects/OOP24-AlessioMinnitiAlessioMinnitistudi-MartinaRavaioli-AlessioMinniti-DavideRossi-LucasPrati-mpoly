package it.unibo.monopoly.model.gameboard.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.CardFactory;
import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.EffectFactory;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.SpecialPropertyFactory;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.SpecialPropertyFactoryImpl;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

/**
 * A {@link CardFactory} implementation, for create entities after the deserialization from the file json.
 */
public class CardFactoryImpl implements CardFactory {

    private final SpecialFactory specialFactory = new SpecialFactoryImpl();
    private final SpecialPropertyFactory specialPropertyFactory = new SpecialPropertyFactoryImpl();
    private final Board board;
    private final Bank bank;

    private final List<Tile> tiles = new ArrayList<>();
    private final Set<TitleDeed> deeds = new HashSet<>();

    /**
     * Create a new {@link CardFactoryImpl}.
     * @param specialFactory the factory that allows to create {@link Special} tiles
     * @param board the {@link Board} of the game for handle specific effects
     * @param bank the {@link Bank} of the game for handle specific effects
     */
    public CardFactoryImpl(final Board board, final Bank bank) {
        this.board = Objects.requireNonNull(board);
        this.bank = Objects.requireNonNull(bank);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final List<CardDTO> dtos) {
        for (var dto : dtos) {
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
        final String name = dto.getName();
        final Position position = dto.getPosition();

        final String effect = dto.getEffect()
            .orElseThrow(() -> new IllegalArgumentException("Missing 'effect' for SPECIAL card: " + dto.getName()));

        switch (effect) {
            case "JAIL"         -> tile = specialFactory.prison(name, position);
            case "GO_TO_JAIL"   -> tile = specialFactory.goToPrison(name, position, board);
            case "INCOME"       -> tile = specialFactory.start(name, bank);
            case "TAX"          -> tile = specialFactory.taxes(name, position, bank);
            case "PARKING"      -> tile = specialFactory.parking(name, position);
            // case "CHANCE"       -> tile = specialFactory.chance(); 
            // case "CHEST"        -> tile = specialFactory.chest();
            default -> throw new IllegalArgumentException("Unknown effect type: " + effect);
        }
        tiles.add(tile);
    }


    private void handleProperty(final CardDTO dto) {
        final String name = dto.getName();
        final Position position = dto.getPosition();

        final Group group = dto.getGroup()
            .orElseThrow(() -> new IllegalArgumentException("Missing 'group' for PROPERTY card: " + name));

        final PropertyImpl property = new PropertyImpl(name, position, group);
        final TitleDeed deed;
        
        if(isSpecialProperty(group)){
            deed = handleSpecialProperty(name, group);
        
        } else {
            final int cost = dto.getCost()
                .orElseThrow(() -> new IllegalArgumentException("Missing 'cost' for PROPERTY card: " + name));
            final int houseCost = dto.getHouseCost()
                .orElseThrow(() -> new IllegalArgumentException("Missing 'houseCost' for PROPERTY card: " + name));
            final int mortgage = dto.getMortgage()
                .orElseThrow(() -> new IllegalArgumentException("Missing 'mortgage' for PROPERTY card: " + name));
            final List<Integer> rents = dto.getRents()
                .orElseThrow(() -> new IllegalArgumentException("Missing 'rents' for PROPERTY card: " + name));

            deed = new BaseTitleDeed();

        }

        
        

        
        // final BaseTitleDeed deed = new BaseTitleDeed(
        //     dto.getName(),
        //     dto.getGroup(),
        //     dto.getCost(),
        //     dto.getHouseCost(),
        //     dto.getMortgage(),
        //     // dto.getRents().stream().map( e -> RentOption.baseRentOption(e.))
        // );
        tiles.add(property);
        deeds.add(deed);
    }

    private boolean isSpecialProperty(final Group group) {
        return group.equals(Group.STATION) || group.equals(Group.SOCIETY);
    }

    private TitleDeed handleSpecialProperty(final String name, final Group group) {
        if(group.equals(Group.STATION)) {
            return specialPropertyFactory.station(name);

        } else if (group.equals(Group.SOCIETY)) {
            return specialPropertyFactory.society(name);
        }
        return switch(group.name())
    }
    
}
