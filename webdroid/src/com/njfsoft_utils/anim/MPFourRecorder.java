package com.njfsoft_utils.anim;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Rect;
import android.os.AsyncTask;
// import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
 
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
import java.text.SimpleDateFormat;
import java.util.Date;

 import org.jcodec.common.FileChannelWrapper;
import org.jcodec.codecs.h264.H264Encoder;
import org.jcodec.common.model.ColorSpace;
import android.graphics.BitmapFactory;
import org.jcodec.common.model.Picture;
// import android.graphics.Picture;
import android.content.res.AssetManager;
 import org.jcodec.scale.RgbToYuv420;


import org.jcodec.codecs.h264.io.model.NALUnit;
import org.jcodec.codecs.h264.io.model.NALUnitType;
import org.jcodec.common.NIOUtils;


import org.jcodec.codecs.h264.H264Utils;
import org.jcodec.codecs.h264.mp4.AvcCBox;
 
import org.jcodec.common.model.Packet;
import org.jcodec.containers.mp4.Brand;
import org.jcodec.containers.mp4.MP4Packet;
import org.jcodec.containers.mp4.TrackType;
import org.jcodec.containers.mp4.boxes.Box;
import org.jcodec.containers.mp4.boxes.LeafBox;
import org.jcodec.containers.mp4.boxes.VideoSampleEntry;
import org.jcodec.containers.mp4.demuxer.AbstractMP4DemuxerTrack;
import org.jcodec.containers.mp4.demuxer.MP4Demuxer;
import org.jcodec.containers.mp4.muxer.FramesMP4MuxerTrack;
import org.jcodec.containers.mp4.muxer.MP4Muxer;


import com.njfsoft_utils.anim.AnimFrameSingleton;
import com.njfsoft_utils.anim.AnimMovSingleton;
import com.njfsoft_utils.core.OnTaskExecutionFinished;
import com.njfsoft_utils.anim.UtilsBitmap;

import com.qbits.R;

public class MPFourRecorder {
    private static final String MOVIE_LOCATION = "http://jcodec.org/downloads/sample.mov";

 
Context contxt;
Activity mActivity;
int fps;
int timescale;
RgbToYuv420 transform;
FileChannelWrapper ch;
MP4Muxer muxer;
FramesMP4MuxerTrack outTrack;
H264Encoder encoder;
ByteBuffer _out;
ArrayList<ByteBuffer> spsList;
ArrayList<ByteBuffer> ppsList;
int numberOfimage;
String path;
Picture rgb;
Picture yuv;
int num;
int[] packed;


// agifrecorder stuff

    Bitmap  bmpCurr;
    Bitmap[] imageBitmaps;
    ArrayList<String> allAImgBytes = new ArrayList<String>();
    byte[] allImgBytes;
 
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

	String recStrFName;




	int pToggle;






    public MPFourRecorder(Activity activity, Context context) {
        contxt = context;
        mActivity = activity;

	   rUtilsBitmap = new UtilsBitmap();
    fps = 2;
    timescale = 1000;
	try {
    transform = new RgbToYuv420(0,0);

    ch = NIOUtils.writableFileChannel(SDPathToFile("quick-order", "outa.mp4"));
    muxer = new MP4Muxer(ch, Brand.MP4);

    // Add a video track
   outTrack = muxer.addTrackForCompressed(TrackType.VIDEO, timescale);
	//  outTrack = muxer.addTrack(TrackType.VIDEO, fps);
    // Create H.264 encoder
    encoder = new H264Encoder(); // not we could use a rate control in the constructor
   //  encoder.setKeyInterval(25);

    // Allocate storage for SPS/PPS, they need to be stored separately in a special place of MP4 file
    spsList = new ArrayList<ByteBuffer>();
    ppsList = new ArrayList<ByteBuffer>();
} catch (Exception e) {

    e.printStackTrace();

}   

    numberOfimage = 5;
    path="cutOuts";

    rgb = null;
    yuv=null;

    packed = null;

     iVST = 0;

 

   pToggle = 0;
// agifrecorder stuff

        bcount = 0;
	  currSwidth = 200;
	  currSheight = 200;
	  isDone = false;
	  isGood = false;
	  allBmaps = 0;
 
	  isMovDone = false;
	  isMovSaving = false;
	  fnlFrameTtl = 5;

          mediaStorageDir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "quick-order");
	     	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	  recStrFName = timeStamp;
	arrAnimFnlFrames = new ArrayList<AnimFrameSingleton>();



	}
 

  

