package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.controller.impl.GameControllerImpl;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.utils.GuiUtils;
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

    private final GameController controller;
    /**
     * Assembles the UI of the game interface and adds all components to {@code mainFrame} object.
     * The {@code mainFrame} is a {@link JFrame}. 
     * @param controller the {@link GameController} that captures the events 
     * happening on this view, implementing the observer pattern. User events
     * will be captured and handled by the {@code controller} provided to this constructor. 
     */
    public MainViewImpl(final GameController controller) {
        this.controller = controller;
        final GamePanelsFactory fact = new SwingPanelsFactory();
        contractPanel = fact.contractPanel();
        playerInfoPanel = fact.userInfoPanel();
        accountInfoPanel = fact.bankAccountInfoPanel();
        gameActionsPanel = fact.gameActionsPanel();
        mainActionsPanel = fact.standardControlsPanel(controller);
        mainGameFrame.getContentPane().add(buildActionPanelUI(), BorderLayout.CENTER);
        mainGameFrame.pack();
        mainGameFrame.setVisible(true);
    }

    private JPanel buildActionPanelUI() {
        final JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BorderLayout());
        final JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BorderLayout());
        userInfoPanel.add(playerInfoPanel.getPanel(), BorderLayout.NORTH);
        userInfoPanel.add(accountInfoPanel.getPanel(), BorderLayout.SOUTH);

        actionPanel.add(userInfoPanel, BorderLayout.NORTH);
        actionPanel.add(contractPanel.getPanel(), BorderLayout.CENTER);
        actionPanel.add(gameActionsPanel.getPanel(), BorderLayout.WEST);
        actionPanel.add(mainActionsPanel.getPanel(), BorderLayout.SOUTH);
        return actionPanel;
    }

    @Override
    public void refreshCurrentPlayerInfo() {
        Player currentPlayer = controller.getCurrentPlayer();
        playerInfoPanel.displayPlayer(currentPlayer);
        accountInfoPanel.displayBankAccount(controller.getPlayerAccount(currentPlayer));
        contractPanel.clear();
        gameActionsPanel.clear();
        mainActionsPanel.clear();
        GuiUtils.refresh(mainGameFrame);
    }

    @Override
    public void displayPropertyContract(final TitleDeed propertyContract) {
        contractPanel.displayPropertyContract(propertyContract);
        GuiUtils.refresh(mainGameFrame);
    }

    @Override
    public void showPlayerActions(final Set<GameAction> actions) {
        gameActionsPanel.buildActionButtons(actions);
        GuiUtils.refresh(mainGameFrame);
    }

    public static void main(final String[] args) {
        new MainViewImpl(new GameControllerImpl(null));
    }

    @Override
    public void displayError(final Exception e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayError'");
    }
}
