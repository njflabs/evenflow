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

package com.njfsoft_utils.cutOuts;
import com.qbits.R;

 

import android.os.Environment;
import java.io.IOException;
import java.util.Collection;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.*; 
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;


import android.net.Uri;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import android.media.MediaRecorder;

import android.media.MediaPlayer;

 
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.view.animation.*;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;



import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;



import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import org.json.JSONArray;
import org.json.JSONObject;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;





import android.view.WindowManager;
import android.widget.ZoomControls;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import java.util.Timer;
import java.util.TimerTask;


import com.njfsoft_utils.cutOuts.JSI_CutOuts;

 



import com.njfsoft_utils.artpad.filters.ColorFilterGenerator;
import com.njfsoft_utils.anim.AnimationDrawableCallback;
import com.njfsoft_utils.anim.AnimEngine;
import com.njfsoft_utils.anim.AnimatedGifRecorder;
import com.njfsoft_utils.anim.AnimFrameSingleton;
import com.njfsoft_utils.anim.AnimMovBuilder;
import com.njfsoft_utils.anim.AnimMovSingleton;
import com.njfsoft_utils.anim.AnimMovSoundPool;
import com.njfsoft_utils.anim.AnimView;
import com.njfsoft_utils.anim.AnimBGView;
import com.njfsoft_utils.anim.AnimPrevView;
import com.njfsoft_utils.anim.MPFourRecorder;
import com.njfsoft_utils.anim.UtilsBitmap;


import com.njfsoft_utils.core.Base64;
import com.njfsoft_utils.core.OnTaskExecutionFinished;
import com.njfsoft_utils.camcapture.IDecoderActivity;
import com.njfsoft_utils.camcapture.DecoderActivityHandler;
import com.njfsoft_utils.camcapture.CameraManager;
import com.njfsoft_utils.camcapture.VerticalTextView;

import com.njfsoft_utils.shareutil.ShareDataResult;
import com.njfsoft_utils.webviewutil.UtilWebDialog;
import com.uraroji.garage.android.mp3recvoice.RecMicToMp3;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

 import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;




/**
 * Example Decoder Activity.
 * 
 * @author Justin Wetherell (phishman3579@gmail.com)
 */
public class CutOuts extends Activity  implements IDecoderActivity, SurfaceHolder.Callback {
      UtilsBitmap utilsBitmap;
      ColorFilterGenerator mednFilter;
      public UtilWebDialog utilWDialog;
      private static final String TAG = CutOuts.class.getSimpleName();
      DecoderActivityHandler handler = null;

      CutOutsView viewfinderView = null;
      CameraManager cameraManager = null;
      boolean hasSurface = false;
      byte[] currImgByte = null;
      String characterSet = null;
	String currGiphyGID;
	String currMovType = "mp4";
    	String currMovFName;
      int currentZoomLevel = 0, maxZoomLevel = 0;

      private boolean isRecording = false;
      MediaPlayer mPlayer;
      private MediaRecorder mMediaRecorder;

      SurfaceView surfaceView;
      SurfaceHolder surfaceHolder;
      TextView vtv;

      private boolean mPreviewRunning = false;
      private boolean mCaptureFrame = false;
      AnimatedGifRecorder gagRecorder;
      MPFourRecorder agRecorder;

      Bitmap currFBmap;


      RecMicToMp3 mRecMicToMp3;
      AnimView gifView;
      AnimBGView gifBgView;
      AnimPrevView gifPrevView;
      AnimMovBuilder animMovBuilder;
      AnimMovSoundPool animMovSoundPool;
      RelativeLayout lnrLyGBGView;

      RelativeLayout lnrLyGPView;
	AnimEngine animEngine;
      AnimationDrawable gifAnimation = null;
      AnimationDrawable gifBgAnimation = null;
      Paint ctClrPaint;


      Animation animPropA;
      Animation animPropB;
      AnimationSet animSet;
      TextView stv;
      Bitmap bmpMainCanvImg;
      TextView tvAPVPreview;
      TextView tvAPVSave;
      TextView tvAPVClear;

	ImageButton btnCam;
	ImageButton btnVid;
	ImageButton btnGifVid;
	ImageButton btnSettings;
      Timer tmrMovRec;
      int iMovStartTstamp;
      long lMovStartTstamp;
      ArrayList<AnimFrameSingleton> arrAnimFSing;
      int iPrepArrPFrame = 0;
      int iMovFDelay = 400;
      int iAnimFrmIdx;
      int iBgAnimFrmIdx;

	int iMovWidth = 0;
	int iMovHeight = 0;

    Bundle extras;

/*
 
	private static final int[] IMAGE_RESOURCES = { R.drawable.an_gimp1,
			R.drawable.an_gimp2, R.drawable.an_gimp3, R.drawable.an_gimp4,
			R.drawable.an_gimp5, R.drawable.an_gimp6, R.drawable.an_gimp7,
			R.drawable.an_gimp8 };

	private static final int[] IMAGE_RESOURCES = { R.drawable.h1,
			R.drawable.h2, R.drawable.h3, R.drawable.h4,
			R.drawable.h5, R.drawable.h6, R.drawable.h7,
			R.drawable.h8 };




*/

	private static final int[] IMAGE_RESOURCES = { R.drawable.an_drag1,
			R.drawable.an_drag2, R.drawable.an_drag3, R.drawable.an_drag4,
			R.drawable.an_drag5, R.drawable.an_drag6, R.drawable.an_drag7,
			R.drawable.an_drag8, R.drawable.an_drag9, R.drawable.an_drag10,
			R.drawable.an_drag11, R.drawable.an_drag12, R.drawable.an_drag13 };

	private static final int ANIMATION_INTERVAL = 500;// 200ms







