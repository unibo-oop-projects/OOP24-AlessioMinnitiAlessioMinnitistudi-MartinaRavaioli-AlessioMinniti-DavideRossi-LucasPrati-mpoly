package it.unibo.monopoly.view.api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PawnTriangle extends JPanel {
    private final Color color;

    public PawnTriangle(Color color) {
        this.color = color;
        setPreferredSize(new Dimension(20, 20));
        setOpaque(false); // Rende lo sfondo trasparente
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        int[] xPoints = { getWidth() / 2, 0, getWidth() };
        int[] yPoints = { 0, getHeight(), getHeight() };
        g.fillPolygon(xPoints, yPoints, 3);
    }
}
