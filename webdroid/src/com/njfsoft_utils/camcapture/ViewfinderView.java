/*
 * Copyright (C) 2008 ZXing authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.njfsoft_utils.camcapture;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
// import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.*;
// import android.graphics.YuvImage;
import android.hardware.Camera;
import android.media.MediaPlayer; 

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;


import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.MotionEvent;
import android.graphics.Point;

import java.io.ByteArrayOutputStream;


import java.util.ArrayList;
import java.util.Random;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import com.njfsoft_utils.core.Base64;
import com.quickorder.R;

 
// import com.google.zxing.ResultPoint;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points.
 * 
 * @author dswitkin@google.com (Daniel Switkin)
 */
public class ViewfinderView extends View {
    private static final String TAG = ViewfinderView.class.getSimpleName();
    private static final int[] SCANNER_ALPHA = { 0, 64, 128, 192, 255, 192, 128, 64 };
    private static final long ANIMATION_DELAY = 100L;
    private static final long ANIMATION_PDELAY = 1000L;
    private static final int CURRENT_POINT_OPACITY = 0xA0;
    private static final int MAX_RESULT_POINTS = 20;
    private static final int POINT_SIZE = 6;
	Paint mcPaint;
    private CameraManager cameraManager;
    private final Paint paint;
	Paint transparentPaint;
    private Bitmap resultBitmap;
    private final int maskColor;
    private final int resultColor;
    int frameColor;
	int intPicTaken;
    int slapCount;
    private final int laserColor;
    private final int resultPointColor;
    private int scannerAlpha;
    BitmapDrawable ball;
    BitmapDrawable slaplogo;
    int intFwidth;
    int intFheight;
    Bitmap theBall;
    Bitmap theSkewedBall;
    Bitmap theSlapLogo;
    Bitmap currSlapLogo;
    Bitmap theCurrBall;
 MediaPlayer mp;
        int ballx = -1;
 	Context tContext;
         int bally = -1;
        boolean isSlapped;
        boolean hasSlap;
	  boolean isTouched;
        private int xVelocity = 10;

        private int yVelocity = 5;
   private float previousX;
   private float previousY;

   private float ballSpeedX = 5;  // Ball's speed (x,y)
   private float ballSpeedY = 3;
 
   //  private List<ResultPoint> possibleResultPoints;
   //  private List<ResultPoint> lastPossibleResultPoints;

