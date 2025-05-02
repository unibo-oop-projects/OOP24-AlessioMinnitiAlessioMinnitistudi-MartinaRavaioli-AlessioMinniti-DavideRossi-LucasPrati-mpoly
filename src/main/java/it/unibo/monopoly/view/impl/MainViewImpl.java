package it.unibo.monopoly.view.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.view.api.MainView;

public class MainViewImpl implements MainView{

    private static final TitleDeed prova = new BaseTitleDeed("viola", "bastoni gran sasso", 20, s -> s / 2, 5);
    private final JFrame mainFrame = new JFrame();
    private final JPanel tilePanel = new ContractPanel(prova);

    private MainViewImpl () {
        mainFrame.add(tilePanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }


    public static void main(final String[] args) {
        new MainViewImpl();
    }
}
