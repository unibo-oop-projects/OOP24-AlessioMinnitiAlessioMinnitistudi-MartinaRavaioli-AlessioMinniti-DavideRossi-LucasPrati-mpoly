package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.view.api.MenuPanel;


final class SwingMenuPanel extends SwingAbstractJPanel implements MenuPanel {

    private static final long serialVersionUID = 1L;

    // Main menu 
    private static final String TITLE_TEXT_MAIN = "Monopoly";
    private static final String PLAYERS_TEXT = "Players:";
    private static final String CONTINUE_TEXT = "Continue";
    private static final String MINUS_TEXT = "-";
    private static final String PLUS_TEXT = "+";
    private static final String RULES_TEXT = "?";
    private static final String SETTINGS_TEXT = "Settings";

    // Grid layout
    private static final int ZERO = 0;
    private static final int SINGLE = 1;
    private static final int ROWS = 3;
    private static final int COLS = 2;
    private static final int GAP = 10;

    // Size of boxes and empty borders
    private static final int TOP_BORDER = 10;

    private final JLabel numPlayersLabel;
    private final JButton decreaseButton;
    private final JButton increaseButton;


    SwingMenuPanel(final MainMenuController controller) {
        this.setLayout(new BorderLayout());
        final JLabel title = new JLabel(TITLE_TEXT_MAIN, SwingConstants.CENTER);
        title.setForeground(Color.RED);
        final JLabel playersLabel = new JLabel(PLAYERS_TEXT, SwingConstants.CENTER);
        numPlayersLabel = new JLabel(String.valueOf(controller.getNumPlayers()));
        numPlayersLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create buttons and their actionListener
        decreaseButton = new JButton(MINUS_TEXT);
        decreaseButton.addActionListener(e -> controller.onClickDecrease());

        increaseButton = new JButton(PLUS_TEXT); 
        increaseButton.addActionListener(e -> controller.onClickIncrease());

        final JButton rulesButton = new JButton(RULES_TEXT);
        rulesButton.addActionListener(e -> controller.onClickRules());

        final JButton settingsButton = new JButton(SETTINGS_TEXT);
        settingsButton.addActionListener(e -> controller.onClickSettings());

        final JButton continueButton = new JButton(CONTINUE_TEXT);
        continueButton.addActionListener(e -> controller.onClickContinue());

        // Create panel for display labels and use buttons
        final JPanel midPanel = new JPanel(new GridLayout(ROWS, COLS, GAP, GAP));
        midPanel.add(playersLabel);
        midPanel.add(numPlayersLabel);
        midPanel.add(decreaseButton);
        midPanel.add(increaseButton);
        midPanel.add(settingsButton);
        midPanel.add(rulesButton);

        // Create panel for use the button for skip to setup all the players
        final JPanel continuePanel = new JPanel(new GridLayout(SINGLE, SINGLE));
        continuePanel.setBorder(BorderFactory.createEmptyBorder(TOP_BORDER, ZERO, ZERO, ZERO));
        continuePanel.add(continueButton);

        this.add(title, BorderLayout.NORTH);
        this.add(midPanel, BorderLayout.CENTER);
        this.add(continuePanel, BorderLayout.SOUTH);
    }

    @Override
    public void renderDefaultUI() {
        increaseButton.setEnabled(true);
        decreaseButton.setEnabled(false);
    }

    @Override
    public void refreshNumPlayers(final int num, final boolean reachMinPlayers, final boolean reachMaxPlayers) {
        numPlayersLabel.setText(String.valueOf(num));
        decreaseButton.setEnabled(!reachMinPlayers);
        increaseButton.setEnabled(!reachMaxPlayers);
    }
}
