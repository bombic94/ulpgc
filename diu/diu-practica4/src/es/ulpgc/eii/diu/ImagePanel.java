/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ulpgc.eii.diu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Panel created for painting images.
 * Shows selected image with or without transformation
 * Used transformation can be blur or highlighting.
 */
public class ImagePanel extends JPanel {

    /**
     * Image that is shown in Panel
     */
    private BufferedImage image;

    /**
     * Matrix for highlight
     */
    private float[] highlightMatrix = {
            0.f, -1.f, 0.f,
            -1.f, 5.0f, -1.f,
            0.f, -1.f, 0.f};

    /**
     * Matrix for blur
     */
    private float[] blurMatrix = {
            0.111f, 0.111f, 0.111f,
            0.111f, 0.111f, 0.111f,
            0.111f, 0.111f, 0.111f,};

    /**
     * Method used to paint image after change.
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

    /**
     * Change the showed image.
     * This method is called when user changes the image he wants to view
     * and also when he checks to use transformations.
     * @param path Path to image in source folder
     * @param blur Boolean indicating whether image should be blurred
     * @param highlight Boolean indication whether image should be highlighted
     */
    public void changeImage(String path, boolean blur, boolean highlight) {
        try {
            image = ImageIO.read(new File(path));
            if (blur) {
                transform(blurMatrix);
            }
            if (highlight) {
                transform(highlightMatrix);
            }
        } catch (IOException ex) {
            System.out.println("Image could not be found");
        }
        this.repaint();
    }

    /**
     * Transform image with given matrix, matrix can be used to blur
     * or highlight the image.
     */
    private void transform(float[] matrix) {
        Kernel sharpkernel = new Kernel(3, 3, matrix);
        ConvolveOp sop = new ConvolveOp(sharpkernel, ConvolveOp.EDGE_NO_OP, null);
        image = sop.filter(image, null);
    }
}