    // This constructor is used when the class is built from an XML resource.
    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
 		tContext = context;
        // Initialize these once for performance rather than calling them every
        // time in onDraw().
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Resources resources = getResources();
        maskColor = resources.getColor(R.color.viewfinder_mask);
        resultColor = resources.getColor(R.color.result_view);
        frameColor = resources.getColor(R.color.viewfinder_frame);
        laserColor = resources.getColor(R.color.viewfinder_laser);
        resultPointColor = resources.getColor(R.color.possible_result_points);
        scannerAlpha = 0;
	intPicTaken = 0;
      isTouched = false;
       isSlapped = false;
	hasSlap = false;
	    this.setFocusableInTouchMode(true);
	   slapCount = 0;
        // possibleResultPoints = new ArrayList<ResultPoint>(5);
        // lastPossibleResultPoints = null;

/*
            mp = new MediaPlayer();

		mp = MediaPlayer.create(this.getContext(), R.raw.slap);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mp.reset();
                    mp.release();
                    // mp=null;
                }

            });

 
	  // ball = (BitmapDrawable) context.getResources().getDrawable(R.raw.mask_small);
	  // slaplogo = (BitmapDrawable) context.getResources().getDrawable(R.raw.soccer185);
        theBall = boost(ball.getBitmap(), (float)7);
        theCurrBall = theBall;
	   theSlapLogo = slaplogo.getBitmap();
        currSlapLogo = null;

         theSkewedBall = getScewedBitmap(theBall, 5, theBall.getWidth(), theBall.getHeight());

*/
mcPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
/*
    transparentPaint = new Paint();
    transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
    transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
*/
 
    }


 


    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }

 



    @Override
    public void onDraw(Canvas canvas) {

		try {

        Rect frame = cameraManager.getFramingRect();

        if (frame == null) {
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();

	  System.out.println("onDraw: " + width + " : h: " + height);
Rect rs = new Rect();
Rect rd = new Rect();
rs.left = rs.top = 0;
rs.right = theSlapLogo.getWidth();
rs.bottom = theSlapLogo.getHeight();

rd.left = rd.top = 0;
rd.right = width;
rd.bottom = height;
 
		// draw something
		// canvas.drawBitmap(theSlapLogo, rs, rd, null);
           // postInvalidateDelayed(ANIMATION_DELAY, frame.left - POINT_SIZE, frame.top - POINT_SIZE, frame.right + POINT_SIZE, frame.bottom + POINT_SIZE);
           postInvalidateDelayed(ANIMATION_DELAY);

		} catch(Exception e) {
        System.out.println("onDraw.error: " + e);
		e.printStackTrace();
		}


    }

    public void drawViewfinder() {
        Bitmap resultBitmap = this.resultBitmap;
        this.resultBitmap = null;
        if (resultBitmap != null) {
            resultBitmap.recycle();
        }
        this.theCurrBall = null;
        if (theCurrBall != null) {
            theCurrBall.recycle();
        }
        theCurrBall = theBall;
        invalidate();
    }

    /**
     * Draw a bitmap with the result points highlighted instead of the live
     * scanning display.
     * 
     * @param barcode
     *            An image of the decoded barcode.
     */
    public void drawResultBitmap(Bitmap barcode) {
        resultBitmap = barcode;
	  hasSlap = true;	
	  refreshTimer();
        invalidate();

    }



    public void undrawSlapBmp() {
        Bitmap currSlapLogo = this.currSlapLogo;
        this.currSlapLogo = null;
        if (currSlapLogo != null) {
            currSlapLogo.recycle();
        }
        invalidate();
    }

    public void drawSlapBmp(Bitmap theBMP) {
        currSlapLogo = theBMP;
        invalidate();

    }

    public Camera.PreviewCallback prevCallBack = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {

            System.out.println("PreviewCallback onPreviewFrame: " + intPicTaken);
            try { 
            System.out.println("PreviewCallback onPreviewFrame: " + intPicTaken);



     doSlapSkew(data);

            } catch (Exception e) {
                System.out.println("onPreviewFrame: " + e.toString());
		e.printStackTrace();
              cameraManager.getCamera().setPreviewCallback(null);
		  intPicTaken = 0;
            }
        }
    };



/*
    public void addPossibleResultPoint(ResultPoint point) {
        List<ResultPoint> points = possibleResultPoints;
        synchronized (point) {
            points.add(point);
            int size = points.size();
            if (size > MAX_RESULT_POINTS) {
                // trim it
                points.subList(0, size - MAX_RESULT_POINTS / 2).clear();
            }
        }
    }
*/

   @Override
   public boolean onTouchEvent(MotionEvent event) {

/*
      cameraManager.getCamera().setPreviewCallback(null);
	isTouched = true;
	drawViewfinder();
	undrawSlapBmp();

*/

      float initialX, initialY;

            initialX = event.getRawX();
            initialY = event.getRawY();
    switch (event.getAction()) {
 
        case MotionEvent.ACTION_DOWN:
	isTouched = true;
       ballx = Math.round(event.getRawX());
      bally = Math.round(event.getRawY());

            initialX = event.getRawX();
            initialY = event.getRawY();
             System.out.println("initialRawX: " + initialX + " :: initialRawY: " + initialY);

            Log.d(TAG, "Action was DOWN");
            break;
 
        case MotionEvent.ACTION_MOVE:
 
       ballx = Math.round(event.getRawX());
      bally = Math.round(event.getRawY());

            initialX = event.getRawX();
            initialY = event.getRawY();

             System.out.println("initialRawX: " + initialX + " :: initialRawY: " + initialY);

            Log.d(TAG, "Action was MOVE");
            break;
 
        case MotionEvent.ACTION_UP:
    
            float finalX = event.getRawX();
            float finalY = event.getRawY();
 
            Log.d(TAG, "Action was UP");
 
            if (initialX < finalX) {
                Log.d(TAG, "Left to Right swipe performed");
            }
 
            if (initialX > finalX) {
                Log.d(TAG, "Right to Left swipe performed");
            }
 
            if (initialY < finalY) {
                Log.d(TAG, "Up to Down swipe performed");
            }
 
            if (initialY > finalY) {
                Log.d(TAG, "Down to Up swipe performed");
            }
      	// isTouched = false;
	      // drawSlapLogo(theSlapLogo);
		// doSlap(ballx, bally);
            break;
        case MotionEvent.ACTION_CANCEL:
            Log.d(TAG,"Action was CANCEL");
 		isTouched = false;
            break;
 
        case MotionEvent.ACTION_OUTSIDE:
		isTouched = false;
            Log.d(TAG, "Movement occurred outside bounds of current screen element");
            break;
    }
 
 
      return true;  // Event handled

   }


 

