package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.monopoly.view.api.GameActionsPanel;
import it.unibo.monopoly.view.api.GameAction;

final class SwingGameActionsPanel extends JPanel implements GameActionsPanel {

    private static final long serialVersionUID = 1L;
    private static final String PLACEHOLDER = 
    """
        TIRA I DADI E MUOVI LA PEDINA
        PER AGGIORNARE LE AZIONI DISPONIBILI
    """;

    @Override
    public void clear() {
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new JLabel(PLACEHOLDER), BorderLayout.CENTER);
    }

    @Override
    public void buildActionButtons(final Set<GameAction> actions) {
        this.removeAll();
        this.setLayout(new GridLayout(actions.size(), 1));
        actions.stream().forEach(action -> {
            final JButton actionButton = new JButton(action.getName());
            actionButton.addActionListener(l -> action.execute());
            this.add(actionButton);
        });
    }

}
