package it.unibo.monopoly.view.api;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PawnRhombus extends JPanel{
    private final Color color;

    public PawnRhombus(Color color) {
        this.color = color;
        setPreferredSize(new Dimension(20, 20));
        setOpaque(false);
    }
}
