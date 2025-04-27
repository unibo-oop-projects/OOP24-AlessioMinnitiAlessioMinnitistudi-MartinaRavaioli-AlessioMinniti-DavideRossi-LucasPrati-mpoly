package it.unibo.monopoly.view.api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PawnSquare extends JPanel{
    private final Color color;

    public PawnSquare(Color color) {
        this.color = color;
        setPreferredSize(new Dimension(20, 20));
        setOpaque(false); // rende lo sfondo trasparente
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        // Disegna un quadrato pieno che riempie tutto il pannello
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
