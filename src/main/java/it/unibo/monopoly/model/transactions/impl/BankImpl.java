package it.unibo.monopoly.model.transactions.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Maps;

import it.unibo.monopoly.model.gameboard.impl.Type;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.bankaccount.ImmutableBankAccountCopy;

/**
 * This implementation hadles all operations
 * as described in the {@link Bank} interface. 
 * It manages a {@code Collection} of {@link BankAccount} {@code objects}
 * and of {@link TitleDeed} {@code objects} 
 */
public final class BankImpl implements Bank {

    private final Map<String, BankAccount> accounts;
    private final Map<String, TitleDeed> titleDeeds;
    private final BiFunction<BankAccount, Set<TitleDeed>, Integer> rankingBiFunction; 


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
     * It also allows to specify a rankingBiFunction used to caluclate 
     * the monetary value of a player (therefore its score).
     * @param accounts the palyers' {@link BankAccount}
     * @param titleDeeds {@link List} of {@link TitleDeed} present in the game
     * @param rankingBiFunction the function used to rank a player. Takes as input its {@link BankAccount}
     * and all the {@link TitleDeed} whose ownership is associated with that player.
     */
    public BankImpl(final Set<BankAccount> accounts, final Set<TitleDeed> titleDeeds,
            final BiFunction<BankAccount, Set<TitleDeed>, Integer> rankingBiFunction) {
        if (accounts.isEmpty() || titleDeeds.isEmpty()) {
            throw new IllegalArgumentException("Input lists cannot be empty");
        }
        this.accounts = Maps.uniqueIndex(accounts, BankAccount::getPlayerName);
        this.titleDeeds = Maps.uniqueIndex(titleDeeds, TitleDeed::getName);
        this.rankingBiFunction = rankingBiFunction;
    }



    private BankAccount findAccount(final String id) {
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

    private Set<TitleDeed> titleDeedsByGroup(final Type group) {
        return titleDeeds.values()
                        .stream()
                        .filter(d -> d.getType().equals(group))
                        .collect(Collectors.toSet());
    }

    private int rankPlayer(final String playerName) {
        final Set<TitleDeed> playerDeeds = titleDeeds.values()
                                        .stream()
                                        .filter(t -> t.getOwner().isPresent() 
                                                    && playerName.equals(t.getOwner().get()))
                                        .collect(Collectors.toSet());
        return rankingBiFunction.apply(findAccount(playerName), playerDeeds);
    }

    @Override
    public void buyTitleDeed(final String titleDeedName, final String playerName) {
        Objects.requireNonNull(titleDeedName);
        Objects.requireNonNull(playerName);
        final BankAccount buyer = findAccount(playerName);
        final TitleDeed td = findTitleDeed(titleDeedName);

        if (td.getOwner().isPresent()) {
            throw new IllegalStateException("Property is already owned by player" + td.getOwner().get());
        }

        buyer.withdraw(td.getSalePrice());
        td.setOwner(playerName);
    }

    @Override
    public BankAccount getBankAccount(final String playerName) {
        return new ImmutableBankAccountCopy(findAccount(playerName));
    }

    @Override
    public TitleDeed getTitleDeed(final String titleDeedName) {
        return new ImmutableTitleDeedCopy(findTitleDeed(titleDeedName));
    }

    @Override
    public void payRent(final String titleDeedName, final String playerName) {
        Objects.requireNonNull(titleDeedName);
        Objects.requireNonNull(playerName);
        final TitleDeed deed = findTitleDeed(titleDeedName);
        final BankAccount payer = findAccount(playerName);
        if (deed.getOwner().isEmpty()) {
            throw new IllegalStateException("Cannot pay rent for title deed with no owner");
        }
        final BankAccount receiver = findAccount(deed.getOwner().get());
        if (receiver.equals(payer)) {
            throw new IllegalStateException("Canot pay rent for property owned by the payer" + playerName);
        }
        final int rentAmount = deed.getRent(
            titleDeedsByGroup(deed.getType())
        );
        receiver.deposit(rentAmount);
        try {
            payer.withdraw(rentAmount);
        } catch (final IllegalStateException e) {
            receiver.withdraw(rentAmount);
            throw e;
        }
    }

    @Override
    public void sellTitleDeed(final String titleDeedName) {
        Objects.requireNonNull(titleDeedName);
        final TitleDeed deed = findTitleDeed(titleDeedName);
        if (deed.getOwner().isEmpty()) {
            throw new IllegalStateException("Cannot sell a title deed with no owner");
        }
        final BankAccount seller = findAccount(deed.getOwner().get());
        seller.deposit(deed.getMortgagePrice());
        deed.removeOwner();
    }

    @Override
    public Set<TitleDeed> getTitleDeedsByOwner(final String ownerName) {
        Objects.requireNonNull(ownerName);
        if (!accounts.keySet().contains(ownerName)) {
            throw new IllegalArgumentException("The player " + ownerName + "is not present in the system");
        }
        return titleDeeds.values()
                        .stream()
                        .filter(d -> d.getOwner().isPresent() && ownerName.equals(d.getOwner().get()))
                        .collect(Collectors.toSet());
    }

    @Override
    public void receivePaymentFromBank(final String ownerName, final int amount) {
        Objects.requireNonNull(ownerName);
        final BankAccount account = findAccount(ownerName);
        account.deposit(amount);
    }

    @Override
    public void makePaymentToBank(final String ownerName, final int amount) {
        Objects.requireNonNull(ownerName);
        final BankAccount account = findAccount(ownerName);
        account.withdraw(amount);
    }

    @Override
    public List<Pair<String, Integer>> rankPlayers() {
        final Map<String, Integer> ranks = accounts.values()
                                    .stream()
                                    .collect(Collectors.toMap(BankAccount::getPlayerName, 
                                            e1 -> rankPlayer(e1.getPlayerName())
                                        )
                                    );
        return ranks.entrySet().stream()
                                .map(Pair::of)
                                .sorted((e1, e2) -> Integer.compare(e1.getRight(), e2.getRight()))
                                .toList();
    }
}
