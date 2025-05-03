package it.unibo.monopoly.view.impl;

import java.util.List;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.RentOptionFactory;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.transactions.impl.RentOptionFactoryImpl;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.view.api.MainGameView;

public class MainViewImpl extends JFrame implements MainGameView{

    private static final RentOptionFactory fact = new RentOptionFactoryImpl();
    private static final TitleDeed prova = new BaseTitleDeed("viola", "bastoni gran sasso", 20, s -> s / 2, 5, List.of(fact.allDeedsOfGroupWithSameOwner(5),fact.allDeedsOfGroupWithSameOwner(27)));
    private final JPanel tilePanel = ContractPanel.createCard(prova);

    private MainViewImpl () {
        mainFrame.add(tilePanel);
        /*Dimension d = new Dimension(100, 400);
        mainFrame.setSize(d);*/
        mainFrame.pack();
        mainFrame.setVisible(true);
    }


    public static void main(final String[] args) {
        new MainViewImpl();
    }


    @Override
    public void displayCurrentPlayerInfo(Player plData, BankAccount accountData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayCurrentPlayerInfo'");
    }
}