	public void loadGifView() {

//


        if (gifView == null) {
      gifView = (AnimView) findViewById(R.id.gif_view);  


      	gifView.setAdjustViewBounds(true);
      	gifView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		animEngine = AnimEngine.getInstance(gifView);
		animEngine.addAllFrames(IMAGE_RESOURCES, ANIMATION_INTERVAL);

		}



		// animEngine.start();



/*





 	// gifView.setBackgroundResource(R.drawable.selfielander_earthrise_mask);
       // gifView.setImageDrawable(getResources().getDrawable(R.drawable.com_elastic_pad_utils_cutouts_horse));
 	 // gifAnimation = (AnimationDrawable) gifView.getDrawable();
	//gifAnimation = new AnimationDrawable((AnimationDrawable) getResources().getDrawable(R.drawable.com_elastic_pad_utils_cutouts_gimphy));
	// gifAnimation = new AnimationDrawable(gifView.getDrawable());
       // gifView.setImageDrawable(gifAnimation);




gifAnimation.setCallback(new AnimationDrawableCallback(gifAnimation, gifView) {
    @Override
    public void onAnimationCompleted() {
        System.out.println("AnimationDrawableCallback:onAnimationComplete ");

        // TODO Do something.
    }
    @Override
    public void onAnimationAdvanced(int currentFrame, int totalFrames) {
		iAnimFrmIdx = currentFrame;

        System.out.println("AnimationDrawableCallback:onAnimationAdvanced: " + currentFrame + " :: " + totalFrames);

    }
});
 
*/

 


		mednFilter = new com.njfsoft_utils.artpad.filters.ColorFilterGenerator();
		ctClrPaint = new Paint();
 		ctClrPaint.setColorFilter(mednFilter.adjustColor(30,15,95,95));
 
	}


	public void startAnimProps(){
/*
      	 animPropA = AnimationUtils.loadAnimation(this,R.anim.com_elastic_pad_utils_cutouts_gimphy);
      	 animPropB = AnimationUtils.loadAnimation(this,R.anim.com_elastic_pad_utils_cutouts_rain);

           animSet = new AnimationSet(false);//false means don't share interpolators
           animSet.addAnimation(animPropA);
           animSet.addAnimation(animPropB);
           gifView.startAnimation(animSet);
*/
	}


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.com_njfsoft_utils_cutouts);
	  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Log.v(TAG, "onCreate()");

	  
		try {
      File extBaseDir = Environment.getExternalStorageDirectory();
      File file = new File(extBaseDir.getAbsoluteFile()+ File.separator + "quick-order");
      if(!file.exists()){
      file.mkdirs();
      }      
                } catch (Exception e) {
 			e.printStackTrace();
                }

        extras = getIntent().getExtras(); 
		animMovSoundPool = new AnimMovSoundPool(this);

            // gifView = (AnimView) findViewById(R.id.gif_view);  
		loadGifView();

	 	// gifAnimation = (AnimationDrawable) gifView.getBackground();
	
            viewfinderView = (CutOutsView) findViewById(R.id.cutouts_view);
       currMovFName = "outa.mp4";

        vtv = (TextView) findViewById(R.id.status_view);
        vtv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
				currMovType = "gif";
				onRecClick(view);
                    // takeAPicture();
                } catch (Exception e) {
 
                }
            }
        });



 
 	  arrAnimFSing = new ArrayList<AnimFrameSingleton>();
	  btnVid = (ImageButton) findViewById(R.id.btn_vid);
	  btnGifVid = (ImageButton) findViewById(R.id.btn_gifvid);
	  btnCam = (ImageButton) findViewById(R.id.btn_cam);
	  btnSettings = (ImageButton) findViewById(R.id.btn_settings);


        btnVid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                 currMovType = "mp4";
			onRecClick(view);
            }
        });

        btnGifVid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                  currMovType = "gif";
			onRecClick(view);
            }
        });

        btnCam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
		 currMovType = "jpeg";
    cameraManager.getCamera().autoFocus(new AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            if(success){
			takeAPicture();
 
            }
        }
     });
	}
    });


 


        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setPagePopUp("quickorder/media_chooser.html","noQvalue");
            }
        });







 


        tvAPVPreview = (TextView) findViewById(R.id.btnAPVPreview);
	  tvAPVPreview.setVisibility(View.INVISIBLE);
        tvAPVPreview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {

				AnimMovSingleton tmpAMS = animMovBuilder.getAnimMovSing();
				if(tmpAMS != null) {
				setToggleAPViewBtns(false);
     			 	 boolean isGTPV = gifPrevView.setAnimMovSing(tmpAMS);
				 playFile();
				}
                } catch (Exception e) {
 			System.out.println("tvAPVPreview.error: " + e.toString());
                }
            }
        });


        tvAPVSave = (TextView) findViewById(R.id.btnAPVSave);
	  tvAPVSave.setVisibility(View.INVISIBLE);
        tvAPVSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
			if(currMovType == "jpeg") {


 	 Bitmap bmpTrmpface = gifBgView.get();
	if(bmpTrmpface != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bmpTrmpface.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    baos.close();
                    byte[] bMapArray = baos.toByteArray();
		ShareDataResult.getInstance().setCallingApp("CutOuts");		
		 ShareDataResult.getInstance().setImgBytes(bMapArray);
		 ShareDataResult.getInstance().setImgExt("jpeg");
               Intent intent = new Intent();
              setResult(RESULT_OK, intent);
		 finish();

     		}
			
			} else {
			}
                } catch (Exception e) {
 			System.out.println("tvAPVSave.error: " + e.toString());
                }
            }
        });


        tvAPVClear = (TextView) findViewById(R.id.btnAPVClear);
	  tvAPVClear.setVisibility(View.INVISIBLE);
        tvAPVClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
			gifBgView.clearBmap();     
			 
			setToggleAPViewBtns(false);
 
		AnimMovSingleton anms = AnimMovSingleton.getInstance();
		anms.clearAMS();
		arrAnimFSing = null;
		arrAnimFSing = new ArrayList<AnimFrameSingleton>();
                } catch (Exception e) {
 			System.out.println("tvAPVClear.error: " + e.toString());
                }
            }
        });


        stv = (TextView) findViewById(R.id.status_stop);
        stv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
			// startAnimProps();

