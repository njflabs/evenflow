package com.njfsoft_utils.anim;
import com.quickorder.R; 

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AnimMovSoundPool  {

	private SoundPool soundPool;
	private int soundID;
	boolean plays = false, loaded = false;
	float actVolume, maxVolume, volume;
	AudioManager audioManager;
	int counter;

      Context amspContext;
	public final int THUMP = 1;

    public AnimMovSoundPool(Context c) {
      amspContext = c;
 

		// AudioManager audio settings for adjusting the volume
		audioManager = (AudioManager) amspContext.getSystemService(amspContext.AUDIO_SERVICE);
		actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		volume = actVolume / maxVolume;

		//Hardware buttons setting to adjust the media sound
 

		// the counter will help us recognize the stream id of the sound played  now
		counter = 0;

		// Load the sounds
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
 
		soundID = soundPool.load(amspContext, R.raw.thump, 1);
		loaded = true;
	}

	public void playSound(int i) {
		// Is the sound loaded does it already play?
		if (loaded && !plays) {
			soundPool.play(soundID, volume, volume, 1, 0, 1f);
			counter = counter++;
 
			plays = true;
		}
	}

	public void playLoop(View v) {
		// Is the sound loaded does it already play?
		if (loaded && !plays) {

			// the sound will play for ever if we put the loop parameter -1
			soundPool.play(soundID, volume, volume, 1, -1, 1f);
			counter = counter++;
 
			plays = true;
		}
	}

	public void pauseSound(View v) {
		// Is the sound loaded already?
		if (plays) {
			soundPool.pause(soundID);
			soundID = soundPool.load(amspContext, R.raw.thump, counter);
 
			plays = false;
		}
	}

	public void stopSound(View v) {
		// Is the sound loaded already?
		if (plays) {
			soundPool.stop(soundID);
			soundID = soundPool.load(amspContext, R.raw.thump, counter);
 
			plays = false;
		}
	}
}
