package com.njfsoft_utils.artpad.filters;
import android.graphics.Bitmap;

import android.graphics.Bitmap;
import android.graphics.Color;
 

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tanner on 1/18/14.
 */
public class MedianFilter {


    public static final int SIZE_MIN = 3;
    public static final int SIZE_DEFAULT = 3;

    protected Bitmap bitmap;
    protected int maskSize = 3;

    public boolean cancelFiltering;


 

    public MedianFilter() {
    }
 
    public Bitmap applyFilter(Bitmap image) {

       bitmap = image;
	 maskSize = SIZE_MIN;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();




        int maxSize = Math.min(width, height);

 
        cancelFiltering = false;
	  int offset = maskSize/2;


        // Retrieve pixels of bitmap for efficiency
        int[] pixels = new int[width*height];
        bitmap.getPixels(pixels,0,width,0,0,width,height);

        // Iterate over all pixels of image determine new values
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // If filtering has been asked to cancel, stop filtering
                if (cancelFiltering) {
                    return null;
                }

                ArrayList<Integer> reds = new ArrayList<Integer>(offset);
                ArrayList<Integer> greens = new ArrayList<Integer>(offset);
                ArrayList<Integer> blues = new ArrayList<Integer>(offset);
                ArrayList<Integer> alphas = new ArrayList<Integer>(offset);

                // Retrieve mask pixels and calculate median value for new pixel
                // This is done primitively for efficiency
                for (int row = y-offset; row <= y+offset; row++) {
                    for (int col = x-offset; col <= x+offset; col++) {
                        if (row >= 0 && col >= 0 && row < height && col < width) {

                            int color = pixels[row*width+col];

                            reds.add(Color.red(color));
                            greens.add(Color.green(color));
                            blues.add(Color.blue(color));
                            alphas.add(Color.alpha(color));
                        }
                    }
                }

                int red = getMedian(reds);
                int green = getMedian(greens);
                int blue = getMedian(blues);
                int alpha = getMedian(alphas);

                pixels[y*width+x] = Color.argb(alpha,red,green,blue);
                System.out.println("MedianFilter:ApplyFilter " + y);

            }
        }
        return Bitmap.createBitmap(pixels,width,height,bitmap.getConfig());
    }

    /**
     * Returns the median value of the passed value list
     */
    private int getMedian(ArrayList<Integer> values) {
        Collections.sort(values);
        int size = values.size();

        if (size % 2 != 0) {
            return values.get(size/2);
        } else {
            return (values.get(size/2) + values.get(size/2 - 1))/2;
        }
    }




    public Bitmap applyMeanFilter(Bitmap image) {


       bitmap = image;
	 maskSize = SIZE_MIN;
 



        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int offset = maskSize/2;

        // Retrieve pixels of bitmap for efficiency
        int[] pixels = new int[width*height];
        bitmap.getPixels(pixels,0,width,0,0,width,height);

        // Iterate over all pixels of image determine new values
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // If filtering has been asked to cancel, stop filtering
                if (cancelFiltering) {
                    return null;
                }

                int red = 0;
                int green = 0;
                int blue = 0;
                int alpha = 0;

                // Retrieve mask pixels and calculate mean value for new pixel
                // This is done primitively for efficiency
                int maskPixels = 0;
                for (int row = y-offset; row <= y+offset; row++) {
                    for (int col = x-offset; col <= x+offset; col++) {
                        if (row >= 0 && col >= 0 && row < height && col < width) {

                            int color = pixels[row*width+col];

                            red += Color.red(color);
                            green += Color.green(color);
                            blue += Color.blue(color);
                            alpha += Color.alpha(color);

                            maskPixels++;
                        }
                    }
                }

                red = red/maskPixels;
                green = green/maskPixels;
                blue = blue/maskPixels;
                alpha = alpha/maskPixels;

                // Set new pixel to mean value
                pixels[y*width+x] = Color.argb(alpha,red,green,blue);
                System.out.println("MedianFilter:ApplyMeanFilter " + y);
            }
        }
        return Bitmap.createBitmap(pixels,width,height,bitmap.getConfig());
    }


}
