package com.njfsoft_utils.shareutil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Browser;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.*;
import android.widget.*;
 



// import com.streampad.utils.SpaceTokenizer;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import com.njfsoft_utils.artpad.ArtPad;
import com.njfsoft_utils.core.Base64;
import com.njfsoft_utils.core.SpaceTokenizer;
import com.njfsoft_utils.shareutil.JSI_Share;
import com.njfsoft_utils.webviewutil.JSI_Simple;
import com.njfsoft_utils.webviewutil.UtilWebView;
import com.njfsoft_utils.webviewutil.UtilWebDialog;



import com.quickorder.R;


public class ShareActivity extends Activity {

    UtilWebView mWebView;
    private final Activity activity = this;
    private static Bundle currConfBundle;


    private final String strHomeUrl = "file:///android_asset/shareActivity/share.html";
    private final String strSettingsSaved = "Settings saved.";


   ProgressDialog progressDialog;


    private SharedPreferences configSettings;
    private SharedPreferences.Editor configEditor;
    String currImgString;
    long currImgID;
    long oldImgID;
    long currStoryID;





  /**
     * Invoked during init to give the Activity a chance to set up its Menu.
     * 
     * @param menu the Menu to which entries may be added
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0,"Share");
        return true;
    }

    /**
     * Invoked when the user selects an item from the Menu.
     * 
     * @param item the Menu entry which was selected
     * @return true if the Menu item was legit (and we consumed it), false
     *         otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                mWebView.setToggleWVBtns();
                return true;
        }

        return false;
    }


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);


       //  setContentView(R.layout.share_layout);

        configSettings = this.getPreferences(MODE_WORLD_WRITEABLE);
        System.out.println("ElasticPad onCreate: ");
        CookieSyncManager.createInstance(activity);

        configEditor = configSettings.edit();
        currConfBundle = getConfBundle();
        int CURR_SCREEN_ORIENT = currConfBundle.getInt("confScreenOrient", 1);
        this.setRequestedOrientation(CURR_SCREEN_ORIENT);
        currImgID = System.currentTimeMillis() / 1000;
        oldImgID = currImgID;
        currStoryID = currImgID;
        currImgString = "noQvalue";
        // webview browser



       	mWebView = new UtilWebView(this, strHomeUrl);
		mWebView.addJavascriptInterface(new JSI_Share(this), "app_share");
 		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(mWebView.getLayout());
    }




 

 







    public void showDaToast(final String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }

    Bundle getConfBundle() {
        Bundle theConfBundle = new Bundle();
        theConfBundle.putString("confBrowBkgd", configSettings.getString("confBrowBkgd", "#800000"));
        theConfBundle.putString("confShowWebImgs", configSettings.getString("confShowWebImgs", "true"));
        theConfBundle.putInt("confScreenOrient", configSettings.getInt("confScreenOrient", 1));
        return theConfBundle;
    }





    public static String getConfValString(String theKey) {
        String strTheKey = "noQvalue";
        try {
            strTheKey = currConfBundle.getString(theKey);
        } catch (Exception err) {
            System.out.println("Error.getConValString: " + err);
        }
        return strTheKey;
    }

    public Integer getConfValInt(String theKey) {

        int strTheKey = 1234;
        try {
            strTheKey = currConfBundle.getInt(theKey);
        } catch (Exception err) {
            System.out.println("Error.getConValString: " + err);
        }
        return strTheKey;
    }


    public void putConfValString(String theKey, String theVal) {


        configEditor = configSettings.edit();
        configEditor.putString(theKey, theVal);
        configEditor.commit();
        currConfBundle = getConfBundle();
        showDaToast(strSettingsSaved);


    }

    public void putConfValInt(String theKey, Integer theVal) {

        configEditor = configSettings.edit();
        configEditor.putInt(theKey, theVal);
        configEditor.commit();
        currConfBundle = getConfBundle();

        showDaToast(strSettingsSaved);

    }

    void doLoadLclUrl(String theLclUstr) {
        String fullUrl = "file:///android_asset/" + theLclUstr;
        mWebView.loadUrl(fullUrl);
    }

    public void doStreamPic(String fromType) {
 
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {

        switch (requestCode) {
 
            case (11):
                if (resultCode == Activity.RESULT_OK) {

                    try {
                        String source = data.getStringExtra("encdBmp");
                        System.out.println("ArtPadRequest.source: " + source);
                        oldImgID = currImgID;
                        currImgID = System.currentTimeMillis() / 1000;
                        currImgString = source;
                        mWebView.loadUrl("javascript:showStreamPic('" + source + "');");
                    } catch (Exception e) {
                        System.out.println("ArtPadRequest: " + e.toString());
                    }
                } else {
                    //gallery MODE CANCELLED
                }
                break;

            default: {
                System.out.println("Its default");
            }

        }



    }

    public Bitmap scaleBitmap(Bitmap theBitmap) {
        // Get the ImageView and its bitmap
        System.out.println("scaleView called::");


        // Get current dimensions AND the desired bounding box
        int width = theBitmap.getWidth();
        int height = theBitmap.getHeight();
        int bounding = 400;
        Log.i("Test", "original width = " + Integer.toString(width));
        Log.i("Test", "original height = " + Integer.toString(height));
        Log.i("Test", "bounding = " + Integer.toString(bounding));

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Log.i("Test", "xScale = " + Float.toString(xScale));
        Log.i("Test", "yScale = " + Float.toString(yScale));
        Log.i("Test", "scale = " + Float.toString(scale));

        // Create a matrix for the scaling and add the scaling data
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and convert it to a format understood by the ImageView
        Bitmap scaledBitmap = Bitmap.createBitmap(theBitmap, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth(); // re-use
        height = scaledBitmap.getHeight(); // re-use
        // BitmapDrawable result = new BitmapDrawable(scaledBitmap);
        Log.i("Test", "scaled width = " + Integer.toString(width));
        Log.i("Test", "scaled height = " + Integer.toString(height));
        return scaledBitmap;
    }

    // saving the file to Gallery
    public void saveBitmap(Bitmap bitmap) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaStorageDir = Environment.getExternalStorageDirectory();
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                System.out.println("saveFile: failed to create directory");
                return;
            }
        }
        try {
            String saved = MediaStore.Images.Media.insertImage(this.getContentResolver(), bitmap, "title", "description");
            Uri sdCardUri = Uri.parse("file://" + Environment.getExternalStorageDirectory());
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, sdCardUri));
            finish();
            System.out.println("StreamPad saveFile: ");
        } catch (Exception e) {
            System.out.println("saveFile: " + e.toString());
            e.printStackTrace();

        }
    }


    public void encBitmap(Bitmap theBitmap) {
        try {
           //  artpadBitmap = theBitmap;
            oldImgID = currImgID;
            currImgID = System.currentTimeMillis() / 1000;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            theBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            baos.close();
            byte[] bMapArray = baos.toByteArray();
            String encodedImage = Base64.encodeBytes(bMapArray);
            System.out.println("encBitmap: " + encodedImage);

            mWebView.loadUrl("javascript:showStreamPic('" + encodedImage + "');");
            //  String isdone = imgUploader.doImgUpload(bMapArray, "New Pic");
        } catch (Exception e) {
            System.out.println("encBitmap.error: " + e.toString());
        }
    }

    public void decBitmap(String theEncStr) {
        try {
            byte[] decodedString = Base64.decode(theEncStr);
        } catch (Exception e) {
            System.out.println("decBitmap: " + e.toString());
        }
    }



    public void doPageLoad() {
        String abgclr = "#800000";
        try {
            abgclr = currConfBundle.getString("confBrowBkgd");
            mWebView.loadUrl("javascript:doPageLoad('" + abgclr + "','noQvalue','noQvalue');");
        } catch (Exception e) {
            System.out.println("doPageLoad" + e.toString());
        }
    }


    public void doStoryShare(String strNwork, String strSTitle, String strSDesc) {
        try {
            this.runOnUiThread(new Runnable() {
                public void run() {
                    progressDialog = ProgressDialog.show(ShareActivity.this, "", "Please wait...", true, true);
                }
            });


        } catch (Exception e) {
            System.out.println("doStoryShare dialog" + e.toString());
        }

        try {

            if (oldImgID == currImgID) {
                doStoryUpload(null, strNwork, strSTitle, strSDesc);
            } else {

                oldImgID = currImgID;
                currImgID = System.currentTimeMillis() / 1000;
                byte[] decodedString = Base64.decode(currImgString);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length,null);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.close();
                byte[] bMapArray = baos.toByteArray();
                doStoryUpload(bMapArray, strNwork, strSTitle, strSDesc);
                // System.out.println("image_str: " + image_str);
            }

        } catch (Exception e) {
            progressDialog.dismiss();
            System.out.println("doStoryShare: " + e.toString());
        }

    }

    public String doStoryUpload(byte[] byte_arr, String strSnwork, String strStoryTitle, String strStoryDesc) {
        String strCurrSID = Long.toString(currStoryID);
        String strOutput = "noQvalue";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("do", "add"));
        nameValuePairs.add(new BasicNameValuePair("sttl", strStoryTitle));
        nameValuePairs.add(new BasicNameValuePair("sdesc", strStoryDesc));
        nameValuePairs.add(new BasicNameValuePair("stime", strCurrSID));
        nameValuePairs.add(new BasicNameValuePair("snwork", strSnwork));
        if (byte_arr != null) {
            String strImgID = Long.toString(currImgID);
            String image_str = Base64.encodeBytes(byte_arr);
            nameValuePairs.add(new BasicNameValuePair("simg", image_str));
            // System.out.println("image_str: " + image_str);
        }


        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://localhost/story/index.html");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            strOutput = convertResponseToString(response);
            if (strOutput.startsWith("http")) {
                mWebView.loadUrl(strOutput);
            }
            System.out.println("the_story_response: " + strOutput);
        } catch (Exception e) {
            showDaToast("Oooops. Could not connect. Check your connection.");
            System.out.println("Error in http connection " + e.toString());
            progressDialog.dismiss();
            return strOutput;
        }
        progressDialog.dismiss();
        return strOutput;

    }

    public String convertResponseToString(HttpResponse response) throws IllegalStateException, IOException {
        String res = "noQvalue";
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = response.getEntity().getContent();
        int contentLength = (int) response.getEntity().getContentLength(); //getting content length…..
        if (contentLength < 0) {
        } else {
            byte[] data = new byte[512];
            int len = 0;
            try {
                while (-1 != (len = inputStream.read(data))) {
                    buffer.append(new String(data, 0, len)); //converting to string and appending  to stringbuffer…..
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close(); // closing the stream…..
            } catch (IOException e) {
                e.printStackTrace();
            }
            res = buffer.toString();     // converting stringbuffer to string…..
        }
        return res;
    }

    public void doNewStoryID() {
        currImgID = System.currentTimeMillis() / 1000;
        oldImgID = currImgID;
        currStoryID = currImgID;
        mWebView.reload();
    }

    public Bitmap doBitmapFromAsset(String strBfile) {
        AssetManager assetManager = ShareActivity.this.getAssets();
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(strBfile);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            return null;
        }
        return bitmap;
    }

    public void doAssetBmp(String strBfile) {
        encBitmap(scaleBitmap(doBitmapFromAsset(strBfile)));
    }



    public static String getLocalUrl(String strLUrl) {
    return "file:///android_asset/" + strLUrl ;
    }
    /* ArtPad functions */

