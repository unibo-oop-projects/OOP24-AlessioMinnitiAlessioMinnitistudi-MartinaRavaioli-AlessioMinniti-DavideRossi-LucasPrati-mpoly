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

import it.unibo.monopoly.controller.api.MainMenuLogic;
import it.unibo.monopoly.utils.PlayerSetup;

/**
 * MainMenuGUI view.
 */
public class MainMenuGUI extends JFrame {

    private static final int MIN_GIOCATORI = 2;
    private static final int MAX_GIOCATORI = 4;
    private static final String ERROR_COLOR = "Il numero dei colori è inferiore al numero massimo di player";
    private static final int BIG_FONT = 24;
    private static final int SMALL_FONT = 16;

    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int TEN = 10;
    private static final int TWENTY = 20;
    private static final int FOURTY = 40;
    private static final int FIFTY = 50;
    private static final int FOUR_HUNDRED = 400;
    private static final int FIVE_HUNDRED = 500;

    private int numGiocatori = MIN_GIOCATORI;
    private JButton menoButton, piuButton;
    private final JLabel giocatoriLabel = new JLabel();
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final MainMenuLogic logic;
    private final Color[] colors = {Color.BLUE, Color.RED, Color.GREEN.darker(), Color.ORANGE, Color.MAGENTA, Color.LIGHT_GRAY};
    private final Map<JTextField, Color> usernamePlayers = new HashMap<>();
    

    /**
     * Creates a new MainMenuGUI with his logic.
     * @param logic the logic of the GUI
     */
    public MainMenuGUI(final MainMenuLogic logic) {
        this.logic = logic;
        setTitle("Monopoly - Menu");
        setSize(FIVE_HUNDRED, FOUR_HUNDRED);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        add(mainPanel);
        mostraMenuIniziale();
        setVisible(true);
    }


    private void mostraMenuIniziale() {
        mainPanel.removeAll();

        final JLabel titolo = new JLabel("Monopoly", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, BIG_FONT));
        titolo.setForeground(Color.RED);
        mainPanel.add(titolo, BorderLayout.NORTH);

        final JPanel menuPanel = new JPanel(new GridLayout(THREE, TWO, TEN, TEN));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(TWENTY, FIFTY, TWENTY, FIFTY));

        giocatoriLabel.setText(String.valueOf(numGiocatori));
        giocatoriLabel.setFont(new Font("Arial", Font.BOLD, SMALL_FONT));
        giocatoriLabel.setHorizontalAlignment(SwingConstants.CENTER);

        menoButton = new JButton("-");
        piuButton = new JButton("+"); 

        menoButton.addActionListener(e -> {
            if (numGiocatori > MIN_GIOCATORI) {
                numGiocatori--;
                aggiornaGUI();
            }
        });

        piuButton.addActionListener(e -> {
            if (numGiocatori < MAX_GIOCATORI) {
                numGiocatori++;
                aggiornaGUI();
            }
        });

        final JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(e -> mostraSchermataGiocatori());

        final JButton rulesButton = new JButton("?");
        rulesButton.addActionListener(e -> logic.onClickShowRules());

        menuPanel.add(new JLabel("Giocatori:"));
        menuPanel.add(giocatoriLabel);
        menuPanel.add(menoButton);
        menuPanel.add(piuButton);
        menuPanel.add(continueButton);
        menuPanel.add(rulesButton);

        mainPanel.add(menuPanel, BorderLayout.CENTER);

        // partendo dal numero di giocatori minimo, disabilito in partenza la possibilità di decrementarlo
        menoButton.setEnabled(false);

        refresh();
    }


    private void mostraSchermataGiocatori() {
        mainPanel.removeAll();

        final JLabel titolo = new JLabel("Inserisci nomi giocatori", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, BIG_FONT));
        mainPanel.add(titolo, BorderLayout.NORTH);

        final JPanel giocatoriPanel = new JPanel();
        giocatoriPanel.setLayout(new BoxLayout(giocatoriPanel, BoxLayout.Y_AXIS));
        giocatoriPanel.setBorder(BorderFactory.createEmptyBorder(TWENTY, TWENTY, TWENTY, TWENTY));
        
        if (colors.length < MAX_GIOCATORI) {
            throw new IllegalStateException(ERROR_COLOR);
        }

        for (int i = 0; i < numGiocatori; i++) {
            final JPanel riga = new JPanel(new BorderLayout(TEN, ZERO));

            final JLabel colorBox = new JLabel();
            colorBox.setOpaque(true);
            colorBox.setBackground(colors[i]);
            colorBox.setPreferredSize(new Dimension(FOURTY, FOURTY));

            final JTextField textField = new JTextField("Player " + (i + 1));
            usernamePlayers.put(textField, colorBox.getBackground());    

            riga.add(colorBox, BorderLayout.WEST);
            riga.add(textField, BorderLayout.CENTER);

            giocatoriPanel.add(riga);
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
            for (final var player : usernamePlayers.entrySet()) {
                players.add(new PlayerSetup(player.getKey().getText(), player.getValue()));
            }
            logic.onClickStart(players);
    }


    private void aggiornaGUI() {
        giocatoriLabel.setText(String.valueOf(numGiocatori));
        menoButton.setEnabled(numGiocatori > MIN_GIOCATORI);
        piuButton.setEnabled(numGiocatori < MAX_GIOCATORI);
    }


    private void refresh() {
        revalidate();
        repaint();
    }
}
