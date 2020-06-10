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
// import android.graphics.Typeface;
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

import com.njfsoft_utils.artpad.filters.BitmapFilter;


import com.qbits.R;

public class AnimMovBuilder {
 
 
Context contxt;
Activity mActivity;
 
int numberOfimage;
String path;
     int currScrWidth;
    int currScrHeight;


    JSONObject apmetaObject;
    JSONObject ambStgsObject;
 


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
	String currITtlStr;
	String currCoTtlStr;
	String currIPriceStr;
	String currUseWmarkStr; 
	String currUseFilter; 


    public AnimMovBuilder(Activity activity, Context context) { 
        contxt = context;
        mActivity = activity;

	   rUtilsBitmap = new UtilsBitmap(mActivity, contxt);
 
 
         apmetaObject = new JSONObject();
         ambStgsObject = new JSONObject();
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

	currITtlStr = "Great Item";
	currCoTtlStr = "noQvalue";
	currIPriceStr = "1.99";
	currUseWmarkStr = "no";
	currUseFilter = "no";



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
 
        System.out.println("AnimMovBuilder:setAPmeta:APMETA: " + str);
	try { 
       apmetaObject = null;
       apmetaObject = new JSONObject(str);
 	 JSONObject ji = apmetaObject.getJSONObject("qitem");


 


       currITtlStr = ji.getString("i_title");
       currIPriceStr = ji.getString("i_price_b");
 	 JSONObject jdata = apmetaObject.getJSONObject("qco");
       currCoTtlStr = jdata.getString("c_title");
        System.out.println("AnimMovBuilder:setAPmeta:apmetaObject.toString(): " + apmetaObject.toString());
      } catch(Exception e) {
        System.out.println("AnimMovBuilder:setAPmeta:ERROR: " + e);
	  e.printStackTrace();
 
   	}    
 	}

	public void setAMBstgsObj(String theStgsstr) {
 
        System.out.println("AnimMovBuilder:setAMBstgsObj:string: " + theStgsstr);
	try { 
       ambStgsObject = null;
       ambStgsObject = new JSONObject(theStgsstr);
		currUseWmarkStr = ambStgsObject.getString("confCUTitleWmark");
		currUseFilter = ambStgsObject.getString("confCUUseFilter");
        System.out.println("AnimMovBuilder:setAMBstgsObj:.toString(): " + ambStgsObject.toString());
      } catch(Exception e) {
        System.out.println("AnimMovBuilder:setAMBstgsObj:ERROR: " + e);
	  e.printStackTrace();
 
   	}    
 	}



