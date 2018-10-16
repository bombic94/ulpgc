/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ulpgc.eii.diu;

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

/**
 * ImageStats for computing color statistics of showed part of image
 */
public class ImageStats {

    public final int BLUE = 0;
    public final int GREEN = 1;
    public final int RED = 2;

    private final int[] Components = {RED, GREEN, BLUE};

    private int max[] = new int[3];
    private int min[] = new int[3];
    private int mean[] = new int[3];

    /**
     * Get array of maximum values
     * @return array of maximum values
     */
    public int[] getMax() {
        return max;
    }

    /**
     * Get array of minimum values
     * @return array of minimum values
     */
    public int[] getMin() {
        return min;
    }

    /**
     * Get array of mean values
     * @return array of mean values
     */
    public int[] getMean() {
        return mean;
    }

    /**
     * Creates subImage from original image,
     * separates three color canals in RGB,
     * and for each canal obtains minimum and maximum value,
     * then calculates average mean of the canal.
     * @param imageColor Mat object representing whole image
     * @param point Top left point of showed part of image
     * @param dimVisible Size of showed part of image
     */
    public void computeStats(Mat imageColor, Point point, Dimension dimVisible) {
        Mat subImage = new Mat(imageColor, new Rect(point.x, point.y, dimVisible.width, dimVisible.height));
        ArrayList<Mat> bgr = new ArrayList<>();
        split(subImage, bgr);
        ImageStats res = new ImageStats();
        for (int c : res.Components) {
            Core.MinMaxLocResult minmax = minMaxLoc(bgr.get(c));
            Scalar prom_scalar = mean(bgr.get(c));
            this.max[c] = (int) minmax.maxVal;
            this.min[c] = (int) minmax.minVal;
            this.mean[c] = (int) prom_scalar.val[0];
        }
    }

}
