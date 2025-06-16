package it.unibo.monopoly.view.api;

import it.unibo.monopoly.model.transactions.api.BankAccountType;

/**
 * interface of {@link SwingSettingsPanel}.
 */
public interface SettingsPanel extends GamePanel {

    /**
     * Display the game mode chosen.
     * Disable the button of the game mode chosen, enable the others one.
     * @param type the {@link BankAccountType} selected
     */
    void refreshSettingsData(BankAccountType type);
}