// gifView.startAnimation(animPropA);
// gifView.startAnimation(animPropB);

		 // setPagePopUp("quickorder/pics.html","noQvalue");
				// boolean isGTPV = gifPrevView.setAnimMovSing(animMovBuilder.getAnimMovSing());
				// playFile();
				// stopRecording();
                    // takeAPicture();









		// 	onEPResult();
		prepMovCrunch();




                } catch (Exception e) {
 			System.out.println("stv.error: " + e.toString());
                }
            }
        });



 

 

        gifBgView = new AnimBGView(this, this);  
        gifBgView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
/*
        gifBgView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
           


    cameraManager.getCamera().autoFocus(new AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            if(success){
			takeAPicture();
 
            }
        }
     });


	}
    });

*/


	  lnrLyGBGView = (RelativeLayout) this.findViewById(R.id.gbgv_main);
 	 lnrLyGBGView.addView(gifBgView);



        gifPrevView = new AnimPrevView(this, this);  
        gifPrevView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

 


	  lnrLyGPView = (RelativeLayout) this.findViewById(R.id.gpv_main);
 	 lnrLyGPView.addView(gifPrevView);


 	 // gifPrevView.setBackgroundResource(R.drawable.com_elastic_pad_games_selfielander_lander_firing);

      //  addContentView(gifPrevView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));



 
	   utilsBitmap = new UtilsBitmap(this,this);
         agRecorder = new MPFourRecorder(this,this);
         gagRecorder = new AnimatedGifRecorder(this,this);
        //  agRecorder = new AnimatedGifRecorder(this,this);
	  animMovBuilder = new AnimMovBuilder(this,this);
	  
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
 	  bmpMainCanvImg = null;
	  handler = null;
        hasSurface = false;
	  mPlayer = new MediaPlayer();
	 iMovStartTstamp = 0;
	lMovStartTstamp = 11;
	currGiphyGID = "123";
	preparePagePopUps("quickorder/media_chooser.html", "noQvalue");

        if (extras != null) {
            if (extras.containsKey("apmode")) {
                currMovType = extras.getString("apmode");
 			System.out.println("extras.apmode: " + currMovType);
            }
            if (extras.containsKey("apmeta")) {


                animMovBuilder.setAPmeta(extras.getString("apmeta"));
            }

            if (extras.containsKey("apfile")) {
                currMovFName = extras.getString("apfile");
            }
    }

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
	  loadGifView();


        // CameraManager must be initialized here, not in onCreate().
        if (cameraManager == null) cameraManager = new CameraManager(getApplication());

	  // cameraManager.setManualFramingRect(280, 280);

        if (viewfinderView == null) {
            viewfinderView = (CutOutsView) findViewById(R.id.cutouts_view);
            viewfinderView.setCameraManager(cameraManager);
        }

        showScanner();


        surfaceView = (SurfaceView) findViewById(R.id.preview_view);
	 // surfaceView.setZOrderOnTop(true);    // necessary

        surfaceHolder = surfaceView.getHolder();
	  // surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
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
        System.out.println("onResume.CutOuts.error: " + e);
		e.printStackTrace();
		}
        if (gifBgView == null) {
        gifBgView = new AnimBGView(this, this);  
        gifBgView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	  lnrLyGBGView = (RelativeLayout) this.findViewById(R.id.gbgv_main);
 	 lnrLyGBGView.addView(gifBgView);
		}
        if (gifPrevView == null) {
        gifPrevView = new AnimPrevView(this, this);  
        gifPrevView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
 	 // gifPrevView.setBackgroundResource(R.drawable.com_elastic_pad_games_selfielander_lander_firing);

	  lnrLyGPView = (RelativeLayout) this.findViewById(R.id.gpv_main);
 	 lnrLyGPView.addView(gifPrevView);
	 }

        // gifPrevView = new AnimPrevView(this, this);  
	  // lnrLyGPView = (LinearLayout) this.findViewById(R.id.gpv_main);
 	//  lnrLyGPView.addView(gifPrevView);
      // addContentView(gifPrevView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));



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

