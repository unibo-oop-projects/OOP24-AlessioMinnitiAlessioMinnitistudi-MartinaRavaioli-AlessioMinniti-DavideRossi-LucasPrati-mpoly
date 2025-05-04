package it.unibo.monopoly.view.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.view.api.AccountPanel;

public class SwingAccountPanel extends JPanel implements AccountPanel{

    private static final String DESC = "Saldo";
    private static final String PLACEHOLDER = "None";

    public SwingAccountPanel() {
        clear();
    }

    @Override
    public void clear() {
        final GridBagLayout balancePanelLayout = new GridBagLayout();
        this.setLayout(balancePanelLayout);

        final JButton balanceTitleLabel = new JButton(DESC);
        final JButton balanceLabel = new JButton(PLACEHOLDER);
        this.add(balanceTitleLabel);
        this.add(balanceLabel);

        final GridBagConstraints balanceTitleConstraints = new GridBagConstraints();                
        balancePanelLayout.setConstraints(balanceTitleLabel, balanceTitleConstraints);

        final GridBagConstraints balanceLabelConstraints = new GridBagConstraints();
        balanceLabelConstraints.weightx = 1.0;
        balanceLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        balancePanelLayout.setConstraints(balanceLabel, balanceLabelConstraints);
    }

    @Override
    public void displayBankAccount(BankAccount ba) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayBankAccount'");
    }

}
