package it.unibo.monopoly.view.impl;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.lang3.tuple.Pair;

public class GUIRanking extends JFrame {

    public GUIRanking(List<Pair<String, Integer>> ranks) {
        JPanel panel = new JPanel();
        this.setSize(300,300);
        DefaultListModel<Pair<String, Integer>> model = new DefaultListModel<>();

        for (Pair<String, Integer> r : ranks) {
            model.addElement(r);
        }

        JList<Pair<String, Integer>> list = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(list);
        panel.add(scrollPane);
        this.getContentPane().add(panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
