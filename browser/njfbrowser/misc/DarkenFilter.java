package njfbrowser.misc;


import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.RGBImageFilter;

public class DarkenFilter extends RGBImageFilter {

    public int filterRGB(int i, int j, int k) {
        DirectColorModel directcolormodel = (DirectColorModel) ColorModel.getRGBdefault();
        int l = directcolormodel.getAlpha(k);
        int i1 = (int) ((double) directcolormodel.getRed(k) * 0.60000000000000009D);
        int j1 = (int) ((double) directcolormodel.getGreen(k) * 0.60000000000000009D);
        int k1 = (int) ((double) directcolormodel.getBlue(k) * 0.60000000000000009D);
        return l << 24 | i1 << 16 | j1 << 8 | k1;
    }

    public DarkenFilter() {
        super.canFilterIndexColorModel = true;
    }
}
