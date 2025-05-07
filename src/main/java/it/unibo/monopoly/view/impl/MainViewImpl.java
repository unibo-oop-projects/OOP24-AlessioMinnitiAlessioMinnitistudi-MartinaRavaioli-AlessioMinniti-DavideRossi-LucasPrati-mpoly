package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;
import java.util.Set;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.monopoly.controller.GameControllerImpl;
import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.view.api.AccountPanel;
import it.unibo.monopoly.view.api.ContractPanel;
import it.unibo.monopoly.view.api.GameControllerAction;
import it.unibo.monopoly.view.api.GameActionsPanel;
import it.unibo.monopoly.view.api.GamePanelsFactory;
import it.unibo.monopoly.view.api.MainGameView;
import it.unibo.monopoly.view.api.PlayerPanel;
import it.unibo.monopoly.view.api.StandardControlsPanel;
import it.unibo.monopoly.view.impl.gamepanels.SwingPanelsFactory;

//RENAME CLASS FOR BETTER UNDERSTANDABILITY
public class MainViewImpl implements MainGameView{

    private final JFrame mainGameFrame = new JFrame();

    private final PlayerPanel playerInfoPanel;
    private final AccountPanel accountInfoPanel;
    private final ContractPanel contractPanel;
    private final GameActionsPanel gameActionsPanel;
    private final StandardControlsPanel mainActionsPanel;

    private MainViewImpl (final GameController controller) {
        GamePanelsFactory fact = new SwingPanelsFactory();
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
        mainGameFrame.getContentPane().add(buildActionPanelUI(controller),BorderLayout.CENTER);
        mainGameFrame.pack();
        mainGameFrame.setVisible(true);
    }

    private JPanel buildActionPanelUI(final GameController controller) {
        final JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BorderLayout());
        final JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BorderLayout());
        userInfoPanel.add((JPanel) playerInfoPanel, BorderLayout.NORTH);
        userInfoPanel.add((JPanel) accountInfoPanel,BorderLayout.SOUTH);

        actionPanel.add(userInfoPanel,BorderLayout.NORTH);
        actionPanel.add((JPanel) contractPanel,BorderLayout.CENTER);
        actionPanel.add((JPanel) gameActionsPanel,BorderLayout.WEST);
        actionPanel.add((JPanel) mainActionsPanel,BorderLayout.SOUTH);    
        return actionPanel;  
    }

    private void checkComponentIsJPanel(final Object oj) {
        if (oj.getClass().isAssignableFrom(JPanel.class)) {
            throw new ClassCastException("The object" + oj.toString() + "provided by the factory is not a JPanel");
        }
    }


    public static void main(final String[] args) {
        new MainViewImpl(new GameControllerImpl());
    }


    @Override
    public void displayCurrentPlayerInfo(final Player plData,final BankAccount accountData) {
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
    public void showPlayerActions(final Set<GameControllerAction> actions) {
        gameActionsPanel.buildActionButtons(actions);
    }
}
