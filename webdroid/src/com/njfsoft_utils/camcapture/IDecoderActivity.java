package com.njfsoft_utils.camcapture;

import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;

public interface IDecoderActivity {

    public ViewfinderView getViewfinder();
 


    public Handler getHandler();

    public CameraManager getCameraManager();

    public void handleDecode(Bitmap barcode);
}
