package it.unibo.monopoly.model.transactions.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Maps;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankState;
import it.unibo.monopoly.model.transactions.api.PropertyAction;
import it.unibo.monopoly.model.transactions.api.PropertyActionFactory;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.api.TransactionLedger;
import it.unibo.monopoly.model.transactions.impl.bankaccount.ImmutableBankAccountCopy;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * This implementation handles all operations
 * as described in the {@link Bank} interface. 
 * It manages a {@link Collection} of {@link BankAccount} {@code objects}
 * and of {@link TitleDeed} {@code objects} 
 */
public final class BankImpl implements Bank {

    private static final String PAY_TRANSACTION = "pay";
    private final Map<Integer, BankAccount> accounts;
    private final Map<String, TitleDeed> titleDeeds;
    private final BiFunction<BankAccount, Set<TitleDeed>, Integer> rankingBiFunction; 
    private final PropertyActionFactory bankActionFactory = new PropertyActionFactoryImpl();
    private final TransactionLedger transactionLedger = new TransactionLedgerImpl();

    /**
     * Creates a new instance of {@link BankImpl} that
     * operates with the given {@code accounts} and {@code title deeds}.
     * @param accounts the palyers' {@link BankAccount}
     * @param titleDeeds {@link List} of {@link TitleDeed} present in the game
     */
    public BankImpl(final Set<BankAccount> accounts, final Set<TitleDeed> titleDeeds) {
        this(accounts, titleDeeds, DEFAULT_RANKING_FUNCTION);
    }


    /**
     * Creates a new instance of {@link BankImpl} that
     * operates with the given {@code accounts} and {@code title deeds}.
     * @param accounts the palyers' {@link BankAccount}
     * @param titleDeeds {@link List} of {@link TitleDeed} present in the game
     * @param rankingBiFunction the function used to rank a player. Takes as input its {@link BankAccount}
     * and all the {@link TitleDeed} whose ownership is associated with that player.
     */
    public BankImpl(final Set<BankAccount> accounts, final Set<TitleDeed> titleDeeds,
    final BiFunction<BankAccount, Set<TitleDeed>, Integer> rankingBiFunction) {
        if (accounts.isEmpty()) {
            throw new IllegalArgumentException("accounts' list cannot be empty");
        }
        this.accounts = Maps.uniqueIndex(accounts, BankAccount::getID);
        this.titleDeeds = titleDeeds.stream().collect(Collectors.toMap(TitleDeed::getName, d -> d));
        this.rankingBiFunction = rankingBiFunction;
    }

    private BankAccount findAccount(final Integer id) {
        if (!accounts.containsKey(id)) {
            throw new IllegalArgumentException("The account of the player " + id + "is not present in the system");
        }
        return accounts.get(id);
    }

    private TitleDeed findTitleDeed(final String id) {
        if (!titleDeeds.containsKey(id)) {
            throw new IllegalArgumentException("The title deed " + id + "is not present in the system");
        }
        return titleDeeds.get(id);
    }

    private Set<TitleDeed> titleDeedsByGroup(final Group group) {
        return titleDeeds.values()
                        .stream()
                        .filter(d -> d.getGroup().equals(group))
                        .collect(Collectors.toSet());
    }

    private int rankPlayer(final int playerId) {
        final Set<TitleDeed> playerDeeds = titleDeeds.values()
                                        .stream()
                                        .filter(t -> t.isOwned()
                                                    && playerId == t.getOwnerId())
                                        .collect(Collectors.toSet());
        return rankingBiFunction.apply(findAccount(playerId), playerDeeds);
    }

    /**
     * Add a new {@link TitleDeed} to the list of deeds managed
     * by this instance.
     * @param titleDeed the {@link TitleDeed} object to add to the system
     * @throws IllegalArgumentException if a {@link TitleDeed} that has the same name
     * as the one returned by the method {@link TitleDeed#getName()}, called on the new {@code titleDeed},
     * is already present in the class internal list of title deeds.
     */
    public void addTitleDeed(final TitleDeed titleDeed) {
        if (titleDeeds.containsKey(titleDeed.getName())) {
            throw new IllegalArgumentException("A title deed with this name is already present in the system");
        }
        titleDeeds.put(titleDeed.getName(), titleDeed);
    }

