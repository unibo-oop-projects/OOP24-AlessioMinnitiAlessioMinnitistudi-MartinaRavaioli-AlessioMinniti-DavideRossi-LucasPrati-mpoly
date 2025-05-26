package it.unibo.monopoly.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
    * circle pawn shape.
*/
public class PawnCircle extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int SIZE = 20;
    private final Color color;

    /**
    * constructor.
    * @param color
    */
    public PawnCircle(final Color color) {
        this.color = color;
        setShapeSize();
        initOpaque();
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
        g.fillOval(0, 0, getWidth(), getHeight());
    }
    /**
    * get the color.
    * @return Color
    */
    public Color getColor() {
        return this.color;
    }
}
