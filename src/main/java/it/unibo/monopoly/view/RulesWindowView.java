package it.unibo.monopoly.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import it.unibo.monopoly.utils.Configuration;
import it.unibo.monopoly.utils.GuiUtils;
import it.unibo.monopoly.utils.ResourceLoader;


/**
 * RulesWindow view.
 */
public final class RulesWindowView extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final String TITLE_WINDOW = "Monopoly - Rules";
    private static final String TITLE_TEXT = "Rules";
    private static final String EXIT_TEXT = "Exit";

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
    public RulesWindowView(final Frame parent, final Configuration config) {
        GuiUtils.configureWindow(this,
                                 config.getWindowWidth(),
                                 config.getWindowHeight(),
                                 TITLE_WINDOW,
                                 new BorderLayout(),
                                 parent);
        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(TOP_BORDER, SIDE_BORDER, BOTTOM_BORDER, SIDE_BORDER));
        add(mainPanel);

        final JLabel titleLabel = new JLabel(TITLE_TEXT, SwingConstants.CENTER);
        titleLabel.setFont(new Font(config.getFontName(), Font.BOLD, config.getBigFont()));
        titleLabel.setForeground(Color.RED);

        // Create a text area for display all the rules
        final JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setFont(new Font(config.getFontName(), Font.PLAIN, config.getSmallFont()));
        rulesTextArea.setText(ResourceLoader.loadTextResource(config.getRulesFilenamename()));

        // Create a scrollable view for the rulesTextArea
        final JScrollPane scrollPane = new JScrollPane(rulesTextArea);

        // Create an exit button for the window
        final JButton exitButton = new JButton(EXIT_TEXT);
        exitButton.addActionListener(e -> dispose());

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(exitButton, BorderLayout.SOUTH);

        GuiUtils.refresh(this);
    }
}