    @Override
    public void buyTitleDeed(final String titleDeedName, final int playerId) {
        Objects.requireNonNull(titleDeedName);
        Objects.requireNonNull(playerId);
        final BankAccount buyer = findAccount(playerId);
        final TitleDeed td = findTitleDeed(titleDeedName);

        if (td.isOwned()) {
            throw new IllegalStateException("Property is already owned by player" + td.getOwnerId());
        }

        buyer.withdraw(td.getSalePrice());
        td.setOwner(playerId);
        transactionLedger.markExecution("buy");
    }

    @Override
    public BankAccount getBankAccount(final int playerId) {
        return new ImmutableBankAccountCopy(findAccount(playerId));
    }

    @Override
    public TitleDeed getTitleDeed(final String titleDeedName) {
        return new ImmutableTitleDeedCopy(findTitleDeed(titleDeedName));
    }

    @Override
    public void payRent(final String titleDeedName, final int playerId, final int diceThrow) {
        Objects.requireNonNull(titleDeedName);
        Objects.requireNonNull(playerId);
        final TitleDeed deed = findTitleDeed(titleDeedName);
        final BankAccount payer = findAccount(playerId);
        if (!deed.isOwned()) {
            throw new IllegalStateException("Cannot pay rent for title deed with no owner");
        }
        final BankAccount receiver = findAccount(deed.getOwnerId());
        if (receiver.equals(payer)) {
            throw new IllegalStateException("Cannot pay rent for property owned by the payer" + playerId);
        }


        final int rentAmount = deed.getRent(
            titleDeedsByGroup(deed.getGroup()), diceThrow
        );

        transactionLedger.markExecution(PAY_TRANSACTION);
        receiver.deposit(rentAmount);
        try {
            payer.withdraw(rentAmount);
        } catch (final IllegalStateException e) {
            receiver.withdraw(rentAmount);
            transactionLedger.unmarkExecution(PAY_TRANSACTION);
            throw e;
        }
    }

    @Override
    public void sellTitleDeed(final String titleDeedName) {
        Objects.requireNonNull(titleDeedName);
        final TitleDeed deed = findTitleDeed(titleDeedName);
        if (!deed.isOwned()) {
            throw new IllegalStateException("Cannot sell a title deed with no owner");
        }
        final BankAccount seller = findAccount(deed.getOwnerId());
        seller.deposit(deed.getMortgagePrice());
        deed.removeOwner();
        transactionLedger.markExecution("sell");
    }

    @Override
    public Set<TitleDeed> getTitleDeedsByOwner(final int ownerId) {
        Objects.requireNonNull(ownerId);
        if (!accounts.keySet().contains(ownerId)) {
            throw new IllegalArgumentException("The player " + ownerId + "is not present in the system");
        }
        return titleDeeds.values()
                        .stream()
                        .filter(TitleDeed::isOwned)
                        .filter(d -> ownerId == d.getOwnerId())
                        .collect(Collectors.toSet());
    }

    @Override
    public void depositTo(final int ownerId, final int amount) {
        Objects.requireNonNull(ownerId);
        final BankAccount account = findAccount(ownerId);
        account.deposit(amount);
    }

    @Override
    public void withdrawFrom(final int ownerId, final int amount) {
        Objects.requireNonNull(ownerId);
        final BankAccount account = findAccount(ownerId);
        account.withdraw(amount);
    }

    @Override
    public void buyHouse(final String titleDeedName) {
        Objects.requireNonNull(titleDeedName);
        final TitleDeed td = findTitleDeed(titleDeedName);
        if (!td.isOwned()) {
            throw new IllegalStateException("Cannot place a house on a property with no owner");
        } 
        final int playerId = td.getOwnerId();
        final BankAccount player = findAccount(playerId);
        if (!titleDeedsByGroup(td.getGroup())
                        .stream()
                        .allMatch(d -> d.isOwned() && d.getOwnerId() == playerId)) {
                            throw new IllegalStateException("You need to buy all title deeds of the group" 
                            + td.getGroup() 
                            + " before asking to buy houses for the title deed " 
                            + titleDeedName);
        }
        player.withdraw(td.getHousePrice());
        transactionLedger.markExecution("buyHouse");
    }

