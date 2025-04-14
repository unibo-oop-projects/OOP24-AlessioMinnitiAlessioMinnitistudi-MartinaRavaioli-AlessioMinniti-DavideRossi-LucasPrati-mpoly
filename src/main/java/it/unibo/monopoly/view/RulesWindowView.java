package it.unibo.monopoly.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import it.unibo.monopoly.utils.Configuration;


/**
 * RulesWindow view.
 */
public class RulesWindowView extends JFrame {

    /**
     * Create a view with the game rules, importing them from a file.
     */
    public RulesWindowView(final Configuration config) {
        
        setTitle("Rules");
        setSize(config.getWindowWidth(), config.getWindowWidth());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        final JLabel titleLabel = new JLabel("Rules", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, config.getBigFont()));
        titleLabel.setForeground(Color.RED);
        add(titleLabel, BorderLayout.NORTH);

        final JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setFont(new Font("Arial", Font.PLAIN, config.getSmallFont()));
        final JScrollPane scrollPane = new JScrollPane(rulesTextArea);
        add(scrollPane, BorderLayout.CENTER);

        final JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> dispose());
        add(exitButton, BorderLayout.SOUTH);

        loadRulesFromFile(rulesTextArea);
        updateView();
    }

    private void loadRulesFromFile(final JTextArea textArea) {
        try (InputStream is = getClass().getResourceAsStream("/rules.txt")) {
            if (is == null) {
                textArea.setText("Impossibile trovare il file delle regole.");
            } else {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                    final String rules = reader.lines().collect(Collectors.joining("\n"));
                    textArea.setText(rules);
                }
            }
        } catch (final Exception e) {
            textArea.setText("Errore durante la lettura del file delle regole.");
        }
    }

    private void updateView() {
        revalidate();
        repaint();
        setVisible(true);
    }
}
