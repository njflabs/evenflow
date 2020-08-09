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
import android.graphics.Paint;
import android.widget.ImageView;
import android.view.WindowManager;
import android.view.Display;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

 import java.io.InputStream;
import android.os.Bundle;
import android.os.Environment;
 

import android.view.View;
import android.util.AttributeSet;
import android.graphics.Rect;

 import com.njfsoft_utils.artpad.filters.ColorFilterGenerator;
import com.njfsoft_utils.cutOuts.CutOuts;


public class AnimBGView extends ImageView {

CutOuts cOuts;
Activity mActivity;
Context mContext;

 Rect rectGPV;
private long movieStart;
Bitmap currBGDmap;

 ColorFilterGenerator mednFilter;
Paint ctClrPaint;

public AnimBGView(Context context) {
    super(context);
    initializeView();
}

public AnimBGView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initializeView();
}

public AnimBGView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initializeView();
}


public AnimBGView(CutOuts activity, Context context) {
    super(context);
 currBGDmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
 
	 cOuts = activity;
	 mContext = context;


mednFilter = new com.njfsoft_utils.artpad.filters.ColorFilterGenerator();
		ctClrPaint = new Paint();
 		// -- !! ctClrPaint.setColorFilter(mednFilter.adjustColor(35,38,95,95));

    initializeView();
}

private void initializeView() {

         WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
 rectGPV = new Rect(0,0,width,height);
}
public void setBmap(Bitmap dMap) {
currBGDmap = dMap;
invalidate();
}
public void clearBmap() {
currBGDmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
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

protected void onDraw(Canvas canvas) {
    // canvas.drawColor(Color.TRANSPARENT);
    super.onDraw(canvas);
		try {
      	canvas.drawBitmap(currBGDmap, null, rectGPV, ctClrPaint);
		} catch(Exception e) {
		e.printStackTrace();
		}
    long now = android.os.SystemClock.uptimeMillis();

 
}

}