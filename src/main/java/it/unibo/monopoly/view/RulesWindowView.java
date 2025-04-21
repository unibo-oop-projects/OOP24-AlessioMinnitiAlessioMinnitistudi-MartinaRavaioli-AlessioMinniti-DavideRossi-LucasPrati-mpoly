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

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import it.unibo.monopoly.utils.Configuration;
import it.unibo.monopoly.utils.GuiUtils;


/**
 * RulesWindow view.
 */
public final class RulesWindowView extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final String TITLE_WINDOW = "Monopoly - Rules";
    private static final String TITLE_TEXT = "Rules";
    private static final String EXIT_TEXT = "Exit";

    private static final String ERROR_FILE_NOT_FOUND = "Impossibile trovare il file delle regole: ";
    private static final String ERROR_DURING_READING_FILE = "Errore durante la lettura del file delle regole: ";


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
        GuiUtils.configureWindow(this,
                                 config.getWindowWidth(),
                                 config.getWindowHeight(),
                                 TITLE_WINDOW,
                                 new BorderLayout(),
                                 parent);

        final JLabel titleLabel = new JLabel(TITLE_TEXT, SwingConstants.CENTER);
        titleLabel.setFont(new Font(config.getFontName(), Font.BOLD, config.getBigFont()));
        titleLabel.setForeground(Color.RED);
        add(titleLabel, BorderLayout.NORTH);

        // Create a text area for display all the rules
        final JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setFont(new Font(config.getFontName(), Font.PLAIN, config.getSmallFont()));

        // Create a scrollable view for the rulesTextArea
        final JScrollPane scrollPane = new JScrollPane(rulesTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Create an exit button for the window
        final JButton exitButton = new JButton(EXIT_TEXT);
        exitButton.addActionListener(e -> dispose());
        add(exitButton, BorderLayout.SOUTH);

        loadRulesFromFile(rulesTextArea, config.getRulesFilenamename());
        GuiUtils.refresh(this);
    }

    private void loadRulesFromFile(final JTextArea textArea, final String filename) {
        // Filename is safe: already validated in configuration, no need to check here
        try (InputStream is = getClass().getResourceAsStream("/" + filename)) {
            if (is == null) {
                textArea.setText(ERROR_FILE_NOT_FOUND + filename);
            } else {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                    final String rules = reader.lines().collect(Collectors.joining("\n"));
                    textArea.setText(rules);
                }
            }
        } catch (final IOException  e) {
            textArea.setText(ERROR_DURING_READING_FILE + filename);
        }
    }
}