/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA) {
            // Handle these events so they don't launch the Camera app
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

*/
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

 
    }


     @Override
    public CutOutsView getViewfinder() {
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
		// cameraManager.getCamera().setPreviewCallback(prevCallBack);
            // Creating the handler starts the preview, which can also throw a
            // RuntimeException.
          if (handler == null)  {
		 handler = new DecoderActivityHandler(this, characterSet, cameraManager);
		}
		Camera.Parameters parameters = cameraManager.getCamera().getParameters();
		iMovWidth = parameters.getPreviewSize().width;
		iMovHeight = parameters.getPreviewSize().height;

parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
//some more settings
cameraManager.getCamera().setParameters(parameters);


        } catch (IOException ioe) {
            Log.w(TAG, ioe);
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            Log.w(TAG, "Unexpected error initializing camera", e);
        }

    }



   public Bitmap getCurrCanvasImg() {
	return currFBmap;
   }





 






    public Camera.PreviewCallback aframeCallBack = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) { 
            try { 
			if(isRecording) {
// 			prepPFrame(data); 
                  
			}         
            } catch (Exception e) {
                System.out.println("onPictureTaken: " + e.toString());

            }
        }
    };


 

    public void sendAnotherFrame(int fcnt) {
		cameraManager.getCamera().setOneShotPreviewCallback(aframeCallBack); 
   }



    protected void takeAPicture() {
                System.out.println("takePicture");
 			
			if(currMovType.equals("trumpster")) {
 
 	  BitmapDrawable myPropIcon = (BitmapDrawable) getResources().getDrawable(R.raw.helmet);
        utilsBitmap.setFaceProp(myPropIcon.getBitmap());
cameraManager.getCamera().setPreviewCallback(trumpsterCallBack);
} else {
		cameraManager.getCamera().setOneShotPreviewCallback(prevCallBack);
}
//  cameraManager.getCamera().setPreviewCallback(prevCallBack);
 
    }

 

 




    public Camera.PreviewCallback trumpsterCallBack = new Camera.PreviewCallback() {
 

 
      @Override
        public void onPreviewFrame(byte[] data, Camera camera) { 
            try { 
 

    int[] previewPixels = new int[iMovWidth * iMovHeight];
    utilsBitmap.decodeYUV420SP(previewPixels, data, iMovWidth, iMovHeight);
    Bitmap b  = Bitmap.createBitmap(previewPixels, iMovWidth, iMovHeight, Bitmap.Config.RGB_565);
 	 Bitmap adbmpAToMask = utilsBitmap.scaleBoundBitmap(b, 520);


 	 Bitmap bmpTrmpface = utilsBitmap.getFaceMap(adbmpAToMask);
	if(bmpTrmpface != null) {
	 
			  
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bmpTrmpface.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    baos.close();
                    byte[] bMapArray = baos.toByteArray();



		ShareDataResult.getInstance().setCallingApp("CutOuts");		
 
 
 		ShareDataResult.getInstance().setImgName(currMovFName.substring(0, currMovFName.lastIndexOf(".") - 1) + ".jpeg");
 

		 ShareDataResult.getInstance().setImgBytes(bMapArray);
 
 

               Intent intent = new Intent();
              setResult(RESULT_OK, intent);
		 finish();

     		}
            } catch (Exception e) {
                System.out.println("trumpCallBack: " + e.toString());
		e.printStackTrace();
            }
        }

 
    };


    public Camera.PreviewCallback prevCallBack = new Camera.PreviewCallback() {
 

 
      @Override
        public void onPreviewFrame(byte[] data, Camera camera) { 
            try { 
 

    int[] previewPixels = new int[iMovWidth * iMovHeight];
    utilsBitmap.decodeYUV420SP(previewPixels, data, iMovWidth, iMovHeight);
    Bitmap b  = Bitmap.createBitmap(previewPixels, iMovWidth, iMovHeight, Bitmap.Config.RGB_565);
 	 Bitmap adbmpAToMask = utilsBitmap.scaleBoundBitmap(b, 520);
 
    gifBgView.setBmap(animMovBuilder.getItemBmp(adbmpAToMask));
	setToggleAPViewBtns(true);
/*
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    adbmpAToMask.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    baos.close();
                    byte[] bMapArray = baos.toByteArray();



		ShareDataResult.getInstance().setCallingApp("CutOuts");		
 
 
 		ShareDataResult.getInstance().setImgName(currMovFName.substring(0, currMovFName.lastIndexOf(".") - 1) + ".jpeg");
 

		 ShareDataResult.getInstance().setImgBytes(bMapArray);
 
 

               Intent intent = new Intent();
              setResult(RESULT_OK, intent);
		 finish();
*/
     
            } catch (Exception e) {
            System.out.println("prevCallBack: " + e.toString());
		e.printStackTrace();
            }
        }

 
    };




    public void onRecClick(View view) {
            try { 
                System.out.println("onRecClick: isRecording " + isRecording);

        if(isRecording) {
            setCaptureButtonText("Capture");
            isRecording = false;
		animEngine.stop();
	   	// gifAnimation.stop();
		if(gifBgAnimation != null) {
 	   	gifBgAnimation.stop();
		}
		tmrMovRec.cancel();
		tmrMovRec.purge();
		if(mMediaRecorder != null) {
            mMediaRecorder.stop();  // stop the recording
            releaseMediaRecorder(); // release the MediaRecorder object
            System.out.println("onRecClick.CronTime:MediaRecorder stop" + System.currentTimeMillis());
		}

		

		prepMovBuild();
		// prepMovCrunch();
            // releaseCamera();
        } else {
            isRecording = true;
	 //  Toast.makeText(CutOuts.this, "Loading...", Toast.LENGTH_LONG).show();
	  //   callAsynchronousTask();
	   callMediaPrepTask();
        }
            } catch (Exception e) {
                System.out.println("onRecClick: " + e.toString());
			e.printStackTrace();

            }

    }








    private void setCaptureButtonText(String title) {
        vtv.setText(title);
    }








// ----------------- unused native recording stuff





 



 

    private void releaseMediaRecorder(){
        if (mMediaRecorder != null) {
            // clear recorder configuration
            mMediaRecorder.reset();
            // release the recorder object
            mMediaRecorder.release();
            mMediaRecorder = null;
            // Lock camera for later use i.e taking it back from MediaRecorder.
            // MediaRecorder doesn't need it anymore and we will release it if the activity pauses.
           //  cameraManager.getCamera().lock();
        }
    }

    private void releaseCamera(){
              //  cameraManager.closeDriver();
    }




 



 
    private boolean prepareVideoRecorder(){


  	     String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        try {   
	  File filePath = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "quick-order");
	  filePath.mkdirs();
	 //  String filename = timeStamp + ".3gp";
       String filename = "outa.3gp";
       File ffile = new File(filePath,filename);

        mMediaRecorder = new MediaRecorder();
 
 
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	  mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
 	  mMediaRecorder.setOutputFile(ffile.toString());
         } catch (Exception e) {
            Log.d(TAG, "Exception preparing MediaRecorder: " + e.getMessage());
		e.printStackTrace();
	   }
        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            Log.d(TAG, "IllegalStateException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d(TAG, "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;
    }



    /**
     * Asynchronous task for preparing the {@link android.media.MediaRecorder} since it's a long blocking
     * operation.
     */
    class MediaPrepareTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            // initialize video camera
            if (prepareVideoRecorder()) {
                // Camera is available and unlocked, MediaRecorder is prepared,
                // now you can start recording
                System.out.println("MediaPrepareTask.CronTime:Recorder start" + System.currentTimeMillis());

                mMediaRecorder.start();

                isRecording = true;
            } else {
                // prepare didn't work, release the camera
                releaseMediaRecorder();
                System.out.println("MediaPrepareTask.releaseMediaRecorder.error");

                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
                System.out.println("MediaPrepareTask.CronTime:Recorder onPostExecute" + System.currentTimeMillis());

            if (!result) {


             //    MainActivity.this.finish();
            }
            // inform the user that recording has started
            // setCaptureButtonText("Stop");
         setCaptureButtonText("Stop");
		 animEngine.start();
	   // gifAnimation.start();
	   if(gifBgAnimation != null) {
 	    gifBgAnimation.start();
	   }
	   callAsynchronousTask();
         isRecording = true;
	  //  mRecMicToMp3.start();
      //  sendAnotherFrame(0);	  

        }
    }






