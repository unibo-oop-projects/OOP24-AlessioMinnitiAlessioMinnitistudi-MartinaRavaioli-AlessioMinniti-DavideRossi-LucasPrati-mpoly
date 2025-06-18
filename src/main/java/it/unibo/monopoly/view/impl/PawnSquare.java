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
    private static final int SIZE = 20; /**default size. */
    private final Color color; /**color. */

    /**
     * Create an element with custom color and size.
     * @param color the custom color to apply
     * @param size the custom size to apply
     */
    public PawnSquare(final Color color, final int size) {
        this.color = color;
        setShapeSize(size);
        initOpaque(); // rende lo sfondo trasparente
    }

    /**
    * Create an element with custom color and default size.
    * @param color the custom color to apply
    */
    public PawnSquare(final Color color) {
        this(color, SIZE);
    }

    /**
     * Set size.
     * @param size the size to set
     */
    private void setShapeSize(final int size) {
        super.setPreferredSize(new Dimension(size, size));
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
