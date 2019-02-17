package com.njfsoft_utils.anim;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
// import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
 
 
import org.json.JSONArray;
import org.json.JSONObject;


import org.jcodec.common.SeekableByteChannel;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
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
 
import android.graphics.BitmapFactory;
import org.jcodec.common.model.Picture;
// import android.graphics.Picture;
import android.content.res.AssetManager;
 


import com.njfsoft_utils.anim.AnimFrameSingleton;
import com.njfsoft_utils.anim.AnimMovSingleton;
import com.njfsoft_utils.core.OnTaskExecutionFinished;
import com.njfsoft_utils.anim.UtilsBitmap;

import com.qbits.R;

public class AnimMovBuilder {
 
 
Context contxt;
Activity mActivity;
 
int numberOfimage;
String path;
     int currScrWidth;
    int currScrHeight;


    JSONObject apmetaObject;
 


// agifrecorder stuff

    Bitmap  bmpCurr;
 
 
    int bcount;
    int currSwidth;
    int currSheight;
    public int allBmaps;
    int fnlFrameTtl;
    boolean isDone;
    boolean isGood;
   boolean isMovSaving;
    boolean isMovDone;

	File mediaStorageDir;
 

        int iVST;

 
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
	int fps = 20;
	int newMovWidth;
	int newMovHeight;
 



    public AnimMovBuilder(Activity activity, Context context) { 
        contxt = context;
        mActivity = activity;

	   rUtilsBitmap = new UtilsBitmap(mActivity, contxt);
 
 
         apmetaObject = new JSONObject();
        bcount = 0;
	  currSwidth = 200;
	  currSheight = 200;
	  isDone = false;
	  isGood = false;
	  allBmaps = 0;
 
	  isMovDone = false;
	  isMovSaving = false;
	  fnlFrameTtl = 5;
        newMovWidth = 320;
	  newMovHeight = 290;
	mediaStorageDir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "quick-order");
	arrAnimFnlFrames = new ArrayList<AnimFrameSingleton>();


	}
 
 
 
 


   static void makeDirectory(String dir)
{
      File extBaseDir = Environment.getExternalStorageDirectory();
      File file = new File(extBaseDir.getAbsoluteFile()+"/"+dir);
      if(!file.exists()){
            if(!file.mkdirs()){
              //  throw new Exception("Could not create directories, "+file.getAbsolutePath());
            }
      }         
}

