package it.unibo.monopoly.view.impl.gamepanels;

import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.view.api.GamePanelsFactory;
import it.unibo.monopoly.view.api.PlayerPanel;
import it.unibo.monopoly.view.api.StandardControlsPanel;
import it.unibo.monopoly.view.api.AccountPanel;
import it.unibo.monopoly.view.api.ContractPanel;
import it.unibo.monopoly.view.api.GameActionsPanel;

/**
 * Implementation of the {@link GamePanelsFactory}
 * that creates {@link JPanel} components.
 */
public final class SwingPanelsFactory implements GamePanelsFactory {

    @Override
    public PlayerPanel userInfoPanel() {
        return new SwingPlayerPanel();
    }

    @Override
    public AccountPanel bankAccountInfoPanel() {
        return new SwingAccountPanel();
    }

    @Override
    public ContractPanel contractPanel() {
        return new SwingContractPanel();
    }

    @Override
    public GameActionsPanel gameActionsPanel() {
        return new SwingGameActionsPanel();
    }

    @Override
    public StandardControlsPanel standardControlsPanel(final GameController controller) {
        return new SwingMainCommandsPanel(controller);
    }

}