	public boolean  addAnimMovBmp() {
	boolean isdbool = true;
       System.out.println("addAnimMovBmp:CronTime: ");

	try { 

	 if(currUseWmarkStr.equals("yes")) {
        Bitmap ttnmp = getMovBmp(allBmaps);

       arrAnimFnlFrames.get(allBmaps).setMBitmap(getMovBmp(allBmaps));
	arrAnimFnlFrames.get(allBmaps).setMThumbBmp(rUtilsBitmap.scaleBoundBitmap(ttnmp, 35));


	} else {

       Bitmap ttnmp =  arrAnimFnlFrames.get(allBmaps).getMBitmap();
	arrAnimFnlFrames.get(allBmaps).setMThumbBmp(rUtilsBitmap.scaleBoundBitmap(ttnmp, 35));

	if(recIncDrwCount == recAnmDrwCount) { recIncDrwCount = 0; }
	if(recIncBgDrwCount == recAnmBgDrwCount) { recIncBgDrwCount = 0; }	
	recIncDrwCount++;
	recIncBgDrwCount++;
 
      newMovWidth = ttnmp.getWidth();
      newMovHeight = ttnmp.getHeight();
	}

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























    public Bitmap getItemBmp(Bitmap bmpAToMask) {

        System.out.println("AnimMovBuilder:getItemBmp:called: ");
 	  Paint fntPaint = new Paint();
	  // Typeface tfbold = Typeface.create("Arial", Typeface.BOLD);
	  // fntPaint.setTypeface(tfbold);
        fntPaint.setTextSize(12);
        fntPaint.setColor(Color.parseColor("#FFFFFF"));
 
 

	String tstrCoTTl =  "noQvalue";
	String tstrIttl =  "new item - 1.00";
	String tstrF = tstrCoTTl + tstrIttl;
       try {
 	 JSONObject jdata = apmetaObject.getJSONObject("qco");
 	 JSONObject ji = apmetaObject.getJSONObject("qitem");

 
       tstrCoTTl = jdata.getString("c_title");
 
       tstrIttl = ji.getString("i_title") + " - only $" + ji.getString("i_price_b") + " !";
		tstrF = tstrCoTTl + " - " + tstrIttl;
 

       } catch(Exception e) {
        System.out.println("AnimMovBuilder:getItemBmp:APMETA:ERROR: " + e);
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

 	 Bitmap bmpToMask = rUtilsBitmap.drawTextToBitmap(bmpMainCanvImg, tstrF, fntPaint, 8);

       cnvMask.drawBitmap(bmpToMask, null, rsa, null);
	 if(currUseFilter.equals("no")) { 
       cnvMask.drawBitmap(bmpAToMask, null, rs, null);
       } else {
       cnvMask.drawBitmap(BitmapFilter.changeStyle(bmpAToMask, Integer.parseInt(currUseFilter)), null, rs, null);	
	}
 
 
 

	// b.recycle();
	// bmpToMask.recycle();
 
    System.out.println("getItemBmp: " + bmpMainCanvImg.getWidth() + " :: " + bmpMainCanvImg.getHeight());
	if(tstrCoTTl.equals("noQvalue")) {
	
      newMovWidth = bmpAToMask.getWidth();
      newMovHeight = bmpAToMask.getHeight();
  	return bmpAToMask;
	} else {

      newMovWidth = bmpMainCanvImg.getWidth();
      newMovHeight = bmpMainCanvImg.getHeight();
  	return bmpMainCanvImg;
 	}
	}











   public String getIPriceStr() {
	String tstrP =  "1.99";	

       try {
 	 JSONObject ji = apmetaObject.getJSONObject("qitem");
       tstrP = ji.getString("i_price_b");
      return tstrP;
	} catch(Exception e) {
	return tstrP;
	 }
   }


   public String getITitleStr() {
	String tstrP =  "Great Item";	
       try {
 	 JSONObject ji = apmetaObject.getJSONObject("qitem");
       return tstrP;
	 } catch(Exception e) {
	 return tstrP;
	 }
   }




   public String getCoTitleStr() {
	String tstrP =  "Quick-Order  ";	

       try {
 	 JSONObject jdata = apmetaObject.getJSONObject("qco");
       tstrP = jdata.getString("c_title");
	 return tstrP;

       } catch(Exception e) {
	 return tstrP;

	}
   }


    public Bitmap getMovBmp(int ibmi) {

  	 Bitmap bmpAToMask =  arrAnimFnlFrames.get(ibmi).getMBitmap();
	String tmpWMarkStr = currCoTtlStr;
      try {







       if(currUseWmarkStr.equals("no") || currCoTtlStr.equals("noQvalue")) {



	if(recIncDrwCount == recAnmDrwCount) { recIncDrwCount = 0; }
	if(recIncBgDrwCount == recAnmBgDrwCount) { recIncBgDrwCount = 0; }

	recIncDrwCount++;
	recIncBgDrwCount++;
 
 
    	System.out.println("getMovBmp: " + bmpAToMask.getWidth() + " :: " + bmpAToMask.getHeight());



      newMovWidth = bmpAToMask.getWidth();
      newMovHeight = bmpAToMask.getHeight();

    Rect rs = new Rect();
    rs.left = rs.top = 0;
    rs.right = newMovWidth;
    rs.bottom = newMovHeight;


     Bitmap bmpMainCanvImg = Bitmap.createBitmap(newMovWidth, newMovHeight, Bitmap.Config.RGB_565);
     Canvas cnvMask = new Canvas(bmpMainCanvImg); 


	 if(currUseFilter.equals("no")) { 
       cnvMask.drawBitmap(bmpAToMask, null, rs, null);
       } else {
       cnvMask.drawBitmap(BitmapFilter.changeStyle(bmpAToMask, Integer.parseInt(currUseFilter)), null, rs, null);	
	}
	return bmpMainCanvImg;




 
 	} else {





    	 System.out.println("AnimMovBuilder:getMovBmp: " + ibmi);
 	  Paint fntPaint = new Paint();
	  // Typeface tfbold = Typeface.create("Arial", Typeface.BOLD);
	  // fntPaint.setTypeface(tfbold);
        fntPaint.setTextSize(12);
        fntPaint.setColor(Color.parseColor("#FFFFFF"));

 


	  if(ibmi < 6) {
        fntPaint.setShadowLayer(4, 4, 4, Color.BLACK);
        fntPaint.setColor(Color.parseColor("#FFFFFF"));
	  tmpWMarkStr = currCoTtlStr;
 	  } else {
        fntPaint.setTextSize(14);
        fntPaint.setShadowLayer(4, 4, 4, Color.GREEN);
        fntPaint.setColor(Color.parseColor("#DECCDE"));
	  tmpWMarkStr = currITtlStr + " - " + currIPriceStr;
	  }


 
	if(recIncDrwCount == recAnmDrwCount) { recIncDrwCount = 0; }
	if(recIncBgDrwCount == recAnmBgDrwCount) { recIncBgDrwCount = 0; }

 

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

 	 Bitmap bmpToMask = rUtilsBitmap.drawTextToBitmap(bmpMainCanvImg, tmpWMarkStr, fntPaint, 8);

       cnvMask.drawBitmap(bmpToMask, null, rsa, null);

	 if(currUseFilter.equals("no")) { 
       cnvMask.drawBitmap(bmpAToMask, null, rs, null);
       } else {
       cnvMask.drawBitmap(BitmapFilter.changeStyle(bmpAToMask, Integer.parseInt(currUseFilter)), null, rs, null);	
	}


	recIncDrwCount++;
	recIncBgDrwCount++;
 
 

	// b.recycle();

 
    System.out.println("getMovBmp: " + bmpMainCanvImg.getWidth() + " :: " + bmpMainCanvImg.getHeight());
      newMovWidth = bmpMainCanvImg.getWidth();
      newMovHeight = bmpMainCanvImg.getHeight();
// arrAnimFnlFrames.get(ibmi).setMThumbBmp(rUtilsBitmap.scaleBoundBitmap(bmpMainCanvImg, 35));
	// bmpToMask.recycle();
  	return bmpMainCanvImg;






	}

    } catch (Exception e) {
    System.out.println("AnimMovBuilder:getMovBmp:ERROR ibmi: " + e);
       e.printStackTrace();
	return bmpAToMask;

    }   

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