    public void doArtPadBmpaNull() {

    }

    public void doArtPadBitmap() {
        try {
            Intent toMain = new Intent(this, ArtPad.class);
            toMain.putExtra("encdBmp", currImgString);
            startActivityForResult(toMain, 11);
        } catch(Exception e) {
            System.out.println("doArtPadBitmap" + e.toString());
        }
    }

    public void doArtPadBitmap(final Bitmap theDoAPBitmap) {
        try {
            Intent toMain = new Intent(this, ArtPad.class);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            theDoAPBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            baos.close();
            byte[] bMapArray = baos.toByteArray();
            String encodedImage = Base64.encodeBytes(bMapArray);
            toMain.putExtra("encdBmp", encodedImage);
            startActivityForResult(toMain, 11);
        } catch(Exception e) {
            System.out.println("doArtPadBitmap" + e.toString());
        }
    }

    public void doImgLoad() {
        try {
            if(currImgString.equalsIgnoreCase("noQvalue")) {

            }   else {
            mWebView.loadUrl("javascript:showStreamPic('" + currImgString + "');");
            }
        } catch(Exception e) {
            System.out.println("doImgLoad" + e.toString());
        }
    }
    public void doImgEdit() {
        try {
            if(currImgString.equalsIgnoreCase("noQvalue")) {

            }   else {
                doArtPadBitmap();
               //  mWebView.loadUrl("javascript:showStreamPic('" + currImgString + "');");
            }
        } catch(Exception e) {
            System.out.println("doImgLoad" + e.toString());
        }
    }






 


	public String getData() { return ShareDataResult.getInstance().getData(); }
	public String getCallingApp() { return ShareDataResult.getInstance().getCallingApp(); } 
	public String getIHID() { return ShareDataResult.getInstance().getIHID(); }
	public String getAPmode() { return ShareDataResult.getInstance().getAPmode(); }
	public String getNworkID() { return ShareDataResult.getInstance().getNworkID(); }
	public String getNwork() { return ShareDataResult.getInstance().getNwork(); }
	public String getImgName() { return ShareDataResult.getInstance().getImgName(); }
	public String getImgStr() { return ShareDataResult.getInstance().getImgStr(); }
	public byte[] getImgBytes() { return ShareDataResult.getInstance().getImgBytes(); }
	public String getASTitle() { return ShareDataResult.getInstance().getTitle(); }
	public String getCaption() { return ShareDataResult.getInstance().getCaption(); }
	public String getDesc() { return ShareDataResult.getInstance().getDesc(); }
	public String getMsg() { return ShareDataResult.getInstance().getMsg(); }




	public void setToggleWViewBtns(boolean boolSBShow, boolean boolSSDShow) {
	mWebView.setToggleWViewBtns(boolSBShow, boolSSDShow);
	}



}
