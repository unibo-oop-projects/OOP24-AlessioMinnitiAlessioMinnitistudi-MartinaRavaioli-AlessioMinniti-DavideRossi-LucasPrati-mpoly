package it.unibo.monopoly.view.impl;

import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.lang3.tuple.Pair;

public class GUIRanking extends JFrame {
    private static final int FRAME_SIZE = 500;
    private static final int LIST_SIZE = 300;

    public GUIRanking(List<Pair<String, Integer>> ranks) {
        this.setTitle("FINAL RANKING");
        JPanel panel = new JPanel();
        this.setSize(FRAME_SIZE, FRAME_SIZE);
        DefaultListModel<Pair<String, Integer>> model = new DefaultListModel<>();

        for (Pair<String, Integer> r : ranks) {
            model.addElement(r);
        }

        JList<Pair<String, Integer>> list = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(LIST_SIZE, LIST_SIZE));
        panel.add(scrollPane);
        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
