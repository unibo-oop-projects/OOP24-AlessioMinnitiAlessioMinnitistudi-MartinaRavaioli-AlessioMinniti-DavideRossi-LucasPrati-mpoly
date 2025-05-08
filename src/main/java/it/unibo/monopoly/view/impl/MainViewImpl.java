package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.monopoly.controller.GameControllerImpl;
import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.view.api.AccountPanel;
import it.unibo.monopoly.view.api.ContractPanel;
import it.unibo.monopoly.view.api.GameAction;
import it.unibo.monopoly.view.api.GameActionsPanel;
import it.unibo.monopoly.view.api.GamePanelsFactory;
import it.unibo.monopoly.view.api.MainGameView;
import it.unibo.monopoly.view.api.PlayerPanel;
import it.unibo.monopoly.view.api.StandardControlsPanel;
import it.unibo.monopoly.view.impl.gamepanels.SwingPanelsFactory;

/**
 * Implementation of the {@link MainGameView} interface
 * using the Swing components library. The class builds 
 * its graphical UI by using a combination of {@link JFrame} {@code objects}.
 */
public final class MainViewImpl implements MainGameView {

    private final JFrame mainGameFrame = new JFrame();

    private final PlayerPanel playerInfoPanel;
    private final AccountPanel accountInfoPanel;
    private final ContractPanel contractPanel;
    private final GameActionsPanel gameActionsPanel;
    private final StandardControlsPanel mainActionsPanel;

    /**
     * Assembles the UI of the game interface and adds all components to {@code mainFrame} object.
     * The {@code mainFrame} is a {@link JFrame}. 
     * @param controller the {@link GameController} that captures the events 
     * happening on this view, implementing the observer pattern. User events
     * will be captured and handled by the {@code controller} provided to this constructor. 
     */
    public MainViewImpl(final GameController controller) {
        final GamePanelsFactory fact = new SwingPanelsFactory();
        contractPanel = fact.contractPanel();
        checkComponentIsJPanel(contractPanel);
        playerInfoPanel = fact.userInfoPanel();
        checkComponentIsJPanel(playerInfoPanel);
        accountInfoPanel = fact.bankAccountInfoPanel();
        checkComponentIsJPanel(accountInfoPanel);
        gameActionsPanel = fact.gameActionsPanel();
        checkComponentIsJPanel(gameActionsPanel);
        mainActionsPanel = fact.standardControlsPanel(controller);
        checkComponentIsJPanel(mainActionsPanel);
        mainGameFrame.getContentPane().add(buildActionPanelUI(), BorderLayout.CENTER);
        mainGameFrame.pack();
        mainGameFrame.setVisible(true);
    }

    private JPanel buildActionPanelUI() {
        final JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BorderLayout());
        final JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BorderLayout());
        userInfoPanel.add((JPanel) playerInfoPanel, BorderLayout.NORTH);
        userInfoPanel.add((JPanel) accountInfoPanel, BorderLayout.SOUTH);

        actionPanel.add(userInfoPanel, BorderLayout.NORTH);
        actionPanel.add((JPanel) contractPanel, BorderLayout.CENTER);
        actionPanel.add((JPanel) gameActionsPanel, BorderLayout.WEST);
        actionPanel.add((JPanel) mainActionsPanel, BorderLayout.SOUTH);
        return actionPanel;
    }

    private void checkComponentIsJPanel(final Object oj) {
        if (oj.getClass().isAssignableFrom(JPanel.class)) {
            throw new ClassCastException("The object" + oj.toString() + "provided by the factory is not a JPanel");
        }
    }

    @Override
    public void displayCurrentPlayerInfo(final Player plData, final BankAccount accountData) {
        playerInfoPanel.displayPlayer(plData);
        accountInfoPanel.displayBankAccount(accountData);
        contractPanel.clear();
        gameActionsPanel.clear();
        mainActionsPanel.clear();
    }

    @Override
    public void displayPropertyContract(final TitleDeed propertyContract) {
        contractPanel.displayPropertyContract(propertyContract);
    }

    @Override
    public void showPlayerActions(final Set<GameAction> actions) {
        gameActionsPanel.buildActionButtons(actions);
    }

    public static void main(final String[] args) {
        new MainViewImpl(new GameControllerImpl());
    }
}
