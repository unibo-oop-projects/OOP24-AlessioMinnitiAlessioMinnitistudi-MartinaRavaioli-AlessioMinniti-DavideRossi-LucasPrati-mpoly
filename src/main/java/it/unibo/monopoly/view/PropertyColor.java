package it.unibo.monopoly.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class PropertyColor extends JComponent{
    private Color color; 

    public PropertyColor (Color color) {
        this.color = color;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(color);
        Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, 25, 25);
        g2d.fill(rect);

    }

    public void setColor(Color nColor) {
        this.color = nColor; 
        this.repaint();
    }

}
