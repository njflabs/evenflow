package com.njfsoft_utils.artpad.filters;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by IntelliJ IDEA.
 * User: boss
 * Date: 29-07-2013
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */
public class FlipFilter {
    public static Bitmap flip(Bitmap src, int type) {
        // create new matrix for transformation
        Matrix matrix = new Matrix();
        // if vertical
        if (type == 1) {
            // y = y * -1
            matrix.preScale(1.0f, -1.0f);
        }
        // if horizonal
        else if (type == 2) {
            // x = x * -1
            matrix.preScale(-1.0f, 1.0f);
            // unknown type
        } else {
            return null;
        }

        // return transformed image
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }
}
