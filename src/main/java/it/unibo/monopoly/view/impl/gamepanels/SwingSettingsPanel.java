package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.view.api.SettingsPanel;


final class SwingSettingsPanel extends SwingAbstractJPanel implements SettingsPanel {

    private static final long serialVersionUID = 1L;

    // Settings Menu
    private static final String TITLE_TEXT_SETTINGS = "Select game mode";
    private static final String DONE_TEXT = "Done";
    private static final String INFINITY_TEXT = "Infinity Mode";
    private static final String CLASSIC_TEXT = "Classic Mode";

    // Grid layout
    private static final int SINGLE = 1;
    private static final int COLS = 2;
    private static final int GAP = 10;

    private final JButton classicModeButton;
    private final JButton infinityModeButton;


    SwingSettingsPanel(final MainMenuController controller) {
        this.setLayout(new BorderLayout());
        final JLabel title = new JLabel(TITLE_TEXT_SETTINGS, SwingConstants.CENTER);
        title.setForeground(Color.RED);

        // Create buttons for settings the game mode and an exit button, with action listeners
        classicModeButton = new JButton(CLASSIC_TEXT);
        infinityModeButton = new JButton(INFINITY_TEXT);
        final JButton doneButton = new JButton(DONE_TEXT);

        classicModeButton.addActionListener(e -> controller.onClickClassicMode());
        infinityModeButton.addActionListener(e -> controller.onClickInfinityMode());
        doneButton.addActionListener(e -> controller.onClickDone());

        // Create a panel for display all game mode and choose which one use
        final JPanel modePanel = new JPanel(new GridLayout(SINGLE, COLS, GAP, GAP));
        modePanel.add(classicModeButton);
        modePanel.add(infinityModeButton);

        // Create a panel that contains modePanel, used for vertical alignement
        final JPanel centerWrapper = new JPanel();
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));
        centerWrapper.add(Box.createVerticalGlue());
        centerWrapper.add(modePanel);
        centerWrapper.add(Box.createVerticalGlue());

        this.add(title, BorderLayout.NORTH);
        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(doneButton, BorderLayout.SOUTH);
    }

    @Override
    public void renderDefaultUI() {
        classicModeButton.setEnabled(false);    // In the beginning the type is set to CLASSIC
        infinityModeButton.setEnabled(true);
    }

    @Override
    public void refreshSettingsData(final BankAccountType type) {
        classicModeButton.setEnabled(!type.equals(BankAccountType.CLASSIC));
        infinityModeButton.setEnabled(!type.equals(BankAccountType.INFINITY));
    }
}