    @Override
    public void buyHotel(final String titleDeedName) {
        Objects.requireNonNull(titleDeedName);
        final TitleDeed td = findTitleDeed(titleDeedName);
        if (!td.isOwned()) {
            throw new IllegalStateException("Cannot place a house on a property with no owner");
        } 
        final int playerId = td.getOwnerId();
        final BankAccount player = findAccount(playerId);
        if (!titleDeedsByGroup(td.getGroup())
                .stream()
                .allMatch(d -> d.isOwned() && d.getOwnerId() == playerId)) {
                    throw new IllegalStateException("You need to buy all title deeds of the group" 
                    + td.getGroup() 
                    + " before asking to buy a hotel for the title deed " 
                    + titleDeedName);
        }
        player.withdraw(td.getHotelPrice());
        transactionLedger.markExecution("buyHotel");
    }

    @Override
    public Set<PropertyAction> getApplicableActionsForTitleDeed(
        final int currentPlayerId, 
        final String titleDeedName, 
        final int diceThrow) {
        if (!accounts.containsKey(currentPlayerId)) {
            throw new IllegalArgumentException("No player with this id is present in the system");
        }
        final Set<PropertyAction> returnSet = new HashSet<>();
        final TitleDeed selected = findTitleDeed(titleDeedName);
        transactionLedger.removeIfPresent(PAY_TRANSACTION);
        transactionLedger.removeIfPresent("buy");
        transactionLedger.removeIfPresent("buyHouse");
        transactionLedger.removeIfPresent("buyHotel");

        if (!selected.isOwned()) {
            returnSet.add(bankActionFactory.createBuy(currentPlayerId, titleDeedName));
            transactionLedger.registerTransaction("buy", 0, 1);
        } else if (selected.getOwnerId() == currentPlayerId) {
            returnSet.add(bankActionFactory.createSell(titleDeedName));
            returnSet.add(bankActionFactory.createBuyHouse(titleDeedName));
            transactionLedger.registerTransaction("buyHouse", 0);
            returnSet.add(bankActionFactory.createBuyHotel(titleDeedName));
            transactionLedger.registerTransaction("buyHotel", 0);
        } else {
            returnSet.add(bankActionFactory.createPayRent(titleDeedName, currentPlayerId, diceThrow));
            transactionLedger.registerTransaction(PAY_TRANSACTION, 1, 1);
        }

        return returnSet;
    }

    @Override
    public BankState getBankStateObject() {
        return this.new BankStateAdapter();
    }

    private final class BankStateAdapter implements BankState {

        @Override
        public boolean canContinuePlay(final Player player) {
            return findAccount(player.getID()).canContinue();
        }

        @Override
        public boolean allMandatoryTransactionsCompleted() {
            return transactionLedger.checkAllMandatoryTransactionsCompleted();
        }

        @Override
        public List<Pair<Integer, Integer>> rankPlayers() {
            final Map<Integer, Integer> ranks = accounts.values()
                                    .stream()
                                    .collect(Collectors.toMap(BankAccount::getID, 
                                            e1 -> rankPlayer(e1.getID())
                                        )
                                    );
            return ranks.entrySet().stream()
                                    .map(Pair::of)
                                    .sorted((e1, e2) -> Integer.compare(e1.getRight(), e2.getRight()))
                                    .toList();
        }

        @Override
        public void resetTransactionData() {
            transactionLedger.reset();
            /*In this version of the game, the player always has permission
            for sell operations, in any given moment of its turn
            */
            transactionLedger.registerTransaction("sell", 0);
        }

        @Override
        public void deletePlayer(final Player pl) {
            getTitleDeedsByOwner(pl.getID())
            .stream()
            .forEach(TitleDeed::removeOwner);
            accounts.remove(pl.getID());
        }
    }

}
