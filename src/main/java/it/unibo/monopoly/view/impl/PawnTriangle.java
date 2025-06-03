package it.unibo.monopoly.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
    * triangle pawn shape.
*/
public class PawnTriangle extends JPanel {
    private static final long serialVersionUID = 1L; /**serial version. */
    private static final int SIZE = 20; /**size. */
    private final Color color; /**color.*/
    /**
    * constructor.
    * @param color color
    */
    public PawnTriangle(final Color color) {
        this.color = color;
        setShapeSize();
        initOpaque(); // Rende lo sfondo trasparente
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
        final int[] xPoints = { getWidth() / 2, 0, getWidth() };
        final int[] yPoints = { 0, getHeight(), getHeight() };
        g.fillPolygon(xPoints, yPoints, 3);
    }
}
