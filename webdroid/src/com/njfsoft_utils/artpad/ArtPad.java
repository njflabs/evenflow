package com.njfsoft_utils.artpad;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.*;
import android.webkit.WebView;
import android.widget.*;

import com.njfsoft_utils.artpad.ap_utils.TouchImageView;
import com.njfsoft_utils.artpad.filters.BitmapFilter;
import com.njfsoft_utils.artpad.JSI_ArtPad;
import com.njfsoft_utils.core.Base64;
import com.njfsoft_utils.webviewutil.UtilWebView;
import com.njfsoft_utils.webviewutil.UtilWebDialog;
import com.njfsoft_utils.webviewutil.JSI_Simple;
import com.njfsoft_utils.camcapture.DecoderActivity;
import com.njfsoft_utils.cropimage.CropImage;
 
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.qbits.R;

public class ArtPad extends Activity implements TouchImageView.OnTapListener {
    ViewFlipper vf;
    Button btnFile;
    Button btnEffects;
    Button extrasButton;
    Button saveButton;
    Button btnShare;
    ImageButton btnHome;

    TouchImageView img;

    Bitmap picture; //Holds the original bitmap
    Bitmap currBitmap; //Holds the original bitmap
    Bitmap origBitmap; //Holds the original bitmap
    Uri picUri; //Holds the uri of the original picture
    int chosenEffect = 0;
    private static int MAX_IMAGE_DIMENSION = 400;
    public static final int FLIP_VERTICAL = 1;
    public static final int FLIP_HORIZONTAL = 2;

    private Context context;
    ProgressDialog dlog;
    Bundle extras;
    String currAPmode;
    String currURIstr;
    WebView apPopWebView;
    String strHTML = "noQvalue";
    String mUrl;
    AlertDialog PalertDialog;
    Handler effectsHandler;
    ProgressDialog effectsProgDlog = null;
    static final int AP_N_CAMERA_REQUEST = 15;

    LinearLayout panBtnsAPEdit;
    public UtilWebDialog utilWDialog;

    @Override
    public void onTapped(Matrix matrix) {

    }




    public void setTextToBitmap(final String theText) {
        try {
            if (PalertDialog != null) {
                PalertDialog.dismiss();
            }
        } catch (Exception e) {
            System.out.println("setTextToBitmap.dismiss.PalertDialog: " + e);
        }
        try {
            runOnUiThread(new Runnable() {
                public void run() {

                    drawTextToBitmap(getApplicationContext(), 2, theText);

                }
            });

        } catch (Exception e) {
            System.out.println("setTextToBitmap.run: " + e);
        }
    }

    public void doApNewPopDialog(String strTitle, String strUrl) {
 		 setPagePopUp(strUrl, "noQvalue");
    }

 


public void setToggleMView(final boolean fnlBooltoShow) {
		this.runOnUiThread(new Runnable() {
		   public void run() {
				try {

        if(fnlBooltoShow) {
	btnShare.setVisibility(View.VISIBLE);
	btnFile.setVisibility(View.VISIBLE);
	btnHome.setVisibility(View.VISIBLE);
	btnEffects.setVisibility(View.VISIBLE);
	  } else {
	btnShare.setVisibility(View.INVISIBLE);
	btnFile.setVisibility(View.INVISIBLE);
	btnHome.setVisibility(View.INVISIBLE);
	btnEffects.setVisibility(View.INVISIBLE);
	  }

	  } catch(Exception e) {
        System.out.println("dev:ERROR:setToggleMView: " + e);
	  }
	   }
         });

	}




    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Activity parAct = getParent();
        setContentView(R.layout.com_njfsoft_utils_artpad_artpad);
        extras = getIntent().getExtras();

        Log.i("TAG", "Image Displayed");
        img = (TouchImageView) findViewById(R.id.ImageView1);

        currAPmode = "noQvalue";
        currURIstr = "noQvalue";
	  panBtnsAPEdit = (LinearLayout)findViewById(R.id.lnrlyBtns);
        btnHome = (ImageButton) findViewById(R.id.btn_Home);
	  
        btnFile = (Button) findViewById(R.id.MainImageButtonFile);
        btnEffects = (Button) findViewById(R.id.MainImageButtonEffects);

        btnShare = (Button) findViewById(R.id.MainImageButtonShare);

        String strPicUrl = "";

        btnHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    finish();
                } catch (Exception e) {

                }


            }
        });

        btnFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                doApNewPopDialog("Effects", "artpad/ap_file.html");

            }
        });
        btnEffects.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
		try {
            runOnUiThread(new Runnable() {
                public void run() {
                doApNewPopDialog("Effects", "artpad/ap_filters.html");
                }
            });

        } catch (Exception e) {
            System.out.println("artPad setBmp: " + e.toString());
            e.printStackTrace();
        }

            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    currBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    baos.close();
                    byte[] bMapArray = baos.toByteArray();
                    String encodedImage = Base64.encodeBytes(bMapArray);
                    Intent intent = new Intent();
                    intent.putExtra("encdBmp", encodedImage);
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (Exception e) {
                    System.out.println("btnShare onClick: " + e.toString());
                }
            }
        });





