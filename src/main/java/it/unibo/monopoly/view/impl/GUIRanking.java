package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.lang3.tuple.Pair;
/**
 * ranking view of the player after finishing the game.
 */
public class GUIRanking extends JFrame {
    private static final long serialVersionUID = 1L; /**serial version UID.*/
    private static final int RANKING_SIZE = 400; /**frame size. */
    private static final int LIST_SIZE = 300; /**list size. */
    private final JFrame frame = new JFrame();


    /**
     * constructor.
     * @param ranks ranking of the players
     * @param winner the winner
    */
    public GUIRanking(final List<Pair<String, Integer>> ranks, final Pair<String, Integer> winner) {
        showGUI(ranks, winner);
    }

    private void showGUI(final List<Pair<String, Integer>> ranks, final Pair<String, Integer> winner) {
        frame.setTitle("FINAL RANKING");
        final JPanel panel = new JPanel();
        //this.setSize(FRAME_SIZE, FRAME_SIZE);
        final DefaultListModel<Pair<String, Integer>> model = new DefaultListModel<>();

        for (final Pair<String, Integer> r : ranks) {
            model.addElement(r);
        }
        final JLabel label = new JLabel("The winner is: " + winner);
        final JList<Pair<String, Integer>> list = new JList<>(model);
        final JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setName("RANKING");
        scrollPane.setPreferredSize(new Dimension(LIST_SIZE, LIST_SIZE));
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);

        //this.pack();
        frame.setLocationRelativeTo(null);

        frame.setSize(new Dimension(RANKING_SIZE, RANKING_SIZE));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
