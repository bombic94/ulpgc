/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ulpgc.eii.diu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

/**
 *
 * @author David
 */
public class ImagePanel extends JPanel {
    
    /**
     * Load the necessary library
     */
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * Mat object representing image
     */
    private Mat mat;
    
    /**
     * Method used to paint image after change.
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mat != null) {
            BufferedImage image = (BufferedImage) HighGui.toBufferedImage(mat);
            g.drawImage(image, 0, 0, this);    
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        if (mat == null) {
            return new Dimension(0,0);
        }
        return new Dimension(mat.cols(), mat.rows());
    }
    /**
     * Setter of Mat object
     * @param mat Mat object
     */
    public void setMat(Mat mat) {
        this.mat = mat;
    }
    
    /**
     * Getter of Mat object
     * @return Mat object
     */
    public Mat getMat() {
        return this.mat;
    }
}
