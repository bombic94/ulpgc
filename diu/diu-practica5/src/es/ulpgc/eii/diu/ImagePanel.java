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
        mat = umbralizar(mat, value);
        this.repaint();
    }
    
    private Mat umbralizar(Mat imagen_original, Integer umbral) {
        // crear dos imágenes en niveles de gris con el mismo tamaño que la original
        Mat imagenGris = new Mat(imagen_original.rows(), imagen_original.cols(), CvType.CV_8U);
        Mat imagenUmbralizada = new Mat(imagen_original.rows(), imagen_original.cols(), CvType.CV_8U); 
        // convierte a niveles de grises la imagen original
        Imgproc.cvtColor(imagen_original, imagenGris, Imgproc.COLOR_BGR2GRAY);       
        // umbraliza la imagen:  
        // ‐ píxeles con nivel de gris > umbral se ponen a 1
        // ‐ píxeles con nivel de gris <= umbra se ponen a 0
        Imgproc.threshold(imagenGris, imagenUmbralizada, umbral, 255, Imgproc.THRESH_BINARY);
        // se devuelve la imagen umbralizada
        return imagenUmbralizada;
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
