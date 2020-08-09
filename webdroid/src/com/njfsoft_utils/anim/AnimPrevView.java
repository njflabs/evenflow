package com.njfsoft_utils.anim;

import com.quickorder.R; 
import android.app.Activity;
 
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.graphics.drawable.Drawable;
import android.graphics.Color;
import android.graphics.Canvas;
import android.widget.ImageView;
import android.view.WindowManager;
import android.view.Display;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
 import java.io.InputStream;

 import java.util.ArrayList;



import android.os.Bundle;
import android.os.Environment;
 
import android.view.View;
import android.util.AttributeSet;

import android.graphics.Rect;
import com.njfsoft_utils.anim.AnimFrameSingleton;
import com.njfsoft_utils.anim.AnimMovSingleton;
import com.njfsoft_utils.cutOuts.CutOuts;


public class AnimPrevView extends ImageView {

CutOuts cOuts;
Activity mActivity;
Context mContext;
Bitmap currDmap;
private long movieStart;
Rect rectGPV;
boolean isPlaying;
	int covIntFCount = 0;
	int incrFdone = 0;

	ArrayList<AnimFrameSingleton> covAnimFnlFrames;

 		Rect rs;
 


public AnimPrevView(Context context) {
    super(context);
	 mContext = context;
    initializeView();
}

public AnimPrevView(Context context, AttributeSet attrs) {
    super(context, attrs);
	 mContext = context;
    initializeView();
}

public AnimPrevView(CutOuts activity, Context context) {
    super(context);
	 cOuts = activity;
	 mContext = context;
    initializeView();
}

public AnimPrevView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
	 mContext = context;
    initializeView();
}

private void initializeView() {
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
 rectGPV = new Rect(0,0,width,height);
isPlaying = false;
}

public void setMaster(CutOuts tCO) {
cOuts = tCO;
}

public void setBmap(Bitmap dMap) {
currDmap = dMap;
invalidate();
}
/*
*/
public Bitmap get(){
// The variable that will guard the frame number

 
this.setDrawingCacheEnabled(true);
this.buildDrawingCache(true);
Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
this.setDrawingCacheEnabled(false);
 
return bmp;
}


    public void doOTFlyBmap(Bitmap dMap) {
		try {
		currDmap = dMap;
            invalidate();
		} catch(Exception e) {
        	System.out.println("onDraw.error: " + e);
		e.printStackTrace();
		}
    }


    public void doArrBmap() {
		try {
            // Bitmap tbmp = covAnimFnlFrames.get(incrFdone).getMBitmap();
		// int ti = covAnimFnlFrames.get(incrFdone).getIpst();
		currDmap = covAnimFnlFrames.get(incrFdone).getMBitmap();
		incrFdone++;
		invalidate();
		} catch(Exception e) {
        	System.out.println("onDraw.error: " + e);
		e.printStackTrace();
		}
    }


    @Override
    public void onDraw(Canvas canvas) {
 
		try {
		if((incrFdone < covIntFCount) && (covIntFCount > 0)){
            System.out.println("AnimPrevView: Ondraw: " + incrFdone);
            Bitmap tbmp = covAnimFnlFrames.get(incrFdone).getMBitmap();
            int ti = 0;
            int ty = 0;
		if(incrFdone == covIntFCount - 1) {
		ti = covAnimFnlFrames.get(incrFdone - 1).getIpst();
		ty = covAnimFnlFrames.get(incrFdone).getIpst();
		} else {
		ti = covAnimFnlFrames.get(incrFdone).getIpst();
		ty = covAnimFnlFrames.get(incrFdone + 1).getIpst();
		}
		int dlay = (int) (ty - ti);
      	canvas.drawBitmap(tbmp, null, rectGPV, null);
		incrFdone++;
        	System.out.println("onDraw.dlay: " + dlay);
            postInvalidateDelayed(Long.valueOf(dlay));
		} else {
		if(covIntFCount > 0) {
		incrFdone = 0;
		covIntFCount = 0;
            cOuts.animPlayEnded();
		} 
		}
		} catch(Exception e) {
        	System.out.println("onDraw.error: " + e);
		e.printStackTrace();
		isPlaying = false;
		}
 
    }




    public boolean setAnimMovSing(AnimMovSingleton covAMSing) {
	try {
	incrFdone = 0;
	covAnimFnlFrames = null;
	covAnimFnlFrames = covAMSing.getMamsArrAFS();
	covIntFCount = covAnimFnlFrames.size(); 
      System.out.println("AnimPrevView:setAnimMovSing true: " + covIntFCount);
	isPlaying = true;
	invalidate();
	return true;
    } catch (Exception e) {
    System.out.println("AnimPrevView:setAnimMovSing:ERROR returns false: " + e);
       e.printStackTrace();
    	return false;

    }   
     }

}