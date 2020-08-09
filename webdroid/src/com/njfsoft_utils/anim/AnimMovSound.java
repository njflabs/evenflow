package com.njfsoft_utils.anim;
import com.quickorder.R; 

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.AudioManager;
import java.lang.Thread;
import android.content.res.Resources;

public class AnimMovSound {
 
      Context amsContext;
	public final int THUMP = 1;
    public AnimMovSound(Context c) {
      amsContext = c;
    }


    public void playSound(final int type)
    {

            new Thread(new Runnable()
            {

                @Override
                public void run()
                {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    int resId = -1;
                    switch (type)
                    {
                    case THUMP:
                        resId=R.raw.thump;
                        break;
                    }

                    if (resId != -1)
                    {
                        mediaPlayer = MediaPlayer.create(amsContext, resId);
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setLooping(false);
                        mediaPlayer.start();

                        while (mediaPlayer.isPlaying() == true)
                        {
                        }
                    }
                }
            }).start();

        }


}
