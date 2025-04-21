package it.unibo.monopoly.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.monopoly.utils.Configuration;
import it.unibo.monopoly.utils.GuiUtils;

/**
 * SettingsWindow view.
 */
public final class SettingsWindowView extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final String TITLE_WINDOW = "Monopoly - Settings";
    private static final String TITLE_TEXT = "Select game mode";
    private static final String EXIT_TEXT = "Exit";
    private static final String INFINITY_TEXT = "Infinity Mode";
    private static final String CLASSIC_TEXT = "Classic Mode";

    // Grid layout
    private static final int ROWS = 1;
    private static final int COLS = 2;
    private static final int GAP = 10;

    // Size of buttons and empty borders
    private static final int TOP_BORDER = 10;
    private static final int BOTTOM_BORDER = 10;
    private static final int SIDE_BORDER = 20;


    /**
     * Creates a view that displays the game rules, importing them from a file.
     * <p>
     * The behavior of the view adapts to the provided {@link Configuration},
     * which defines essential game settings such as window size, font size
     *
     * @param parent the parent frame that owns this dialog and will be blocked while the dialog is visible
     * @param config the configuration object containing the base settings for the game
     */
    public SettingsWindowView(final Frame parent, final Configuration config) {
        GuiUtils.configureWindow(this,
                         config.getWindowWidth(),
                         config.getWindowHeight(),
                         TITLE_WINDOW,
                         new BorderLayout(),
                         parent);
        // Create a Main Panel for all the next items
        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(TOP_BORDER, SIDE_BORDER, BOTTOM_BORDER, SIDE_BORDER));
        add(mainPanel);

        final JLabel titleLabel = new JLabel(TITLE_TEXT, SwingConstants.CENTER);
        titleLabel.setFont(new Font(config.getFontName(), Font.BOLD, config.getBigFont()));
        titleLabel.setForeground(Color.RED);

        // Create buttons for settings the game mode and an exit button for the window
        final JButton classicModeButton = new JButton(CLASSIC_TEXT);
        final JButton infinityModeButton = new JButton(INFINITY_TEXT);
        final JButton exitButton = new JButton(EXIT_TEXT);

        // Adding action listener
        classicModeButton.addActionListener(e -> dispose());
        infinityModeButton.addActionListener(e -> dispose());
        exitButton.addActionListener(e -> dispose());

        // Create a panel for display all game mode and choose which one use
        final JPanel modePanel = new JPanel(new GridLayout(ROWS, COLS, GAP, GAP));
        modePanel.add(classicModeButton);
        modePanel.add(infinityModeButton);

        // Create a panel that contains modePanel, used for vertical alignement
        final JPanel centerWrapper = new JPanel();
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));
        centerWrapper.add(Box.createVerticalGlue());
        centerWrapper.add(modePanel);
        centerWrapper.add(Box.createVerticalGlue());

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);
        mainPanel.add(exitButton, BorderLayout.SOUTH);
        GuiUtils.refresh(this);
    }
}
