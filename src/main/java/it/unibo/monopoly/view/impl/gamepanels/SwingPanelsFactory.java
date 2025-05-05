package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.Component;


import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.view.api.GamePanelsFactory;
import it.unibo.monopoly.view.api.PlayerPanel;
import it.unibo.monopoly.view.api.AccountPanel;
import it.unibo.monopoly.view.api.ContractPanel;
import it.unibo.monopoly.view.api.GameActionsPanel;

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
    public GameActionsPanel gameActionsPanel(final GameController controller) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gameActionsPanel'");
    }

}
