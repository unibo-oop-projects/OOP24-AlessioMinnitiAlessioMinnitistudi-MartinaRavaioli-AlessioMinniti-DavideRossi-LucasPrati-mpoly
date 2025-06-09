package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.controller.impl.MainMenuControllerImpl;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.utils.impl.Configuration;
import it.unibo.monopoly.utils.impl.GuiUtils;

/**
 * MainMenuGUI view.
 */
public final class MainMenuView extends JFrame {

    private static final long serialVersionUID = 1L;

    // Grid layout
    private static final int ZERO = 0;
    private static final int SINGLE = 1;
    private static final int ROWS = 3;
    private static final int COLS = 2;
    private static final int GAP = 10;

    // Size of boxes and empty borders
    private static final int TOP_BORDER = 10;
    private static final int BOTTOM_BORDER = 10;
    private static final int SIDE_BORDER = 20;
    private static final int COLOR_BOX_SIZE = 40;

    // Main menu 
    private static final String TITLE_WINDOW = "Monopoly - Main Menu";
    private static final String TITLE_TEXT_MAIN = "Monopoly";
    private static final String PLAYERS_TEXT = "Players:";
    private static final String CONTINUE_TEXT = "Continue";
    private static final String MINUS_TEXT = "-";
    private static final String PLUS_TEXT = "+";
    private static final String RULES_TEXT = "?";
    private static final String SETTINGS_TEXT = "settings";

    // Player Setup Screen
    private static final String TITLE_TEXT_PLAYER_SETUP = "Set players nicknames";
    private static final String DEFAULT_PLAYER_TEXT = "Player ";
    private static final String START_TEXT = "Start";

    // Settings Menu
    private static final String TITLE_TEXT_SETTINGS = "Select game mode";
    private static final String EXIT_TEXT = "Exit";
    private static final String INFINITY_TEXT = "Infinity Mode";
    private static final String CLASSIC_TEXT = "Classic Mode";

    private final transient Configuration config;
    private final transient MainMenuController controller;
    private final Map<Color, JTextField> playersInfo = new LinkedHashMap<>();

    private JButton decreaseButton;
    private JButton increaseButton;
    private final JLabel numPlayersLabel = new JLabel();
    private JButton classicModeButton;
    private JButton infinityModeButton;
    private final JPanel mainPanel = new JPanel(new BorderLayout());


    /**
     * Creates a new MainMenuView with his controller. Based on the given {@link Configuration}
     * @param config a consistent configuration for settings
     */
    public MainMenuView(final Configuration config) {
        this.config = config;
        this.controller = new MainMenuControllerImpl(config);
        GuiUtils.configureWindow(this,
                                 (int) GuiUtils.getDimensionWindow().getWidth(),
                                 (int) GuiUtils.getDimensionWindow().getHeight(),
                                 TITLE_WINDOW);
        add(mainPanel);
        showMainMenu();
        setVisible(true);
    }


    private void showMainMenu() {
        mainPanel.removeAll();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(TOP_BORDER, SIDE_BORDER, BOTTOM_BORDER, SIDE_BORDER));

        numPlayersLabel.setHorizontalAlignment(SwingConstants.CENTER);

        final JLabel title = new JLabel(TITLE_TEXT_MAIN, SwingConstants.CENTER);
        title.setFont(getBigFont());
        title.setForeground(Color.RED);

        final JLabel playersLabel = new JLabel(PLAYERS_TEXT, SwingConstants.CENTER);

        // Create buttons and their actionListener
        decreaseButton = new JButton(MINUS_TEXT);
        decreaseButton.addActionListener(e -> {
            controller.decreaseNumPlayer();
            updateNumPlayers();
        });

        increaseButton = new JButton(PLUS_TEXT); 
        increaseButton.addActionListener(e -> {
            controller.increaseNumPlayer();
            updateNumPlayers();
        });

        final JButton rulesButton = new JButton(RULES_TEXT);
        rulesButton.addActionListener(e -> new RulesWindowView(this, config, controller.getRules()));

        final JButton settingsButton = new JButton(SETTINGS_TEXT);
        settingsButton.addActionListener(e -> showSettingsMenu());

        final JButton continueButton = new JButton(CONTINUE_TEXT);
        continueButton.addActionListener(e -> showPlayerSetupScreen());

        // Create panel for display players and use buttons
        final JPanel menuPanel = new JPanel(new GridLayout(ROWS, COLS, GAP, GAP));
        menuPanel.add(playersLabel);
        menuPanel.add(numPlayersLabel);
        menuPanel.add(decreaseButton);
        menuPanel.add(increaseButton);
        menuPanel.add(settingsButton);
        menuPanel.add(rulesButton);

