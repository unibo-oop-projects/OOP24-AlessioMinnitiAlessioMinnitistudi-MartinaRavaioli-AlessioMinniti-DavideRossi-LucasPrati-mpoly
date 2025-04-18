package it.unibo.monopoly.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import it.unibo.monopoly.utils.Configuration;


/**
 * RulesWindow view.
 */
public final class RulesWindowView extends JDialog {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a view that displays the game rules, importing them from a file.
     * <p>
     * The behavior of the view adapts to the provided {@link Configuration},
     * which defines essential game settings such as window size, font size
     *
     * @param parent the parent frame that owns this dialog and will be blocked while the dialog is visible
     * @param config the configuration object containing the base settings for the game
     */
    public RulesWindowView(final Frame parent, final Configuration config) {

        super(parent, "Rules", true);

        setSize(config.getWindowWidth(), config.getWindowHeight());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        final JLabel titleLabel = new JLabel("Rules", SwingConstants.CENTER);
        titleLabel.setFont(new Font(config.getFontName(), Font.BOLD, config.getBigFont()));
        titleLabel.setForeground(Color.RED);
        add(titleLabel, BorderLayout.NORTH);

        final JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setFont(new Font(config.getFontName(), Font.PLAIN, config.getSmallFont()));
        final JScrollPane scrollPane = new JScrollPane(rulesTextArea);
        add(scrollPane, BorderLayout.CENTER);

        final JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> dispose());
        add(exitButton, BorderLayout.SOUTH);

        loadRulesFromFile(rulesTextArea, config.getRulesFilenamename());
        updateView();
    }

    private void loadRulesFromFile(final JTextArea textArea, final String filename) {
        // Filename is safe: already validated in configuration, no need to check here
        try (InputStream is = getClass().getResourceAsStream("/" + filename)) {
            if (is == null) {
                textArea.setText("Impossibile trovare il file delle regole.");
            } else {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                    final String rules = reader.lines().collect(Collectors.joining("\n"));
                    textArea.setText(rules);
                }
            }
        } catch (final IOException  e) {
            textArea.setText("Errore durante la lettura del file delle regole [ " + filename + " ].");
        }
    }

    private void updateView() {
        revalidate();
        repaint();
        setVisible(true);
    }
}
