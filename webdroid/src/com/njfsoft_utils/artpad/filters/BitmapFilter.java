package com.njfsoft_utils.artpad.filters;
import android.graphics.Bitmap;

/**
 * ??????,??????????
 * @author ragnarok
 *
 */
public class BitmapFilter {
	/**
	 * ???????id
	 */
	public static final int GRAY_STYLE = 1;  
	public static final int RELIEF_STYLE = 2; 
	public static final int VAGUE_STYLE = 3;  
	public static final int OIL_STYLE = 4;  
	public static final int NEON_STYLE = 5;  
	public static final int PIXELATE_STYLE = 6;  
	public static final int TV_STYLE = 7; 
	public static final int INVERT_STYLE = 8; 
	public static final int BLOCK_STYLE = 9; 
	public static final int OLD_STYLE = 10;  
	public static final int SHARPEN_STYLE = 11;  
	public static final int LIGHT_STYLE = 12; 
	public static final int LOMO_STYLE = 13;
    public static final int WATERMARK_STYLE = 14;
    public static final int REFLECTION_STYLE = 15;
    public static final int ROUNDCORNER_STYLE = 16;
    public static final int SEPIA_STYLE = 17;
    public static final int GAUSSIAN_STYLE = 18;
    public static final int FLIP_VERTICLE_STYLE = 19;
    public static final int FLIP_HORIZONTAL_STYLE = 20;
    public static final int BOOST_STYLE = 21;
    public static final int ROTATE_STYLE = 22;
	/**
	 * ??????,
	 * @param bitmap
	 * @param styleNo
	 */
	public static Bitmap changeStyle(Bitmap bitmap, int styleNo) {
		if (styleNo == GRAY_STYLE) {
			//return changeToGray(bitmap);
			return GrayFilter.changeToGray(bitmap);
		}
		else if (styleNo == RELIEF_STYLE) {
			return ReliefFilter.changeToRelief(bitmap);
		}
		else if (styleNo == VAGUE_STYLE) {
			return VagueFilter.changeToVague(bitmap);
		}
		else if (styleNo == OIL_STYLE) {
			return OilFilter.changeToOil(bitmap);
		}
		else if (styleNo == NEON_STYLE) {
			return NeonFilter.changeToNeon(bitmap);
		}
		else if (styleNo == PIXELATE_STYLE) {
			return PixelateFilter.changeToPixelate(bitmap);
		}
			
		else if (styleNo == TV_STYLE) {
			return TvFilter.changeToTV(bitmap);
		}
		else if (styleNo == INVERT_STYLE) {
			return InvertFilter.chageToInvert(bitmap);
		}
		else if (styleNo == BLOCK_STYLE) {
			return BlockFilter.changeToBrick(bitmap);
		}
		else if (styleNo == OLD_STYLE) {
			return OldFilter.changeToOld(bitmap);
		}
		else if (styleNo == SHARPEN_STYLE) {
			return SharpenFilter.changeToSharpen(bitmap);
		}
		else if (styleNo == LIGHT_STYLE) {
			return LightFilter.changeToLight(bitmap);
		}
		else if (styleNo == LOMO_STYLE) {
			return LomoFilter.changeToLomo(bitmap);
		}
        else if (styleNo == REFLECTION_STYLE) {
            return ReflectionFilter.applyReflection(bitmap);
        }
        else if (styleNo == ROUNDCORNER_STYLE) {
            return RoundCornerFilter.roundCorner(bitmap, 45);
        }
        else if (styleNo == SEPIA_STYLE) {
            return SepiaFilter.getSepiaEffect(bitmap);
        }
        else if (styleNo == GAUSSIAN_STYLE) {
            return GaussianFilter.applyGaussianBlur(bitmap);
        }
        else if (styleNo == FLIP_VERTICLE_STYLE) {
            return FlipFilter.flip(bitmap, 1);
        }
        else if (styleNo == FLIP_HORIZONTAL_STYLE) {
            return FlipFilter.flip(bitmap, 2);
        }
        else if (styleNo == BOOST_STYLE) {
            return BoostFilter.boost(bitmap, 1, 150);
        }
        else if (styleNo == ROTATE_STYLE) {
            return RotateFilter.getRotatedBmp(bitmap);
        }
			return bitmap;
	}
	
	

}

















