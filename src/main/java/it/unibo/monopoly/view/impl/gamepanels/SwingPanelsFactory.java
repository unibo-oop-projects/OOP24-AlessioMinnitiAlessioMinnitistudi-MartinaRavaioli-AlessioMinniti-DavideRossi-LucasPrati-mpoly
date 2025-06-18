package it.unibo.monopoly.view.impl.gamepanels;

import javax.swing.JPanel;

import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.view.api.GamePanelsFactory;
import it.unibo.monopoly.view.api.MainMenuPanelsFactory;
import it.unibo.monopoly.view.api.MenuPanel;
import it.unibo.monopoly.view.api.PlayerPanel;
import it.unibo.monopoly.view.api.SettingsPanel;
import it.unibo.monopoly.view.api.SetupPanel;
import it.unibo.monopoly.view.api.StandardControlsPanel;
import it.unibo.monopoly.view.api.AccountPanel;
import it.unibo.monopoly.view.api.ContractPanel;
import it.unibo.monopoly.view.api.GameActionsPanel;

/**
 * Implementation of the {@link GamePanelsFactory}
 * that creates {@link JPanel} components.
 */
public final class SwingPanelsFactory implements GamePanelsFactory, MainMenuPanelsFactory {

    /**
     * Creates a new {@link SwingPanelsFactory}.
     */
    public SwingPanelsFactory() {
        //intentional empty constructor
    }

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

    @Override
    public MenuPanel menuPanel(final MainMenuController controller) {
        return new SwingMenuPanel(controller);
    }

    @Override
    public SetupPanel setupPanel(final MainMenuController controller) {
        return new SwingSetupPanel(controller);
    }

    @Override
    public SettingsPanel settingsPanel(final MainMenuController controller) {
        return new SwingSettingsPanel(controller);
    }

}
