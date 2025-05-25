package it.unibo.monopoly.model.transactions.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAction;
import it.unibo.monopoly.model.transactions.api.BankActionFactory;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.bankaccount.ImmutableBankAccountCopy;

/**
 * This implementation hadles all operations
 * as described in the {@link Bank} interface. 
 * It manages a {@code Collection} of {@link BankAccount} {@code objects}
 * and of {@link TitleDeed} {@code objects} 
 */
public final class BankImpl implements Bank {

    private final Map<Integer, BankAccount> accounts;
    private final Map<String, TitleDeed> titleDeeds;
    private final BankActionFactory bankActionFactory = new BankActionFactoryImpl(this);


    /**
     * Creates a new instance of {@link BankImpl} that
     * operates with the given {@code accounts} and {@code title deeds}.
     * @param accounts the palyers' {@link BankAccount}
     * @param titleDeeds {@link List} of {@link TitleDeed} present in the game
     * @throws IllegalArgumentException if {@code accounts} or {@code titleDeeds} are {@code null}
     */
    public BankImpl(final Set<BankAccount> accounts, final Set<TitleDeed> titleDeeds) {
        if (accounts.isEmpty() || titleDeeds.isEmpty()) {
            throw new IllegalArgumentException("Input lists cannot be empty");
        }
        this.accounts = Maps.uniqueIndex(accounts, BankAccount::getID);
        this.titleDeeds = Maps.uniqueIndex(titleDeeds, TitleDeed::getName);
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
    public void payRent(final String titleDeedName, final int playerId) {
        Objects.requireNonNull(titleDeedName);
        Objects.requireNonNull(playerId);
        final TitleDeed deed = findTitleDeed(titleDeedName);
        final BankAccount payer = findAccount(playerId);
        if (!deed.isOwned()) {
            throw new IllegalStateException("Cannot pay rent for title deed with no owner");
        }
        final BankAccount receiver = findAccount(deed.getOwnerId());
        if (receiver.equals(payer)) {
            throw new IllegalStateException("Canot pay rent for property owned by the payer" + playerId);
        }
        final int rentAmount = deed.getRent(
            titleDeedsByGroup(deed.getGroup())
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
        if (!deed.isOwned()) {
            throw new IllegalStateException("Cannot sell a title deed with no owner");
        }
        final BankAccount seller = findAccount(deed.getOwnerId());
        seller.deposit(deed.getMortgagePrice());
        deed.removeOwner();
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
    public void receivePaymentFromBank(final int ownerId, final int amount) {
        Objects.requireNonNull(ownerId);
        final BankAccount account = findAccount(ownerId);
        account.deposit(amount);
    }

    @Override
    public void makePaymentToBank(final int ownerId, final int amount) {
        Objects.requireNonNull(ownerId);
        final BankAccount account = findAccount(ownerId);
        account.withdraw(amount);
    }

    @Override
    public Set<BankAction> setTurnTransactions(final int currentPlayerId, final String titleDeedName, final int diceThrow) {
        final TitleDeed selected = findTitleDeed(titleDeedName);

        if (!selected.isOwned()) {
            return Set.of(bankActionFactory.createBuy(currentPlayerId, titleDeedName));
        } else if (selected.getOwnerId() == currentPlayerId){
            return Set.of(bankActionFactory.createSell(titleDeedName));
            //TODO build houses
        } else {
            return Set.of(bankActionFactory.createPayRent(titleDeedName, currentPlayerId, diceThrow));
        }
    }
}
