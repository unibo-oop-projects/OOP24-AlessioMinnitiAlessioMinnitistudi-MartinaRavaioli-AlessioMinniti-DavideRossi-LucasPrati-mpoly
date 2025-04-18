package it.unibo.monopoly.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
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
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.utils.Configuration;

/**
 * MainMenuGUI view.
 */
public final class MainMenuView extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int TEN = 10;
    private static final int TWENTY = 20;
    private static final int FOURTY = 40;
    private static final int FIFTY = 50;

    private final Configuration config;
    private final MainMenuController controller;
    private final Map<Color, JTextField> nicknamePlayers = new HashMap<>();

    private JButton decreaseButton;
    private JButton increaseButton;
    private final JLabel numPlayersLabel = new JLabel();
    private final JPanel mainPanel = new JPanel(new BorderLayout());


    /**
     * Creates a new MainMenuView with his controller. Based on the given {@link Configuration}
     * @param config a consistent configuration for settings
     * @param controller the controller of the GUI
     */
    public MainMenuView(final Configuration config, final MainMenuController controller) {
        this.config = config;
        this.controller = controller;

        setTitle("Monopoly - Menu");
        setSize(this.config.getWindowWidth(), this.config.getWindowHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        add(mainPanel);
        showMainMenu();
        setVisible(true);
    }


    private void showMainMenu() {
        mainPanel.removeAll();

        final JLabel title = new JLabel("Monopoly", SwingConstants.CENTER);
        title.setFont(new Font(config.getFontName(), Font.BOLD, config.getBigFont()));
        title.setForeground(Color.RED);
        mainPanel.add(title, BorderLayout.NORTH);

        final JPanel menuPanel = new JPanel(new GridLayout(THREE, TWO, TEN, TEN));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(TWENTY, FIFTY, TWENTY, FIFTY));

        final JLabel playersLabel = new JLabel("Players:", SwingConstants.CENTER);
        playersLabel.setFont(new Font(config.getFontName(), Font.BOLD, config.getSmallFont()));

        numPlayersLabel.setFont(new Font(config.getFontName(), Font.BOLD, config.getSmallFont()));
        numPlayersLabel.setHorizontalAlignment(SwingConstants.CENTER);

        decreaseButton = new JButton("-");
        increaseButton = new JButton("+"); 

        decreaseButton.addActionListener(e -> {
            controller.decreaseNumPlayer();
            updateGUI();
        });

        increaseButton.addActionListener(e -> {
            controller.increaseNumPlayer();
            updateGUI();
        });

        final JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(e -> showPlayerSetupScreen());

        final JButton rulesButton = new JButton("?");
        rulesButton.addActionListener(e -> new RulesWindowView(this, config));

        menuPanel.add(playersLabel);
        menuPanel.add(numPlayersLabel);
        menuPanel.add(decreaseButton);
        menuPanel.add(increaseButton);
        menuPanel.add(continueButton);
        menuPanel.add(rulesButton);

        mainPanel.add(menuPanel, BorderLayout.CENTER);

        updateGUI();
        refresh();
    }


    private void showPlayerSetupScreen() {
        mainPanel.removeAll();

        final JLabel title = new JLabel("Set player nicknames", SwingConstants.CENTER);
        title.setFont(new Font(config.getFontName(), Font.BOLD, config.getBigFont()));
        mainPanel.add(title, BorderLayout.NORTH);

        final JPanel giocatoriPanel = new JPanel();
        giocatoriPanel.setLayout(new BoxLayout(giocatoriPanel, BoxLayout.Y_AXIS));
        giocatoriPanel.setBorder(BorderFactory.createEmptyBorder(TWENTY, TWENTY, TWENTY, TWENTY));

        for (int i = 0; i < controller.getNumPlayers(); i++) {
            final JPanel row = new JPanel(new BorderLayout(TEN, ZERO));

            final JLabel colorBox = new JLabel();
            colorBox.setOpaque(true);
            colorBox.setBackground(config.getPlayerColors().get(i));
            colorBox.setPreferredSize(new Dimension(FOURTY, FOURTY));

            final JTextField textField = new JTextField("Player " + (i + 1));
            nicknamePlayers.put(colorBox.getBackground(), textField);

            row.add(colorBox, BorderLayout.WEST);
            row.add(textField, BorderLayout.CENTER);

            giocatoriPanel.add(row);
            giocatoriPanel.add(Box.createVerticalStrut(TEN));
        }

        final JScrollPane scrollPane = new JScrollPane(giocatoriPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        final JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> initializePlayers());

        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        southPanel.setBorder(BorderFactory.createEmptyBorder(TEN, TWENTY, TEN, TWENTY));
        southPanel.add(Box.createHorizontalGlue());
        southPanel.add(startGameButton);

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        refresh();
    }


    /**
     * Initializes the players according to the preferences entered by the users.
     * 
     * It collects the text from each {@link JTextField} in the {@code nicknamePlayers} map,
     * trims the inputs, and creates a new map of {@link Color} to {@link String}.
     * This map is then passed to the controller, which creates the {@link PlayerImpl} instances.
     */
    private void initializePlayers() {
        final Map<Color, String> playersSetup = nicknamePlayers.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,                      // chiave: Color
                e -> e.getValue().getText().trim()      // valore: testo dal JTextField pulito con trim()
            ));

        controller.onClickStart(playersSetup);
    }


    private void updateGUI() {
        numPlayersLabel.setText(String.valueOf(controller.getNumPlayers()));
        decreaseButton.setEnabled(!controller.alreadyMinPlayers());
        increaseButton.setEnabled(!controller.alreadyMaxPlayers());
    }


    private void refresh() {
        revalidate();
        repaint();
    }
}
