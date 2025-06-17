package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.view.api.SetupPanel;
import it.unibo.monopoly.view.impl.PawnSquare;

final class SwingSetupPanel extends SwingAbstractJPanel implements SetupPanel {

    private static final long serialVersionUID = 1L;

    // Player Setup Screen
    private static final String TITLE_TEXT_PLAYER_SETUP = "Set players nicknames";
    private static final String DEFAULT_PLAYER_TEXT = "Player ";
    private static final String START_TEXT = "Start";

    // Grid layout
    private static final int ZERO = 0;
    private static final int GAP = 10;

    // Size of boxes and empty borders
    private static final int TOP_BORDER = 10;
    private static final int BOTTOM_BORDER = 10;
    private static final int COLOR_BOX_SIZE = 40;


    private final Map<Color, JTextField> playersInfo = new LinkedHashMap<>();
    private final JPanel playersPanel;
    private final MainMenuController controller;



    SwingSetupPanel(final MainMenuController controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        final JLabel title = new JLabel(TITLE_TEXT_PLAYER_SETUP, SwingConstants.CENTER);

        playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));

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
            controller.disposeMainMenu();
        });

        // Create a panel for the start button
        final JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.X_AXIS));
        startPanel.add(Box.createHorizontalGlue());
        startPanel.add(startGameButton);

        this.add(title, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(startPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void renderDefaultUI() {
        playersInfo.clear();
        playersPanel.removeAll();

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
            playersInfo.put(colorBox.getColor(), textField);

            playersPanel.add(row);
            playersPanel.add(Box.createVerticalStrut(GAP));
        }
        playersPanel.revalidate();
        playersPanel.repaint();
    }
}