/*
        //doDialogDismiss();
        try {
            runOnUiThread(new Runnable() {
                public void run() {

                    scaleIView();

                }
            });

        } catch (Exception e) {
            System.out.println("artPad setBmp: " + e.toString());
            e.printStackTrace();
        }
*/
        setVisible(true);
	preparePagePopUps("blank.html", "noQvalue");

        if (extras != null) {
            if (extras.containsKey("wPic")) {
                strPicUrl = extras.getString("wPic");
                getImageFromUrl(strPicUrl);

            }

            // Base64 encoded byte array sent
            if (extras.containsKey("encdBmp")) {
                try {

                    byte[] decodedString = Base64.decode(extras.getString("encdBmp"));
                    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, null);
                    origBitmap = bitmap;
                    currBitmap = origBitmap;
                    picture = currBitmap;
                    img.setImageBitmap(picture);
 
                } catch (Exception e) {
                    System.out.println("extras.containsKey: " + e.toString());  //To change body of catch statement use File | Settings | File Templates.
                }
                getIntent().removeExtra("encdBmp");
                //We have picture picked from gallery
            }

            if (extras.containsKey("apmode")) {
                try {
			  currAPmode = extras.getString("apmode");
			  if(currAPmode.equals("apmodeCam")) {
			  
 			  } else if(currAPmode.equals("apmodeCamCrop"))  {
 			  } else if(currAPmode.equals("apmodeCamCirCrop"))  {
                    getAPInhouseCam();
 			  } else if(currAPmode.equals("apmodeCamEdCrop"))  {
 			  } else if(currAPmode.equals("apmodeCamEdCirCrop"))  {
 			  } else if(currAPmode.equals("apmodeCamera"))  {
                    getAPInhouseCam();
 			  } else if(currAPmode.equals("apmodeCamEdit"))  {
 			  } else if(currAPmode.equals("apmodeGallery"))  {
                     runAPJSComm(15);
 			  } else if(currAPmode.equals("apmodeGalCirCrop"))  {
                     runAPJSComm(15);
                    } else {
			  }
                } catch (Exception e) {
                    System.out.println("extras.containsKey: " + e.toString());  //To change body of catch statement use File | Settings | File Templates.
                }
                getIntent().removeExtra("apmode");
                //We have picture picked from gallery
            }


        }

	// panBtnsAPEdit.setVisibility(View.GONE);

                    if(currAPmode.indexOf("Ed") != -1){
		        // setToggleMView(true);
	} else {
	btnShare.setVisibility(View.GONE);
	btnFile.setVisibility(View.GONE);
	btnHome.setVisibility(View.GONE);
	btnEffects.setVisibility(View.GONE);
	}
    }


    public void setPicUr(Uri thePicUri) {
        picUri = thePicUri;
    }


    @Override
    public void onPause() {
        System.out.println("ArtPad onPause: -");
        //  doArtPadBmpaNull();
        super.onPause();
    }

    public void onResume() {
        img.setOnTapListener(this);
        img.setDrawingCacheEnabled(true);
        img.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        img.layout(0, 0, img.getMeasuredWidth(), img.getMeasuredHeight());

        img.buildDrawingCache(true);


        System.out.println("ArtPad onResume: -");
        super.onResume();
    }


    //Saving the file to Gallery/
    public void saveFile() throws IOException {
        System.out.println("artPad saveFile");
        OutputStream fOut = null;

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaStorageDir = Environment.getExternalStorageDirectory();
        //File mediaStorageDir = new File(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "selfie-Lander");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                showDaToast("Filed to create directory");
                return;
            }
        }
        try {
            BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            String saved = MediaStore.Images.Media.insertImage(this.getContentResolver(), bitmap, "title", "description");
            Uri sdCardUri = Uri.parse("file://" + Environment.getExternalStorageDirectory());
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, sdCardUri));
            showDaToast("File has been saved: " + saved);
        } catch (Exception e) {
            System.out.println("saveFile: " + e.toString());
            e.printStackTrace();
        }
    }


    public void drawTextToBitmap(Context gContext,
                                 int gResId,
                                 String gText) {


        Resources resources = gContext.getResources();
        float scale = resources.getDisplayMetrics().density;
        Bitmap bitmap = picture;

        android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();
        // set default bitmap config if none
        if (bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }
        // resource bitmaps are imutable,
        // so we need to convert it to mutable one
        bitmap = bitmap.copy(bitmapConfig, true);


        Canvas canvas = new Canvas(bitmap);
        // new antialised Paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // TextPaint paint = new TextPaint();
        // text color - #3D3D3D

        // text size in pixels
        final float GESTURE_THRESHOLD_DIP = 80.0f;
        paint.setTextSize(dpToPx(20));
        // paint.setTextSize(GESTURE_THRESHOLD_DIP * scale + 0.5f);
        // text shadow
        paint.setShadowLayer(4, 4, 4, Color.YELLOW);
        // paint.bgColor = Color.parseColor("#990000");
        paint.setColor(Color.parseColor("#101010"));
        // draw text to the Canvas center
        Rect bounds = new Rect();
        Rect wordBounds = new Rect();


        int linenum = 1;
        int axft, ayft, tline = 0;
        paint.getTextBounds(gText, 0, gText.length(), bounds);
        int fullheight = bounds.height();
        for (String line : gText.split("\n")) {
            if (line.length() > tline) {
                tline = line.length();
                paint.getTextBounds(line, 0, line.length(), wordBounds);
            }
            // ayft+=15;
            linenum++;
            fullheight = fullheight + bounds.height();
        }
        //  int x = (bitmap.getWidth() - bounds.width())/2;
        int x, y;
        //  int ay = (bitmap.getHeight() + fullheight)/2;


        paint.setStyle(Paint.Style.FILL);
        paint.setShader(new Shader());
        Paint bgpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgpaint.setTextSize(dpToPx(20));
        bgpaint.setStyle(Paint.Style.FILL_AND_STROKE);
        bgpaint.setStrokeWidth(1);
        bgpaint.setColor(Color.parseColor("#990000"));
        bgpaint.setAntiAlias(true);
        // bgpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        bgpaint.setStrokeCap(Paint.Cap.ROUND);
        // bgpaint.setStrokeMiter(3.6f);
        // paint.setFakeBoldText(true);
        // paintpaint.setTextSize(20);

        if (bounds.width() > (bitmap.getWidth() - 50)) {
            //    x = 80;
        }
        /*
        // top left
              x = 100;
              y = 30;
       // top middle
              y = 30;
       // top right
              x = (bitmap.getWidth() - wordBounds.width()) - 100;
              y = 30;
        // left center
              x = 100;
      // center
              x = (bitmap.getWidth() - wordBounds.width())/2;
              y = (bitmap.getHeight() - fullheight)/2;
       // right center
               x = (bitmap.getWidth() - wordBounds.width()) - 100;
       // left bottom
               x = 100;
               y = (bitmap.getHeight() - fullheight);
        //  bottom  center
              x = (bitmap.getWidth() - wordBounds.width())/2;
               y = (bitmap.getHeight() - fullheight);

        //  bottom  right
               x = (bitmap.getWidth() - wordBounds.width()) - 100;
               y = (bitmap.getHeight() - fullheight);
         */
        x = (bitmap.getWidth() - wordBounds.width()) - 100;
        y = (bitmap.getHeight() - fullheight);
        int xft = x, yft = y;
        // RectF fullrect = new RectF(x, y - bounds.height(), wordBounds.width() + x, (fullheight + y) - bounds.height());
        RectF fullrect = new RectF(x - 2, y - bounds.height() - 2, wordBounds.width() + x + 5, (fullheight + y) - bounds.height() + 5);
        // bgpaint.setColor(Color.parseColor("#FFFF99"));
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_artpad), null, fullrect, bgpaint);
        // canvas.drawRoundRect(fullrect, x, y, bgpaint);
        // canvas.drawRoundRect(fullrect, x, y, bgpaint);
        // canvas.drawRect(x, y - bounds.height(), wordBounds.width() +x, (fullheight+y) - bounds.height(),  bgpaint );
        // bgpaint.setStrokeWidth(0);
        //  bgpaint.setColor(Color.parseColor("#FFFFFF"));
        // canvas.drawRect(x + 5, (y - bounds.height())  +5, (wordBounds.width() +x) - 5, ((fullheight+y) - bounds.height()) - 5,  bgpaint );

        // gText = "bmpW: " + bitmap.getWidth() + "bmpH: " +  bitmap.getHeight() + "\n";
        // gText += "boundsW: " + bounds.width() + "boundsH: " +  bounds.height();
        for (String line : gText.split("\n")) {
            canvas.drawText(line, xft, yft, paint);
            //canvas.drawText(line, xft, yft, bgpaint);

            yft += -paint.ascent() + paint.descent();
        }


        System.out.println("drawTextToBitmap: bitmap" + bitmap.getWidth() + " :: " + bitmap.getHeight());
        System.out.println("drawTextToBitmap: text bounds" + bounds.width() + " :: " + bounds.height());
        Point theP = new Point(30, 30);

        currBitmap = bitmap;
        img.setImageBitmap(currBitmap);
        picture = currBitmap;
    }


    //Back to original colors
    public void applyOriginal() {
        img.setImageBitmap(origBitmap);
    }

    public Bitmap getOrigBmp() {
        return origBitmap;
    }



    public void doBmpFilterEfect(final int intEffect) {
        effectsProgDlog = ProgressDialog.show(this, "Processing", "Please Wait...");
        new Thread() {
            @Override
            public void run() {
                super.run();
                currBitmap = BitmapFilter.changeStyle(picture, intEffect);
                Message msg = Message.obtain();
                msg.what = 1;
                effectsHandler.sendMessage(msg);
            }

        }.start();
        effectsHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                img.setImageBitmap(currBitmap);
                picture = currBitmap;
                effectsProgDlog.dismiss();
            }

        };
    }

    public void doAPJSComm(final int theJSCommID) {
    try {
        this.runOnUiThread(new Runnable() {
            public void run() {
			utilWDialog.doDismiss();
                runAPJSComm(theJSCommID);
                chosenEffect = theJSCommID;
            }
        });
    } catch (Exception e) {
        System.out.println("doAPJSComm: " + e.toString());
    }
    }
    public void runAPJSComm(int theJSCommID) {
        if (theJSCommID > 0) {
            switch (theJSCommID) {
                case 1: {
/*
                   Intent intent = new Intent(getApplicationContext(), com.cutOuts.DecoderActivity.class);
                  //  intent.setData(getIntent().getData());
                  startActivityForResult(intent, 110);

*/

                    currBitmap = getOrigBmp();
                    img.setImageBitmap(currBitmap);
                    picture = currBitmap;
                    break;
 
               }
                case 2: {
                    doBmpFilterEfect(1);
                    break;
                }
                case 3: {
                    doBmpFilterEfect(17);
                    break;
                }
                case 4: {
                    doBmpFilterEfect(8);
                    // currBitmap = getInverseEffect();
                    //img.setImageBitmap(currBitmap);
                    //picture = currBitmap;
                    break;
                }
                case 5: {
                    doBmpFilterEfect(18);

                    break;
                }
                case 6: {
                    doBmpFilterEfect(21);
                    break;
                }
                case 7: {
                    doBmpFilterEfect(16);
                    break;
                }
                case 8: {
                    doBmpFilterEfect(19);
                    break;
                }
                case 9: {
                    doBmpFilterEfect(20);
                    break;
                }
                case 10: {
                    doBmpFilterEfect(12);
                    break;
                }
                case 11: {
                    doBmpFilterEfect(15);
                    break;
                }
                case 12: {
                    doApNewPopDialog("Add Text", "text.html");
                    break;
                }
                case 13: {
                    doBmpFilterEfect(22);
                    break;
                }
                case 14: {
                    BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                    Bitmap tbmp = drawable.getBitmap();
                    /*
                    String saved = MediaStore.Images.Media.insertImage(this.getContentResolver(), bitmap, "title", "description");
                    Uri sdCardUri = Uri.parse("file://" + Environment.getExternalStorageDirectory());

				sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, sdCardUri));
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setType("image/*");
                    intent.setDataAndType(Uri.parse(saved), "image/*");
                    startActivityForResult(intent, 14);
			  */
				try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    currBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    baos.close();
                    byte[] bMapArray = baos.toByteArray();
                    String encodedImage = Base64.encodeBytes(bMapArray);

		         Intent intent = new Intent(getApplicationContext(), com.njfsoft_utils.cropimage.CropImage.class);
                         intent.putExtra("circleCrop","yes");
                         intent.putExtra("return-data",true);


				 if (extras.getString("mOutputX") != null) {
                         intent.putExtra("mOutputX",extras.getString("mOutputX"));
				}
				 if (extras.getString("mOutputY") != null) {
                         intent.putExtra("mOutputY",extras.getString("mOutputY"));
				}
                         intent.putExtra("image-bytes",encodedImage);
                    	startActivityForResult(intent, 120);


                        } catch (Exception e) {

            System.out.println("get result image-bytes: " + e.toString());
            e.printStackTrace();
                        }


                    break;
                }
                case 15: {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, (MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
                    //  Intent intent = new Intent(Intent.ACTION_PICK,(MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
                    //   startActivityForResult(intent,PICK_EXISTING_PHOTO_RESULT_CODE);
                    startActivityForResult(pickPhoto, 4);
                    //  doEPPicture("gallery");
                    // finish();
                    break;
                }
                case 16: {
                    // getAPNativeCam();
				getAPInhouseCam();
                    break;
                }
                case 17: {
                    break;
                }
                case 18: {
                    BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();

                    try {
                        getApplicationContext().setWallpaper(bitmap);
                        showDaToast("Wallpaper Updated");
                    } catch (Exception e) {
                        System.out.println("case 18: " + e.toString());
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    break;
                }
                case 19: {

                    try {
                        doSaveFileDlg();
                        //
                    } catch (Exception e) {
                        System.out.println("saveFile: " + e.toString());  //To change body of catch statement use File | Settings | File Templates.
                    }

                    break;
                }
                case 20: {

                    finish();
                    break;
                }
                case 21: {
                    finish();
                    break;
                }
                case 30: {
                    doBmpFilterEfect(9);
                    break;
                }
                case 31: {
                    doBmpFilterEfect(13);
                    break;
                }
                case 32: {
                    doBmpFilterEfect(5);
                    break;
                }
                case 33: {
                    doBmpFilterEfect(4);
                    break;
                }
                case 34: {
                    doBmpFilterEfect(10);
                    break;
                }
                case 35: {
                    doBmpFilterEfect(6);
                    break;
                }
                case 36: {
                    doBmpFilterEfect(2);
                    break;
                }
                case 37: {
                    doBmpFilterEfect(11);
                    break;
                }
                case 38: {
                    doBmpFilterEfect(7);
                    break;
                }
                case 39: {
                    doBmpFilterEfect(3);
                    break;
                }
                case 40: {
                    showDaToast("w: " + picture.getWidth() + " :: " + "h: " + picture.getHeight());
                    break;
                }
                case 50: {
                    break;
                } // do nothing

                default:
                    break;
            }

            //After setting the effect, we reset the variable.
            chosenEffect = 0;

        }
    }


    public static int getOrientation(Context context, Uri photoUri) {
        /* it's on the external media. */
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[]{MediaStore.Images.ImageColumns.ORIENTATION}, null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }

        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public static Bitmap scaleImage(Context context, Uri photoUri) throws IOException {
        InputStream is = context.getContentResolver().openInputStream(photoUri);
        BitmapFactory.Options dbo = new BitmapFactory.Options();
        dbo.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, dbo);
        is.close();

        int rotatedWidth, rotatedHeight;
        int orientation = getOrientation(context, photoUri);

        if (orientation == 90 || orientation == 270) {
            rotatedWidth = dbo.outHeight;
            rotatedHeight = dbo.outWidth;
        } else {
            rotatedWidth = dbo.outWidth;
            rotatedHeight = dbo.outHeight;
        }

        Bitmap srcBitmap;
        is = context.getContentResolver().openInputStream(photoUri);
        if (rotatedWidth > MAX_IMAGE_DIMENSION || rotatedHeight > MAX_IMAGE_DIMENSION) {
            float widthRatio = ((float) rotatedWidth) / ((float) MAX_IMAGE_DIMENSION);
            float heightRatio = ((float) rotatedHeight) / ((float) MAX_IMAGE_DIMENSION);
            float maxRatio = Math.max(widthRatio, heightRatio);

            // Create the bitmap from file
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = (int) maxRatio;
            srcBitmap = BitmapFactory.decodeStream(is, null, options);
        } else {
            srcBitmap = BitmapFactory.decodeStream(is);
        }
        is.close();

        /*
        * if the orientation is not 0 (or -1, which means we don't know), we
        * have to do a rotation.
        */
        if (orientation > 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);

            srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(),
                    srcBitmap.getHeight(), matrix, true);
        }

        String type = context.getContentResolver().getType(photoUri);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (type.equals("image/png")) {
            srcBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        } else if (type.equals("image/jpg") || type.equals("image/jpeg")) {
            srcBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        }
        byte[] bMapArray = baos.toByteArray();
        baos.close();
        return srcBitmap;
    }


    public void getImageFromUrl(String url) {
        System.out.println("getImageFromUrl: " + url);
        try {
            origBitmap = grabImageFromUrl(url);

            img.setImageBitmap(origBitmap);
            System.out.println("getImageFromUrl setImageBitmap: " + url);
            picture = origBitmap;
        } catch (Exception e) {
            System.out.println("getImageFromUrl: " + e.toString());
            // txtUrl.setText("Error: Exception");
        }

    }

    public Bitmap grabImageFromUrl(String url) {
        Bitmap bitmap = null;
        try {

            bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            // img.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    public void scaleIView() {


        // Get the ImageView and its bitmap
        System.out.println("scaleView called::");

        int Measuredwidth = 0;
        int Measuredheight = 0;
        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        Measuredwidth = d.getWidth() - 2;
        Measuredheight = d.getHeight() - 10;


        // Get current dimensions AND the desired bounding box
        int width = picture.getWidth();
        int height = picture.getHeight();
        // int bounding = dpToPx(500);
        int bounding = 400;


        Point size = new Point();


        Log.i("Test", "original width = " + Integer.toString(width));
        Log.i("Test", "original height = " + Integer.toString(height));
        Log.i("Test", "bounding = " + Integer.toString(bounding));

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) Measuredwidth) / width;
        float yScale = ((float) Measuredheight) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Log.i("Test", "xScale = " + Float.toString(xScale));
        Log.i("Test", "yScale = " + Float.toString(yScale));
        Log.i("Test", "scale = " + Float.toString(scale));

        // Create a matrix for the scaling and add the scaling data
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and convert it to a format understood by the ImageView
        Bitmap scaledBitmap = Bitmap.createBitmap(picture, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth(); // re-use
        height = scaledBitmap.getHeight(); // re-use
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
        Log.i("Test", "scaled width = " + Integer.toString(width));
        Log.i("Test", "scaled height = " + Integer.toString(height));

        // Apply the scaled bitmap
        // img.setImageDrawable(result);
        img.setImageBitmap(scaledBitmap);
        currBitmap = scaledBitmap;
        picture = scaledBitmap;
        origBitmap = scaledBitmap;
        // Now change ImageView's dimensions to match the scaled image
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) img.getLayoutParams();
        params.width = width;
        params.height = height;
        img.setLayoutParams(params);

        Log.i("Test", "done");
    }

    private int dpToPx(int dp) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }


    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {

        switch (requestCode) {
            case (4): {
                if (resultCode == Activity.RESULT_OK) {
                    Uri artpadPhotoUri = data.getData();
			  currURIstr = artpadPhotoUri.toString();
                    Uri artpadBitmap = null;
                    Uri artpadPhotoURL = null;
                    try {
                        origBitmap = scaleImage(getApplicationContext(), artpadPhotoUri);
                        picture = origBitmap;
                        currBitmap = origBitmap;
                        img.setImageBitmap(picture);
                        scaleIView();

                    if(currAPmode.indexOf("Crop") != -1){
		        runAPJSComm(14);
                    } else {
			  
			   doFinishData();
			 }

				
 
                    } catch (Exception e) {
                        System.out.println("GALLERY_REQUEST: " + e.toString());
                    }
                     } else {
                        setResult(RESULT_CANCELED);
                        finish();
                    // CROP MODE CANCELLED
                }
                break;
            }

            case (115): 
                if (resultCode == Activity.RESULT_OK) {
Bundle ddata = data.getExtras();
picture = (Bitmap) ddata.getParcelable("data");
img.setImageBitmap(picture);
                } else {
                        setResult(RESULT_CANCELED);
                        finish();
                    // CROP MODE CANCELLED
                }
break;

            case (14): {
                if (resultCode == Activity.RESULT_OK) {
                    System.out.println("Crop onActivityResult");
                    // Describe the columns you'd like to have returned. Selecting from the Thumbnails location gives you both the Thumbnail Image ID, as well as the original image ID
                    String[] projection = {
                            MediaStore.Images.Thumbnails._ID,  // The columns we want
                            MediaStore.Images.Thumbnails.IMAGE_ID,
                            MediaStore.Images.Thumbnails.KIND,
                            MediaStore.Images.Thumbnails.DATA};
                    String selection = MediaStore.Images.Thumbnails.KIND + "=" + // Select only mini's
                            MediaStore.Images.Thumbnails.MINI_KIND;

                    String sort = MediaStore.Images.Thumbnails._ID + " DESC";

                    //At the moment, this is a bit of a hack, as I'm returning ALL images, and just taking the latest one. There is a better way to narrow this down I think with a WHERE clause which is currently the selection variable
                    Cursor myCursor = this.managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection, selection, null, sort);

                    long imageId = 0l;
                    long thumbnailImageId = 0l;
                    String thumbnailPath = "";

                    try {
                        myCursor.moveToFirst();
                        imageId = myCursor.getLong(myCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.IMAGE_ID));
                        thumbnailImageId = myCursor.getLong(myCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID));
                        thumbnailPath = myCursor.getString(myCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA));
                    } finally {
                        myCursor.close();
                    }

                    //Create new Cursor to obtain the file Path for the large image

                    String[] largeFileProjection = {
                            MediaStore.Images.ImageColumns._ID,
                            MediaStore.Images.ImageColumns.DATA
                    };

                    String largeFileSort = MediaStore.Images.ImageColumns._ID + " DESC";
                    myCursor = this.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, largeFileProjection, null, null, largeFileSort);
                    String largeImagePath = "";

                    try {
                        myCursor.moveToFirst();

                        //This will actually give yo uthe file path location of the image.
                        largeImagePath = myCursor.getString(myCursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
                    } finally {
                        myCursor.close();
                    }
                    imageId = imageId + 1;
                    Uri artpadCu = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(imageId));
                    System.out.println("Crop artpadCu" + artpadCu);
                    try {

                        picture = MediaStore.Images.Media.getBitmap(getContentResolver(), artpadCu);
                        currBitmap = picture;
                        img.setImageBitmap(getCircularBitmap(picture));
                    } catch (Exception e) {
                        System.out.println("Crop error" + e.toString());
                    }
                } else {
                        setResult(RESULT_CANCELED);
                        finish();
                    // CROP MODE CANCELLED
                }
                break;
            }
            case (AP_N_CAMERA_REQUEST):

                if (resultCode == Activity.RESULT_OK) {
                    // Describe the columns you'd like to have returned. Selecting from the Thumbnails location gives you both the Thumbnail Image ID, as well as the original image ID
                    String[] projection = {
                            MediaStore.Images.Thumbnails._ID,  // The columns we want
                            MediaStore.Images.Thumbnails.IMAGE_ID,
                            MediaStore.Images.Thumbnails.KIND,
                            MediaStore.Images.Thumbnails.DATA};
                    String selection = MediaStore.Images.Thumbnails.KIND + "=" + // Select only mini's
                            MediaStore.Images.Thumbnails.MINI_KIND;

                    String sort = MediaStore.Images.Thumbnails._ID + " DESC";

                    //At the moment, this is a bit of a hack, as I'm returning ALL images, and just taking the latest one. There is a better way to narrow this down I think with a WHERE clause which is currently the selection variable
                    Cursor myCursor = this.managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection, selection, null, sort);

                    long imageId = 0l;
                    long thumbnailImageId = 0l;
                    String thumbnailPath = "";

                    try {
                        myCursor.moveToFirst();
                        imageId = myCursor.getLong(myCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.IMAGE_ID));
                        thumbnailImageId = myCursor.getLong(myCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID));
                        thumbnailPath = myCursor.getString(myCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA));
                    } finally {
                        myCursor.close();
                    }

                    //Create new Cursor to obtain the file Path for the large image

                    String[] largeFileProjection = {
                            MediaStore.Images.ImageColumns._ID,
                            MediaStore.Images.ImageColumns.DATA
                    };

                    String largeFileSort = MediaStore.Images.ImageColumns._ID + " DESC";
                    myCursor = this.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, largeFileProjection, null, null, largeFileSort);
                    String largeImagePath = "";

                    try {
                        myCursor.moveToFirst();

                        //This will actually give yo uthe file path location of the image.
                        largeImagePath = myCursor.getString(myCursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
                    } finally {
                        myCursor.close();
                    }

                    Uri artpadPhotoUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(imageId));
                    try {
                        origBitmap = scaleImage(getApplicationContext(), artpadPhotoUri);
                        picture = origBitmap;
                        currBitmap = origBitmap;
                        img.setImageBitmap(picture);
                        scaleIView();
                    } catch (IOException e) {
                        System.out.println(": " + e.toString());  //To change body of catch statement use File | Settings | File Templates.
                    }

                } else {
                    //CAMERA MODE CANCELLED
                }
                break;
            case (9): {
                try {
                    System.out.println("Crop File:" + data.getExtras().toString());
                    //  toMain.putExtra("gPic", scaleImage(getApplicationContext(), data.getData()));
                } catch (Exception e) {
                    System.out.println(" GALLERY_REQUEST: " + e.toString());
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
		break;	

		case (110):
                if (resultCode == Activity.RESULT_OK) {

                    try {
				Bundle aextras = data.getExtras();




        if (aextras != null) {
                    System.out.println("ArtPadRequest: aextras not null");

            //We have picture taken with the camera
            if (aextras.containsKey("encdBmp")) {
 



                    byte[] decodedString = Base64.decode(aextras.getString("encdBmp"));
                    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, null);
                    
                    origBitmap = bitmap;
                    currBitmap = origBitmap;
                    picture = currBitmap;
                    img.setImageBitmap(picture);
			 
                    if(currAPmode.indexOf("Crop") != -1){
		        runAPJSComm(14);
                    } else {
				// doSaveFileDlg();
			  doFinishData();
			  }
                    System.out.println("ArtPadRequest: aextras not null and encdBmp: " + aextras.getString("encdBmp"));
                //We have picture picked from gallery
            }
        }
 
    
                    } catch (Exception e) {
                        System.out.println("ArtPadRequest: " + e.toString());
				e.printStackTrace();
                    }
                } else {

                        setResult(RESULT_CANCELED);
                        finish();
                    // CROP MODE CANCELLED
                
                    //gallery MODE CANCELLED
                }
                break;



		case (120):
                if (resultCode == Activity.RESULT_OK) {

                    try {
				Bundle aextras = data.getExtras();




        if (aextras != null) {
                    System.out.println("ArtPadRequest: aextras not null");
            //We have picture taken with the camera
            if (aextras.containsKey("encdBmp")) {
                    byte[] decodedString = Base64.decode(aextras.getString("encdBmp"));
                    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, null);
                    origBitmap = bitmap;
                    currBitmap = origBitmap;
                    picture = currBitmap;
                    img.setImageBitmap(picture);
                    System.out.println("ArtPadRequest: aextras not null and encdBmp: " + aextras.getString("encdBmp"));
                //We have picture picked from gallery
     
                    if(currAPmode.indexOf("Ed") != -1){
		        setToggleMView(true);
                    } else {
			   Intent intent = new Intent();
                    intent.putExtra("encdBmp", aextras.getString("encdBmp"));
                    setResult(RESULT_OK, intent);
                    finish();
			 }


        }
	   
        }


                    } catch (Exception e) {
                        System.out.println("ArtPadRequest: " + e.toString());
				e.printStackTrace();
                    }




                   } else {
                        setResult(RESULT_CANCELED);
                        finish();
                    // CROP MODE CANCELLED
                }
                break;

	   
	}


    }


    public void doSaveFileDlg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ArtPad.this);
        builder.setMessage("Do you want to save your picture?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    //If we click Yes, picture is saved to gallery/Pimped Pictures
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            saveFile();
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    //If we click No, we return to the current activity
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }



    public void getAPInhouseCam() {
		         Intent intent = new Intent(getApplicationContext(), com.njfsoft_utils.camcapture.DecoderActivity.class);
 


                    	startActivityForResult(intent, 110);


    }

    public void getAPNativeCam() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(pictureIntent, AP_N_CAMERA_REQUEST);
    }

    public void closeAPDialog() {
        try {
            if (PalertDialog != null) {
                PalertDialog.dismiss();
            }
        } catch (Exception e) {
            System.out.println("doApNewPopDialog: " + e);
        }
    }




