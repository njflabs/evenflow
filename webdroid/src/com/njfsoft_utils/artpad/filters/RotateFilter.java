package com.njfsoft_utils.artpad.filters;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by IntelliJ IDEA.
 * User: boss
 * Date: 29-07-2013
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
public class RotateFilter {
    public static Bitmap getRotatedBmp(Bitmap aBitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        Bitmap rotated = Bitmap.createBitmap(aBitmap, 0, 0, aBitmap.getWidth(), aBitmap.getHeight(),
                matrix, true);
        return rotated;

    }
}
