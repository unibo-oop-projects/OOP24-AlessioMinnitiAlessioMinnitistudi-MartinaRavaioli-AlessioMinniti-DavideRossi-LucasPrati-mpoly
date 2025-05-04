package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.monopoly.controller.GameControllerImpl;
import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.view.api.AccountPanel;
import it.unibo.monopoly.view.api.ContractPanel;
import it.unibo.monopoly.view.api.GamePanelsFactory;
import it.unibo.monopoly.view.api.MainGameView;
import it.unibo.monopoly.view.api.PlayerPanel;

//RENAME CLASS FOR BETTER UNDERSTANDABILITY
public class MainViewImpl extends JFrame implements MainGameView{

    private final PlayerPanel playerInfoPanel;
    private final AccountPanel accountInfoPanel;
    private final ContractPanel contractPanel;
    private final JPanel mainActionsPanel;

    private MainViewImpl (final GameController controller) {
        GamePanelsFactory fact = new SwingPanelsFactory();
        contractPanel = fact.contractPanel();
        checkComponentIsJPanel(contractPanel);
        playerInfoPanel = fact.userInfoPanel();
        checkComponentIsJPanel(playerInfoPanel);
        accountInfoPanel = fact.bankAccountInfoPanel();
        checkComponentIsJPanel(accountInfoPanel);
        mainActionsPanel = (JPanel) fact.mainCommandsPanel(controller);
        this.getContentPane().add(buildActionPanelUI(controller),BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private JPanel buildActionPanelUI (final GameController controller) {
        final JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BorderLayout());
        final JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BorderLayout());
        userInfoPanel.add((JPanel) playerInfoPanel, BorderLayout.NORTH);
        userInfoPanel.add((JPanel) accountInfoPanel,BorderLayout.SOUTH);

        actionPanel.add(userInfoPanel,BorderLayout.NORTH);
        actionPanel.add((JPanel) contractPanel,BorderLayout.CENTER);
        //actionPanel.add(actionsPanel(controller),BorderLayout.WEST);
        actionPanel.add(mainActionsPanel,BorderLayout.SOUTH);    
        return actionPanel;  
    }

    private void checkComponentIsJPanel (Object oj) {
        if (oj.getClass().isAssignableFrom(JPanel.class)) {
            throw new ClassCastException("The object" + oj.toString() + "provided by the factory is not a JPanel");
        }
    }


    public static void main(final String[] args) {
        new MainViewImpl(new GameControllerImpl());
    }


    @Override
    public void displayCurrentPlayerInfo(Player plData, BankAccount accountData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayCurrentPlayerInfo'");
    }

    @Override
    public void displayPropertyContract(TitleDeed propertyContract) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayPropertyContract'");
    }
}
