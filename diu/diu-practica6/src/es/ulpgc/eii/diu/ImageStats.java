/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ulpgc.eii.diu;

/**
 *
 * @author David
 */
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import org.opencv.core.Core;
import static org.opencv.core.Core.mean;
import static org.opencv.core.Core.minMaxLoc;
import static org.opencv.core.Core.split;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

public class ImageStats {

    public final int BLUE = 0;
    public final int GREEN = 1;
    public final int RED = 2;

    private final int[] Components = {RED, GREEN, BLUE};

    private int max[] = new int[3];
    private int min[] = new int[3];
    private int mean[] = new int[3];

    public int[] getMax() {
        return max;
    }

    public int[] getMin() {
        return min;
    }

    public int[] getMean() {
        return mean;
    }

    public void computeStats(Mat imageColor, Point point,
            Dimension dimVisible) {

        // crea la subimagen correspondiente al viewport
        Mat subImage = new Mat(imageColor, new Rect(point.x, point.y,
                dimVisible.width, dimVisible.height));

        // separa los tres canales de la subimagen BGR
        ArrayList<Mat> bgr = new ArrayList<>();
        split(subImage, bgr);

        // estadisticas 
        ImageStats res = new ImageStats();

        for (int c : res.Components) {
            // obtiene el máximo y mínimo del canal c de la subimagen
            Core.MinMaxLocResult minmax = minMaxLoc(bgr.get(c));
            // calcula el premodio del canal c de la subimagen
            Scalar prom_scalar = mean(bgr.get(c));

            this.max[c] = (int) minmax.maxVal;
            this.min[c] = (int) minmax.minVal;
            this.mean[c] = (int) prom_scalar.val[0];
        }
    }

}
