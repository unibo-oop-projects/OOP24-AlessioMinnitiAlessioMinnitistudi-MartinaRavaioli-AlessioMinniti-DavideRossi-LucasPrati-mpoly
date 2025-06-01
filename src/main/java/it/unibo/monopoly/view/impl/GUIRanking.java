package it.unibo.monopoly.view.impl;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.apache.commons.lang3.tuple.Pair;

public class GUIRanking extends JFrame {
    private static final long serialVersionUID = 1L; /**serial version UID.*/

    public GUIRanking(List<Pair<String, Integer>> ranks) {
        DefaultListModel<Pair<String, Integer>> model = new DefaultListModel<>();

        for (Pair<String, Integer> r : ranks) {
            model.addElement(r);
        }

        JList<Pair<String, Integer>> list = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(list);
        this.add(scrollPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
