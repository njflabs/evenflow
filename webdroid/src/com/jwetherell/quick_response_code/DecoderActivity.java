/*
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

package com.jwetherell.quick_response_code;

import java.io.IOException;
import java.util.Collection;

import com.qbits.R;


import android.graphics.drawable.BitmapDrawable;
// import com.jwetherell.quick_response_code.R;
import com.jwetherell.quick_response_code.camera.CameraManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultMetadataType;
import com.jwetherell.quick_response_code.result.ResultHandler;
import com.jwetherell.quick_response_code.result.ResultHandlerFactory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.os.AsyncTask;
import java.util.Timer;
import java.util.TimerTask;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

import com.njfsoft_utils.anim.UtilsBitmap;


/**
 * Example Decoder Activity.
 * 
 * @author Justin Wetherell (phishman3579@gmail.com)
 */
public class DecoderActivity extends Activity implements IDecoderActivity, SurfaceHolder.Callback {

    private static final String TAG = DecoderActivity.class.getSimpleName();

    protected DecoderActivityHandler handler = null;
    protected ViewfinderView viewfinderView = null;
    protected CameraManager cameraManager = null;
    protected boolean hasSurface = false;
    protected Collection<BarcodeFormat> decodeFormats = null;
    protected String characterSet = null;
	String strRunning;

    private TextView statusView = null;
    private View resultView = null;
    private boolean inScanMode = false;
		UtilsBitmap utilsBitmap;
Timer atmrMovRec;

