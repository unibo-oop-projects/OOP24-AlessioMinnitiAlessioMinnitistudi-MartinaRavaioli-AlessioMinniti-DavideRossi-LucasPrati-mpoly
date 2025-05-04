package it.unibo.monopoly.view.impl;

import java.awt.Component;


import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.view.api.GamePanelsFactory;
import it.unibo.monopoly.view.api.PlayerPanel;
import it.unibo.monopoly.view.api.AccountPanel;
import it.unibo.monopoly.view.api.ContractPanel;

/**
 * Implementation of the {@link GamePanelsFactory}
 * that creates {@link JPanel} components
 */
public class SwingPanelsFactory implements GamePanelsFactory{

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
    public Component mainCommandsPanel(final GameController controller) {
        return new SwingMainCommandsPanel(controller);
    }
}
