package com.njfsoft_utils.anim;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
// import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
 
import android.media.FaceDetector;
import android.media.FaceDetector.Face;

import android.graphics.BitmapFactory;
 
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;



import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import android.os.Environment;
 import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
 
import android.content.res.AssetManager;
 

 

import com.njfsoft_utils.core.OnTaskExecutionFinished;
import com.njfsoft_utils.artpad.filters.ColorFilterGenerator;

import com.qbits.R;



// code from: 
// http://stackoverflow.com/questions/2214735/android-animationdrawable-and-knowing-when-animation-ends
// needed to extend AnimationDrawable to get currentFrame

public class AnimationDrawable2 extends AnimationDrawable
{
    private boolean finished = false;
    public IAnimationFinishListener animationFinishListener;
    public int iFidx;
   public interface IAnimationFinishListener
    {
        public void onAnimationFinished();
        public void onAnimationChanged(int i);
    }


    public AnimationDrawable2(AnimationDrawable aniDrawable) {
	  iFidx = 0;
        /* Add each frame to our animation drawable */
        for (int i = 0; i < aniDrawable.getNumberOfFrames(); i++) {
            this.addFrame(aniDrawable.getFrame(i), aniDrawable.getDuration(i));
        }
    }



    public IAnimationFinishListener getAnimationFinishListener()
    {
        return animationFinishListener;
    }

    public void setAnimationFinishListener(IAnimationFinishListener animationFinishListener)
    {
        this.animationFinishListener = animationFinishListener;
    }

    public int getAnimFrmIdx() {
	return iFidx;
    }


    @Override
    public boolean selectDrawable(int idx)
    {
	  iFidx = idx;
        boolean ret = super.selectDrawable(idx);
        System.out.println("selectDrawable: " + idx);

        if(animationFinishListener != null) {
		animationFinishListener.onAnimationChanged(idx);
	  }
        if ((idx != 0) && (idx == getNumberOfFrames() - 1))
        {
            if (!finished)
            {
                finished = true;
        System.out.println("selectDrawable:finished " + idx);
       		 if(animationFinishListener != null) {
				animationFinishListener.onAnimationFinished();
	 		 }
            }
        }

        return ret;
    }
}