	int iMovWidth = 0;
	int iMovHeight = 0;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.capture);
        Log.v(TAG, "onCreate()");

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	strRunning = "no";
        handler = null;
        hasSurface = false;
        resultView = findViewById(R.id.result_view);
        statusView = (TextView) findViewById(R.id.status_view);
		utilsBitmap = new UtilsBitmap(this,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume()");

        // CameraManager must be initialized here, not in onCreate().
        if (cameraManager == null) cameraManager = new CameraManager(getApplication());

	  // cameraManager.setManualFramingRect(280, 280);

        if (viewfinderView == null) {
            viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
            viewfinderView.setCameraManager(cameraManager);
 
        }

        showScanner();

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            // The activity was paused but not stopped, so the surface still
            // exists. Therefore
            // surfaceCreated() won't be called, so init the camera here.
            initCamera(surfaceHolder);
        } else {
            // Install the callback and wait for surfaceCreated() to init the
            // camera.
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause()");

        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }

        cameraManager.closeDriver();

        if (!hasSurface) {
            SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
            SurfaceHolder surfaceHolder = surfaceView.getHolder();
            surfaceHolder.removeCallback(this);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA) {
            // Handle these events so they don't launch the Camera app
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null)
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
 

        // Ignore
    }

    @Override
    public ViewfinderView getViewfinder() {
        return viewfinderView;
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    @Override
    public CameraManager getCameraManager() {
        return cameraManager;
    }

    @Override
    public void handleDecode(Result rawResult, Bitmap barcode) {
        drawResultPoints(barcode, rawResult);
		atmrMovRec.cancel();
		atmrMovRec.purge();
        ResultHandler resultHandler = ResultHandlerFactory.makeResultHandler(this, rawResult);
        handleDecodeInternally(rawResult, resultHandler, barcode);
    }














public void runTmer(byte[] dta) {
 if(strRunning.equals("no")) {
strRunning = "yes";
// resultView.setVisibility(View.GONE);


                     System.out.println("runTmer: called ");   
 

                    try {
Rect frame = cameraManager.getFramingRect();
            Rect previewFrame = cameraManager.getFramingRectInPreview();
            float scaleX = frame.width() / (float) previewFrame.width();
            float scaleY = frame.height() / (float) previewFrame.height();
            int frameLeft = frame.left;
            int frameTop = frame.top;
		int currTopX = 150;
		int currTopY = 150;
		int currBtmX = 150;
		int currBtmY = 150;
		int currMinP = 150 * 150;
		int currMaxP = 150 * 150;

String ss = "";

List<ResultPoint> cpoints = viewfinderView.getCurrPoints();
			if(cpoints.size() == 4) {
			


	            for (ResultPoint point : cpoints) {
				if((point.getX() * point.getY()) < 	currMinP) {
				currMinP = (int) (point.getX() * point.getY());
				currTopX = (int) point.getX();
				currTopY = (int) point.getY();
				} else {
				if((point.getX() * point.getY()) > 	currMaxP) {
				currMaxP = (int) (point.getX() * point.getY());
				currBtmX = (int) point.getX();
				currBtmY= (int) point.getY();
				}

				}				
 				ss = ss + point.toString() + " :: ";
                  }




                  System.out.println("runTmer full: " + ss);

    int[] previewPixels = new int[iMovWidth * iMovHeight];
    utilsBitmap.decodeYUV420SP(previewPixels, dta, iMovWidth, iMovHeight);
    Bitmap b  = Bitmap.createBitmap(previewPixels, iMovWidth, iMovHeight, Bitmap.Config.RGB_565);

/*
int pleft = frameLeft + (int) (cpoints.get(0).getX() * scaleX);
int ptop = frameTop + (int) (cpoints.get(0).getY() * scaleY); 
int pright = frameLeft + (int) (cpoints.get(3).getX() * scaleX);
int pbottom = frameTop + (int) (cpoints.get(3).getY() * scaleY); 

*/

int pleft = frameLeft + (int) (currTopX * scaleX);
int ptop = frameTop + (int) (currTopY * scaleY); 
int pright = frameLeft + (int) (currBtmX * scaleX);
int pbottom = frameTop + (int) (currBtmY * scaleY); 


System.out.println("runTmer rect : " + pleft + " : " + ptop + " : " + pright + " : " + pbottom);

   Bitmap c  = Bitmap.createBitmap(b, pleft, ptop, pright, pbottom);
Canvas canvas = new Canvas(c);
System.out.println("runTmer img  : " + iMovWidth + " : " + iMovHeight);
System.out.println("runTmer cimg  : " + c.getWidth() + " : " + c.getHeight());
int wdt = c.getWidth(); 
int hght = c.getHeight();

 

for(int iqq=0; iqq < hght; iqq++)  
{  
    for(int j=0; j < wdt; j++)
    {

	  int pix = c.getPixel(j, iqq);
	System.out.println("pix: " + pix + " : " + j + " - " + iqq + " :: "  + Color.red(pix) + " : " + Color.green(pix) + " : " + Color.blue(pix));
    }

}
 


} else { // length isnt 4
strRunning = "no";
}
                    } catch (Exception e) {
				strRunning = "no";
                     System.out.println("runTmer: " + e.toString());
                        // TODO Auto-generated catch block
                    }

 
} else {
}
 
}






    // Put up our own UI for how to handle the decodBarcodeFormated contents.
    private void handleDecodeInternally(Result rawResult, ResultHandler resultHandler, Bitmap barcode) {
        onPause();
        inScanMode = false;

       CharSequence displayContents = resultHandler.getDisplayContents();
                            Intent intent = new Intent();
                    intent.putExtra("encdBmp", displayContents);
                    setResult(RESULT_OK, intent);
                    finish();
    }



    protected void drawResultPoints(Bitmap barcode, Result rawResult) {
				System.out.println("drawResultPoints called... : ");

        ResultPoint[] points = rawResult.getResultPoints();
        if (points != null && points.length > 0) {
                for (ResultPoint point : points) {
				System.out.println("drawResultPoints not null... : " + points.length + " :: " + point.toString());
                }

            Canvas canvas = new Canvas(barcode);
            Paint paint = new Paint();
            paint.setColor(getResources().getColor(R.color.result_image_border));
            paint.setStrokeWidth(3.0f);
            paint.setStyle(Paint.Style.STROKE);
            Rect border = new Rect(2, 2, barcode.getWidth() - 2, barcode.getHeight() - 2);
            canvas.drawRect(border, paint);

            paint.setColor(getResources().getColor(R.color.result_points));
            if (points.length == 2) {
                paint.setStrokeWidth(4.0f);
                drawLine(canvas, paint, points[0], points[1]);
            } else if (points.length == 4 && (rawResult.getBarcodeFormat() == BarcodeFormat.UPC_A || rawResult.getBarcodeFormat() == BarcodeFormat.EAN_13)) {
                // Hacky special case -- draw two lines, for the barcode and
                // metadata
                drawLine(canvas, paint, points[0], points[1]);
                drawLine(canvas, paint, points[2], points[3]);
		
		} else if (points.length == 4) {
		                     for (ResultPoint point : points) {
                    canvas.drawPoint(point.getX(), point.getY(), paint);
				System.out.println("drawResultPoints all 4: " + point.toString());
 				}
            } else {
                paint.setStrokeWidth(10.0f);
                for (ResultPoint point : points) {
                    canvas.drawPoint(point.getX(), point.getY(), paint);
				System.out.println("drawResultPoints... : " + points.length + " :: " + point.toString());
                }
            }
        }
		
    }

    protected static void drawLine(Canvas canvas, Paint paint, ResultPoint a, ResultPoint b) {
        canvas.drawLine(a.getX(), a.getY(), b.getX(), b.getY(), paint);
    }

    protected void showScanner() {
        viewfinderView.setVisibility(View.VISIBLE);
    }

    protected void initCamera(SurfaceHolder surfaceHolder) {
        try {
            cameraManager.openDriver(surfaceHolder);

 
            // Creating the handler starts the preview, which can also throw a
            // RuntimeException.
            if (handler == null) handler = new DecoderActivityHandler(this, decodeFormats, characterSet, cameraManager);

		Camera.Parameters parameters = cameraManager.getCamera().getParameters();
		iMovWidth = parameters.getPreviewSize().width;
		iMovHeight = parameters.getPreviewSize().height;


        } catch (IOException ioe) {
            Log.w(TAG, ioe);
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            Log.w(TAG, "Unexpected error initializing camera", e);
        }
    }
}
