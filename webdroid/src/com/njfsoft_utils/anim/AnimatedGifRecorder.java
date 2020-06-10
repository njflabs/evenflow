package com.njfsoft_utils.anim;
// import android.R;

 

import android.graphics.drawable.AnimationDrawable;
import android.graphics.BitmapFactory;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.provider.MediaStore;
import android.graphics.drawable.Drawable;
import android.graphics.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.*;
import android.webkit.WebViewClient;
import android.provider.Browser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.graphics.Rect;
import android.widget.*;
 
import com.njfsoft_utils.anim.AnimatedGifEncoder;
import com.njfsoft_utils.anim.AnimFrameSingleton;
import com.njfsoft_utils.anim.AnimMovSingleton;
import com.njfsoft_utils.core.OnTaskExecutionFinished;
import com.njfsoft_utils.anim.UtilsBitmap;


import java.util.regex.Pattern;
import java.nio.IntBuffer;
import org.apache.http.util.ByteArrayBuffer;
 

public class AnimatedGifRecorder {
 
    Bitmap  bmpCurr;
    Bitmap[] imageBitmaps;
    ArrayList<String> allAImgBytes = new ArrayList<String>();
    byte[] allImgBytes;
    ByteArrayBuffer baBuff;
    int bcount;
    int currSwidth;
    int currSheight;
    public int allBmaps;
    int fnlFrameTtl;
    boolean isDone;
    public ByteArrayOutputStream boaAGR;
    List<List<Integer>> arrBMPS;
	// AnimatedByteEncoder animGRrec;
    AnimatedGifEncoder animGRrec;
    boolean isGood;
   boolean isMovSaving;
    boolean isMovDone;

	File mediaStorageDir;
     Context contxt;
 Activity mActivity;


	AnimMovSingleton mAMSing;
	ArrayList<AnimFrameSingleton> arrAnimFnlFrames;

 	AnimationDrawable recAnimDrwbl;
 	AnimationDrawable recBgAnimDrwbl;
	UtilsBitmap rUtilsBitmap;

	int recMovWidth;
	int recMovHeight;

      int recAnmDrwCount;
      int recAnmBgDrwCount;
      int recIncDrwCount;
      int recIncBgDrwCount;
	int recIntFBoost;
	int recIntFDelay;
	int recIntFCount;
	String recStrFName;
	int fps = 20;

    public AnimatedGifRecorder(Activity activity, Context context) {
 
        contxt = context;
        mActivity = activity;
 
        bcount = 0;
	  baBuff = new ByteArrayBuffer(0);
	  currSwidth = 200;
	  currSheight = 200;
	  isDone = false;
	  isGood = false;
	  allBmaps = 0;
	  arrBMPS = new ArrayList<List<Integer>>();
	  boaAGR = new ByteArrayOutputStream();
	  isMovDone = false;
	  isMovSaving = false;
	  fnlFrameTtl = 5;
	  // isGood = doNoMedScan();
	  animGRrec = new AnimatedGifEncoder();
	     	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	  recStrFName = timeStamp;
		animGRrec.start(boaAGR);
		// animGRrec.setDelay(390);
		animGRrec.setDelay(330);
		animGRrec.setQuality(10);
		animGRrec.setRepeat(0);

          mediaStorageDir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "quick-order");

 

 	arrAnimFnlFrames = new ArrayList<AnimFrameSingleton>();

    }


  
    public static int argb(int R, int G, int B, int A){     
        byte[] colorByteArr = { (byte) A, (byte) R, (byte) G, (byte) B };
        return byteArrToInt(colorByteArr);
    }


    public static final int byteArrToInt(byte[] colorByteArr) {
        return (colorByteArr[0] << 24) + ((colorByteArr[1] & 0xFF) << 16) + ((colorByteArr[2] & 0xFF) << 8) + (colorByteArr[3] & 0xFF);
    }



 public static byte[] intToByteArray(int a)
{
    byte[] ret = new byte[4];
    ret[3] = (byte) (a & 0xFF);   
    ret[2] = (byte) ((a >> 8) & 0xFF);   
    ret[1] = (byte) ((a >> 16) & 0xFF);   
    ret[0] = (byte) ((a >> 24) & 0xFF);
    return ret;
}



