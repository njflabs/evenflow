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

package com.njfsoft_utils.camcapture;

import java.io.IOException;
import java.util.Collection;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.*; 
import android.hardware.Camera;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ZoomControls;

 
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

 
import com.qbits.R;
import com.njfsoft_utils.core.Base64;
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
 
    protected String characterSet = null;
int currentZoomLevel = 0, maxZoomLevel = 0;
UtilsBitmap utilsBitmap;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.com_njfsoft_utils_camcapture_decoderactivity);
        Log.v(TAG, "onCreate()");

        VerticalTextView vtv = (VerticalTextView) findViewById(R.id.status_view);
        vtv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                   takeAPicture();
                } catch (Exception e) {
 
                }
            }
        });

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
 	   utilsBitmap = new UtilsBitmap(this,this);
	  handler = null;
        hasSurface = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy()");
    }

    @Override
    protected void onResume() {
		try {
        super.onResume();
        Log.v(TAG, "onResume()");

        // CameraManager must be initialized here, not in onCreate().
        if (cameraManager == null) cameraManager = new CameraManager(getApplication());

	  cameraManager.setManualFramingRect(280, 280);

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
		} catch(Exception e) {
        System.out.println("onResume.DecoderActivity.error: " + e);
		e.printStackTrace();
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

  //  Camera.Parameters params = cameraManager.getCamera().getParameters();

   
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
    public void handleDecode(Bitmap barcode) {

    }

    protected void drawResultPoints(Bitmap barcode) {
 
    }

    protected static void drawLine(Canvas canvas, Paint paint) {
 
    }

    protected void showScanner() {
        viewfinderView.setVisibility(View.VISIBLE);
    }


 

    protected void initCamera(SurfaceHolder surfaceHolder) {
        try {
            cameraManager.openDriver(surfaceHolder);
            // Creating the handler starts the preview, which can also throw a
            // RuntimeException.
            if (handler == null) handler = new DecoderActivityHandler(this, characterSet, cameraManager);
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            Log.w(TAG, "Unexpected error initializing camera", e);
        }


 
     

    }

 



    protected void takeAPicture() {
                System.out.println("takePicture");
		cameraManager.getCamera().setOneShotPreviewCallback(prevCallBack);
 
    }

 





    public Camera.PreviewCallback prevCallBack = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {

 
            try { 
 
 			doArtPadBitmap(data);
           
            } catch (Exception e) {
                System.out.println("onPictureTaken: " + e.toString());

            }
        }
    };


    public static Bitmap getSepiaEffect(Bitmap mBitmap) {
        ColorMatrix sepiaMatrix = new ColorMatrix();
        float[] sepMat = {0.3930000066757202f, 0.7689999938011169f, 0.1889999955892563f, 0, 0, 0.3490000069141388f, 0.6859999895095825f, 0.1679999977350235f, 0, 0, 0.2720000147819519f, 0.5339999794960022f, 0.1309999972581863f, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1};
        sepiaMatrix.set(sepMat);
        final ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(sepiaMatrix);
        Bitmap rBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Paint paint = new Paint();
        paint.setColorFilter(colorFilter);
        Canvas myCanvas = new Canvas(rBitmap);
        myCanvas.drawBitmap(rBitmap, 0, 0, paint);

        return changeToGray(rBitmap);
    }


 
	public static Bitmap changeToGray(Bitmap bitmap) {
		int width, height;
		width = bitmap.getWidth();
		height = bitmap.getHeight();
			
		Bitmap grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(grayBitmap);
		Paint paint = new Paint();
		paint.setAntiAlias(true); // ?????
			
		ColorMatrix colorMatrix = new ColorMatrix();
		colorMatrix.setSaturation(2.833f);
			
		ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
			
		paint.setColorFilter(filter);
		canvas.drawBitmap(bitmap, 0, 0, paint);
			
		return grayBitmap;
	}




    public Bitmap doClipMask(Bitmap bmpToMask, Bitmap bmpTheMask, int iw, int ih) {
     Bitmap bmpBG = Bitmap.createBitmap(iw,ih, Bitmap.Config.ARGB_8888);



    Canvas cnvMask = new Canvas(bmpBG); 
    Paint maskPaint = new Paint();
 
 Rect rs = new Rect();
Rect rd = new Rect();
Rect rss = new Rect();
rs.left = rs.top = 0;
rs.right = bmpToMask.getWidth();
rs.bottom = bmpToMask.getHeight();
rss.left = rss.top = 0;
rss.right = bmpTheMask.getWidth();
rss.bottom = bmpTheMask.getHeight();

rd.left = rd.top = 0;
rd.right = iw;
rd.bottom = ih;
 
 


    cnvMask.drawBitmap(bmpToMask, rs, rd, null);
    cnvMask.drawBitmap(bmpTheMask, rss, rd, null);

    return changeToGray(bmpBG);
    }




    public void doArtPadBitmap(byte[] bMapArray) {
        try {

    Camera.Parameters parameters = cameraManager.getCamera().getParameters();
    int width = parameters.getPreviewSize().width;
    int height = parameters.getPreviewSize().height;
    int iwidth = width;
    int iheight = height;




         if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }

        cameraManager.closeDriver();
 
      Bitmap rotated;
      Bitmap srotated;
      Bitmap bitmap;
     Matrix matrix = new Matrix();

    int[] previewPixels = new int[width * height];
    decodeYUV420SP(previewPixels, bMapArray, width, height);
      bitmap  = Bitmap.createBitmap(previewPixels, width, height, Bitmap.Config.RGB_565);
	// BitmapDrawable aslaplogo = (BitmapDrawable) getApplicationContext().getResources().getDrawable(R.drawable.splash);
  // Bitmap atheSlapLogo = aslaplogo.getBitmap();

     ByteArrayOutputStream bbaos = new ByteArrayOutputStream();

/*
        if (iwidth > 400 || iheight > 400) {
            float widthRatio = ((float) iwidth) / ((float) 400);
            float heightRatio = ((float) iheight) / ((float) 400);

                iwidth = (int)widthRatio;
		    iheight = (int)heightRatio; 
           //  float maxRatio = Math.max(widthRatio, heightRatio);
 	   }


    Bitmap bmpDrotated;

    if(height > width) {
    iwidth = height; 
    iheight = width;
     matrix.postRotate(90);
      bmpDrotated = Bitmap.createBitmap(bitmap, 0, 0, iwidth, iheight,matrix, true);
	rotated = utilsBitmap.getResizedBitmap(bmpDrotated, (int)(iwidth*0.40), (int)(iheight*0.40));
   }  else {

 	}
      matrix.postRotate(90);
      rotated = Bitmap.createBitmap(drotated, 0, 0, drotated.getHeight(), drotated.getWidth(), matrix, true);
 
  */


     rotated = utilsBitmap.getResizedBitmap(bitmap, (int)(width*0.40), (int)(height*0.40));
    

 
    rotated.compress(Bitmap.CompressFormat.JPEG, 100, bbaos);

                    bbaos.close();
                    byte[] bbMapArray = bbaos.toByteArray();
                    String bencodedImage = Base64.encodeBytes(bbMapArray);



                    Intent intent = new Intent();
			  // String encodedImage = Base64.encodeBytes(bMapArray);
                    intent.putExtra("encdBmp", bencodedImage);
                    setResult(RESULT_OK, intent);
                    finish();

        } catch(Exception e) {
            System.out.println("doArtPadBitmap: " + e.toString());
        }
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



}
