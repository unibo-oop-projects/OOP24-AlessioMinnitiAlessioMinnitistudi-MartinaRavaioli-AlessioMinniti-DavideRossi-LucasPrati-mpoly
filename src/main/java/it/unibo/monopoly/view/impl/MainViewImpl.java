package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.controller.impl.GUIVenditaLogicImpl;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.utils.impl.GuiUtils;
import it.unibo.monopoly.view.api.AccountPanel;
import it.unibo.monopoly.view.api.ContractPanel;
import it.unibo.monopoly.view.api.GameAction;
import it.unibo.monopoly.view.api.GameActionsPanel;
import it.unibo.monopoly.view.api.GamePanelsFactory;
import it.unibo.monopoly.view.api.GameboardView;
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

    private static final double PL_DATA_VIEW_PROPORTION = 0.5;


    private final JFrame mainGameFrame = new JFrame();

    private final PlayerPanel playerInfoPanel;
    private final AccountPanel accountInfoPanel;
    private final ContractPanel contractPanel;
    private final GameActionsPanel gameActionsPanel;
    private final StandardControlsPanel mainActionsPanel;
    private final GameboardView gameBoardPanel;

    private final GameController controller;
    /**
     * Assembles the UI of the game interface and adds all components to {@code mainFrame} object.
     * The {@code mainFrame} is a {@link JFrame}. 
     * @param controller the {@link GameController} that captures the events 
     * happening on this view, implementing the observer pattern. User events
     * will be captured and handled by the {@code controller} provided to this constructor. 
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
                justification = "must return reference to the object instead of a copy")
    public MainViewImpl(final GameController controller) {
        this.controller = controller;
        this.gameBoardPanel = new GameboardViewImpl(controller);
        this.controller.setBoardView(this.gameBoardPanel);
        final GamePanelsFactory fact = new SwingPanelsFactory();
        contractPanel = fact.contractPanel();
        contractPanel.renderDefaultUI();
        playerInfoPanel = fact.userInfoPanel();
        playerInfoPanel.renderDefaultUI();
        accountInfoPanel = fact.bankAccountInfoPanel();
        accountInfoPanel.renderDefaultUI();
        gameActionsPanel = fact.gameActionsPanel();
        gameActionsPanel.renderDefaultUI();
        mainActionsPanel = fact.standardControlsPanel(controller);
        mainActionsPanel.renderDefaultUI();
        final JPanel actionPanel = buildActionPanelUI(controller);
        mainGameFrame.getContentPane().add(actionPanel, BorderLayout.EAST);
        mainGameFrame.getContentPane().add(this.gameBoardPanel.getPanel(), BorderLayout.WEST);
        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gameBoardPanel.getPanel(), actionPanel);
        splitPane.setResizeWeight(0.5); 
        splitPane.setDividerSize(2);    // Spessore del divisore
        splitPane.setEnabled(false);    // Rendi il divisore fisso, se vuoi

        mainGameFrame.add(splitPane);
        //mainGameFrame.pack();
        mainGameFrame.setVisible(true);
        mainGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private JPanel buildActionPanelUI(final GameController controller) {
        final JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BorderLayout());
        final JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BorderLayout());
        userInfoPanel.add(playerInfoPanel.getPanel(), BorderLayout.NORTH);
        userInfoPanel.add(accountInfoPanel.getPanel(), BorderLayout.SOUTH);

        actionPanel.add(userInfoPanel, BorderLayout.NORTH);
        actionPanel.add(contractPanel.getPanel(), BorderLayout.CENTER);
        actionPanel.add(gameActionsPanel.getPanel(), BorderLayout.WEST);

        final JButton handlePropertiesButton = new JButton("Gestione proprietà");
        handlePropertiesButton.addActionListener(e -> controller.loadCurrentPlayerInformation());

        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(handlePropertiesButton, BorderLayout.SOUTH);
        southPanel.add(mainActionsPanel.getPanel(), BorderLayout.CENTER);

        actionPanel.add(southPanel, BorderLayout.SOUTH);
        return actionPanel;
    }

    @Override
    public void refreshCurrentPlayerInfo(final Player player, final BankAccount account) {
        playerInfoPanel.displayPlayer(player);
        accountInfoPanel.displayBankAccount(account);
        contractPanel.renderDefaultUI();
        gameActionsPanel.renderDefaultUI();
        mainActionsPanel.renderDefaultUI();
        mainGameFrame.repaint();
    }

    @Override
    public void displayPropertyContract(final TitleDeed propertyContract) {
        contractPanel.displayPropertyContract(propertyContract);
        mainGameFrame.repaint();
    }

    @Override
    public void displaySpecialInfo(final Special tile) {
        contractPanel.displaySpecialInfo(tile);
        mainGameFrame.repaint();
    }

    @Override
    public void showPlayerActions(final Set<GameAction> actions) {
        gameActionsPanel.buildActionButtons(actions);
        mainGameFrame.repaint();
    }

    @Override
    public void showRules(final String rules) {
        new RulesWindowView(this.mainGameFrame, controller.getConfiguration(), rules);
    }

    @Override
    public void displayPlayerStats(final Player player, final Bank bank) {
        // percentuale personalizzata dello schermo
        final Dimension screenDimension = GuiUtils.getDimensionWindow(PL_DATA_VIEW_PROPORTION, PL_DATA_VIEW_PROPORTION);
        new GUIVendita(player,
            (int) screenDimension.getWidth(), 
            (int) screenDimension.getHeight(), 
            new GUIVenditaLogicImpl(), 
            bank
        );
    }

    @Override
    public void displayMessage(final String message) {
        GuiUtils.showInfoMessage(mainGameFrame, message, message);  // TODO può andare bene?
    }

    @Override
    public void displayError(final Exception e) {
        // TODO se ti può andare bene questo termina anche l'applicazione, altrimenti fai con GuiUtils.showInfoMessage()
        GuiUtils.showErrorAndExit(mainGameFrame, null, e.getMessage());

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayError'");
    }

}
