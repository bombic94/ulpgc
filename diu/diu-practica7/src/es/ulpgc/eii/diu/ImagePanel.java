/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ulpgc.eii.diu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

/**
 *
 */
public class ImagePanel extends JPanel {

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
        //BufferedImage imagen = (BufferedImage) HighGui.toBufferedImage(imagen_mat);
        super.paintComponent(g);
        if (mat != null) {
            BufferedImage image = (BufferedImage) HighGui.toBufferedImage(mat);
            Image scaledImage = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
            g.drawImage(scaledImage, 0, 0, this); // see javadoc for more info on the parameters        
        }
    }

    /**
     * Call the transformation and repaint image.
     * @param value Threshold value in range (0-255)
     */
    public void threshold(int value) {
        mat = applyThreshold(mat, value);
        this.repaint();
    }

    /**
     * Creates two images on grey scale, converts original image to grey scale,
     * and based on level of grey, every pixel is converted to 0 or 1.
     * pixels with grey level greater than threshold are converted to 1
     * pixels with grey level less than of equal to threshold are converted to 0
     * @param originalImage Colored input image
     * @param value Threshold value in range (0-255)
     * @return Thresholded image
     */
    private Mat applyThreshold(Mat originalImage, Integer value) {
        Mat imageGrey = new Mat(originalImage.rows(), originalImage.cols(), CvType.CV_8U);
        Mat imageTresholded = new Mat(originalImage.rows(), originalImage.cols(), CvType.CV_8U); 
        Imgproc.cvtColor(originalImage, imageGrey, Imgproc.COLOR_BGR2GRAY);       
        Imgproc.threshold(imageGrey, imageTresholded, value, 255, Imgproc.THRESH_BINARY);
        return imageTresholded;
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