public void setToggleAPViewBtns(final boolean fnlBooltoShow) {
		this.runOnUiThread(new Runnable() {
		   public void run() {
				try {

        if(fnlBooltoShow) {
	tvAPVPreview.setVisibility(View.VISIBLE);
	tvAPVSave.setVisibility(View.VISIBLE);
	tvAPVClear.setVisibility(View.VISIBLE);
 
	  } else {
	tvAPVPreview.setVisibility(View.INVISIBLE);
	tvAPVSave.setVisibility(View.INVISIBLE);
	tvAPVClear.setVisibility(View.INVISIBLE);
 
	  }

	  } catch(Exception e) {
        System.out.println("dev:ERROR:setToggleAPViewBtns: " + e);
	  }
	   }
         });

	}




    public void preparePagePopUps(String pageUrl, String pageHtml) {
        String fullUrl = "file:///android_asset/" + pageUrl;
        String newHTML = "";

        UtilWebDialog.UtilWDListener utilWDListener = new UtilWebDialog.UtilWDListener() {

            public void epMDcom(int cbType, String cbArgs, UtilWebDialog epmd) {
                final String fnlCbArgs;
                epmd.doDismiss();
			// stopPlayFile();
			// handler.removeCallbacks(thrdTask);
			// handler.postDelayed(thrdTask, 0);
                   
            System.out.println("dev:SmsCanvas:epMainDListener: " + cbType + " : " + cbArgs);
                switch (cbType) {
                    case 60:
		            // doAPJSComm(60);
                    default:
                        break;
                }
            }
        };
        utilWDialog = new UtilWebDialog(this, fullUrl, pageHtml, utilWDListener, new JSI_CutOuts(this), "app_share");
	  utilWDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }



    public void setPagePopUp(final String pageUrl, final String pageHtml) {

 
        try {
            this.runOnUiThread(new Runnable() {
                public void run() {
                    utilWDialog.setPopPage(pageUrl, pageHtml);
                }
            });


        } catch (Exception e) {
            System.out.println("dev:ERROR:setPagePopUp:" + e.toString());
		e.printStackTrace();
        }


    }










public void stopPlayFile()
{
    try {
        mPlayer.stop();
    } catch (Exception e) {
        e.printStackTrace();
    }

}//end stopPlayFile

