package it.unibo.monopoly.view.impl;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.lang3.tuple.Pair;

public class GUIRanking extends JFrame {
    private static final long serialVersionUID = 1L; /**serial version UID.*/

    public GUIRanking(List<Pair<String, Integer>> ranks) {
        final JPanel panel = new JPanel();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
