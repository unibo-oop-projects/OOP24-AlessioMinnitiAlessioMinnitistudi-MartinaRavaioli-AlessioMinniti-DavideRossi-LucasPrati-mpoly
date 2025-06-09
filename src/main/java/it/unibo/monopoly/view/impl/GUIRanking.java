package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monopoly.utils.impl.GuiUtils;
/**
 * ranking view of the player after finishing the game.
 */
public class GUIRanking extends JDialog {

    private static final long serialVersionUID = 1L; /**serial version UID.*/
    private static final String TITLE = "FINAL RANKING";
    private static final int LIST_SIZE = 200; /**list size. */

    /**
     * constructor.
     * @param parent the parent frame that owns this dialog and will be blocked while the dialog is visible
     * @param ranks ranking of the players
     * @param winner the winner
    */
    public GUIRanking(final Frame parent, final List<Pair<String, Integer>> ranks, final Pair<String, Integer> winner) {
        showGUI(parent, ranks, winner);
    }

    private void showGUI(final Frame parent, final List<Pair<String, Integer>> ranks, final Pair<String, Integer> winner) {
        final Dimension size = GuiUtils.getDimensionWindow();
        GuiUtils.configureWindow(
            this,
            (int) size.getWidth(),
            (int) size.getHeight(),
            TITLE,
            new BorderLayout(),
            parent
        );

        final JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        final DefaultListModel<Pair<String, Integer>> model = new DefaultListModel<>();

        for (final Pair<String, Integer> r : ranks) {
            model.addElement(r);
        }
        final JLabel label = new JLabel("The winner is: " + winner.getKey() + ", " + winner.getValue());
        final JList<Pair<String, Integer>> list = new JList<>(model);
        final JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setName("RANKING");
        scrollPane.setPreferredSize(new Dimension(LIST_SIZE, LIST_SIZE));
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        GuiUtils.refresh(this);
    }
}
