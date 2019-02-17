package com.njfsoft_utils.artpad.filters;

import android.graphics.*;

/**
 * Created by IntelliJ IDEA.
 * User: boss
 * Date: 29-07-2013
 * Time: 13:12
 * To change this template use File | Settings | File Templates.
 */
public class SepiaFilter {
    public static Bitmap getSepiaEffect(Bitmap mBitmap) {
        ColorMatrix sepiaMatrix = new ColorMatrix();
        float[] sepMat = {0.3930000066757202f, 0.7689999938011169f, 0.1889999955892563f, 0, 0, 0.3490000069141388f, 0.6859999895095825f, 0.1679999977350235f, 0, 0, 0.2720000147819519f, 0.5339999794960022f, 0.1309999972581863f, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1};
        sepiaMatrix.set(sepMat);
        final ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(sepiaMatrix);
        Bitmap rBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Paint paint = new Paint();
        paint.setColorFilter(colorFilter);
        Canvas myCanvas = new Canvas(rBitmap);
        myCanvas.drawBitmap(rBitmap, 0, 0, paint);

        return rBitmap;
    }
}