        // Create panel for use the button for skip to setup all the players
        final JPanel continuePanel = new JPanel(new GridLayout(SINGLE, SINGLE));
        continuePanel.setBorder(BorderFactory.createEmptyBorder(TOP_BORDER, ZERO, ZERO, ZERO));
        continuePanel.add(continueButton);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        mainPanel.add(continuePanel, BorderLayout.SOUTH);
        updateNumPlayers();
        GuiUtils.refresh(this);
    }


    private void showPlayerSetupScreen() {
        mainPanel.removeAll();
        playersInfo.clear();

        final JLabel title = new JLabel(TITLE_TEXT_PLAYER_SETUP, SwingConstants.CENTER);
        title.setFont(getBigFont());

        // Create panel for display the players info for edit
        final JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));

        // Create one row per player with color and editable nickname
        for (int i = 0; i < controller.getNumPlayers(); i++) {
            final JPanel row = new JPanel(new BorderLayout(GAP, GAP));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, COLOR_BOX_SIZE));
            final JLabel colorBox = GuiUtils.colorBoxFactory(config.getPlayerColors().get(i), COLOR_BOX_SIZE);
            final JTextField textField = new JTextField(DEFAULT_PLAYER_TEXT + (i + 1));

            row.add(colorBox, BorderLayout.WEST);
            row.add(textField, BorderLayout.CENTER);
            playersInfo.put(colorBox.getBackground(), textField);

            playersPanel.add(row);
            playersPanel.add(Box.createVerticalStrut(GAP));
        }

        // Create a scrollable view for the playersPanel
        final JScrollPane scrollPane = new JScrollPane(playersPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(TOP_BORDER, ZERO, BOTTOM_BORDER, ZERO));

        final JButton startGameButton = new JButton(START_TEXT);
        startGameButton.addActionListener(e -> initializePlayers());

        // Create a panel for the start button
        final JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.X_AXIS));
        startPanel.add(Box.createHorizontalGlue());
        startPanel.add(startGameButton);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(startPanel, BorderLayout.SOUTH);
        GuiUtils.refresh(this);
    }

    private void showSettingsMenu() {
        mainPanel.removeAll();
        final JLabel title = new JLabel(TITLE_TEXT_SETTINGS, SwingConstants.CENTER);
        title.setFont(getBigFont());
        title.setForeground(Color.RED);

        // Create buttons for settings the game mode and an exit button
        classicModeButton = new JButton(CLASSIC_TEXT);
        infinityModeButton = new JButton(INFINITY_TEXT);
        final JButton exitButton = new JButton(EXIT_TEXT);

        // Adding action listener
        classicModeButton.addActionListener(e -> {
            controller.setBankAccountType(BankAccountType.CLASSIC);
            updateSettingsButton();
        });
        infinityModeButton.addActionListener(e -> {
            controller.setBankAccountType(BankAccountType.INFINITY);
            updateSettingsButton();
        });
        exitButton.addActionListener(e -> showMainMenu());

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

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);
        mainPanel.add(exitButton, BorderLayout.SOUTH);
        updateSettingsButton();
        GuiUtils.refresh(this);
    }


    /**
     * Initializes the players according to the preferences entered by the users.
     * 
     * It collects the text from each {@link JTextField} in the {@code playersInfo} map,
     * trims the inputs, and creates a new map of {@link Color} to {@link String}.
     * This map is then passed to the controller, which creates the {@link PlayerImpl} instances.
     */
    private void initializePlayers() {
        final Map<Color, String> playersSetup = playersInfo.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,                      // chiave: Color
                e -> e.getValue().getText().trim(),     // valore: testo dal JTextField pulito da spazi extra
                (a, b) -> b,                            // risolve eventuali duplicati Colore mantenendo l'ultimo valore
                LinkedHashMap::new                      // preservo l'ordine di inserimento
            ));

        try {
            controller.onClickStart(playersSetup);

        } catch (final IOException e) {
            GuiUtils.showErrorAndExit(
                this,
                e.getMessage(),
                "Error loading Json");

        } catch (final UncheckedIOException e) {
            GuiUtils.showErrorAndExit(
                this,
                e.getMessage(),
                "Error parsing Json");

        }
        this.dispose();
    }

    private void updateSettingsButton() {
        classicModeButton.setEnabled(!controller.getBankAccountType().equals(BankAccountType.CLASSIC));
        infinityModeButton.setEnabled(!controller.getBankAccountType().equals(BankAccountType.INFINITY));
    }

    private void updateNumPlayers() {
        numPlayersLabel.setText(String.valueOf(controller.getNumPlayers()));
        decreaseButton.setEnabled(!controller.alreadyMinPlayers());
        increaseButton.setEnabled(!controller.alreadyMaxPlayers());
    }

    private Font getBigFont() {
        return GuiUtils.getBigFontFromConfiguration(config);
    }
}
