/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ulpgc.eii.diu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JPanel;

/**
 * Panel created for drawing with mouse.
 * It is designed to show the dotted trace of mouse drag.
 * Class contains Queue of points to draw and info about color to draw.
 */
public class DrawingPanel extends JPanel {

    public Queue<Point> positions = new LinkedList<>(); //queue of points
    private final int SIZE_OF_TRACE = 10; //size of circle in px
    private final int QUEUE_LENGTH = 5; //length of queue, how many points to show

    /**
     * Method used to draw into the label
     * @param g Graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //draw each point in queue as a filled circle
        positions.forEach((p) -> {
            g.fillOval(p.x, p.y, SIZE_OF_TRACE, SIZE_OF_TRACE);
        });

        // if queue is full (5 elements, remove the last one)
        if (positions.size() >= QUEUE_LENGTH) {
            positions.remove();
        }
    }

    /**
     * Sets foreground color from String
     * @param stringColor String representing color
     */
    public void setForegroundFromString(String stringColor) {
        Color color = getColorFromString(stringColor, Color.BLACK);
        this.setForeground(color);
    }

    /**
     * Sets background color from String
     * @param stringColor String representing color
     */
    public void setBackgroundFromString(String stringColor) {
        Color color = getColorFromString(stringColor, Color.WHITE);
        this.setBackground(color);
    }

    /**
     * Method that extracts color from string. If it cannot be extracted, it
     * sets the default value.
     * @param stringColor Color represented by string
     * @param defaultColor Color to be set if string cannot be resolved
     * @return Color object
     */
    private Color getColorFromString(String stringColor, Color defaultColor) {
        Color color;
        try {
            Field field = Class.forName("java.awt.Color").getField(stringColor);
            color = (Color) field.get(null);
        } catch (Exception e) {
            color = defaultColor;
        }
        return color;
    }
    
    /**
     * Add element to queue. 
     * Creates new point and adds to queue
     * @param x X position
     * @param y Y position
     */
    public void addToQueue(int x, int y){
        this.positions.add(new Point(x, y));
    }

    /**
     * Clear queue.
     * Removes all elements from queue.
     */
    public void clearQueue() {
        this.positions.clear();
    }
}
