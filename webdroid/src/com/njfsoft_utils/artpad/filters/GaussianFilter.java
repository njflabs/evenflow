package com.njfsoft_utils.artpad.filters;

import android.graphics.Bitmap;

/**
 * Created by IntelliJ IDEA.
 * User: boss
 * Date: 29-07-2013
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
public class GaussianFilter {
    public static Bitmap applyGaussianBlur(Bitmap src) {
        double[][] GaussianBlurConfig = new double[][]{
                {1, 2, 1},
                {2, 4, 2},
                {1, 2, 1}
        };
        ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
        convMatrix.applyConfig(GaussianBlurConfig);
        convMatrix.Factor = 16;
        convMatrix.Offset = 0;
        return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
    }
}