static File SDPathToFile(String filePatho, String fileName)
{
     File extBaseDir = Environment.getExternalStorageDirectory();
      if (filePatho==null||filePatho.length()==0||filePatho.charAt(0)!='/')
          filePatho="/"+filePatho;
      makeDirectory(filePatho);
      File file = new File(extBaseDir.getAbsoluteFile()+filePatho);
      return new File(file.getAbsolutePath()+"/"+fileName);//file;
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
           } else {   
          //  boolean bb = addAnimMovFrame();
           boolean bb = addAnimMovBmp();

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



 
	public void setAPmeta(String str) {
 

	try { 
       apmetaObject = new JSONObject(str);

      } catch(Exception e) {
        System.out.println("AnimMovBuilder:setAPmeta:ERROR: " + e);
	  e.printStackTrace();
 
   	}    
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




    private int dpToPx(int dp) {
        float density = contxt.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    private float dpAToPx(int dp) {
        float density = contxt.getResources().getDisplayMetrics().density;
    System.out.println("dpAToPx: " + density + " :: " + (float) dp * density);
        return (float) dp * density;
    }

    public Bitmap getMovBmp(int ibmi) {


 	  Paint fntPaint = new Paint();
	  Typeface tfbold = Typeface.create("Arial", Typeface.BOLD);
	  fntPaint.setTypeface(tfbold);
        fntPaint.setTextSize(12);
        fntPaint.setColor(Color.parseColor("#FFFFFF"));

/* 
	  if(ibmi < 6) {
        fntPaint.setShadowLayer(4, 4, 4, Color.BLACK);
        fntPaint.setColor(Color.parseColor("#FFFFFF"));
 	  } else {
        fntPaint.setTextSize(14);
        fntPaint.setShadowLayer(4, 4, 4, Color.GREEN);
        fntPaint.setColor(Color.parseColor("#DECCDE"));
	  }



    	 System.out.println("AnimMovBuilder:getMovBmp: " + ibmi);

   byte[] bMapArray = arrAnimFnlFrames.get(ibmi).getMBytes();

    int[] previewPixels = new int[recMovWidth * recMovHeight];
    rUtilsBitmap.decodeYUV420SP(previewPixels, bMapArray, recMovWidth, recMovHeight);
    Bitmap b  = Bitmap.createBitmap(previewPixels, recMovWidth, recMovHeight, Bitmap.Config.RGB_565);
    previewPixels = null;
 */
	if(recIncDrwCount == recAnmDrwCount) { recIncDrwCount = 0; }
	if(recIncBgDrwCount == recAnmBgDrwCount) { recIncBgDrwCount = 0; }
	// Bitmap bmpToMask = rUtilsBitmap.getFaceMap(rUtilsBitmap.getResizedBitmap(b, (int)(recMovWidth*0.70), (int)(recMovHeight*0.70)));
	// Bitmap bmpToMask = rUtilsBitmap.changeToColor(rUtilsBitmap.getFaceMap(rUtilsBitmap.getResizedBitmap(b, (int)(recMovWidth*0.70), (int)(recMovHeight*0.70))));
 	 // -- !! Bitmap bmpToMask = rUtilsBitmap.getResizedBitmap(b, (int)(recMovWidth*0.20), (int)(recMovHeight*0.20));

 	//  Bitmap bmpAToMask = rUtilsBitmap.scaleBoundBitmap(b, 320);
 	 Bitmap bmpAToMask =  arrAnimFnlFrames.get(ibmi).getMBitmap();

	String tstrP =  "njflabs  ";
       try {
 	 JSONObject jdata = apmetaObject.getJSONObject("qco");
 	 JSONObject ji = apmetaObject.getJSONObject("qitem");


	  if(ibmi < 6) {
       tstrP = jdata.getString("c_title");
 
	} else {
       tstrP = ji.getString("i_title") + " - " + ji.getString("i_price_a");

	}

       } catch(Exception e) {
	}


 

    Rect rs = new Rect();
    rs.left = rs.top = 0;
    rs.right = bmpAToMask.getWidth();
    rs.bottom = bmpAToMask.getHeight();

 
     Bitmap bmpMainCanvImg = Bitmap.createBitmap((int)(rs.right), (int)(rs.bottom) + 50, Bitmap.Config.RGB_565);

    Rect rsa = new Rect();
    rsa.left = rs.top = 0;
    rsa.right = bmpMainCanvImg.getWidth();
    rsa.bottom = bmpMainCanvImg.getHeight();

     Canvas cnvMask = new Canvas(bmpMainCanvImg); 

 	 Bitmap bmpToMask = rUtilsBitmap.drawTextToBitmap(bmpMainCanvImg, tstrP, fntPaint, 8);

       cnvMask.drawBitmap(bmpToMask, null, rsa, null);
       cnvMask.drawBitmap(bmpAToMask, null, rs, null);
      


	recIncDrwCount++;
	recIncBgDrwCount++;
 
 

	// b.recycle();
	bmpToMask.recycle();
 
    System.out.println("getMovBmp: " + bmpMainCanvImg.getWidth() + " :: " + bmpMainCanvImg.getHeight());
      newMovWidth = bmpMainCanvImg.getWidth();
      newMovHeight = bmpMainCanvImg.getHeight();
  	return bmpMainCanvImg;
 
	}



     public void procAnimBuild(String s, OnTaskExecutionFinished theEvent){
    System.out.println("AnimMovBuilder:isGettingDone: " + arrAnimFnlFrames.size() + " :: " +  isDone + " :: " + allBmaps + " :: " + isMovDone);
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

}
