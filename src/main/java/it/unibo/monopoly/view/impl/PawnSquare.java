package it.unibo.monopoly.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
    * square pawn shape.
*/
public class PawnSquare extends JPanel {
    private static final long serialVersionUID = 1L; /**serial version. */
    private static final int SIZE = 20; /**size. */
    private final Color color; /**color. */
    /**
    * constructor.
    * @param color color
    */
    public PawnSquare(final Color color) {
        this.color = color;
        setShapeSize();
        initOpaque(); // rende lo sfondo trasparente
    }
    /**
    * set size.
    */
    private void setShapeSize() {
        super.setPreferredSize(new Dimension(SIZE, SIZE));
    }
    /**
    * set opaque.
    */
    private void initOpaque() {
        super.setOpaque(false); // transparent background
    }

    @Override
    protected final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        // Disegna un quadrato pieno che riempie tutto il pannello
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    /**
    * get the color.
    * @return Color
    */
    public Color getColor() {
        return this.color;
    }
}
