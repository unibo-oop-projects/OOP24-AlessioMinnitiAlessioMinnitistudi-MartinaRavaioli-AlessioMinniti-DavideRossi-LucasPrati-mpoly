package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.utils.impl.GuiUtils;
import it.unibo.monopoly.view.api.MainMenuView;

public final class MainMenuViewImpl implements MainMenuView{
    
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
    private JButton decreaseButton;
    private JButton increaseButton;
    private final JLabel numPlayersLabel = new JLabel();

    // Player Setup Screen
    private static final String TITLE_TEXT_PLAYER_SETUP = "Set players nicknames";
    private static final String DEFAULT_PLAYER_TEXT = "Player ";
    private static final String START_TEXT = "Start";

    // Settings Menu
    private static final String TITLE_TEXT_SETTINGS = "Select game mode";
    private static final String DONE_TEXT = "Done";
    private static final String INFINITY_TEXT = "Infinity Mode";
    private static final String CLASSIC_TEXT = "Classic Mode";
    private JButton classicModeButton;
    private JButton infinityModeButton;

    // Container
    private final JFrame mainFrame = new JFrame();
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final JPanel menuPanel;
    private final JPanel settingsPanel;
    private final JPanel setupPanel;

    private final MainMenuController controller;
    private final Map<Color, JTextField> playersInfo = new LinkedHashMap<>();


    /**
     * Assembles the UI of the menu interface and adds all components to {@code mainFrame} object.
     * The {@code mainFrame} is a {@link JFrame}. 
     * @param controller the {@link MainMenuController} that captures the events 
     * happening on this view, implementing the observer pattern. 
     * User events will be captured and handled by the {@code controller} provided to this constructor.
     */
    // @SuppressFBWarnings(value = "EI_EXPOSE_REP",
    //             justification = "Must return reference to the object instead of a copy")
    public MainMenuViewImpl(final MainMenuController controller) {
        this.controller = controller;
        GuiUtils.configureWindow(mainFrame,
                                 (int) GuiUtils.getDimensionWindow().getWidth(),
                                 (int) GuiUtils.getDimensionWindow().getHeight(),
                                 TITLE_WINDOW
        );
        menuPanel = createMainMenuPanel();
        settingsPanel = createSettingsPanel();
        setupPanel = createSetupPanel();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(TOP_BORDER, SIDE_BORDER, BOTTOM_BORDER, SIDE_BORDER));
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        displayMainMenu();
    }

    @Override
    public void displayMainMenu() {
        mainPanel.removeAll();
        mainPanel.add(menuPanel);
        GuiUtils.refresh(mainFrame);
    }

    @Override
    public void displaySettingsMenu() {
        mainPanel.removeAll();
        mainPanel.add(settingsPanel);
        GuiUtils.refresh(mainFrame);
    }

    @Override
    public void displaySetupMenu() {
        mainPanel.removeAll();
        mainPanel.add(setupPanel);
        GuiUtils.refresh(mainFrame);
    }

    @Override
    public void displayRules(String rules) {
        new RulesWindowView(this.mainFrame, rules);
    }

    @Override
    public void displayErrorAndExit(final String title, final String message) {
        GuiUtils.showErrorAndExit(mainFrame, title, message);
        mainFrame.dispose();
    }

    @Override
    public void refreshSettingsData() {
        classicModeButton.setEnabled(!controller.getBankAccountType().equals(BankAccountType.CLASSIC));
        infinityModeButton.setEnabled(!controller.getBankAccountType().equals(BankAccountType.INFINITY));
    }

    @Override
    public void refreshNumPlayers() {
        numPlayersLabel.setText(String.valueOf(controller.getNumPlayers()));
        decreaseButton.setEnabled(!controller.alreadyMinPlayers());
        increaseButton.setEnabled(!controller.alreadyMaxPlayers());
    }



    private JPanel createMainMenuPanel() {
        final var panel = new JPanel(new BorderLayout());

        final JLabel title = new JLabel(TITLE_TEXT_MAIN, SwingConstants.CENTER);
        title.setForeground(Color.RED); 
        final JLabel playersLabel = new JLabel(PLAYERS_TEXT, SwingConstants.CENTER);
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

        panel.add(title, BorderLayout.NORTH);
        panel.add(midPanel, BorderLayout.CENTER);
        panel.add(continuePanel, BorderLayout.SOUTH);
        refreshNumPlayers();
        return panel;
    }



    private JPanel createSetupPanel() {
        final var panel = new JPanel(new BorderLayout());

        playersInfo.clear();

        final JLabel title = new JLabel(TITLE_TEXT_PLAYER_SETUP, SwingConstants.CENTER);

        // Create panel for display the players info for edit
        final JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));

        // Create one row per player with color and editable nickname
        for (int i = 0; i < controller.getNumPlayers(); i++) {
            final JPanel row = new JPanel(new BorderLayout(GAP, GAP));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, COLOR_BOX_SIZE));
            final var colorBox = new PawnSquare(
                controller.getConfiguration().getPlayerColors().get(i),
                COLOR_BOX_SIZE
            );
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
        startGameButton.addActionListener(e -> {
            controller.onClickStart(
                playersInfo.entrySet().stream()
                    .collect(Collectors.toMap(
                        Map.Entry::getKey,                      // chiave: Color
                        k -> k.getValue().getText().trim(),     // valore: testo dal JTextField pulito da spazi extra
                        (a, b) -> b,                            // risolve eventuali duplicati Colore mantenendo l'ultimo valore
                        LinkedHashMap::new                      // preservo l'ordine di inserimento
                ))
            );
            mainFrame.dispose();
        });

        // Create a panel for the start button
        final JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.X_AXIS));
        startPanel.add(Box.createHorizontalGlue());
        startPanel.add(startGameButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(startPanel, BorderLayout.SOUTH);
        return panel;
    }



    private JPanel createSettingsPanel() {
        final var panel = new JPanel(new BorderLayout());

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

        panel.add(title, BorderLayout.NORTH);
        panel.add(centerWrapper, BorderLayout.CENTER);
        panel.add(doneButton, BorderLayout.SOUTH);
        refreshSettingsData();
        return panel;

    }
}