// rounded bitmap for the round face
public static Bitmap getCircularBitmap(Bitmap bitmap) {
    Bitmap output;
    Bitmap bmSprite;
    if (bitmap.getWidth() > bitmap.getHeight()) {
        output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    } else {
        output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
    }
    bmSprite = Bitmap.createBitmap(bitmap.getWidth()*4, bitmap.getHeight()*4, Bitmap.Config.ARGB_8888);
    final int color = 0xff424242;
 
    Canvas canvas = new Canvas(output);
    Canvas cnvsDone = new Canvas(bmSprite); 

    cnvsDone.drawARGB(0, 0, 0, 0);
        Paint bgpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgpaint.setColor(color);
        bgpaint.setAntiAlias(true);




    final Paint paint = new Paint();
    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

    float r = 0;

    if (bitmap.getWidth() > bitmap.getHeight()) {
        r = bitmap.getHeight() / 2;
    } else {
        r = bitmap.getWidth() / 2;
    }

    paint.setAntiAlias(true);
    canvas.drawARGB(0, 0, 0, 0);
    paint.setColor(color);
    canvas.drawCircle(r, r, r, paint);
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
  

    canvas.drawBitmap(bitmap, rect, rect, paint);
    cnvsDone.drawBitmap(output, 0, 0, bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth(), 0, bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth()*2, 0, bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth()*3, 0, bgpaint);

    cnvsDone.drawBitmap(output, 0, bitmap.getHeight(), bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth(), bitmap.getHeight(), bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth()*2, bitmap.getHeight(), bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth()*3, bitmap.getHeight(), bgpaint);

    cnvsDone.drawBitmap(output, 0, bitmap.getHeight()*2, bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth(), bitmap.getHeight()*2, bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth()*2, bitmap.getHeight()*2, bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth()*3, bitmap.getHeight()*2, bgpaint);

    

    cnvsDone.drawBitmap(output, 0, bitmap.getHeight()*3, bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth(), bitmap.getHeight()*3, bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth()*2, bitmap.getHeight()*3, bgpaint);
    cnvsDone.drawBitmap(output, bitmap.getWidth()*3, bitmap.getHeight()*3, bgpaint);


    return bmSprite;
}



    public void showDaToast(final String cnvsFnlStrDToast) {

        try {
            final Context cnvsFnlCtx = getApplicationContext();
            this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(cnvsFnlCtx, cnvsFnlStrDToast, Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            System.out.println("dev:ERROR:showDaToast:" + e.toString());
        }

    }



public String getRealPathFromURI(Context context, Uri contentUri) {
  Cursor cursor = null;
  try { 
    String[] proj = { MediaStore.Images.Media.DATA };
    cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    cursor.moveToFirst();
    return cursor.getString(column_index);
  } finally {
    if (cursor != null) {
      cursor.close();
    }
  }
}


    public void preparePagePopUps(String pageUrl, String pageHtml) {
        String fullUrl = "file:///android_asset/" + pageUrl;
        String newHTML = "";

        UtilWebDialog.UtilWDListener utilWDListener = new UtilWebDialog.UtilWDListener() {

            public void epMDcom(int cbType, String cbArgs, UtilWebDialog epmd) {
                final String fnlCbArgs;
                epmd.doDismiss();

			// handler.removeCallbacks(thrdTask);
			// handler.postDelayed(thrdTask, 0);

            System.out.println("dev:SmsCanvas:epMainDListener: " + cbType + " : " + cbArgs);
                switch (cbType) {
                    case 5:
		            System.out.println("dev:SmsCanvas:epMainDListener: " + cbType + " : " + cbArgs);
                        break;
                    default:
                       //  simpleWebView.loadUrl("javascript:doDLgCUp();");
                        break;
                }
            }
        };

        utilWDialog = new UtilWebDialog(this, fullUrl, pageHtml, utilWDListener, new JSI_ArtPad(this), "app_artpad");
	  utilWDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }



    public void setPagePopUp(final String pageUrl, final String pageHtml) {

 
        try {
            this.runOnUiThread(new Runnable() {
                public void run() {
                   utilWDialog.setPopPage(pageUrl, pageHtml);
			 utilWDialog.show();

                }
            });


        } catch (Exception e) {
            System.out.println("dev:ERROR:setPagePopUp:" + e.toString());
		e.printStackTrace();
        }


    }



    public String getImgLoadStr() {
        try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    currBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    baos.close();
                    byte[] bMapArray = baos.toByteArray();
                    String encodedImage = Base64.encodeBytes(bMapArray);
 			  return encodedImage;
        } catch(Exception e) {
            System.out.println("selfieLander:getImgLoadStr: " + e.toString());
		return "noQvalue";
        }
    }




	public void doFinishData() {
                try {
			  if(currAPmode.indexOf("apmodeShareEdit") != -1){
                    System.out.println("nothing to return");
			  } else {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    currBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    baos.close();
                    byte[] bMapArray = baos.toByteArray();
                    String encodedImage = Base64.encodeBytes(bMapArray);
                    Intent intent = new Intent();
                    intent.putExtra("encdBmp", encodedImage);
                    intent.putExtra("apmode", currAPmode);
			   if(currURIstr == "noQvalue") {
			   } else {
			  intent.putExtra("image-path",currURIstr);
                    }
			  setResult(RESULT_OK, intent);
			  }
                    finish();
                } catch (Exception e) {
                    System.out.println("btnShare onClick: " + e.toString());
                }
	}






}