public void decodeYUV420SP(int[] rgb, byte[] yuv420sp, int width, int height) {

   final int frameSize = width * height;

   for (int j = 0, yp = 0; j < height; j++) {
     int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
     for (int i = 0; i < width; i++, yp++) {
       int y = (0xff & ((int) yuv420sp[yp])) - 16;
       if (y < 0)
         y = 0;
       if ((i & 1) == 0) {
         v = (0xff & yuv420sp[uvp++]) - 128;
         u = (0xff & yuv420sp[uvp++]) - 128;
       }

       int y1192 = 1192 * y;
       int r = (y1192 + 1634 * v);
       int g = (y1192 - 833 * v - 400 * u);
       int b = (y1192 + 2066 * u);

       if (r < 0)
         r = 0;
       else if (r > 262143)
         r = 262143;
       if (g < 0)
         g = 0;
       else if (g > 262143)
         g = 262143;
       if (b < 0)
         b = 0;
       else if (b > 262143)
         b = 262143;

       rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
     }
   }
 
 }    


 


private void refreshTimer() {
        Timer autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
 
                        hasSlap = false;
            }
        }, 600L);
}

    public Bitmap doClipMask(Bitmap bmpToMask, Bitmap bmpTheMask, int iw, int ih) {
     Bitmap bmpBG = Bitmap.createBitmap(bmpToMask.getWidth(),bmpToMask.getHeight(), Bitmap.Config.ARGB_8888);



    Canvas cnvMask = new Canvas(bmpBG); 
    Paint maskPaint = new Paint();
 
 


    cnvMask.drawBitmap(bmpToMask, 0, 0, maskPaint);
    cnvMask.drawBitmap(bmpTheMask, 0, 0, maskPaint);

    return bmpBG;
    }



     public void doSlapSkew(byte[] data) {
   Camera.Parameters parameters = cameraManager.getCamera().getParameters();
    int width = parameters.getPreviewSize().width;
    int height = parameters.getPreviewSize().height;

		Rect frame = cameraManager.getFramingRect();
int previewFormat = 0;


Rect apreviewRect = cameraManager.getFramingRectInPreview();
int pwidth = apreviewRect.width();
int pheight = apreviewRect.height();


  Bitmap bitmap;
 
    int[] previewPixels = new int[width * height];
    decodeYUV420SP(previewPixels, data, width, height);
    bitmap = Bitmap.createBitmap(previewPixels, intFwidth, intFheight, Bitmap.Config.RGB_565);



 
	// Bitmap tttSlapLogo = Bitmap.createScaledBitmap(bitmap, intFwidth, intFheight, false);
 
       Bitmap bmpFF = doClipMask(bitmap,theSlapLogo,intFwidth,intFheight);
        //  theSlapLogo = bitmap;
// doArtPadBitmap(bitmap);
//  drawResultBitmap(bmpFF); 
	}

     public void doSlap(int thex, int they) {
            isSlapped = true;
		isTouched = false;
	}

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
/*
        this.intFwidth = w;
        this.intFheight = h;
	  Bitmap tttSlapLogo = Bitmap.createScaledBitmap(slaplogo.getBitmap(), intFwidth, intFwidth, false);
       theSlapLogo = tttSlapLogo;
*/ 
       super.onSizeChanged(w, h, oldw, oldh);
    }









}
