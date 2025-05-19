package it.unibo.monopoly.view.api;

import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * A panel that displays information related to
 * a {@link TitleDeed}.
 */
public interface ContractPanel extends GamePanel {

    /**
     * Displays information related to a {@link TitleDeed}.
     * @param deed The {@link TitleDeed} to unpack
     */
    void displayPropertyContract(TitleDeed deed);
}
