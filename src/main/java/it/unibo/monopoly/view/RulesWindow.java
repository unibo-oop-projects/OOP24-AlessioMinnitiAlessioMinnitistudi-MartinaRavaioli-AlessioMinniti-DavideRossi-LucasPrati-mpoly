package it.unibo.monopoly.view;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class RulesWindow extends JFrame {

    public RulesWindow() {
        setTitle("Rules");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Rules", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.RED);
        add(titleLabel, BorderLayout.NORTH);

        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(rulesTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> dispose());
        add(exitButton, BorderLayout.SOUTH);

        loadRulesFromFile(rulesTextArea);
        updateView();
    }

    private void loadRulesFromFile(JTextArea textArea) {
        try (InputStream is = getClass().getResourceAsStream("/rules.txt")) {
            if (is == null) {
                textArea.setText("Impossibile trovare il file delle regole.");
            } else {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                    String rules = reader.lines().collect(Collectors.joining("\n"));
                    textArea.setText(rules);
                }
            }
        } catch (Exception e) {
            textArea.setText("Errore durante la lettura del file delle regole.");
        }
    }

    private void updateView() {
        revalidate();
        repaint();
        setVisible(true);
    }
}
