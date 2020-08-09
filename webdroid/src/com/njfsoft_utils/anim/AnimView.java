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



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

 import java.io.InputStream;
import android.os.Bundle;
import android.os.Environment;
 

import android.view.View;
import android.util.AttributeSet;



public class AnimView extends ImageView {

 
private long movieStart;

public AnimView(Context context) {
    super(context);
    initializeView();
}

public AnimView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initializeView();
}

public AnimView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initializeView();
}

private void initializeView() {

 
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
    long now = android.os.SystemClock.uptimeMillis();

 
}

}