public void start(Bitmap bMap) {
	try {
           System.gc();
       // Allocate a buffer that would hold an encoded frame
    _out = ByteBuffer.allocate(bMap.getWidth() * bMap.getHeight() * 6); //Not sur about RGB

        System.out.println("MPFourRecorder:start() : " + bMap.getWidth() + " : " + bMap.getHeight());

        long l = System.currentTimeMillis()/1000;
        String s = Long.toString(l);
        iVST = Integer.parseInt(s);
    isGood = true;
    System.out.println("MPFourRecorder:start isGood == true");

} catch (Exception e) {
    System.out.println("MPFourRecorder:start() ERROR: " + e);
    e.printStackTrace();

}           
}

public void stop() {
	try {
     iVST = 0;
    yuv=null;
    packed=null;
    System.gc();
    outTrack.addSampleEntry(H264Utils.createMOVSampleEntry(spsList, ppsList));
    // Write MP4 header and finalize recording
    muxer.writeHeader();
    NIOUtils.closeQuietly(ch);
    System.out.println("MPFourRecorder:stop() : " + spsList.size() + " : " + ppsList.size());

} catch (Exception e) {
    System.out.println("MPFourRecorder:stop() ERROR: " + e);

    e.printStackTrace();

}           
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
	 // File extBaseDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
      File extBaseDir = Environment.getExternalStorageDirectory();
      if (filePatho==null||filePatho.length()==0||filePatho.charAt(0)!='/')
          filePatho="/"+filePatho;
      makeDirectory(filePatho);
      File file = new File(extBaseDir.getAbsoluteFile()+filePatho);
      return new File(file.getAbsolutePath()+"/"+fileName);//file;
}

 

 












	public boolean  addAnimMovFrame() {
	boolean isdbool = true;
 

	try {

 
       //  AnimFrameSingleton currAFS = arrAnimFnlFrames.get(allBmaps);


 
		Bitmap xbitmap = arrAnimFnlFrames.get(allBmaps).getMBitmap();

 

        System.out.println("MPFourRecorder:addAnimMovFrame " + xbitmap.getWidth() + " : " + xbitmap.getHeight());

 
        if(rgb == null) {
            rgb = Picture.create((int)xbitmap.getWidth(), (int)xbitmap.getHeight(), ColorSpace.RGB);//YUV420);//RGB);
        }               
        int[] dstData = rgb.getPlaneData(0);

        if (packed==null) {
            packed = new int[xbitmap.getWidth() * xbitmap.getHeight()];
	  }

        System.out.println("MPFourRecorder:getPixels start");
        xbitmap.getPixels(packed, 0, xbitmap.getWidth(), 0, 0, xbitmap.getWidth(),xbitmap.getHeight());
        System.out.println("MPFourRecorder:getPixels stop");


        System.out.println("MPFourRecorder:addAnimMovFrame start for int ");
        for (int iu = 0, srcOff = 0, dstOff = 0; iu < xbitmap.getHeight(); iu++) {
            for (int j = 0; j < xbitmap.getWidth(); j++, srcOff++, dstOff += 3) {
                int rgbo = packed[srcOff];
                dstData[dstOff] = (rgbo >> 16) & 0xff;
                dstData[dstOff + 1] = (rgbo >> 8) & 0xff;
                dstData[dstOff + 2] = rgbo & 0xff;
            }
        }            
        System.out.println("MPFourRecorder:addAnimMovFrame stop for int ");   
        if (yuv==null) {
            yuv = Picture.create(rgb.getWidth(), rgb.getHeight(), ColorSpace.YUV420);//444
	  }
		transform.transform(rgb, yuv);

       
       // xbitmap.recycle();

       System.out.println("MPFourRecorder:encodeFrame start");
       ByteBuffer result = encoder.encodeFrame(_out, yuv); //toEncode
       System.out.println("MPFourRecorder:encodeFrame stop");
       _out.clear();
 







	  // !! Bottleneck here
        System.out.println("MPFourRecorder:encodeMOVPacket start");
        H264Utils.encodeMOVPacket(result, spsList, ppsList);
        System.out.println("MPFourRecorder:encodeMOVPacket stop");


 
      int nInt = 0;
      int ttlfps = (int) 1000 / fps;
	int ttlfpdelay = (int) 1000 / recIntFDelay;
	int rfps = ttlfps / ttlfpdelay;


	// recIntFCount = arrAnimFnlFrames.size(); 
        int iTDif = 0;
	  int iNTime = arrAnimFnlFrames.get(allBmaps).getIpst();
        int iABM = allBmaps * rfps;

	  try {
	  iTDif = (int) ((arrAnimFnlFrames.get(allBmaps + 1).getIpst() - iNTime) / rfps);
	  } catch(Exception e) {
	  iTDif = fps;
	  }
        // int irCount = arrAnimFnlFrames.size();


        int niNTime = (int) (iNTime / 70);
	 outTrack.addFrame(new MP4Packet(result,allBmaps * 500,(timescale * allBmaps) + 500,500,allBmaps,false,null,allBmaps * 500,0));

        if(pToggle == 0) {
	  pToggle = 500;
	  } else {
	  pToggle = 0;
	  }

      // outTrack.addFrame(new MP4Packet(result, allBmaps, 25, 1, allBmaps, true, null, allBmaps, 0));

         System.out.println("MPFourRecorder:CronTime:allBmaps :" +  allBmaps  + " :: nInt: " + niNTime + " :: iNTime: " + iNTime);
	 
 


 

 		result = null;
            System.gc();
            return true;
      } catch(Exception e) {
        System.out.println("MPFourRecorder:addAnimMovFrame:ERROR: " + e);
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
           stop();
           } else {   
         	boolean bb = addAnimMovFrame();
           //   boolean bb = addAnimMovBmp();

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














 



    public boolean prepare() {
	try {
 
    _out = ByteBuffer.allocate(recMovWidth * recMovHeight * 6); //Not sur about RGB
    System.out.println("MPFourRecorder:prepare true");
	return true;
    } catch (Exception e) {
    System.out.println("MPFourRecorder:prepare:ERROR returns false: " + e);
       e.printStackTrace();
    	return false;

    }   
    }
 

   public AnimMovSingleton getAnimMovSing() {
 	try {
    System.out.println("MPFourRecorder:getAnimMovSing:size  " + mAMSing.getMamsArrAFS().size());
 	// mAMSing.setMamsAnimation(null);
 	// mAMSing.setMamsBgAnimation(null);
	//mAMSing.setMamsArrAFS(arrAnimFnlFrames);
	return mAMSing;
	} catch(Exception e) {
    System.out.println("MPFourRecorder:getAnimMovSing:ERROR  " + e);
       e.printStackTrace();
	return null;
	}
   }
 

    public boolean setAnimMovSing(AnimMovSingleton tmpval) {
	try {
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
 	Bitmap xbitmap = arrAnimFnlFrames.get(0).getMBitmap();

	recMovWidth = xbitmap.getWidth();
	recMovHeight = xbitmap.getHeight();
	// -- !! recMovHeight = mAMSing.getMmovHeight() + 50;
	fps = mAMSing.getMmovFPS();
	recIntFDelay = mAMSing.getMmovFDelay();
	recIntFCount = arrAnimFnlFrames.size(); 
	// timescale = (int)(recIntFDelay * recIntFCount);

    System.out.println("MPFourRecorder:setAnimMovSing true");
	return true;
    } catch (Exception e) {
    System.out.println("MPFourRecorder:setAnimMovSing:ERROR returns false: " + e);
       e.printStackTrace();
    	return false;

    }   
     }



    public boolean setAnimFnlFrames(ArrayList<AnimFrameSingleton> tmpval) {
	try {
	arrAnimFnlFrames = tmpval;
    System.out.println("MPFourRecorder:setAnimFnlFrames true");
	return true;
    } catch (Exception e) {
    System.out.println("MPFourRecorder:setAnimFnlFrames:ERROR returns false: " + e);
       e.printStackTrace();
    	return false;

    }   
     }


    public void setAnimFrmSing(AnimFrameSingleton tmpval) {
	try {
	arrAnimFnlFrames.add(tmpval);
    System.out.println("MPFourRecorder:setAnimFrmSing true");
    } catch (Exception e) {
    System.out.println("MPFourRecorder:setAnimFnlFrames:ERROR returns false: " + e);
       e.printStackTrace();

    }   
     }



 
    public void procAnimFnlFrames(String s, OnTaskExecutionFinished theEvent){
    System.out.println("MPFourRecorder:isGettingDone: " + arrAnimFnlFrames.size() + " :: " +  isDone + " :: " + allBmaps + " :: " + isMovDone);
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
    System.out.println("MPFourRecorder:procBmap:ERROR Function Deprecated:");
    }




	public String getFNameString() {
	return recStrFName;
	}
 
 

}
