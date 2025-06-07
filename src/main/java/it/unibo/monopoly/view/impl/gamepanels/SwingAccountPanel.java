package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.view.api.AccountPanel;

final class SwingAccountPanel extends SwingAbstractJPanel implements AccountPanel {

    private static final String BALANCE = "Balance:";
    private static final String BL_PLACEHOLDER = "No balance";
    private static final long serialVersionUID = 1L;


    private final JLabel balanceJLabel;

    SwingAccountPanel() {
        final GridBagLayout balancePanelLayout = new GridBagLayout();
        this.setLayout(balancePanelLayout);

        final JLabel balanceTitleLabel = new JLabel(BALANCE);
        balanceJLabel = new JLabel();
        balanceJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(balanceTitleLabel);
        this.add(balanceJLabel);

        final GridBagConstraints balanceTitleConstraints = new GridBagConstraints();
        balanceTitleConstraints.fill = GridBagConstraints.BOTH;
        balanceTitleConstraints.weighty = 1.0;
        balancePanelLayout.setConstraints(balanceTitleLabel, balanceTitleConstraints);

        final GridBagConstraints balanceLabelConstraints = new GridBagConstraints();
        balanceLabelConstraints.weighty = 1.0;
        balanceLabelConstraints.weightx = 1.0;
        balanceLabelConstraints.fill = GridBagConstraints.BOTH;
        balanceLabelConstraints.gridwidth = GridBagConstraints.REMAINDER;
        balancePanelLayout.setConstraints(balanceJLabel, balanceLabelConstraints);
    }

    @Override
    public void renderDefaultUI() {
        balanceJLabel.setText(BL_PLACEHOLDER);
    }

    @Override
    public void displayBankAccount(final BankAccount ba) {
        balanceJLabel.setText(Integer.toString(ba.getBalance()));
    }
}