// chops a list into non-view sublists of length L
static <T> List<List<T>> chopped(List<T> list, final int L) {
    List<List<T>> parts = new ArrayList<List<T>>();
    final int N = list.size();
    for (int i = 0; i < N; i += L) {

        parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L)))
        );
    }
    return parts;
}

 
 
 





   public boolean saveMov() {
 
        try {
 		boolean fileCreated = false;
	     	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
          	String filename = recStrFName;
	  	if(mediaStorageDir.exists()) {
		fileCreated = true;
            System.out.println("saveMov.exists: " + fileCreated);
		} else {
		mediaStorageDir.mkdirs();
 		}
       	File ffile = new File(mediaStorageDir,filename);
            fileCreated = ffile.createNewFile();
		if(fileCreated) {
	  	FileOutputStream os = new FileOutputStream(ffile, true);
	  	os.write(boaAGR.toByteArray());
        	os.flush();
	  	os.close();
            System.out.println("saveMov.created: " + fileCreated);
 
		}
	  return true;
        } catch (Exception e) {
             System.out.println("error saveMov: " + e.toString() );
           	 e.printStackTrace();
		 return false;

        }
    }
   

   public boolean doNoMedScan() {
    try {
            System.out.println("doNoMedScan START:");
 		boolean fileCreated = false;
	 	String filename =  ".nomedia";
		File ffile = new File(mediaStorageDir,filename);
	  	if(ffile.exists()) {
		fileCreated = true;
            System.out.println("doNoMedScan.exists: " + fileCreated);
		} else {
		mediaStorageDir.mkdirs();
		fileCreated = ffile.createNewFile();
            System.out.println("doNoMedScan.fileCreated: " + fileCreated);
		}
		return fileCreated;
        } catch (Exception e) {
             System.out.println("error doNoMedScan: " + e.toString() );
           	 e.printStackTrace();
		 return false;

        }

	}



 





	public boolean  addAnimMovFrame() {
	boolean isdbool = true;
 

	try {

		Bitmap xbitmap = arrAnimFnlFrames.get(allBmaps).getMBitmap();
     		// Bitmap bmpMainCanvImg = rUtilsBitmap.getResizedBitmap(xbitmap, (int)(recMovWidth*0.40), (int)(recMovHeight*0.40));
  		animGRrec.addFrame(xbitmap);
            return true;
      } catch(Exception e) {
        System.out.println("AnimatedGifRecorder:addAnimMovFrame:ERROR: " + e);
	  e.printStackTrace();
	  return false;
   }    
 }






    class AnimAsync extends AsyncTask<String, String, String>
    { 
           String dString;
           com.njfsoft_utils.core.OnTaskExecutionFinished _task_finished_event;

           public void setOnTaskFinishedEvent(OnTaskExecutionFinished _event)
           {
               if(_event != null)
               {
                   this._task_finished_event = _event;
               }
           }
           
           protected void onPreExecute (){
               System.out.println("AnimAsync:onPreExecute");
           }

           protected String doInBackground(String... params) {
           dString = params[0];
           if(dString.equals("isDone")) {
	     // addLastFrame(allBmaps);
           // stop();
    if(animGRrec.finish()) {
    allBmaps = 0; 
     saveMov();
	}
           } else {   
         	boolean bb = addAnimMovFrame();

           }
           System.out.println("AnimAsync:doInBackground: " + dString);
           return "AnimAsync:doInBackground: " + dString;
           }

           protected void onProgressUpdate(String s){
           System.out.println("AnimAsync:onProgressUpdate: " + s);
           }

           protected void onPostExecute(String result) {
               super.onPostExecute(result);
               if(this._task_finished_event != null)
               {
       		 allBmaps++;
                   System.out.println("AnimAsyn:onPostExecute: " + allBmaps + " :: " + result);
                   this._task_finished_event.OnTaskFihishedEvent(dString);
               }
               else
               {
                   System.out.println("AnimAsyn:task_finished even is null: dString: " + dString);
               }
          }
   }





 























    public Bitmap getMovBmp(int ibmi) {
    	 System.out.println("AnimMovBuilder:getMovBmp: " + ibmi);
    byte[] bMapArray = arrAnimFnlFrames.get(ibmi).getMBytes();

    int[] previewPixels = new int[recMovWidth * recMovHeight];
    rUtilsBitmap.decodeYUV420SP(previewPixels, bMapArray, recMovWidth, recMovHeight);
    Bitmap b  = Bitmap.createBitmap(previewPixels, recMovWidth, recMovHeight, Bitmap.Config.RGB_565);
    previewPixels = null;
 


	if(recIncDrwCount == recAnmDrwCount) { recIncDrwCount = 0; }
	if(recIncBgDrwCount == recAnmBgDrwCount) { recIncBgDrwCount = 0; }
 
 
     Bitmap bmpMainCanvImg = rUtilsBitmap.getResizedBitmap(b, (int)(recMovWidth*0.20), (int)(recMovHeight*0.20));
    


	recIncDrwCount++;
	recIncBgDrwCount++;
 


	 //cnvMask.drawBitmap(bmpTheBGMask, null, rs, null);
	// cnvMask.drawBitmap(bmpTheMask, null, rs, null);

	// b.recycle();
 
	// bmpTheBGMask.recycle();
	// bmpTheMask.recycle();
  	return bmpMainCanvImg;
	}

















    public Bitmap OLDgetMovBmp(int ibmi) {
    	 System.out.println("AnimMovBuilder:getMovBmp: " + ibmi);
    byte[] bMapArray = arrAnimFnlFrames.get(ibmi).getMBytes();

    int[] previewPixels = new int[recMovWidth * recMovHeight];
    rUtilsBitmap.decodeYUV420SP(previewPixels, bMapArray, recMovWidth, recMovHeight);
    Bitmap b  = Bitmap.createBitmap(previewPixels, recMovWidth, recMovHeight, Bitmap.Config.RGB_565);
    previewPixels = null;
 
	if(recIncDrwCount == recAnmDrwCount) { recIncDrwCount = 0; }
	if(recIncBgDrwCount == recAnmBgDrwCount) { recIncBgDrwCount = 0; }
 
	Bitmap bmpToMask = rUtilsBitmap.changeToColor(rUtilsBitmap.getResizedBitmap(b, (int)(recMovWidth*0.70), (int)(recMovHeight*0.70)));

	// Bitmap bmpToMask = rUtilsBitmap.changeToColor(rUtilsBitmap.getResizedBitmap(b, (int)(recMovWidth*0.70), (int)(recMovHeight*0.70)));
	// Bitmap bmpToMask = rUtilsBitmap.changeToColor(rUtilsBitmap.getResizedBitmap(b, 200, 150));

    Rect rs = new Rect();
    rs.left = rs.top = 0;
    rs.right = bmpToMask.getWidth();
    rs.bottom = bmpToMask.getHeight();

 
     Bitmap bmpMainCanvImg = Bitmap.createBitmap((int)(rs.right), (int)(rs.bottom), Bitmap.Config.RGB_565);
     Canvas cnvMask = new Canvas(bmpMainCanvImg); 

       cnvMask.drawBitmap(bmpToMask, null, rs, null);
      try {
	  if(recAnimDrwbl != null) { }
	  int ibtbg = arrAnimFnlFrames.get(ibmi).getIanmFrmTS();
 	  // Bitmap bmpTheBGMask = rUtilsBitmap.drawableToBitmap(recAnimDrwbl.getFrame(recIncDrwCount));


		InputStream is = contxt.getResources().openRawResource(arrAnimFnlFrames.get(ibmi).getMAnimResInt());
		Bitmap bmpTheBGMask = BitmapFactory.decodeStream(is);  

     Rect rn = new Rect();
    rn.left = rn.top = 0;
    rn.right = bmpToMask.getWidth();
    rn.bottom = bmpToMask.getHeight();

 	  // Bitmap bmpTheBGMask = rUtilsBitmap.drawableToBitmap(recAnimDrwbl.getFrame(ibtbg));
    	 System.out.println("AnimMovBuilder:bmpTheBGMask:getIanmFrmTS: " + ibtbg);

	  cnvMask.drawBitmap(bmpTheBGMask, null, rs, null);
	 
	  if(recBgAnimDrwbl != null) {
	  Bitmap bmpTheMask =  rUtilsBitmap.drawableToBitmap(recBgAnimDrwbl.getFrame(recIncBgDrwCount));
       cnvMask.drawBitmap(bmpTheMask, null, rs, null);
	 }

    } catch (Exception e) {
    System.out.println("AnimMovBuilder:getMovBmp:ERROR: " + e);
       e.printStackTrace();

    }   


	recIncDrwCount++;
	recIncBgDrwCount++;
 


	 //cnvMask.drawBitmap(bmpTheBGMask, null, rs, null);
	// cnvMask.drawBitmap(bmpTheMask, null, rs, null);

	b.recycle();
	bmpToMask.recycle();
	// bmpTheBGMask.recycle();
	// bmpTheMask.recycle();
  	return bmpMainCanvImg;
	}




	public boolean  addAnimMovBmp() {
	boolean isdbool = true;
       System.out.println("addAnimMovBmp:CronTime: ");

	try { 
       //  AnimFrameSingleton currAFS = arrAnimFnlFrames.get(allBmaps);
	 //  Bitmap xbitmap = getMovBmp(arrAnimFnlFrames.get(allBmaps).getMBytes());
	  arrAnimFnlFrames.get(allBmaps).setMBitmap(getMovBmp(allBmaps));
	  // arrAnimFnlFrames.get(allBmaps).setMBytes(null);
	  return true;
      } catch(Exception e) {
        System.out.println("AnimMovBuilder:addAnimMovBmp:ERROR: " + e);
	  e.printStackTrace();
	  return false;
   	}    
 	}
 
 

   public AnimMovSingleton getAnimMovSing() {
 	try {
    System.out.println("AnimMovBuilder:getAnimMovSing:size  " + mAMSing.getMamsArrAFS().size());
 	// mAMSing.setMamsAnimation(null);
 	// mAMSing.setMamsBgAnimation(null);
	// mAMSing.setMamsArrAFS(arrAnimFnlFrames);
	return mAMSing;
	} catch(Exception e) {
    System.out.println("AnimMovBuilder:getAnimMovSing:ERROR  " + e);
       e.printStackTrace();
	return null;
	}
   }
 

    public boolean setAnimMovSing(AnimMovSingleton tmpval) {
	try {
	mAMSing = null;
	mAMSing = tmpval;
	arrAnimFnlFrames = mAMSing.getMamsArrAFS();

 
 	recAnimDrwbl = mAMSing.getMamsAnimation();
      if(recAnimDrwbl != null) {
	recAnmDrwCount = recAnimDrwbl.getNumberOfFrames();
	}
 	recBgAnimDrwbl = mAMSing.getMamsBgAnimation();
      if(recBgAnimDrwbl != null) {
	recAnmBgDrwCount = recBgAnimDrwbl.getNumberOfFrames();
	}
      recIncDrwCount = 0;
      recIncBgDrwCount = 0;
 

	recMovWidth = mAMSing.getMmovWidth();
	recMovHeight = mAMSing.getMmovHeight();
	fps = mAMSing.getMmovFPS();
	recIntFDelay = mAMSing.getMmovFDelay();
	recIntFCount = arrAnimFnlFrames.size(); 


    System.out.println("AnimMovBuilder:setAnimMovSing true");
	return true;
    } catch (Exception e) {
    System.out.println("AnimMovBuilder:setAnimMovSing:ERROR returns false: " + e);
       e.printStackTrace();
    	return false;

    }   
     }



 


    public void setAnimFrmSing(AnimFrameSingleton tmpval) {
	try {
	arrAnimFnlFrames.add(tmpval);
    System.out.println("AnimMovBuilder:setAnimFrmSing true");
    } catch (Exception e) {
    System.out.println("AnimMovBuilder:setAnimFnlFrames:ERROR returns false: " + e);
       e.printStackTrace();

    }   
     }

    public boolean prepare() {
	try {
 
//     _out = ByteBuffer.allocate(recMovWidth * recMovHeight * 6); //Not sur about RGB
    System.out.println("AnimatedGifRecorder:prepare true");
	return true;
    } catch (Exception e) {
    System.out.println("AnimatedGifRecorder:prepare:ERROR returns false: " + e);
       e.printStackTrace();
    	return false;

    }   
    }

    public void procAnimFnlFrames(String s, OnTaskExecutionFinished theEvent){
    System.out.println("AnimatedGifRecorder:isGettingDone: " + arrAnimFnlFrames.size() + " :: " +  isDone + " :: " + allBmaps + " :: " + isMovDone);
    AnimAsync tasync = new AnimAsync();
    tasync.setOnTaskFinishedEvent(theEvent);

      //   AnimFrameSingleton currTAFS = arrAnimFnlFrames.get(allBmaps);
	 // int ntime = currTAFS.getIpst();
    // if(ntime >= 10000) {
    if(allBmaps >= arrAnimFnlFrames.size()) {

    // recIntFCount = allBmaps;
    s = "isDone";
    }
    tasync.execute(s, String.valueOf(allBmaps), "nada");
    }


    public void procBmap(String s, Bitmap bbb, OnTaskExecutionFinished theEvent){
    System.out.println("AnimatedGifRecorder:procBmap:ERROR Function Deprecated:");
    }

	public void setFNameString(String s) {
	recStrFName = s;
	}

	public String getFNameString() {
	return recStrFName;
	}
}
