package it.unibo.monopoly.view.api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PawnCircle extends JPanel {
    private final Color color;

    public PawnCircle(Color color) {
        this.color = color;
        setPreferredSize(new Dimension(20, 20));
        setOpaque(false); // Rende lo sfondo trasparente
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}
