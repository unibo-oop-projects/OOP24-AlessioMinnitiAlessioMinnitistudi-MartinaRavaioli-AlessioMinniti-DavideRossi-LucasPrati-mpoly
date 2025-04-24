package it.unibo.monopoly.model.transactions.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.bankaccount.ImmutableBankAccountView;

/**
 * This implementation hadles all operations
 * as described in the {@link Bank} interface. 
 * It manages a {@code Collection} of {@link BankAccount} {@code objects}
 * and of {@link TitleDeed} {@code objects} 
 */
public final class BankImpl implements Bank {

    private final Map<String, BankAccount> accounts;
    private final Map<String, TitleDeed> titleDeeds;


    /**
     * Creates a new instance of {@link BankImpl} that 
     * operates with the given {@code accounts} and {@code title deeds}.
     * @param accounts the palyers' {@link BankAccount}
     * @param titleDeeds {@link List} of {@link TitleDeed} present in the game
     */
    public BankImpl(final Set<BankAccount> accounts, final Set<TitleDeed> titleDeeds) {
        if (accounts.isEmpty() || titleDeeds.isEmpty()) {
            throw new IllegalArgumentException("Input lists cannot be empty");
        }
        this.accounts = Maps.uniqueIndex(accounts, BankAccount::getPlayerName);
        this.titleDeeds = Maps.uniqueIndex(titleDeeds, TitleDeed::getName);
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

    private Set<TitleDeed> titleDeedsByGroup(final String group) {
        return titleDeeds.values()
                        .stream()
                        .filter(d -> d.getGroup().equals(group))
                        .collect(Collectors.toSet());
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
        return new ImmutableBankAccountView(findAccount(playerName));
    }

    @Override
    public TitleDeed getTitleDeed(final String titleDeedName) {
        return findTitleDeed(titleDeedName);
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
            titleDeedsByGroup(deed.getGroup())
                                    .stream()
                                    .filter(d -> !d.equals(deed))
                                    .collect(Collectors.toSet())
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
}
