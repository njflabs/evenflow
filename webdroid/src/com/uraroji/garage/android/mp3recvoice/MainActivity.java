/* 
 * Copyright (c) 2011-2012 Yuichi Hirano
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.uraroji.garage.android.mp3recvoice;
import com.qbits.R;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

 
	private RecMicToMp3 mRecMicToMp3 = new RecMicToMp3(
			Environment.getExternalStorageDirectory() + "/mezzo.mp3", 8000);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mp3_main);

		final TextView statusTextView = (TextView) findViewById(R.id.StatusTextView);

		mRecMicToMp3.setHandle(new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case RecMicToMp3.MSG_REC_STARTED:
					statusTextView.setText("�^��'�");
					break;
				case RecMicToMp3.MSG_REC_STOPPED:
					statusTextView.setText("");
					break;
				case RecMicToMp3.MSG_ERROR_GET_MIN_BUFFERSIZE:
					statusTextView.setText("");
 
					break;
				case RecMicToMp3.MSG_ERROR_CREATE_FILE:
					statusTextView.setText("");
 
					break;
				case RecMicToMp3.MSG_ERROR_REC_START:
					statusTextView.setText("");
 
					break;
				case RecMicToMp3.MSG_ERROR_AUDIO_RECORD:
					statusTextView.setText("");
 
					break;
				case RecMicToMp3.MSG_ERROR_AUDIO_ENCODE:
					statusTextView.setText("");
 
					break;
				case RecMicToMp3.MSG_ERROR_WRITE_FILE:
					statusTextView.setText("");
 
					break;
				case RecMicToMp3.MSG_ERROR_CLOSE_FILE:
					statusTextView.setText("");
 
					break;
				default:
					break;
				}
			}
		});

		Button startButton = (Button) findViewById(R.id.StartButton);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mRecMicToMp3.start();
			}
		});
		Button stopButton = (Button) findViewById(R.id.StopButton);
		stopButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mRecMicToMp3.stop();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mRecMicToMp3.stop();
	}
}
