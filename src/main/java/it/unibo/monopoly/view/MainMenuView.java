package it.unibo.monopoly.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import it.unibo.monopoly.utils.Configuration;
import it.unibo.monopoly.utils.PlayerSetup;

/**
 * MainMenuGUI view.
 */
public class MainMenuView extends JFrame {

    private static final int BIG_FONT = 24;
    private static final int SMALL_FONT = 16;

    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int TEN = 10;
    private static final int TWENTY = 20;
    private static final int FOURTY = 40;
    private static final int FIFTY = 50;

    private final int minPlayers;
    private final int maxPlayers;
    private final int height;
    private final int width;
    private final MainMenuController controller;
    private final List<Color> colors;
    private final Map<JTextField, Color> nicknamePlayers = new HashMap<>();

    private int numPlayers;
    private JButton decreaseButton, increaseButton;
    private final JLabel playersLabel = new JLabel();
    private final JPanel mainPanel = new JPanel(new BorderLayout());


    /**
     * Creates a new MainMenuGUI with his controller.
     * @param config a consistent configuration for initialise fields 
     * @param controller the controller of the GUI
     */
    public MainMenuView(final Configuration config, final MainMenuController controller) {
        this.minPlayers = config.getMinPlayer();
        this.maxPlayers = config.getMaxPlayer();
        this.width = config.getWindowWidth();
        this.height = config.getWindowHeight();
        this.colors = config.getPlayerColors();
        this.controller = controller;
        this.numPlayers = minPlayers;

        setTitle("Monopoly - Menu");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        add(mainPanel);
        showMainMenu();
        setVisible(true);
    }


    private void showMainMenu() {
        mainPanel.removeAll();

        final JLabel title = new JLabel("Monopoly", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, BIG_FONT));
        title.setForeground(Color.RED);
        mainPanel.add(title, BorderLayout.NORTH);

        final JPanel menuPanel = new JPanel(new GridLayout(THREE, TWO, TEN, TEN));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(TWENTY, FIFTY, TWENTY, FIFTY));

        playersLabel.setText(String.valueOf(numPlayers));
        playersLabel.setFont(new Font("Arial", Font.BOLD, SMALL_FONT));
        playersLabel.setHorizontalAlignment(SwingConstants.CENTER);

        decreaseButton = new JButton("-");
        increaseButton = new JButton("+"); 

        decreaseButton.addActionListener(e -> {
            if (numPlayers > minPlayers) {
                numPlayers--;
                updateGUI();
            }
        });

        increaseButton.addActionListener(e -> {
            if (numPlayers < maxPlayers) {
                numPlayers++;
                updateGUI();
            }
        });

        final JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(e -> showPlayerSetupScreen());

        final JButton rulesButton = new JButton("?");
        rulesButton.addActionListener(e -> controller.onClickShowRules());

        menuPanel.add(new JLabel("Players:"));
        menuPanel.add(playersLabel);
        menuPanel.add(decreaseButton);
        menuPanel.add(increaseButton);
        menuPanel.add(continueButton);
        menuPanel.add(rulesButton);

        mainPanel.add(menuPanel, BorderLayout.CENTER);

        // partendo dal numero di giocatori minimo, disabilito in partenza la possibilità di decrementarlo
        decreaseButton.setEnabled(false);

        refresh();
    }


    private void showPlayerSetupScreen() {
        mainPanel.removeAll();

        final JLabel title = new JLabel("Set player nicknames", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, BIG_FONT));
        mainPanel.add(title, BorderLayout.NORTH);

        final JPanel giocatoriPanel = new JPanel();
        giocatoriPanel.setLayout(new BoxLayout(giocatoriPanel, BoxLayout.Y_AXIS));
        giocatoriPanel.setBorder(BorderFactory.createEmptyBorder(TWENTY, TWENTY, TWENTY, TWENTY));


        for (int i = 0; i < numPlayers; i++) {
            final JPanel row = new JPanel(new BorderLayout(TEN, ZERO));

            final JLabel colorBox = new JLabel();
            colorBox.setOpaque(true);
            colorBox.setBackground(colors.get(i));
            colorBox.setPreferredSize(new Dimension(FOURTY, FOURTY));

            final JTextField textField = new JTextField("Player " + (i + 1));
            nicknamePlayers.put(textField, colorBox.getBackground());

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


    private void initializePlayers() {
        final List<PlayerSetup> players = new ArrayList<>();
            for (final var player : nicknamePlayers.entrySet()) {
                players.add(new PlayerSetup(player.getKey().getText(), player.getValue()));
            }
            controller.onClickStart(players);
    }


    private void updateGUI() {
        playersLabel.setText(String.valueOf(numPlayers));
        decreaseButton.setEnabled(numPlayers > minPlayers);
        increaseButton.setEnabled(numPlayers < maxPlayers);
    }


    private void refresh() {
        revalidate();
        repaint();
    }
}