public void playFile()
{
    try {
        String path = new File(Environment.getExternalStorageDirectory() + "/quick-order/outa.3gp").getAbsolutePath();
        mPlayer.reset();
	  mPlayer.setLooping(false);
        mPlayer.setDataSource(path);
        mPlayer.prepare();
        mPlayer.start();
    } catch (Exception e) {
        e.printStackTrace();
    }

}//end playFile

 

 

    public Camera.PreviewCallback cbMovFrame = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) { 
            try { 
			if(isRecording) {
		int itmpIFI = 0;
       long l = System.currentTimeMillis();
       int iNTime = (int)(l - lMovStartTstamp); 

    int[] previewPixels = new int[iMovWidth * iMovHeight];
    utilsBitmap.decodeYUV420SP(previewPixels, data, iMovWidth, iMovHeight);
    Bitmap b  = Bitmap.createBitmap(previewPixels, iMovWidth, iMovHeight, Bitmap.Config.RGB_565);
 	 Bitmap adbmpAToMask = utilsBitmap.scaleBoundBitmap(b, 320);


			if(arrAnimFSing.size() == 0) { // add first fram at 0 timestamp
			System.out.println("cbMovFrame: 0000000000");
		       AnimFrameSingleton anfs = new AnimFrameSingleton();
			 anfs.setIpst(0);
			  anfs.setMBytes(data);
			 anfs.setIanmFrmTS(0);		
	 		 anfs.setMBitmap(adbmpAToMask);	 
			 arrAnimFSing.add(anfs);
			 // gifAnimation.getAnimFrmIdx();

		     }
       AnimFrameSingleton aanfs = new AnimFrameSingleton();
	 aanfs.setIpst(iNTime);
	  aanfs.setMBytes(data);



    previewPixels = null;
    gifBgView.setBmap(adbmpAToMask);




		 // aanfs.setMAnimResInt(animEngine.getAEngFrameRes());
		aanfs.setIanmFrmTS(iAnimFrmIdx);
	 aanfs.setMBitmap(adbmpAToMask);
       arrAnimFSing.add(aanfs);

stv.setText("Recording Frame : " + arrAnimFSing.size()  + " of 14");
       if(iAnimFrmIdx == 7) {
			// playFile();
	// animMovSoundPool.playSound(1);
	  }
 
      if(arrAnimFSing.size() == 14) {
      onRecClick(vtv);
      }

 
	  // itmpIFI = gifAnimation.getAnimFrmIdx();
	   System.out.println("cbMovFrame: " + iNTime + "iAnimFrmIdx: " + iAnimFrmIdx);
			 // prepArrPFrame(data);                 
			}         
            } catch (Exception e) {
                System.out.println("cbMovFrame: " + e.toString());
		e.printStackTrace();
            }
        }


    };







 


  public void timeMovParse() {
			

			if(currMovType.equals("mp4")) {
 

 		 // currMovFName = currMovFName.substring(0, currMovFName.lastIndexOf(".") - 1) + ".mp4";
                  String tmpFnae = ShareDataResult.getInstance().getImgName() + ".mp4";
                  new com.njfsoft_utils.anim.MPFourParser().procMPFourPars(tmpFnae, new com.njfsoft_utils.core.OnTaskExecutionFinished()
                  {
                  @Override
                  public void OnTaskFihishedEvent(String result)
                  {

                        System.out.println("Cutouts:timeMovParse: " +  (String)result);
 
				AnimMovSingleton tmpAMS = agRecorder.getAnimMovSing();
				if(tmpAMS != null) {
                        // System.out.println("Cutouts:timeMovParse:getAnimMovSing tmpAMS" +  tmpAMS.getMamsArrAFS().size());
				// boolean isGTPV = gifPrevView.setAnimMovSing(tmpAMS);
				// playFile();
				onEPResult();
				}

 				// postGiphyVid();
                        // setPagePopUp("canvas/characters.html","noQvalue");

                  }
                 });


			} else {
				onEPResult();
			}	

  }

 
  public void animPlayEnded() {
       setToggleAPViewBtns(true);
     // setPagePopUp("quickorder/media_chooser.html","noQvalue");
  }
  public void timeAnimMovBuild() {
                        System.out.println("Cutouts:timeAnimMovBuild: nada ");

                  animMovBuilder.procAnimBuild("nada", new com.njfsoft_utils.core.OnTaskExecutionFinished()
                  {
                  @Override
                  public void OnTaskFihishedEvent(String result)
                  {
               		try {
				
                        if(result.equals("isDone")) {
 				 stv.setText(" Share");

				// dont make it yet, just show it:
     				// prepMovCrunch();
				AnimMovSingleton tmpAMS = animMovBuilder.getAnimMovSing();
				if(tmpAMS != null) {
				setToggleAPViewBtns(true);
     				// setPagePopUp("quickorder/media_chooser.html","noQvalue");
                        // System.out.println("Cutouts:timeAnimMovBuild:getAnimMovSing tmpAMS" +  tmpAMS.getMamsArrAFS().size());
				// boolean isGTPV = gifPrevView.setAnimMovSing(tmpAMS);
				// playFile();
				}
 

              		} else {
				iPrepArrPFrame++;
 				 // stv.setText("Recording Frame : " + animMovBuilder.allBmaps  + " of " + animMovBuilder.getAnimMovSing().getMamsArrAFS().size());
              	       timeAnimMovBuild();
              		}
                        System.out.println("Cutouts:timeAnimMovBuild Response From Asynchronous task: " + animMovBuilder.allBmaps + " :: " +  (String)result);

                    } catch (Exception e) {
                        System.out.println("Cutouts:timeAnimMovBuild:getAnimMovSing Error" +  e);
				e.printStackTrace();
                        // TODO Auto-generated catch block
                    }
                  }
                 });

  }


  public void timeAnimMovFrame() {

			OnTaskExecutionFinished tAMFTEF = new com.njfsoft_utils.core.OnTaskExecutionFinished()
                  {
                  @Override
                  public void OnTaskFihishedEvent(String result)
                  {

                        if(result.equals("isDone")) {
				iPrepArrPFrame = 0;
                    try {
 				 stv.setText(" timeMovParse");

				timeMovParse();

                    } catch (Exception e) {
                        System.out.println("Cutouts:timeAnimMovFrame:getAnimMovSing Error" +  e);
				e.printStackTrace();
                        // TODO Auto-generated catch block
                    }
				// timeMovParse();
              		} else {
				iPrepArrPFrame++;
 				 stv.setText("TAF: " + iPrepArrPFrame);

              	       timeAnimMovFrame();
              		}
                        System.out.println("Cutouts:timeAnimMovFrame Response From Asynchronous task: " + iPrepArrPFrame + " :: " +  (String)result);
                  }
                 };

			if(currMovType.equals("mp4")) {
                  agRecorder.procAnimFnlFrames("nada", tAMFTEF);
			} else {
                  gagRecorder.procAnimFnlFrames("nada", tAMFTEF);
			}			
  }


public void callMediaPrepTask() {

 

    final Handler whandler = new Handler();
    Timer atmrMovRec = new Timer();
    TimerTask adoAsynchronousTask = new TimerTask() {       
        @Override
        public void run() {
            handler.post(new Runnable() {
                public void run() {       
                    try {
	  new MediaPrepareTask().execute(null, null, null);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                    }
                }
            });
        }
    };
    atmrMovRec.schedule(adoAsynchronousTask, 600); //execute in every 600 ms
 
}

