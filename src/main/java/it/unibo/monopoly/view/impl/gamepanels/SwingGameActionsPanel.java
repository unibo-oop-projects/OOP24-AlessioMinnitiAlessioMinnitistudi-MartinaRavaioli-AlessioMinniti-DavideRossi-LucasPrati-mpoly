package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;

import it.unibo.monopoly.view.api.GameActionsPanel;
import it.unibo.monopoly.view.api.GameAction;

final class SwingGameActionsPanel extends SwingAbstractJPanel implements GameActionsPanel {

    private static final long serialVersionUID = 1L;
    private static final int BUTTONS_PH_WIDTH = 120;
    private static final int BUTTONS_PH_HEIGHT = 100;
    private static final String PLACEHOLDER = 
    """
    TIRA I DADI E MUOVI LA PEDINA
    PER AGGIORNARE LE AZIONI DISPONIBILI
    """;

    SwingGameActionsPanel() {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
 
    @Override
    public void clear() {
        this.removeAll();
        this.setLayout(new BorderLayout());
        final JTextArea textPlaceholder = new JTextArea(PLACEHOLDER);
        textPlaceholder.setLineWrap(true);
        textPlaceholder.setWrapStyleWord(true);
        textPlaceholder.setEditable(false);
        textPlaceholder.setPreferredSize(new Dimension(BUTTONS_PH_WIDTH, BUTTONS_PH_HEIGHT));
        this.add(textPlaceholder, BorderLayout.CENTER);
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
