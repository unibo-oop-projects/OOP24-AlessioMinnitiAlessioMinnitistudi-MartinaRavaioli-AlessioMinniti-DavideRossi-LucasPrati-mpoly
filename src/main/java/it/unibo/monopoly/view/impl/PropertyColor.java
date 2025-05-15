package it.unibo.monopoly.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
/**
 * class used to draw the rectangle representing the color of a property.
 */
public final class PropertyColor extends JComponent {
    private static final long serialVersionUID = -6218820567019985015L;
    private Color color; 

    /**
     * the constructor sets the color that will be displayed.
     * @param color of the property
     */
    public PropertyColor(final Color color) {
        this.color = color;
    }

    @Override
    /**
     * this method is the one that actually draws the graphic.
     * @param g graphic object 
     */
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        g.setColor(color);
        final Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, 25, 25);
        g2d.fill(rect);

    }

    /**
     * changes the color og the graphic.
     * @param nColor
     */
    public void setColor(final Color nColor) {
        this.color = nColor; 
        this.repaint();
    }

}