public void callAsynchronousTask() {

	lMovStartTstamp = System.currentTimeMillis(); 	


    final Handler wwhandler = new Handler();
    tmrMovRec = new Timer();
    TimerTask doAsynchronousTask = new TimerTask() {       
        @Override
        public void run() {
            handler.post(new Runnable() {
                public void run() {       
                    try {
                       cameraManager.getCamera().setOneShotPreviewCallback(cbMovFrame); 
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                    }
                }
            });
        }
    };
    tmrMovRec.schedule(doAsynchronousTask, 0, setIMovFDelay(500)); //execute in every 285 ms
 
}



  public void prepMovBuild() {
		try {
		
            System.out.println("Cutouts:prepMovBuild: start");
		Camera.Parameters parameters = cameraManager.getCamera().getParameters();
		// AnimMovSingleton anms = new AnimMovSingleton();
		AnimMovSingleton anms = AnimMovSingleton.getInstance();
		anms.setMamsArrAFS(arrAnimFSing);
		anms.setMamsAnimInt(IMAGE_RESOURCES);
		// anms.setMamsAnimation(gifAnimation);
	   	if(gifBgAnimation != null) {
		anms.setMamsBgAnimation(gifBgAnimation);
		}
		anms.setMmovWidth(parameters.getPreviewSize().width);
		anms.setMmovHeight(parameters.getPreviewSize().height);
		// anms.setMmovFPS((int)getIMovFDelay()/10);
		anms.setMmovFPS(2);
		// anms.setMmovFPS(21);
		anms.setMmovFDelay(getIMovFDelay());
		// anms.setMmovTSstart(lMovStartTstamp);
		// anms.setMmovTSstop(System.currentTimeMillis());


		boolean isFramed = animMovBuilder.setAnimMovSing(anms);
		if(isFramed) {
            System.out.println("Cutouts:prepMovBuild.isFramed: " + isFramed);

            timeAnimMovBuild();
		}

		} catch(Exception e) {
            System.out.println("Cutouts:prepMovBuild.error: " + e.toString());
		}

  }



  public void prepMovCrunch() {
 

			if(currMovType.equals("mp4")) {
 		 currMovFName = currMovFName.substring(0, currMovFName.lastIndexOf(".") - 1) + ".mp4";

		boolean isFramed = agRecorder.setAnimMovSing(animMovBuilder.getAnimMovSing());
		if(isFramed) {
		boolean isPrepped = agRecorder.prepare();
		if(isPrepped) {
            timeAnimMovFrame();
		}
 		}
			} else {

		boolean isFramed = gagRecorder.setAnimMovSing(animMovBuilder.getAnimMovSing());
		if(isFramed) {
 		 currMovFName = currMovFName.substring(0, currMovFName.lastIndexOf(".") - 1) + ".gif";

		gagRecorder.setFNameString(currMovFName);
		boolean isPrepped = gagRecorder.prepare();
		if(isPrepped) {
            timeAnimMovFrame();
		}
 		}
			}	




  }

 

 public int getIMovFDelay() {
 return iMovFDelay;
 }

 public int setIMovFDelay(int tmpval) {
 iMovFDelay = tmpval;
 return tmpval;
 }

 



 

    public String convertResponseToString(HttpResponse response) throws IllegalStateException, IOException {
        String res = "noQvalue";
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = response.getEntity().getContent();
        int contentLength = (int) response.getEntity().getContentLength();  
        if (contentLength < 0) {
        } else {
            byte[] data = new byte[512];
            int len = 0;
            try {
                while (-1 != (len = inputStream.read(data))) {
                    buffer.append(new String(data, 0, len));  
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close(); 
            } catch (IOException e) {
                e.printStackTrace();
            }
            res = buffer.toString();  
        }
        return res;
    }


public static byte[] convertFileToByteArray(File f)
 {
 byte[] byteArray = null;
 try
 {
 InputStream inputStream = new FileInputStream(f);
 ByteArrayOutputStream bos = new ByteArrayOutputStream();
 byte[] b = new byte[1024*8];
 int bytesRead =0;
 
 while ((bytesRead = inputStream.read(b)) != -1)
 {
 bos.write(b, 0, bytesRead);
 }
 
 byteArray = bos.toByteArray();
 }
 catch (IOException e)
 {
 e.printStackTrace();
 }
 return byteArray;
 }



 


    public void doGiphyShare(HttpResponse response) throws IllegalStateException, IOException {
        String res = "noQvalue";
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = response.getEntity().getContent();
        int contentLength = (int) response.getEntity().getContentLength();  
        if (contentLength < 0) {
        } else {
            byte[] data = new byte[512];
            int len = 0;
            try {
                while (-1 != (len = inputStream.read(data))) {
                    buffer.append(new String(data, 0, len));  
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close(); 
            } catch (IOException e) {
                e.printStackTrace();
            }
            res = buffer.toString();  
 		System.out.println("CutOuts:doGiphyShare:res: " + res);
		try {

 
    
    	   JSONObject jobj = new JSONObject(res);
	   JSONObject jdata = jobj.getJSONObject("data");
         String param =  jdata.getString("id");
	   currGiphyGID = param; // sending back the giphyID
		onEPResult();
 		System.out.println("CutOuts:doGiphyShare:param: " + param);
		 // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.giphy.com/gifs/" + param));
		// startActivity(browserIntent);

} catch (Exception e) {
        e.printStackTrace();
		System.out.println("CutOuts:doGiphyShare:error: " + e);
}



        }
 
    }


public void postGiphyVid() {
    HttpClient httpClient = new DefaultHttpClient();
    HttpContext localContext = new BasicHttpContext();
    HttpPost httpPost = new HttpPost("http://upload.giphy.com/v1/gifs");


	  String fnime = agRecorder.getFNameString();
	 String fNewM = Environment.getExternalStorageDirectory() + "/quick-order/" + fnime + ".gif";
        String strOutput = "noQvalue";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("username", "njflabs"));
        nameValuePairs.add(new BasicNameValuePair("api_key", "dc6zaTOxFJmzC"));
        nameValuePairs.add(new BasicNameValuePair("file", fNewM));
        // nameValuePairs.add(new BasicNameValuePair("is_hidden", false));
        nameValuePairs.add(new BasicNameValuePair("tags", "beta,test"));
 



    try {
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

        for(int index=0; index < nameValuePairs.size(); index++) {
            if(nameValuePairs.get(index).getName().equalsIgnoreCase("file")) {
                // If the key equals to "image", we use FileBody to transfer the data
                entity.addPart(nameValuePairs.get(index).getName(), new FileBody(new File (nameValuePairs.get(index).getValue())));
            } else {
                // Normal string data
                entity.addPart(nameValuePairs.get(index).getName(), new StringBody(nameValuePairs.get(index).getValue()));
            }
        }

        httpPost.setEntity(entity);

        HttpResponse response = httpClient.execute(httpPost, localContext);


            doGiphyShare(response);
 


    } catch (IOException e) {
        e.printStackTrace();
		System.out.println("CutOuts:postVid:error: " + e);
    }
}




public void postVid() {
    HttpClient httpClient = new DefaultHttpClient();
    HttpContext localContext = new BasicHttpContext();
    HttpPost httpPost = new HttpPost("http://a-njfsoft.rhcloud.com/index.php");


 
        String strCurrSID = Long.toString(System.currentTimeMillis());
	  String fnime = agRecorder.getFNameString();
	 String fNewM = Environment.getExternalStorageDirectory() + "/quick-orerder/" + fnime + ".gif";
        String strOutput = "noQvalue";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("do", "add"));
        nameValuePairs.add(new BasicNameValuePair("sttl", "My test vid title"));
        nameValuePairs.add(new BasicNameValuePair("sdesc", "my test vid desc"));
        nameValuePairs.add(new BasicNameValuePair("stime", strCurrSID));
        nameValuePairs.add(new BasicNameValuePair("snwork", "vp"));
        nameValuePairs.add(new BasicNameValuePair("epvideo", fNewM));


    try {
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

        for(int index=0; index < nameValuePairs.size(); index++) {
            if(nameValuePairs.get(index).getName().equalsIgnoreCase("epvideo")) {
                // If the key equals to "image", we use FileBody to transfer the data
                entity.addPart(nameValuePairs.get(index).getName(), new FileBody(new File (nameValuePairs.get(index).getValue())));
            } else {
                // Normal string data
                entity.addPart(nameValuePairs.get(index).getName(), new StringBody(nameValuePairs.get(index).getValue()));
            }
        }

        httpPost.setEntity(entity);

        HttpResponse response = httpClient.execute(httpPost, localContext);


            strOutput = convertResponseToString(response);
		System.out.println("CutOuts:postVid:response: " + strOutput);
            if (strOutput.startsWith("http")) {
             //    mWebView.loadUrl(strOutput);
            }


    } catch (IOException e) {
        e.printStackTrace();
		System.out.println("CutOuts:postVid:error: " + e);
    }
}

