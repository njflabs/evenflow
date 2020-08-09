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

import android.graphics.Movie;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

 import java.io.InputStream;
import android.os.Bundle;
import android.os.Environment;
 

import android.view.View;
import android.util.AttributeSet;



public class GIFView extends View {

private Movie mMovie;
private long movieStart;

public GIFView(Context context) {
    super(context);
    initializeView();
}

public GIFView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initializeView();
}

public GIFView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initializeView();
}

private void initializeView() {

    InputStream is = getContext().getResources().openRawResource(R.drawable.lightning);
    mMovie = Movie.decodeStream(is);
}


public Bitmap get(){
this.setDrawingCacheEnabled(true);
this.buildDrawingCache(true);
Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
this.setDrawingCacheEnabled(false);
return bmp;
}


protected void onDraw(Canvas canvas) {
    canvas.drawColor(Color.TRANSPARENT);
    super.onDraw(canvas);
    long now = android.os.SystemClock.uptimeMillis();

    if (movieStart == 0) {
        movieStart = (int) now;
    }
    if (mMovie != null) {
        int relTime = (int) ((now - movieStart) % mMovie.duration());
        mMovie.setTime(relTime);
  mMovie.draw(canvas, getWidth() - mMovie.width(), getHeight() - mMovie.height());
//       		canvas.scale(420,360);
	//	mMovie.draw(canvas,0,0);
        this.invalidate();
    }
}}