package com.njfsoft_utils.anim;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.View.OnClickListener;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.TrackBox;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.AppendTrack;
import com.googlecode.mp4parser.authoring.tracks.CroppedTrack;
import com.googlecode.mp4parser.authoring.tracks.TextTrackImpl;
import com.googlecode.mp4parser.authoring.tracks.H264TrackImpl;
import com.googlecode.mp4parser.authoring.tracks.AACTrackImpl;
import com.googlecode.mp4parser.authoring.Mp4TrackImpl;
import com.googlecode.mp4parser.FileDataSourceImpl;


import com.njfsoft_utils.core.OnTaskExecutionFinished;


public class MPFourParser  {
  String fRootFldr;
  String fOrigVi;
  String fOrigAu;
  String fNewM;
  String fNewSM;
  String TAG = "MPFourParser";

  public  MPFourParser() {
   fRootFldr = Environment.getExternalStorageDirectory() + "/quick-order/";
   fOrigVi = Environment.getExternalStorageDirectory() + "/quick-order/outa.mp4";
   fOrigAu = Environment.getExternalStorageDirectory() + "/quick-order/outa.3gp";
   fNewM = Environment.getExternalStorageDirectory() + "/quick-order/";
  }




 





    public boolean append() {
      try {
 
        Movie[] inMovies = new Movie[]{
                MovieCreator.build(fOrigVi),
                MovieCreator.build(fOrigAu)};
  
        // 1つのファイルに結合
        List<Track> videoTracks = new LinkedList<Track>();
        List<Track> audioTracks = new LinkedList<Track>();
        for (Movie m : inMovies) {
            for (Track t : m.getTracks()) {
    		 System.out.println("MPFourParser:Track t : m.getTracks " + t.getHandler().toString());
                if (t.getHandler().equals("soun")) {
                    audioTracks.add(t);
                }
                if (t.getHandler().equals("vide")) {
                    videoTracks.add(t);
                }
            }
        }
        Movie result = new Movie();
        if (audioTracks.size() > 0) {
            result.addTrack(new AppendTrack(audioTracks.toArray(new Track[audioTracks.size()])));
        }
        if (videoTracks.size() > 0) {
            result.addTrack(new AppendTrack(videoTracks.toArray(new Track[videoTracks.size()])));
        }
  
        // 出力
        Container out = new DefaultMp4Builder().build(result);
 		// IsoFile out = new DefaultMp4Builder().build(result);
        FileOutputStream fos = new FileOutputStream(new File(fNewM));
        out.writeContainer(fos.getChannel());
        fos.close();
        return true;
      } catch (Exception e) {
    	   System.out.println("MPFourParser:append " + e);
	  e.printStackTrace();
        return false;
      }
      

    }
    
 

 













    public boolean Newappend() {
        H264TrackImpl h264Track = null;
        AACTrackImpl aacTrack = null;
        Track voiceLessTrack = null;
        Track maudTrack = null;
        Movie undubbed = null;
	  Movie maud = null;
        try {

            undubbed = MovieCreator.build(fOrigVi);
            voiceLessTrack = undubbed.getTracks().get(0);
            undubbed.getTracks().clear();
            undubbed.getTracks().add(voiceLessTrack);


            maud = MovieCreator.build(fOrigAu);
            maudTrack = maud.getTracks().get(0);
            maud.getTracks().clear();
            maud.getTracks().add(maudTrack);

            // create file reference to un-dubbed video file
            File videoFile = new File(fOrigVi);

            // logd("videofile isfile", videoFile.isFile(), "name", videoFile.getName());

            // create AAC/H.264 tracks
           // h264Track = new H264TrackImpl(new FileDataSourceImpl(fOrigVi));
            // aacTrack = new AACTrackImpl(new FileDataSourceImpl(fOrigAu));

 


 
         // create mux project file
        /*Movie movie = undubbed;*/
        Movie movie = new Movie();
        movie.addTrack(voiceLessTrack);
        movie.addTrack(maudTrack);

 
        File outFile = new File(fNewM);
        FileChannel fc = null;

            // open file
            fc = new FileOutputStream(outFile).getChannel();

 

        // feed movie project into MP4Builder
        Container mp4file = new DefaultMp4Builder().build(movie);



            // create the mp4 file
            mp4file.writeContainer(fc);
            fc.close();

        } catch (Exception e) {
            e.printStackTrace();

 

            // TODO damage control

            // terminate
            return false;
        }

 

        return true;

    } // end doInBackground()







	


  class MPFParsAsync extends AsyncTask<String, String, String>
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
               System.out.println("MPFParsAsync:onPreExecute");
           }

           protected String doInBackground(String... params) {
           dString = params[0];
	     boolean isFMPFPDone = Newappend();
	     /// boolean isFMPFPDone = (isMPFPDone == doShort());

	     dString = String.valueOf(isFMPFPDone);
           System.out.println("MPFParsAsync:doInBackground: " + dString);
           return "MPFParsAsync:doInBackground: " + dString;
           }

           protected void onProgressUpdate(String s){
           System.out.println("MPFParsAsync:onProgressUpdate: " + s);
           }

           protected void onPostExecute(String result) {
               if(this._task_finished_event != null)
               {
                   System.out.println("MPFParsAsync:onPostExecute: " + result);
                   this._task_finished_event.OnTaskFihishedEvent(dString);
               }
               else
               {
                   System.out.println("MPFParsAsync:task_finished even is null: dString: " + dString);
               }
          }
   }


    public void procMPFourPars(String s, OnTaskExecutionFinished theEvent){
   fNewM = Environment.getExternalStorageDirectory() + "/quick-order/" + s;

    System.out.println("MPFParsAsync:isCalled: " + s);
    MPFParsAsync mpfptasync = new MPFParsAsync();
    mpfptasync.setOnTaskFinishedEvent(theEvent);
    mpfptasync.execute(s, "nada", "nada");
    }

}