public void post(String url, ArrayList<NameValuePair> nameValuePairs) {
    HttpClient httpClient = new DefaultHttpClient();
    HttpContext localContext = new BasicHttpContext();
    HttpPost httpPost = new HttpPost(url);

    try {
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

        for(int index=0; index < nameValuePairs.size(); index++) {
            if(nameValuePairs.get(index).getName().equalsIgnoreCase("epvid")) {
                // If the key equals to "image", we use FileBody to transfer the data
                entity.addPart(nameValuePairs.get(index).getName(), new FileBody(new File (nameValuePairs.get(index).getValue())));
            } else {
                // Normal string data
                entity.addPart(nameValuePairs.get(index).getName(), new StringBody(nameValuePairs.get(index).getValue()));
            }
        }

        httpPost.setEntity(entity);

        HttpResponse response = httpClient.execute(httpPost, localContext);
    } catch (IOException e) {
        e.printStackTrace();
    }
}




    protected void onPicResult() {
 
        Log.v(TAG, "onPicResult()");
 
 

		
 

		ShareDataResult.getInstance().setCallingApp("CutOuts");		
 
		ShareDataResult.getInstance().setImgStr(currGiphyGID);
 
 
		// ShareDataResult.getInstance().setImgName(currMovFName.substring(0, currMovFName.lastIndexOf(".") - 1) + ".jpeg");

		// ShareDataResult.getInstance().setImgBytes(agRecorder.boaAGR.toByteArray());
		ShareDataResult.getInstance().setTitle("selfieLander Landing Title");
		ShareDataResult.getInstance().setDesc("selfieLander Landing Desc");
		ShareDataResult.getInstance().setMsg("selfieLander User Message");
 

               Intent intent = new Intent();
              setResult(RESULT_OK, intent);
		 finish();
		///setPagePopUp("selfieLander/share.html", "noQvalue");


    }

 



    protected void onEPResult() {
 
        Log.v(TAG, "onEPResult()");
 
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

 

		ShareDataResult.getInstance().setCallingApp("CutOuts");		
 

			if(currMovType.equals("mp4")) {
		// ShareDataResult.getInstance().setImgName(agRecorder.getFNameString());
			} else {
		// ShareDataResult.getInstance().setImgName(gagRecorder.getFNameString());
			}	
 

               Intent intent = new Intent();
              setResult(RESULT_OK, intent);
		 finish();
 


    }

 




    public String getImgLoadStr() {
/*
        try {
 	 		  Bitmap result = utilsBitmap.getResizedBitmap(animMovBuilder.getAnimMovSing().getMamsArrAFS().get(2).getMBitmap(), 250, 200);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    result.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                    baos.close();
                    byte[] bMapArray = baos.toByteArray();
                    String encodedImage = Base64.encodeBytes(bMapArray);
 			  return encodedImage;
        } catch(Exception e) {
            System.out.println("selfieLander:getImgLoadStr: " + e.toString());
		return "noQvalue";
        }
*/
		return currGiphyGID;
    }




 public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA) {
            // Handle these events so they don't launch the Camera app
            return true;
        }
  if (keyCode == KeyEvent.KEYCODE_BACK) {

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
               Intent intent = new Intent();
              setResult(RESULT_OK, intent);
		 finish();

		 finish();  
		}
  return super.onKeyDown(keyCode, event);
 }




}
