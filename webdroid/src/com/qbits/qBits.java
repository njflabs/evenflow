package com.qbits;


/*
* this is the main android app activity.
* the project started out as a QR code reader for commerce
* reading whle category of products into a QR code.
* see the QR functions at end.
* it evolved into a simple ecommerce script.
* using android camera, speech, and other components to
* enhance the way an online shop is run.
*/

import android.app.Activity;
import android.app.Dialog;
import android.app.Instrumentation;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Color;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.provider.ContactsContract.Contacts;
import android.provider.AlarmClock;
import android.provider.Browser;
import android.provider.MediaStore;
import android.speech.*;
import android.speech.RecognizerIntent;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.*;
import android.widget.*;
import android.view.WindowManager;
import android.widget.AbsListView.OnScrollListener;

import org.apache.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Locale;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.qbits.js_interfaces.JSI_qBits;

import com.njfsoft_utils.anim.AnimMovSingleton;
import com.njfsoft_utils.core.StringUtils;
import com.njfsoft_utils.core.SpaceTokenizer;
import com.njfsoft_utils.core.Base64;
import com.njfsoft_utils.cutOuts.CutOuts;
import com.njfsoft_utils.dbutil.UtilDbRecord;
import com.njfsoft_utils.dbutil.UtilSQLAdapter;
import com.njfsoft_utils.shareutil.ShareDataResult;
import com.njfsoft_utils.shoputil.ShopItemArr;
import com.njfsoft_utils.smsutil.SmsUtils;
import com.njfsoft_utils.smsutil.ServiceListener;

import com.njfsoft_utils.webviewutil.UtilWebView;
import com.njfsoft_utils.webviewutil.UtilWebDialog;
import com.njfsoft_utils.webviewutil.ClientHttpRequest;
import com.njfsoft_utils.widgetutil.DroidWriterEditText;
import com.njfsoft_utils.widgetutil.DWETListener;

import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;


import com.jwetherell.quick_response_code.CaptureActivity;



public class qBits extends Activity implements
RecognitionListener, TextToSpeech.OnInitListener {

 private UtilWebView mWebView;
 private final Activity activity = this;
 private static Bundle currConfBundle;
 private SharedPreferences configSettings;
 private SharedPreferences.Editor configEditor;
 String currImgString;
 public String currAQRstring;
 long currImgID;
 long oldImgID;
 public int currStoryID;
 public int currStoryType;
 //  private SlidingDrawer slidingDrawer;
 public ProgressDialog progressDialog;
 private MultiAutoCompleteTextView autoCompleteTextView;
 ArrayList aListHistUrls;
 ArrayList aListHistTitles;
 String currFileName;
 UtilWebDialog utilWDialog;
 public UtilWebDialog epMainDialog;
 public UtilSQLAdapter dbMSQLA;
 public UtilSQLAdapter challSQLiteAdapter;
 SmsUtils smsUtils;
 StringUtils stringUtils;
 ServiceListener cServiceListener;
 public ProgressDialog dialog;
 private Handler mHandler;
 private LinearLayout lnrLyt_UWView;
 private LinearLayout lnrLyt_UWVBtns;
 String strHomeUrl = "file:///android_asset/quickorder/index.php";
 String currHomeUrl = strHomeUrl;
 String currShareUrl = strHomeUrl;
 private final String strSettingsSaved = "Settings saved.";
 static final int CAMERA_REQUEST = 3;
 static final int GALLERY_REQUEST = 4;
 public String currPageVars;

 ScrollView mScrollView;
 File mediaStorageDir;
 ClientHttpRequest chttpreq;
 static final int SHARE_ACTIVITY_RES = 555;

 private ValueCallback < Uri > mUploadMessage;
 public ValueCallback < Uri[] > uploadMessage;
 public static final int REQUEST_SELECT_FILE = 100;
 private final static int FILECHOOSER_RESULTCODE = 1;
 private final static int SHARE_SOCIAL_RES = 556;
 private final static int VOICE_RECOGNITION_REQUEST_CODE = 1234;
 final Instrumentation instrumentation = new Instrumentation();
 private View mCustomView;
 private FrameLayout mCustomViewContainer;
 private WebChromeClient.CustomViewCallback mCustomViewCallback;
 private FrameLayout mContentView;
 SpeechRecognizer speech = null;
 Intent recognizerIntent;
 TextToSpeech mTts;
 static final String LOG_TAG = "qBits";
 String isSpeaking;
 String currContactType;
 String currContactName;
 String currContactStr;
 String currSpkMatches;
 String currSpkLast;
 String currSpkLChoice;
 String currSpkChoice;
 String currSpkComms;
 String[] currCommsArr;
 int currCmdInt;
 boolean useLastCmd = false;
 boolean isLastTry = false;
 Map < String, String > extraHeaders = new HashMap < String, String > ();
 ShopItemArr shopItemArr;


 // bunch of speech to text commands
 // SEARCH,BUSCA,PROCURA,FAVORITE,FAVORITO,ALARM,ALARME,TEST,TESTE,SHOP,LOJA,CALL
 // ,CHAMA,FIX,FINO,SMS,CALCULATE,CALCULA,READ,BOUNCE,LEVA,HELP,AJUDA
 public final int SPK_CMND_SEARCH = 0;
 public final int SPK_CMND_BUSCA = 1;
 public final int SPK_CMND_PROCURA = 2;
 public final int SPK_CMND_FAVORITE = 3;
 public final int SPK_CMND_FAVORITO = 4;
 public final int SPK_CMND_ALARM = 5;
 public final int SPK_CMND_ALARME = 6;
 public final int SPK_CMND_TEST = 7;
 public final int SPK_CMND_TESTE = 8;
 public final int SPK_CMND_SHOP = 9;
 public final int SPK_CMND_LOJA = 10;
 public final int SPK_CMND_CALL = 11;
 public final int SPK_CMND_CHAMA = 12;
 public final int SPK_CMND_FIX = 13;
 public final int SPK_CMND_FINO = 14;
 public final int SPK_CMND_SMS = 15;
 public final int SPK_CMND_CALCULATE = 16;
 public final int SPK_CMND_CALCULA = 17;
 public final int SPK_CMND_READ = 18;
 public final int SPK_CMND_BOUNCE = 19;
 public final int SPK_CMND_LEVA = 20;
 public final int SPK_CMND_HELP = 21;
 public final int SPK_CMND_AJUDA = 22;
 public final int SPK_CMND_WHATSAPP = 23;




 String currPopPageUrl;
 String currPopPageHtml;

 /**
  * Called when the activity is first created.
  */
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  requestWindowFeature(Window.FEATURE_NO_TITLE);
  requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
  configSettings = this.getPreferences(MODE_WORLD_WRITEABLE);
  System.out.println("qBits onCreate: ");
  CookieSyncManager.createInstance(activity);
  configEditor = configSettings.edit();
  currConfBundle = getConfBundle();
  currPopPageUrl = "noQvalue";
  currPopPageHtml = "noQvalue";
  int CURR_SCREEN_ORIENT = currConfBundle.getInt("confScreenOrient", 0);
  setContentView(R.layout.com_ezflyr_mains);
  cServiceListener = new ServiceListener() {
   @Override
   public void onComplete(String s, UtilDbRecord tmpASCpnum) {
    try {
     int theLastID = dbMSQLA.getLastID();
     if (dialog != null) {
      dialog.dismiss();
     }
     mWebView.loadUrl("javascript:doSvcMsgComm('" + theLastID + "','" + s + "')");
    } catch (Exception e) {
     System.out.println("dev:ERROR:qBits:cServiceListener:onComplete: " + e);
    }
    System.out.println("flow:qBits:cServiceListener: " + s);
   }
  };

  initEFui();
  isSpeaking = "no";




  // these initialize the speech to text to speech.
  // will be used for automating quickorder serches and item add/edit.
  speech = SpeechRecognizer.createSpeechRecognizer(this);
  speech.setRecognitionListener(this);
  recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
  recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
  recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
  recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
  mTts = new TextToSpeech(this, this); // TextToSpeech.OnInitListener
  currContactType = "noQvalue";
  currContactName = "noQvalue";
  currContactStr = "noQvalue";
  currSpkMatches = "noQvalue";
  currSpkLast = "noQvalue";
  currSpkLChoice = "noQvalue";
  currSpkChoice = "noQvalue";
  currSpkComms = "search,busca,procura,favorite,favorito,alarm,alarme,test,teste,shop,loja,call";
  currSpkComms += ",chama,fix,fino,sms,calculate,calcula,read,bounce,leva,help,ajuda,whatsapp";
  currCommsArr = currSpkComms.split(",");
  currCmdInt = 0;



  smsUtils = new SmsUtils(this, cServiceListener);
  stringUtils = new StringUtils();
  mHandler = new Handler();
  dbMSQLA = new UtilSQLAdapter(this);
  preparePagePopUps("blank.html", "noQvalue");
  currStoryID = 0;
  currStoryType = 50;
  currPageVars = "noQvalue";
  currAQRstring = "noQvalue";
  currFileName = "noQvalue.jpeg";
  mediaStorageDir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "quick-order");
 }


// initilizes the webview
 public void initEFui() {
  try {
   if (mScrollView == null) {
    mScrollView = (ScrollView) qBits.this.findViewById(R.id.scrlEditText);
   }
   if (mWebView == null) {
    UtilWebView.UtilWVListener tWVListener = new UtilWebView.UtilWVListener() {
     public void doWVEvent(int cbType) {
      InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(mWebView.getWindowToken(), 0);
      getSpeechToText();
     }
    };
    mWebView = new UtilWebView(this, this, currHomeUrl, "noQvalue", tWVListener, new JSI_qBits(this), "app");
    mWebView.getSettings().setJavaScriptEnabled(true);
    mWebView.clearSslPreferences();
    // mWebView.enablePlatformNotifications();
    mWebView.addJavascriptInterface(new JSI_qBits(this), "app");
    mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    boolean CURR_SHOW_WEB_IMAGES = Boolean.parseBoolean(currConfBundle.getString("confShowWebImgs"));
    mWebView.getSettings().setLoadsImagesAutomatically(CURR_SHOW_WEB_IMAGES);
    mWebView.getSettings().setSupportZoom(true);
    mWebView.getSettings().setBuiltInZoomControls(true);
    // mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    // avoids flickering
    mWebView.setBackgroundColor(Color.parseColor("#FFFFFF"));
    mWebView.setWebViewClient(new DefWViewClient());
    mWebView.setWebChromeClient(new qBChromeClient());
    mWebView.getSettings().setTextZoom(currConfBundle.getInt("confBrowZoom"));
    // registerForContextMenu(mWebView);
    lnrLyt_UWView = (LinearLayout) qBits.this.findViewById(R.id.ll_uwv);
    lnrLyt_UWView.addView(mWebView.getLayout());
   } else {
    System.out.println("onCreate mWebView.notnull: ");
   }
  } catch (Exception e) {
   System.out.println("onCreate: " + e.toString());
   e.printStackTrace();
  }
 }





// trying to save the last url used in webview to use on resume
 @Override
 public void onRestoreInstanceState(Bundle savedInstanceState) {
  super.onRestoreInstanceState(savedInstanceState);
  strHomeUrl = savedInstanceState.getString("lurl");
 }
 @Override
 public void onSaveInstanceState(Bundle savedInstanceState) {
  super.onSaveInstanceState(savedInstanceState);
  savedInstanceState.putString("lurl", mWebView.getAddressUrl());
  // etc.
 }
 @Override
 protected void onPause() {
  super.onPause();
  // strHomeUrl = mWebView.getAddressUrl(); 
 }
 @Override
 public void onResume() {
  super.onResume();
  // currHomeUrl = strHomeUrl;
  Intent intent = getIntent();
  String action = intent.getAction();
  String type = intent.getType();
  if (Intent.ACTION_SEND.equals(action) && type != null) {
   if ("text/plain".equals(type)) {
    String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
    if (sharedText != null) {
     currHomeUrl = "file:///android_asset/quickorder/index.php?pid=aa-share";
     currShareUrl = sharedText;
    }
   }
  }
  initEFui();
  try {
   dialog.dismiss();
  } catch (Exception e) {
   System.out.println("onResume: " + e.toString());
  }
 }





// generic Toast function
 public void showDaToast(final String toast) {
  try {
   activity.runOnUiThread(new Runnable() {
    public void run() {
     Toast.makeText(activity, toast, Toast.LENGTH_LONG).show();
    }
   });
  } catch (Exception e) {
   System.out.println("qBits.showDaToast.Error: " + e.toString());
  }
 }







// returns current user settings as an android Bundle
 Bundle getConfBundle() {
  Bundle theConfBundle = new Bundle();
  theConfBundle.putString("confBrowBkgd", configSettings.getString("confBrowBkgd", "#800000"));
  theConfBundle.putString("confShowWebImgs", configSettings.getString("confShowWebImgs", "true"));
  theConfBundle.putInt("confBrowZoom", configSettings.getInt("confBrowZoom", 100));
  theConfBundle.putInt("quid", configSettings.getInt("quid", 0));
  theConfBundle.putString("cartID", configSettings.getString("cartID", "abc123"));
  theConfBundle.putString("usrlang", configSettings.getString("usrlang", "en_us"));
  theConfBundle.putString("prfsSHOPuser", configSettings.getString("prfsSHOPuser", "x1prfDspLmenux4falsex5scvx2gx6"));
  theConfBundle.putInt("confScreenOrient", configSettings.getInt("confScreenOrient", 1));
  return theConfBundle;
 }

// generic get and put functions to edit name/value pairs in getConfBundle()
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
   // showDaToast("getConfValInt: " + theKey + " : " + strTheKey);
  } catch (Exception err) {
   System.out.println("Error.getConValInt: " + err);
  }
  return strTheKey;
 }

 public void putConfValString(String theKey, String theVal) {
  configEditor = configSettings.edit();
  configEditor.putString(theKey, theVal);
  configEditor.commit();
  currConfBundle = getConfBundle();
  if (theKey.equals("confShowWebImgs")) {
   mWebView.getSettings().setLoadsImagesAutomatically(Boolean.parseBoolean(theVal));
  }
  showDaToast(strSettingsSaved + ": " + theKey + " : " + theVal);
 }

 public void putConfValInt(String theKey, Integer theVal) {
  try {
   configEditor = configSettings.edit();
   configEditor.putInt(theKey, theVal);
   configEditor.commit();
   currConfBundle = getConfBundle();
   showDaToast(strSettingsSaved + " putConfValInt: " + theKey + " : " + theVal);
  } catch (Exception e) {
   System.out.println("Error.putConfValInt: " + e);
  }

 }





// onActivityResult functions for  mostly everything.
// need get back to this. mostly used for media-fetching and editing, and speech functions.
 @Override
 protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
  switch (requestCode) {
   case (2):
    if (resultCode == Activity.RESULT_OK) {
     System.out.println("Activity.RESULT_OK:  ");
     Uri contactData = data.getData();
     String fullNums = "";
     Cursor c = managedQuery(contactData, null, null, null, null);
     if (c.moveToFirst()) {
      String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
      System.out.println("c.moveToFirst() name:  " + name);
      // TODO Whatever you want to do with the selected contact
      // name.
      String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
      System.out.println("Contacts.id:  " + id);
      // currSpkMatches

      long nowLong = System.currentTimeMillis() / 1000;
      String datestring = Long.toString(nowLong);
      String sqs = "";
      String strRet = "";
      if (isLastTry == true) {
       isLastTry = false;
       sqs = "insert into qextras (e_rtype,e_uid,e_vala,e_valb,e_valc,e_vald,e_vale,e_valf,e_dadded) values (1001,5,'" + id + "','" + name + "','" + currSpkLChoice + "','nada','nada','nada','" + datestring + "')";
       strRet = dbMSQLA.setDBselectQ(sqs);
      }
      sqs = "insert into qextras (e_rtype,e_uid,e_vala,e_valb,e_valc,e_vald,e_vale,e_valf,e_dadded) values (1001,5,'" + id + "','" + name + "','" + currSpkChoice + "','nada','nada','nada','" + datestring + "')";
      strRet = dbMSQLA.setDBselectQ(sqs);
      // showDaToast(strRet);
      System.out.println("onActivityRes.2.currContactStr :  " + currContactStr);
      showinformation(id, currContactStr);
     }
    }
    break;
   case (SHARE_ACTIVITY_RES):
    currFileName = getShareImgName();
    String tmpSSstrn = getShareImgName() + ".jpeg";
    if ((currFileName.indexOf(".gif") != -1) || (currFileName.indexOf(".mp4") != -1)) {
     mWebView.loadUrl("javascript:appFnshImgUload('" + getShareImgName() + "','" + getThmbStr(getShareImgName()) + "');");
     AnimMovSingleton tmpAMS = AnimMovSingleton.getInstance();
     if (tmpAMS != null) {
      mWebView.loadUrl("javascript:JSSHOP.logJSdbug('onActivityResult', 'nada', 'sum: " + tmpAMS.getMamsArrAFS().size() + "');");
     }
    } else {
     try {
      byte[] sdrbset = getShareImgBytes();
      JSONArray aresultSet = jarrFileSaved(sdrbset, currFileName);
      mWebView.loadUrl("javascript:appFnshImgUload('" + currFileName + "','" + aresultSet.get(1) + "');");
     } catch (Exception e) {
      System.out.println("Cutouts.onActivityResult.error : " + e.toString());
      e.printStackTrace();
     }
    }
    System.out.println("Cutouts.onActivityResult:  " + currFileName + " :: " + getShareImgName() + " :: " + tmpSSstrn);
    break;
   case (SHARE_SOCIAL_RES):
    currFileName = getShareImgName();
    if ((currFileName.indexOf(".gif") != -1) || (currFileName.indexOf(".mp4") != -1)) {
     File ffile = new File(mediaStorageDir, getShareImgName());
     Uri daUri = getImageContentUri(getApplicationContext(), ffile);
     mUploadMessage.onReceiveValue(daUri);
     mUploadMessage = null;
    } else {
     try {
      byte[] sdrbset = getShareImgBytes();
      JSONArray aresultSet = jarrFileSaved(sdrbset, currFileName);
      File ffile = new File(mediaStorageDir, currFileName);
      Uri daUri = getImageContentUri(getApplicationContext(), ffile);
      mUploadMessage.onReceiveValue(daUri);
      mUploadMessage = null;
      //  mWebView.loadUrl("javascript:appFnshImgUload('" + currFileName + "','" + aresultSet.get(1) + "');");
     } catch (Exception e) {
      System.out.println("Cutouts.onActivityResult.error : " + e.toString());
      e.printStackTrace();
     }
    }
    System.out.println("Cutouts.onActivityResult:  " + currFileName + " :: " + getShareImgName());
    break;
   case (110):
    if (resultCode == Activity.RESULT_OK) {
     try {
      Bundle aextras = data.getExtras();
      System.out.println("ArtPadRequest: aextras not null: ");
      if (aextras.containsKey("encdBmp")) {
       byte[] decodedString = Base64.decode(aextras.getString("encdBmp"));
       JSONArray aresultSet = jarrFileSaved(decodedString, currFileName);
       showDaToast("aresultSet: " + aresultSet.get(0));
       mWebView.loadUrl("javascript:appFnshImgUload('" + currFileName + "','" + aresultSet.get(1) + "');");
      }
     } catch (Exception e) {
      System.out.println("ArtPadRequest: " + e.toString());
      e.printStackTrace();
     }
    } else {
     // canceled
    }
    break;
   case (111):
    if (resultCode == Activity.RESULT_OK) {
     try {
      Bundle aextras = data.getExtras();
      System.out.println("onActitivyResult[111] OK");
      if (aextras.containsKey("image-path")) {
       String tmpUstr = aextras.getString("image-path");
       Uri daUri = Uri.parse(tmpUstr);
       System.out.println("onActitivyResult[111] image-path: " + tmpUstr);
       // this needs to be fixd. app returning file to app from html file selector.
       // or app returning file to another site html selector
       if (mWebView.getUrl().contains("file:")) {
        mWebView.loadUrl("javascript:appFnshImgUload('" + currFileName + "','" + tmpUstr + "');");
       } else {
        mUploadMessage.onReceiveValue(daUri);
        mUploadMessage = null;
       }
      }
     } catch (Exception e) {
      System.out.println("onActitivyResult[111] error: " + e.toString());
      e.printStackTrace();
     }
    } else {
     mUploadMessage.onReceiveValue(null);
     mUploadMessage = null;
    }
    break;
   case (11):
    if (resultCode == Activity.RESULT_OK) {
     try {
      String strRecValC = data.getStringExtra("encdBmp").toString();
      System.out.println("onActivityResult: " + strRecValC);
      currAQRstring = strRecValC;
      // mWebView.loadlclUrl("javascript:transRes('" + strRecValC + "');");
      // doLoadLclUrl("quickorder/show.php");
      mWebView.loadUrl("javascript:alert('" + currAQRstring + "');");
     } catch (Exception e) {
      System.out.println("onActivityResult: " + e.toString());
     }
    } else {
     // CANCELLED
    }
    break;
   case (FILECHOOSER_RESULTCODE):
    if (null == mUploadMessage)
     return;
    Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
    mUploadMessage.onReceiveValue(result);
    mUploadMessage = null;
    break;
   case VOICE_RECOGNITION_REQUEST_CODE:
    if (resultCode == RESULT_OK) {
     // Fill the list view with the strings the recognizer thought it could have heard
     ArrayList < String > matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
     // showDaToast(matches.toString());
     // startSpeechCopy(matches.get(0));
     //  mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, matches));
    }
    break;
   default:
    {
     System.out.println("Its default");
    }
  }
 }



 public String getCurrPageVars(String tmpStrQstr) {
  return currPageVars;
 }
 public void setWVScrollTop() {
  this.runOnUiThread(new Runnable() {
   public void run() {
    // mScrollView.scrollTo(0,0);
   }
  });
 }
 public void setWVScrollPoint(int tmpscx, int tmpscy) {
  final int thescx = tmpscx;
  final int thescy = tmpscy;
  this.runOnUiThread(new Runnable() {
   public void run() {
    mScrollView.scrollTo(thescx, thescy);
   }
  });
 }


 public static String getLocalUrl(String strLUrl) {
  return "file:///android_asset/" + strLUrl;
 }

 public void doLoadLclUrl(final String theFnlLclUstr) {
  try {
   this.runOnUiThread(new Runnable() {
    public void run() {
     String fullUrl = "file:///android_asset/" + theFnlLclUstr;
     mWebView.loadUrl(fullUrl);
    }
   });
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("doLoadLclUrl" + e.toString());
  }
 }

 public void doWaitDialog() {
  dialog = ProgressDialog.show(qBits.this, "", "Pf Aguarde..", true, true);
 }

 public void doPageReload() {
  mWebView.reload();
 }


 private class DefWViewClient extends WebViewClient {
  @Override
  public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
   Toast.makeText(getApplicationContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
  }
  @Override
  public void onPageStarted(WebView view, String url, Bitmap favicon) {
   if (url.indexOf("http") != -1) {
    // autoCompleteTextView.setText(url);
   }
   System.out.print("onPageStarted1: " + url);
   // TODO Auto-generated method stub
   // Toast.makeText(mContext, "page started: " + url, Toast.LENGTH_LONG).show();
   CookieSyncManager.getInstance().sync();
   //  super.onPageStarted(view, url, favicon);
   if (url.startsWith("file://") && url.contains("?")) {
    System.out.print("onPageStarted:oride " + url);
    String[] temp;
    temp = url.split(Pattern.quote("?"));
    // showDaToast("is one: " + temp[1]);
    // epMainHbook.setCurrPageVars(temp[1]);
    currPageVars = temp[1];
   }

  }
  @Override
  public void onReceivedHttpAuthRequest(android.webkit.WebView view, android.webkit.HttpAuthHandler handler, java.lang.String host, java.lang.String realm) {
   // TODO Auto-generated method stub
   // Toast.makeText(mContext, "page started: " + url, Toast.LENGTH_LONG).show();
   CookieSyncManager.getInstance().sync();
   System.out.print("onReceivedHttpAuthRequest: " + host);
   //    super.onReceivedHttpAuthRequest(view, handler, host, realm);
  }
  @Override
  public void onPageFinished(WebView view, String url) {
   //  showDaUrlToast(url);
   //  Toast.makeText(mContext, "page finished: " + url, Toast.LENGTH_LONG).show();
   // TODO Auto-generated method stub
   System.out.print("onPageFinished: " + url);
   CookieSyncManager.getInstance().sync();
   mWebView.setAddressUrl(url);
   // super.onPageFinished(view, url);
   // pumpToUrlString();
  }
  @Override
  public boolean shouldOverrideUrlLoading(WebView view, String url) {
   currPageVars = "noQvalue";
   if (url.startsWith("file://") && url.contains("?")) {
    System.out.print("shouldOverrideUrlLoading:oride " + url);
    String[] temp;
    temp = url.split(Pattern.quote("?"));
    // showDaToast("is one: " + temp[1]);
    // epMainHbook.setCurrPageVars(temp[1]);
    currPageVars = temp[1];
    mWebView.loadUrl(url);
    return true;
   } else if (url.contains(".mp4")) {
    setPlayMPF(url);
    return true;
   } else {
    // extraHeaders dont work
    // mWebView.loadUrl(url, extraHeaders);
    return false;
   }
  }

  public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError errer) {
   System.out.print("onReceivedSslError: " + errer);
   handler.proceed();
  }
 }






 private class qBChromeClient extends WebChromeClient {

  private Bitmap mDefaultVideoPoster;
  private View mVideoProgressView;
  long nowLong = System.currentTimeMillis() / 1000;
  String datestring = Long.toString(nowLong);

  @Override
  public void onConsoleMessage(String message, int lineNumber, String sourceID) {
   System.out.println("onConsoleMessage: " + message + " -- From line " + String.valueOf(lineNumber) + " of " + sourceID);
   super.onConsoleMessage(message, lineNumber, sourceID);
  }
  @Override
  public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
   System.out.println("+-------------------------------");
   System.out.println("|WebChromeClient onJsAlert	" + message);
   System.out.println("+-------------------------------");
   result.confirm();
   showDaToast("Alert... " + message);
   return true;
  }


  @Override
  public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
   System.out.println("onShowCstmView: ");
   mWebView.setVisibility(View.GONE);
   // if a view already exists then immediately terminate the new one
   if (mCustomView != null) {
    callback.onCustomViewHidden();
    return;
   }
   mCustomViewContainer.addView(view);
   mCustomView = view;
   mCustomViewCallback = callback;
   mCustomViewContainer.setVisibility(View.VISIBLE);
  }

  @Override
  public void onHideCustomView() {
   System.out.println("onHideCustomView");
   if (mCustomView == null)
    return;
   // Hide the custom view.
   mCustomView.setVisibility(View.GONE);
   // Remove the custom view from its container.
   mCustomViewContainer.removeView(mCustomView);
   mCustomView = null;
   mCustomViewContainer.setVisibility(View.GONE);
   mCustomViewCallback.onCustomViewHidden();
   mWebView.setVisibility(View.VISIBLE);
   mWebView.goBack();
   //Log.i(LOGTAG, "set it to webVew");
  }


  @Override
  public View getVideoLoadingProgressView() {
   //Log.i(LOGTAG, "here in on getVideoLoadingPregressView");
   System.out.println("getVideoLoadingProgressView: ");
   if (mVideoProgressView == null) {
    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
    mVideoProgressView = inflater.inflate(R.layout.com_njfsoft_utils_utilwebview_video_loading_progress, null);
   }
   return mVideoProgressView;
  }


  @Override
  public void onReceivedTitle(WebView view, String title) {
   qBits.this.setTitle(title);
  }

  @Override
  public void onProgressChanged(WebView view, int newProgress) {
   qBits.this.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress * 100);
  }




  // inlined file-chooser dialog
  public void openFileChooser(ValueCallback < Uri > uploadMsg) {
   mUploadMessage = uploadMsg;
   // doImgGet(40, datestring + ".jpeg", "noQvalue");
   setPagePopUp("quickorder/media_chooser.html", "noQvalue");
   // Intent i = new Intent(Intent.ACTION_GET_CONTENT);  
   // i.addCategory(Intent.CATEGORY_OPENABLE);  
   // i.setType("*/*");  
   // qBits.this.startActivityForResult(Intent.createChooser(i,"File Chooser"), FILECHOOSER_RESULTCODE);  
  }

  // For Android 3.0+
  public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
   mUploadMessage = uploadMsg;
   //doImgGet(40, datestring + ".jpeg", "noQvalue");
   setPagePopUp("quickorder/media_chooser.html", "noQvalue");
   // qBits.this.startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
  }

  //For Android 4.1
  public void openFileChooser(ValueCallback < Uri > uploadMsg, String acceptType, String capture) {
   mUploadMessage = uploadMsg;
   // doImgGet(40, datestring + ".jpeg", "noQvalue");
   setPagePopUp("quickorder/media_chooser.html", "noQvalue");
   // qBits.this.startActivityForResult( Intent.createChooser( i, "File Chooser" ), qBits.FILECHOOSER_RESULTCODE );
  }
 }

 


// opens up a Dialog with WebView  pop-up [com.njfsoft_utils.webviewutil.UtilWebDialog]
 public void setPagePopUp(String fnlPageUrl, String fnlPageHtml) {
  currPopPageUrl = fnlPageUrl;
  currPopPageHtml = fnlPageHtml;
  try {
   this.runOnUiThread(new Runnable() {
    public void run() {
     utilWDialog.setPopPage(currPopPageUrl, currPopPageHtml);
    }
   });
  } catch (Exception e) {
   System.out.println("doStoryShare dialog" + e.toString());
  }
 }


 // callback functions for the WebView Dialog PopUp
 public void doMDcom(final int idomdcom, final String strfnlDoMD) {
  try {
   this.runOnUiThread(new Runnable() {
    public void run() {
     final int theTestInt = idomdcom;
     utilWDialog.doHide();
     long nowLong = System.currentTimeMillis() / 1000;
     String datestring = Long.toString(nowLong);
     switch (theTestInt) {
      case 20:
       Intent pickAPhoto = new Intent(getApplicationContext(), com.njfsoft_utils.artpad.ArtPad.class);
       pickAPhoto.putExtra("apmode", "apmodeGallery");
       startActivityForResult(pickAPhoto, 110);
      case 25:
       Intent pictureBgIntent = new Intent(getApplicationContext(), com.njfsoft_utils.artpad.ArtPad.class);
       pictureBgIntent.putExtra("apmode", "apmodeCamera");
       startActivityForResult(pictureBgIntent, 110);
       break;
      case 101:
       Intent pickBgPhoto = new Intent(getApplicationContext(), com.njfsoft_utils.artpad.ArtPad.class);
       pickBgPhoto.putExtra("apmode", "apmodeGallery");
       startActivityForResult(pickBgPhoto, 111);
       break;
      case 102:
       doImgGet(40, datestring + ".jpeg", "noQvalue");
       break;
      case 103:
       doImgGet(41, datestring + ".jpeg", "noQvalue");
       break;
      case 125:
       doCopyTxtFrmSpch(strfnlDoMD);
       // startSpeechCopy(strfnlDoMD);		
       break;
      case 130:
       startSpeechCopy(strfnlDoMD);
       // startSpeechCopy(strfnlDoMD);		
       break;
     }
    }
   });
  } catch (Exception e) {
   System.out.println("dev:ERROR:doTest:" + e.toString());
   e.printStackTrace();
  }
 }



// prepares the UtilWebDialog popups and loads the page url or data as html
// includes the utilWDListener interface for callbacks
 public void preparePagePopUps(String pageUrl, String pageHtml) {
  String fullUrl = "file:///android_asset/" + pageUrl;
  String newHTML = "";
  UtilWebDialog.UtilWDListener utilWDListener = new UtilWebDialog.UtilWDListener() {
   public void epMDcom(int cbType, String cbArgs, UtilWebDialog epmd) {
    final String fnlCbArgs;
    doMDcom(cbType, cbArgs);
    System.out.println("dev:SmsCanvas:epMainDListener: " + cbType + " : " + cbArgs);
   }
  };
  utilWDialog = new UtilWebDialog(this, fullUrl, utilWDListener);
  utilWDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
 }










// to move. speach functions.
// this is just a mock up of using a simple voice activated assistant.
// for instance, clicking the top menu microphone button, wait for beep, then say "help"
// will load up the help pop-up page (inacurate and uncomplete").
// the goal is to add speech shortcuts to add and edit web-shop items. search, and other commands.
// some simple logic has been added like recording command errors to database
// and user actual intention for that error.

 public void getSpeechToText() {
  try {
   this.runOnUiThread(new Runnable() {
    public void run() {
     if (isSpeaking.equals("no")) {
      isSpeaking = "yes";
      recognizerIntent.putExtra(RecognizerIntent.EXTRA_ORIGIN, "... " + currSpkChoice);
      speech.startListening(recognizerIntent);
     } else {
      isSpeaking = "no";
      speech.stopListening();
     }
    }
   });
  } catch (Exception e) {
   System.out.println("getSpeechToText.error: " + e.toString());
  }
 }



 public void startSpeechCopy(String ctext) {
  System.out.println("startSpeechCopy: " + ctext);
  String strTLwr = ctext.toLowerCase();
  // some assistante names like alexa. these names seem to work, but you can change it or delete it. 
  if (strTLwr.startsWith("ronda") || strTLwr.startsWith("rhonda") || strTLwr.startsWith("rita")) {
   if (ctext.indexOf(" ") != -1) {
    String newCCtext = ctext.substring(ctext.indexOf(" ") + 1, ctext.length());
    doSpkComms(newCCtext);
   } else {
    tryAgain("what");
    // cctext = ctext;
   }
  } else {
   if (useLastCmd == true) {
    useLastCmd = false;
    doCurrSpkComm(currCmdInt, ctext);
   } else {
    doSpkComms(ctext);
   }
   // showDaToast(ctext);
  }
 }



 public void getOldSpeechToText() {

  try {
   this.runOnUiThread(new Runnable() {
    public void run() {
     Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
     // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt_PT");
     // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
     intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say It");
     // intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
     startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }
   });

  } catch (Exception e) {
   System.out.println("getSpeechToText: " + e.toString());
  }

 }





 public void shareFromPage(final String fnlPageUrl) {
  try {
   this.runOnUiThread(new Runnable() {
    public void run() {
     mWebView.loadUrl(fnlPageUrl + currShareUrl);
    }
   });
  } catch (Exception e) {
   System.out.println("shareFromPage.error: " + e.toString());
  }

 }

 public void shareFromClipBoard(final String fnlClipUrl) {
  try {
   this.runOnUiThread(new Runnable() {
    public void run() {
     ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
     if (!(clipboard.hasPrimaryClip())) {
      showDaToast("No clipboard url to share");
      // mWebView.loadUrl(fnlPageUrl + mWebView.getAddressUrl());
     } else {
      ClipData.Item cditem = clipboard.getPrimaryClip().getItemAt(0);
      CharSequence commonUrl = cditem.coerceToText(getApplicationContext());
      mWebView.loadUrl(fnlClipUrl + commonUrl.toString());
     }
    }
   });
  } catch (Exception e) {
   System.out.println("shareFromClipBoard.error: " + e.toString());
  }
 }



 public void doFix(String cctext) {
  if (cctext.indexOf(" ") != -1) {
   String tcctxt = cctext.substring(cctext.indexOf(" ") + 1, cctext.length());
   String sssttxt = tcctxt.toLowerCase();
   if (sssttxt.contains("media")) {
    String theVal = currConfBundle.getString("confShowWebImgs");
    String theFVal = "false";
    if (theVal.equals("false")) {
     theFVal = "true";
    }
    configEditor = configSettings.edit();
    configEditor.putString("confShowWebImgs", theFVal);
    configEditor.commit();
    currConfBundle = getConfBundle();

    final String theFixFSval = theFVal;
    mWebView.post(new Runnable() {
     @Override
     public void run() {
      mWebView.getSettings().setLoadsImagesAutomatically(Boolean.parseBoolean(theFixFSval));
     }
    });


   } else if (sssttxt.contains("font")) {
    if (cctext.contains("bigger") || cctext.contains("maior")) {
     //mWebView.loadUrl("javascript:(document.body.style.fontSize ='20pt');");
     mWebView.post(new Runnable() {
      @Override
      public void run() {
       mWebView.getSettings().setTextZoom(mWebView.getSettings().getTextZoom() + 10);
       configEditor = configSettings.edit();
       configEditor.putInt("confBrowZoom", mWebView.getSettings().getTextZoom() + 10);
       configEditor.commit();
       System.out.println("startSpeechCopy: wbsettings..getTextZoom: " + mWebView.getSettings().getTextZoom());
       //  mWebView.getSettings().setDefaultFontSize(mWebView.getSettings().getDefaultFontSize() + 2);
      }
     });

    } else {
     // mWebView.loadUrl("javascript:(document.body.style.fontSize ='12pt');");
     mWebView.post(new Runnable() {
      @Override
      public void run() {
       mWebView.getSettings().setTextZoom(mWebView.getSettings().getTextZoom() - 10);
       System.out.println("startSpeechCopy: wbsettings..getTextZoom: " + mWebView.getSettings().getTextZoom());
       configEditor = configSettings.edit();
       configEditor.putInt("confBrowZoom", mWebView.getSettings().getTextZoom() - 10);
       configEditor.commit();
       // mWebView.getSettings().setDefaultFontSize(mWebView.getSettings().getDefaultFontSize() - 2);
      }
     });
    }
    currConfBundle = getConfBundle();
   }
   // System.out.println("startSpeechCopy: wbsettings.getDefaultFontSize: " + mWebView.getSettings().getDefaultFontSize());
  }
 }





 public void doCurrSpkComm(int tmpICSC, String tStrSpk) {
  System.out.println("doCurrSpkComm: tmpICSC: " + tmpICSC + "  ::  tStrSpk: " + tStrSpk);

  String tcctxt = "";
  String ttcctxt = "";
  String bstr = "";
  String sssttxt = "";
  long nowLong = System.currentTimeMillis() / 1000;
  String datestring = Long.toString(nowLong);
  String commonUrl = mWebView.getAddressUrl();
  String strUshare = "http://www.facebook.com/sharer.php?u=";

  switch (tmpICSC) {
   case SPK_CMND_SEARCH:
    tcctxt = "http://www.google.com/search?q=" + tStrSpk;
    mWebView.loadUrl(tcctxt);
    break;

   case SPK_CMND_BUSCA:
    tcctxt = "http://www.google.com/search?q=" + tStrSpk;
    mWebView.loadUrl(tcctxt);
    break;

   case SPK_CMND_PROCURA:
    tcctxt = "http://www.google.com/search?q=" + tStrSpk;
    mWebView.loadUrl(tcctxt);
    break;

   case SPK_CMND_FAVORITE:
    if (tStrSpk.indexOf(" ") != -1) {
     tcctxt = tStrSpk;
     if (tcctxt.startsWith("save") || tcctxt.startsWith("guardar")) {
      ttcctxt = tcctxt.substring(tcctxt.indexOf(" ") + 1, tcctxt.length());
      String sqs = "insert into qextras (e_rtype,e_uid,e_vala,e_valb,e_valc,e_vald,e_vale,e_valf,e_dadded) values (1100,5,'" + mWebView.getAddressUrl() + "','" + qBits.this.getTitle() + "','" + ttcctxt + "','nada','nada','nada','" + datestring + "')";
      String strRet = dbMSQLA.setDBselectQ(sqs);
      showDaToast(strRet);
     } else {
      getFavorite(tcctxt);
     }
    } else {
     getFavorite(tStrSpk);
    }
    break;

   case SPK_CMND_FAVORITO:
    if (tStrSpk.indexOf(" ") != -1) {
     tcctxt = tStrSpk;
     if (tcctxt.startsWith("save") || tcctxt.startsWith("guardar")) {
      ttcctxt = tcctxt.substring(tcctxt.indexOf(" ") + 1, tcctxt.length());
      String sqs = "insert into qextras (e_rtype,e_uid,e_vala,e_valb,e_valc,e_vald,e_vale,e_valf,e_dadded) values (1100,5,'" + mWebView.getAddressUrl() + "','" + qBits.this.getTitle() + "','" + ttcctxt + "','nada','nada','nada','" + datestring + "')";
      String strRet = dbMSQLA.setDBselectQ(sqs);
      // showDaToast(strRet);
     } else {
      getFavorite(tcctxt);
     }
    } else {
     getFavorite(tStrSpk);
    }
    break;

   case SPK_CMND_ALARM:
    break;

   case SPK_CMND_ALARME:
    break;

   case SPK_CMND_TEST:
    bstr = "<html><head></head><body>";
    bstr += "<div name=\"dvSpeech\"  id=\"dvSpeech\"  style=\"padding: 10px; border: 1px solid #dedede;min-height:60px;\" contenteditable=\"true\">";
    bstr += tStrSpk;
    bstr += "</div>";
    bstr += "<br><a href=\"javascript:app_dlg.getEpMDcom('125',dvSpeech.innerHTML);\">Done</a>";
    bstr += "</body></html>";
    setPagePopUp("quickorder/x_speech_editor.html", bstr);
    break;

   case SPK_CMND_TESTE:
    bstr = "<html><head></head><body>";
    bstr += "<div name=\"dvSpeech\"  id=\"dvSpeech\"  style=\"padding: 10px; border: 1px solid #dedede;min-height:60px;\" contenteditable=\"true\">";
    bstr += tStrSpk;
    bstr += "</div>";
    bstr += "<br><a href=\"javascript:app_dlg.getEpMDcom('125',dvSpeech.innerHTML);\">Done</a>";
    bstr += "</body></html>";
    setPagePopUp("quickorder/x_speech_editor.html", bstr);
    break;

   case SPK_CMND_SHOP:
    break;

   case SPK_CMND_LOJA:
    break;

   case SPK_CMND_CALL:

    getPInfo(tStrSpk, "call");
    break;

   case SPK_CMND_CHAMA:
    getPInfo(tStrSpk, "chama");
    break;

   case SPK_CMND_FIX:
    doFix(tStrSpk);
    break;

   case SPK_CMND_FINO:
    doFix(tStrSpk);
    break;


   case SPK_CMND_SMS:
    getPInfo(tStrSpk, tStrSpk);
    break;

   case SPK_CMND_WHATSAPP:
    getPInfo(tStrSpk, tStrSpk);
    break;

   case SPK_CMND_CALCULATE:
    break;

   case SPK_CMND_CALCULA:
    break;

   case SPK_CMND_READ:
    break;

   case SPK_CMND_BOUNCE:
    if (tStrSpk.indexOf(" ") != -1) {}
    tcctxt = tStrSpk;
    sssttxt = tcctxt.toLowerCase();
    if (sssttxt.contains("facebook")) {
     strUshare = "http://www.facebook.com/sharer.php?u=";
    }
    if (sssttxt.contains("twitter")) {
     String twtcctxt = sssttxt.substring(sssttxt.indexOf("twitter") + 8, sssttxt.length());
     strUshare = "http://www.twitter.com/home?status=" + twtcctxt + " ";
    }
    if (sssttxt.contains("linkedin")) {
     strUshare = "http://www.linkedin.com/shareArticle?mini=true&url=";
    }
    if (sssttxt.contains("google")) {
     strUshare = "https://plus.google.com/share?url=";
    }

    if (tStrSpk.contains("clip")) {
     shareFromClipBoard(strUshare);
    } else {
     mWebView.loadUrl(strUshare + commonUrl);
    }

    break;

   case SPK_CMND_LEVA:
    if (tStrSpk.indexOf(" ") != -1) {}
    tcctxt = tStrSpk;
    sssttxt = tcctxt.toLowerCase();
    if (sssttxt.contains("facebook")) {
     strUshare = "http://www.facebook.com/sharer.php?u=";
    }
    if (sssttxt.contains("twitter")) {
     String twtcctxt = sssttxt.substring(sssttxt.indexOf("twitter") + 8, sssttxt.length());
     strUshare = "http://www.twitter.com/home?status=" + twtcctxt + " ";
    }
    if (sssttxt.contains("linkedin")) {
     strUshare = "http://www.linkedin.com/shareArticle?mini=true&url=";
    }
    if (sssttxt.contains("google")) {
     strUshare = "https://plus.google.com/share?url=";
    }

    if (tStrSpk.contains("clip")) {
     shareFromClipBoard(strUshare);
    } else {
     mWebView.loadUrl(strUshare + commonUrl);
    }

    break;
   case SPK_CMND_HELP:
    setPagePopUp("quickorder/helpr.html", "noQvalue");
    break;
   case SPK_CMND_AJUDA:
    setPagePopUp("quickorder/helpr.html", "noQvalue");
    break;
   default:
    doSpeechEditor();

  }

 }


 public void tryAgain(String rtry) {
  // getSpeechToText();
  doSpeechOut(rtry + " ", "repeat");
 }

 public void doSpkComms(String tmpStrSpkn) {
  System.out.println("doSpkComms tmpStrSpkn: " + tmpStrSpkn);
  String tmpStrSpknCmnd = "noQvalue";
  String tmpStrCmnd = "noQvalue";
  String tmpStrpdCmd = "noQvalue";
  int iCmdInt = 999;
  if (tmpStrSpkn.indexOf(" ") != -1) {
   tmpStrpdCmd = tmpStrSpkn.substring(tmpStrSpkn.indexOf(" ") + 1, tmpStrSpkn.length());
   String[] strWstr = tmpStrSpkn.split(" ");
   tmpStrSpknCmnd = strWstr[0];
  } else {
   tmpStrSpknCmnd = tmpStrSpkn;
   tmpStrpdCmd = tmpStrSpkn;
  }
  tmpStrCmnd = tmpStrSpknCmnd.toLowerCase();
  int iMi = 0;
  while (iMi < currCommsArr.length) {
   System.out.println("doSpkComms currCommsArr: " + currCommsArr[iMi] + " " + iMi);

   if (tmpStrCmnd.equals(currCommsArr[iMi])) {
    System.out.println("doSpkComms tmpStrCmnd.equals currCommsArr: " + tmpStrCmnd);
    currCmdInt = iMi;
    iCmdInt = iMi;
    // doCurrSpkComm(iMi);	
    // tryAgain("more ");
    iMi = 30;
   }
   iMi++;
  }

  if (iCmdInt == 999) {
   System.out.println("doSpkComms less than 2: ");
   final String fnlStrSPkn = currSpkMatches;
   // instrumentation.sendStringSync(tmpStrSpkn);

   try {
    new Thread(new Runnable() {
     @Override
     public void run() {

      System.out.println("doSpkComms running instrumentionn on: " + fnlStrSPkn);

      doSpeechEditor();




      // instrumentation.sendStringSync(fnlStrSPkn);
     }
    }).start();
   } catch (Exception e) {
    System.out.println("doSpkComms.error: " + e.toString());
   }


  } else {
   doCurrSpkComm(iCmdInt, tmpStrpdCmd);
  }
 }







 public void showinformation(String id, String strCmnd) {
  String name = "nada";
  String phone = "nada";
  String email = "nada";
  Uri resultUri = ContactsContract.Contacts.CONTENT_URI;
  Cursor cont = getContentResolver().query(resultUri, null, null, null, null);
  String whereName = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?";

  String[] whereNameParams1 = new String[] {
   ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, id
  };
  Cursor nameCur1 = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams1, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
  while (nameCur1.moveToNext()) {
   name = nameCur1.getString(nameCur1.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME));
  }
  nameCur1.close();
  cont.close();
  nameCur1.close();

  String[] whereNameParams2 = new String[] {
   ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, id
  };
  Cursor nameCur2 = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams2, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
  while (nameCur2.moveToNext()) {
   phone = nameCur2.getString(nameCur2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
  }
  nameCur2.close();
  cont.close();
  nameCur2.close();

  String[] whereNameParams3 = new String[] {
   ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE, id
  };
  Cursor nameCur3 = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams3, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
  while (nameCur3.moveToNext()) {
   email = nameCur3.getString(nameCur3.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
  }
  nameCur3.close();
  cont.close();
  nameCur3.close();

  String[] whereNameParams4 = new String[] {
   ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE, id
  };
  Cursor nameCur4 = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams4, ContactsContract.CommonDataKinds.StructuredPostal.DATA);
  while (nameCur4.moveToNext()) {
   phone = nameCur4.getString(nameCur4.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.DATA));
  }
  nameCur4.close();
  cont.close();
  nameCur4.close();

  // showDaToast("Name= "+ name+"\nPhone= "+phone+"\nEmail= "+email);  


  if (currCmdInt == SPK_CMND_WHATSAPP) {

   String strFnalText = "";
   if (strCmnd.indexOf("message") != -1) {
    strFnalText = strCmnd.substring(strCmnd.indexOf("message") + 7, strCmnd.length());
   } else {
    if (strCmnd.indexOf("mensagem") != -1) {
     strFnalText = strCmnd.substring(strCmnd.indexOf("mensagem") + 8, strCmnd.length());
    }

   }
   String strNphone = phone;
   if (phone.startsWith("00")) {
    strNphone = phone.substring(2, phone.length());
   }
   sendWhatsApp(strNphone, strFnalText);
   return;
  }

  if (currCmdInt == SPK_CMND_SMS) {
   // if(strCmnd.toLowerCase().startsWith("sms")) {
   if (strCmnd.toLowerCase().contains("mensagem")) {
    doSpeechOut("o sms para " + name + " est? pronto");
   } else {
    doSpeechOut("sms for " + name + " is ready");
   }
   Intent smsIntent = new Intent(Intent.ACTION_VIEW);
   smsIntent.setType("vnd.android-dir/mms-sms");
   // smsIntent.setData(Uri.parse("sms:"));
   smsIntent.putExtra("address", phone);
   if (strCmnd.indexOf("message") != -1) {
    String tcctxt = strCmnd.substring(strCmnd.indexOf("message") + 7, strCmnd.length());
    smsIntent.putExtra("sms_body", tcctxt);
   } else {

    if (strCmnd.indexOf("mensagem") != -1) {
     String tcctxt = strCmnd.substring(strCmnd.indexOf("mensagem") + 8, strCmnd.length());
     smsIntent.putExtra("sms_body", tcctxt);
    }
   }
   startActivity(smsIntent);
  } else {
   if (currCmdInt == SPK_CMND_CHAMA) {
    doSpeechOut("a chamada para " + name + "  est? pronta");
   } else {
    doSpeechOut("the call to " + name + " is ready");
   }


   Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
   startActivity(intent);
  }

 }





 public void getFavorite(String name) {
  System.out.println("getFavorite: name: " + name);
  try {
   String sqs = "select * from qextras where e_valc = '" + name + "'";
   String strRet = "n";
   strRet = dbMSQLA.setDBselectQ(sqs);
   showDaToast("getFavorite.results: " + sqs + " :: " + strRet);
   if (strRet.indexOf("_id") != -1) {
    JSONArray array = new JSONArray(strRet);
    JSONObject row = array.getJSONObject(0);
    showDaToast("getFavorite has res: " + row.getString("_id"));
    mWebView.loadUrl(row.getString("e_vala"));
   } else {
    System.out.println("getFavorite: nada");
    doSpeechEditor();
   }
  } catch (Exception e) {
   doSpeechEditor();
   System.out.println("getFavorite: " + e);
   e.printStackTrace();
  }

 }



 public void tryPInfo(String name, String strTcmd) {
  try {
   String sqs = "select * from qextras where e_valc = '" + name + "'";
   String strRet = "n";
   strRet = dbMSQLA.setDBselectQ(sqs);
   showDaToast("tryPInfo.results: " + sqs + " :: " + strRet);
   if (strRet.indexOf("_id") != -1) {
    JSONArray array = new JSONArray(strRet);
    JSONObject row = array.getJSONObject(0);
    // showDaToast("tryPINFo has res: " + row.getString("_id"));
    showinformation(row.getString("e_vala"), strTcmd);
   } else {
    if (isLastTry == true) {
     if (strTcmd.startsWith("call") || strTcmd.contains("message")) {
      doSpeechOut("sorry, did not get the name");
     } else {
      doSpeechOut("desculpa, nao percebi o nome  ");
     }
     currContactStr = strTcmd;
     Intent intentContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
     startActivityForResult(intentContact, 2);

    } else {
     isLastTry = true;
     useLastCmd = true;
     tryAgain("who ");
    }
   }
  } catch (Exception e) {
   System.out.println("tryPINFO.error: " + e);
   e.printStackTrace();
  }
 }


 public void tryOldPInfo(String name, String strTcmd) {

  try {

   String sqs = "select * from qextras where e_valc =";
   String strRet = "n";

   if (currSpkMatches.indexOf(",") != -1) {
    String[] strSplitted = currSpkMatches.split(",");
    sqs += " '" + strSplitted[0] + "' or e_valc = '" + strSplitted[1] + "'";

   } else {
    sqs += " '" + currSpkMatches + "'";

   }
   strRet = dbMSQLA.setDBselectQ(sqs);
   showDaToast("tryPInfo.results: " + sqs + " :: " + strRet);

   if (strRet.indexOf("_id") != -1) {
    JSONArray array = new JSONArray(strRet);
    JSONObject row = array.getJSONObject(0);
    showDaToast("tryPINFo has res: " + row.getString("_id"));


   } else {


    if (strTcmd.startsWith("call") || strTcmd.contains("message")) {
     doSpeechOut("sorry, did not get the name.");
    } else {
     doSpeechOut("desculpa, nao percebi o nome  ");
    }
    currContactStr = strTcmd;
    Intent intentContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    startActivityForResult(intentContact, 2);
   }
  } catch (Exception e) {
   System.out.println("tryPINFO.error: " + e);
   e.printStackTrace();
  }

 }





 public void getPInfo(String name, String strTcmd) {
  String id_name = null;
  System.out.println("getPInfo.name: " + name);
  System.out.println("getPInfo.strTcmd: " + strTcmd);
  System.out.println("getPInfo.currCmdInt: " + currCmdInt);

  if ((currCmdInt == SPK_CMND_SMS) || (currCmdInt == SPK_CMND_WHATSAPP)) {
   System.out.println("getPInfo.SPK_CMND_SMS");
   if (strTcmd.indexOf("message") != -1) {
    name = strTcmd.substring(0, strTcmd.indexOf("message") - 1);
    currContactName = name;
   }
   if (strTcmd.indexOf("mensagem") != -1) {
    name = strTcmd.substring(0, strTcmd.indexOf("mensagem") - 1);
    currContactName = name;
   }
  }


  Uri resultUri = ContactsContract.Contacts.CONTENT_URI;
  Cursor cont = getContentResolver().query(resultUri, null, null, null, null);
  String whereName = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME + " = ?";
  String[] whereNameParams = new String[] {
   ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, name
  };
  Cursor nameCur = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME + " COLLATE NOCASE ASC");
  while (nameCur.moveToNext()) {
   id_name = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID));
  }
  nameCur.close();
  cont.close();
  nameCur.close();
  //for calling of following method
  // showDaToast("pinfo: " + id_name + " :: " + name +  " :: " + strTcmd);

  if (id_name != null) {
   showinformation(id_name, strTcmd);
  } else {

   tryPInfo(name, strTcmd);



  }
 }


 @Override
 public void onResults(Bundle results) {
  Log.i(LOG_TAG, "onResults");
  setTitle("onResults");
  isSpeaking = "no";
  int ial = 0;
  String strSCret = "";
  String tsm = "";
  ArrayList < String > matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
  // showDaToast(results.toString());


  while (ial < matches.size()) {
   System.out.println(matches.get(ial));
   tsm += matches.get(ial) + "|:|";
   ial++;
  }
  if (ial == 0) {
   // strSCret = "No results found:<br>" + tsm;
  } else if (ial == 1) {
   // One results found 
   String AstrSCret = tsm.substring(0, tsm.length() - 3);

   strSCret = AstrSCret;
  } else {
   // Various results found
   String BstrSCret = tsm.substring(0, tsm.length() - 3);
   strSCret = BstrSCret;
  }

  System.out.println("getSpeechToText.onResults: size: " + strSCret);

  currSpkLast = currSpkMatches;
  currSpkMatches = strSCret;
  currSpkLChoice = currSpkChoice;
  currSpkChoice = matches.get(0);


  // startSpeechCopy(strSCret);

  startSpeechCopy(currSpkChoice);
 }





 @Override
 public void onBeginningOfSpeech() {
  Log.i(LOG_TAG, "onBeginningOfSpeech");
  setTitle("onBeginningOfSpeech");

 }

 @Override
 public void onBufferReceived(byte[] buffer) {
  Log.i(LOG_TAG, "onBufferReceived: " + buffer);
  setTitle("onBufferReceived");
 }

 @Override
 public void onEndOfSpeech() {
  Log.i(LOG_TAG, "onEndOfSpeech");
  setTitle("onEndOfSpeech");

 }

 @Override
 public void onError(int errorCode) {
  String errorMessage = getErrorText(errorCode);
  Log.d(LOG_TAG, "FAILED " + errorMessage);
  setTitle("onError: " + errorMessage);
  isSpeaking = "no";
 }

 @Override
 public void onEvent(int arg0, Bundle arg1) {
  Log.i(LOG_TAG, "onEvent");
  setTitle("onEvent");
 }

 @Override
 public void onPartialResults(Bundle arg0) {
  Log.i(LOG_TAG, "onPartialResults");
  setTitle("onPartialResults");
 }

 @Override
 public void onReadyForSpeech(Bundle arg0) {
  Log.i(LOG_TAG, "onReadyForSpeech");
  setTitle("onReadyForSpeech");
 }



 @Override
 public void onRmsChanged(float rmsdB) {
  setTitle("onRmsChanged: " + rmsdB);
  Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
 }

 public static String getErrorText(int errorCode) {
  String message;
  switch (errorCode) {
   case SpeechRecognizer.ERROR_AUDIO:
    message = "Audio recording error";
    break;
   case SpeechRecognizer.ERROR_CLIENT:
    message = "Client side error";
    break;
   case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
    message = "Insufficient permissions";
    break;
   case SpeechRecognizer.ERROR_NETWORK:
    message = "Network error";
    break;
   case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
    message = "Network timeout";
    break;
   case SpeechRecognizer.ERROR_NO_MATCH:
    message = "No match";
    break;
   case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
    message = "RecognitionService busy";
    break;
   case SpeechRecognizer.ERROR_SERVER:
    message = "error from server";
    break;
   case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
    message = "No speech input";
    break;
   default:
    message = "Didn't understand, please try again.";
    break;
  }
  return message;
 }


 // Implements TextToSpeech.OnInitListener.
 public void onInit(int status) {
  // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
  if (status == TextToSpeech.SUCCESS) {


   mTts.setOnUtteranceCompletedListener(new OnUtteranceCompletedListener() {

    @Override
    public void onUtteranceCompleted(String utteranceId) {
     if (utteranceId.contains("repeat")) {
      try {
       getSpeechToText();
      } catch (Exception e) {
       System.out.println("onUtteranceError: " + utteranceId);
       e.printStackTrace();
      }
     } else {
      // showDaToast("onUtteranceCompleted: " + utteranceId);
     }
    }
   });
   /*
               // Set preferred language to US english.
               // Note that a language may not be available, and the result will indicate this.
               int result = mTts.setLanguage(Locale.US);
               // Try this someday for some interesting results.
               // int result mTts.setLanguage(Locale.FRANCE);
               if (result == TextToSpeech.LANG_MISSING_DATA ||
                   result == TextToSpeech.LANG_NOT_SUPPORTED) {
                  // Lanuage data is missing or the language is not supported.
                   Log.e(LOG_TAG, "Language is not available.");
               } else {
                   // Check the documentation for other possible result codes.
                   // For example, the language may be available for the locale,
                   // but not for the specified country and variant.

                   // The TTS engine has been successfully initialized.
                   // Allow the user to press the button for the app to speak again.
                   // mAgainButton.setEnabled(true);
                   // Greet the user.
                  //  sayHello();
               }
   */
  } else {
   // Initialization failed.
   Log.e(LOG_TAG, "Could not initialize TextToSpeech.");
  }
 }



 // KEY_PARAM_UTTERANCE_ID is used to prolong the silence period in speaking basically.
 private void doSpeechOut(String outstr, String utterID) {
  HashMap < String, String > map = new HashMap();
  map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, utterID);
  mTts.speak(outstr, TextToSpeech.QUEUE_FLUSH,  map);  // Drop all pending entries in the playback queue.
 }

 private void doSpeechOut(String outstr) {
  doSpeechOut(outstr, "nadayet");
 }


 public void doSpeechEditor() {
  String bstr = "<html><head>";
  bstr += "<script src=\"js/x_all.js\" language=\"javascript\" type=\"text/javascript\">";
  bstr += "</script>";
  bstr += "<script src=\"js/x_booter.js\" language=\"javascript\" type=\"text/javascript\">";
  bstr += "</script>";
  bstr += "<script src=\"js/x_speech_editor.js\" language=\"javascript\" type=\"text/javascript\">";
  bstr += "</script>";
  bstr += "<script language=\"javascript\" type=\"text/javascript\">";
  bstr += "function doTest(){ document.getElementById('dvSpeech').innerHTML = document.getElementById('txtSpeech').value; }";
  bstr += "</script>";
  bstr += "<link href=\"css/x_dev.css\" rel=\"stylesheet\">";
  bstr += "</head><body>";
  //   bstr += "</head><body onload=\"doTest();\">";
  bstr += "<div style=\"position: absolute; left: 1px; top: -477px;\"><textarea name=\"txtSpeech\"  id=\"txtSpeech\">" + currSpkMatches + "</textarea></div>";
  bstr += "<div name=\"dvMain\"  id=\"dvMain\"  style=\"min-height:60px;\">";
  bstr += "<div name=\"dvSpeech\"  id=\"dvSpeech\"  style=\"padding: 10px; border: 1px solid #dedede;min-height:60px;\" contenteditable=\"true\">";
  // bstr += ctext;
  bstr += "</div></div>";
  bstr += "<br><a href=\"javascript:alert(JSON.stringify(JSON.parse(eval(dvSpeech.innerHTML))));\">Done</a>";
  bstr += "<br><a href=\"javascript:doTest();\">Test</a>";
  bstr += "<br><a href=\"javascript:doAppTest();\">AppTest</a>";
  bstr += "</body></html>";
  setPagePopUp("baseurlquickorder/speech_editor.html", bstr);
 }


 public void doCopyTxtFrmSpch(String tmpStrDTFS) {
  final String fnlStrDTFS = tmpStrDTFS;
  try {
   new Thread(new Runnable() {
    @Override
    public void run() {
     System.out.println("doCopyTxtFrmSpch running instrumentionn on: " + fnlStrDTFS);
     instrumentation.sendStringSync(fnlStrDTFS);
    }
   }).start();
  } catch (Exception e) {
   System.out.println("doCopyTxtFrmSpch.error: " + e.toString());
  }
 }


 public void sendWhatsApp(String strNum, String strMsg) {
  try {
   PackageManager packageManager = getApplicationContext().getPackageManager();
   Intent i = new Intent(Intent.ACTION_VIEW);
   String toNumber = strNum; // contains spaces.
   toNumber = toNumber.replace("+", "").replace(" ", "");
   String url = "https://api.whatsapp.com/send?phone=" + toNumber + "&text=" + URLEncoder.encode(strMsg, "UTF-8");
   i.setPackage("com.whatsapp");
   i.setData(Uri.parse(url));
   i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   if (i.resolveActivity(packageManager) != null) {
    //  this.runOnUiThread(new Runnable() { public void run() {
    getApplicationContext().startActivity(i);
    // } });
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
  /*
  Intent sendIntent = new Intent("android.intent.action.MAIN");
  // sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
  sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
  sendIntent.putExtra(Intent.EXTRA_TEXT, "whats up?");
  sendIntent.setAction(Intent.ACTION_SEND);
  sendIntent.setPackage("com.whatsapp");
  // sendIntent.setType("image/png");
  startActivity(sendIntent);
  */
 }


// ---
// end of to move. speach functions











// to move. QR code functions
// taken from the QRcode reader project.
// database tables and such are invalid.
// some off the wal stuff is done with the QR reader.
// for example, launch the quickorder web bsaed site and add some items to a category.
// then click on the QR code button left or right menus.
// the return data is broken, but the general ideia was to see
// how much delimited data can be packed in a QR code and ways it can be shared.
 public String procsData(String strRecValT) {
  String retStr = "noQvalue";
  int innew = 0;
  int currInt = 0;

  try {
   String strRecValC = getCompdMsg(strRecValT);
   // showDaToast(strRecValC);
   if (strRecValC.contains("|")) {
    StringTokenizer astringtokenizer = new StringTokenizer(strRecValC, "|");
    int ai = astringtokenizer.countTokens();
    String[] strFA = dbMSQLA.getStrArr(strRecValC, "|");
    if (strFA[0].contains(";")) {
     String[] asHStr = strFA[0].split(";");
     String[] parseT;
     parseT = new String[] {
      asHStr[0], asHStr[1]
     };
     String fstr = "50";
     String getChallQ = "rec_type=? and rec_val_a=?";
     ArrayList < UtilDbRecord > lpDbRecord = dbMSQLA.getUtilDbRecords(getChallQ, parseT, "1");
     if (lpDbRecord.size() >= 1) {
      currInt = lpDbRecord.get(0).getKeyRecID();
      innew = lpDbRecord.get(0).getKeyRecType();
     } else {
      System.out.println("lpDbRecord.size() <= 0");
      dbMSQLA.openToWrite();
      currInt = dbMSQLA.doIntRecordAdd(Integer.parseInt(asHStr[0]), asHStr[1], asHStr[2], asHStr[3], asHStr[4]);
      innew = Integer.parseInt(asHStr[0]);
      dbMSQLA.openToWrite();
      for (int jj = 1; jj < ai; jj++) {
       if (strFA[jj].contains(";")) {
        String[] asStr = strFA[jj].split(";");
        if ((innew == 5) || (innew == 500)) { // folheto or recipe
         fstr = asStr[3] + "-" + asStr[1];
         asStr[0] = "50";
         asStr[1] = String.valueOf(currInt);
        } else {
         fstr = asStr[3];
        }
        dbMSQLA.insert(Integer.parseInt(asStr[0]), asStr[1], asStr[2], fstr, asStr[4]);
       }
      }
      dbMSQLA.close();
     } // else lpDbRecord.size() greater 0 
    } // if ad[0].contains ;
   } // strRecValC.contains |
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("procsData" + e.toString());
   return e.toString();
  }
  //  return String.valueOf(currInt);
  return String.valueOf(innew) + "','" + String.valueOf(currInt);

 }



 public void rinsert() {
  String d = "5;223;Folheto Campanha Abril;Produtos em campanha...;Dadded!h1;BACALHAU C/ AZEITE E ALHO RAMIREZ 120gr;2.79-1.99-1;2!h2;ATUM EM AZEITE RAMIREZ 120gr;1.59-0.79-1;1!h3;SARDINHAS EM TOMATE RAMIREZ 125gr;1.14-0.8!i!h4;MAIONESE HELLMAN\'S 412gr;2.59-1.68-1;3!h5;AÇUCAR BRAMCO RAR 1KG;0.99-0.79-1;3!h6;CHOCOLATE P/ CULINARIA NESTLÉ 200gr;1.69-1.26-1;2!h7;CAFÉ MOAGEM UNIVERSAL DELTA 250gr;2.66-1.74-1;1!h8;CAFÉ 5 ESTRELAS SICAL 1KG;10.49-7.86-1;2!h9;CEVADA DELTA Q PURE 10CAPSULAS;2.29-1.9!i!h10;CEREAIS ESTRELITAS SABOR BOLACHA MARIA 270gr;1.99-0.99-1;1!h11;CHOCOLATE P/ CULINARIA NESTLÉ 200gr;1.69-1.26-1;2";
  mWebView.loadUrl("javascript:getPageThread('" + procsData(d) + "');");
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

 public JSONArray jarrFileSaved(byte[] byte_arr, String theOutfile) {
  JSONArray resultSet;
  String encoded;
  String uString;
  resultSet = new JSONArray();
  encoded = "noQvalue";
  uString = "noQvalue";
  try {
   boolean fileCreated = false;
   String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
   File ffile = new File(mediaStorageDir, theOutfile);
   if (mediaStorageDir.exists()) {
    fileCreated = true;
    System.out.println("saveMov.exists: " + fileCreated);
   } else {
    mediaStorageDir.mkdirs();
   }

   fileCreated = ffile.createNewFile();
   if (fileCreated) {
    FileOutputStream os = new FileOutputStream(ffile, true);
    os.write(byte_arr);
    os.flush();
    os.close();
    System.out.println("boulFileSaved.created: " + fileCreated);

    Uri daUri = getImageContentUri(getApplicationContext(), ffile);
    uString = ffile.toString();
    long id = ContentUris.parseId(daUri);
    System.out.println("boulFileSaved.daUri: " + ffile.toString());

    byte[] byteArray = getTemporaryImage(daUri);
    if (byteArray != null) {
     encoded = Base64.encodeBytes(byteArray);
     System.out.println("boulFileSaved.exif-thumb: " + encoded);
    } else {
     Bitmap aabitmap = MediaStore.Images.Thumbnails.getThumbnail(
      getContentResolver(), id,
      3,
      (BitmapFactory.Options) null);
     if (aabitmap != null) {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      aabitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
      byte[] abyteArray = byteArrayOutputStream.toByteArray();
      encoded = Base64.encodeBytes(abyteArray);
      System.out.println("boulFileSaved.thumb: " + encoded);
     }
    }
   }
   resultSet.put(uString);
   resultSet.put(encoded);
   return resultSet;
  } catch (Exception e) {
   System.out.println("error saveMov: " + theOutfile + " : " + e.toString());
   e.printStackTrace();
   resultSet.put(uString);
   resultSet.put(e.toString());
   return resultSet;
  }
 }


 public boolean boulFileSaved(byte[] byte_arr, String theOutfile) {
  try {
   boolean fileCreated = false;
   String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
   File ffile = new File(mediaStorageDir, theOutfile);
   if (mediaStorageDir.exists()) {
    fileCreated = true;
    System.out.println("saveMov.exists: " + fileCreated);
   } else {
    mediaStorageDir.mkdirs();
   }
   fileCreated = ffile.createNewFile();
   if (fileCreated) {
    FileOutputStream os = new FileOutputStream(ffile, true);
    os.write(byte_arr);
    os.flush();
    os.close();
    System.out.println("boulFileSaved.created: " + fileCreated);
    Uri daUri = getImageContentUri(getApplicationContext(), ffile);
    long id = ContentUris.parseId(daUri);
    System.out.println("boulFileSaved.daUri: " + daUri.toString());
    return true;
   } else {
    return false;
   }
  } catch (Exception e) {
   System.out.println("error saveMov: " + theOutfile + " : " + e.toString());
   e.printStackTrace();
   return false;

  }
 }

 public void setPageThread(int ptype, int pid, String purl) {
  try {
   currStoryID = pid;
   currStoryType = ptype;
   if (ptype == 50) {
    setPagePopUp(purl, "noQvalue");
   } else {
    doLoadLclUrl(purl);
   }
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("setPageThread" + e.toString());

  }
 }

 public void getPageThread() {
  try {
   mWebView.loadUrl("javascript:getPageThread('" + currStoryType + "','" + currStoryID + "');");
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("setPageThread" + e.toString());
  }
 }
 public void getPopPageThread() {
  try {
   setPagePopUp("javascript:getPageThread('" + currStoryType + "','" + currStoryID + "');", "noQvalue");
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("setPageThread" + e.toString());
  }
 }


 public void runFormatSMS(String strRKey, String strMsgArgs, String strPhoneNums, String strMsg) {
  smsUtils.setFormatSMS("20", strMsgArgs, strPhoneNums, "noQvalue");
 }
 public void runSmsDetails() {
  smsUtils.getSmsDetails();
 }

 public void doRecInsert(int rt, String va, String vb, String vc, String da) {
  try {
   dbMSQLA.openToWrite();
   dbMSQLA.insert(rt, va, vb, vc, da);
   dbMSQLA.close();

  } catch (Exception e) {
   dbMSQLA.close();
   e.printStackTrace();
   System.out.println("doRecInsert" + e.toString());

  }
 }

 public void setOrderClean(String onum, String strOtitle, String strOttl, String strOtype) {
  try {
   String[] parseT;
   String chgstr = "1";
   parseT = new String[] {
    "2",
    "3",
    "50"
   };
   int oid = 5;
   String getChallQ = "rec_date_modified=? or rec_date_modified=? and rec_type=?";
   dbMSQLA.close();
   dbMSQLA.openToRead();
   ArrayList < UtilDbRecord > lpDbRecord = dbMSQLA.getUtilDbRecords(getChallQ, parseT, "300");
   dbMSQLA.close();
   if (lpDbRecord.size() <= 0) {
    System.out.println("doOrderClean count: IS NULL");
   } else {
    dbMSQLA.openToWrite();
    oid = dbMSQLA.doIntRecordAdd(80, onum, strOtitle, strOttl, strOtype);
    dbMSQLA.close();
    for (UtilDbRecord list: lpDbRecord) {
     String strRecID = String.valueOf(list.getKeyRecID());
     String strRecType = String.valueOf(list.getKeyRecType());
     String strRecValA = list.getKeyRecValA();
     String strRecValB = list.getKeyRecValB();
     String strRecValC = list.getKeyRecValC();
     String strRecDateAdded = list.getKeyRecDateAdded();
     String strRecDateModified = list.getKeyRecDateModified();
     System.out.println("flow:doSQLtest:record: " + strRecID + " :: " + strRecType + " :: " + strRecValA + " :: " + strRecValB + " :: " + strRecValC + " :: " + strRecDateAdded + " :: " + strRecDateModified);
     dbMSQLA.openToWrite();
     dbMSQLA.insert(81, String.valueOf(oid), strRecValB, strRecValC, strRecDateModified);
     dbMSQLA.close();
    }

    dbMSQLA.openToRead();
    dbMSQLA.openToWrite();
    dbMSQLA.doUpdateRecords("rec_date_modified=? and rec_type=?", "2:50", "rec_date_modified=;1");
    dbMSQLA.close();
    dbMSQLA.openToRead();
    dbMSQLA.openToWrite();
    dbMSQLA.doUpdateRecords("rec_date_modified=? and rec_type=?", "3:50", "rec_date_modified=;0");
    dbMSQLA.close();
    System.out.println("flow:setOrderClean:total: " + String.valueOf(lpDbRecord.size()));
    mWebView.loadUrl("javascript:setSentConf()");
   }
  } catch (Exception e) {
   dbMSQLA.close();
   e.printStackTrace();
   System.out.println("setOrderClean" + e.toString());

  }
 }

// to delete
 public String getCompdMsg(String strEdTxt) {
  String strPaa = strEdTxt.replace("!j", "-1;0");
  String strPab = strPaa.replace("!i", "9-1;0");
  String strPac = strPab.replace("!h", "|\n50;");
  String strPad = strPac.replace("!g", " 1lt;");
  String strPb = strPad.replace("!f", " 2kg;");
  String strPc = strPb.replace("!e", " 1kg;");
  String strPd = strPc.replace("!d", " de ");
  String strPe = strPd.replace("!c", " e ");
  String strPf = strPe.replace("!b", " o ");
  String strPg = strPf.replace("!a", " QuickOrder");
  String strPh = strPg.replace("!!", "<br>");
  return strPg;
 }




// --
// end of to move. QR functions







// to move. rewrite
// a bunch of share functions
// trying to use a share object, media, title, etc
 public void doStoryShare(String strNwork, String strSTitle, String strSDesc) {
  try {
   this.runOnUiThread(new Runnable() {
    public void run() {
     progressDialog = ProgressDialog.show(qBits.this, "", "Please wait...", true, true);
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
    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, null);
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
  ArrayList < NameValuePair > nameValuePairs = new ArrayList < NameValuePair > ();
  nameValuePairs.add(new BasicNameValuePair("do", "add"));
  nameValuePairs.add(new BasicNameValuePair("sttl", strStoryTitle));
  nameValuePairs.add(new BasicNameValuePair("sdesc", strStoryDesc));
  nameValuePairs.add(new BasicNameValuePair("sdesc", strStoryDesc));
  nameValuePairs.add(new BasicNameValuePair("stime", strCurrSID));
  nameValuePairs.add(new BasicNameValuePair("snwork", strSnwork));
  try {
  if (byte_arr != null) {
   String strImgID = Long.toString(currImgID);
   String image_str = Base64.encodeBytes(byte_arr);
   nameValuePairs.add(new BasicNameValuePair("simg", image_str));
   // System.out.println("image_str: " + image_str);
  }
   HttpClient httpclient = new DefaultHttpClient();
   HttpPost httppost = new HttpPost("http://www.mysite.com/story/index.php");
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
  if (contentLength < 0) {} else {
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
    inputStream.close(); // closing the stream
   } catch (IOException e) {
    e.printStackTrace();
   }
   res = buffer.toString(); // converting stringbuffer to string…..
  }
  return res;
 }

 public void doNewStoryID() {
  currImgID = System.currentTimeMillis() / 1000;
  oldImgID = currImgID;
  // currStoryID = currImgID;
  mWebView.reload();
 }


 public String getShareData() {
  return ShareDataResult.getInstance().getData();
 }
 public String getShareCallingApp() {
  return ShareDataResult.getInstance().getCallingApp();
 }
 public String getShareIHID() {
  return ShareDataResult.getInstance().getIHID();
 }
 public String getShareAPmode() {
  return ShareDataResult.getInstance().getAPmode();
 }
 public String getShareNworkID() {
  return ShareDataResult.getInstance().getNworkID();
 }
 public String getShareNwork() {
  return ShareDataResult.getInstance().getNwork();
 }
 public String getShareImgName() {
  return ShareDataResult.getInstance().getImgName();
 }
 public String getShareImgStr() {
  return ShareDataResult.getInstance().getImgStr();
 }
 public byte[] getShareImgBytes() {
  return ShareDataResult.getInstance().getImgBytes();
 }
 public String getShareTitle() {
  return ShareDataResult.getInstance().getTitle();
 }
 public String getShareCaption() {
  return ShareDataResult.getInstance().getCaption();
 }
 public String getShareDesc() {
  return ShareDataResult.getInstance().getDesc();
 }
 public String getShareMsg() {
  return ShareDataResult.getInstance().getMsg();
 }
// --
// end of to move. share functions







// to move. rewrite. media edit and assign functions    
// alot of garbage in the mix

 public void setCutOuts() {
  Intent toMain = new Intent(getApplicationContext(), CutOuts.class);
  startActivityForResult(toMain, SHARE_ACTIVITY_RES);
 }

 public byte[] getLocalImageAsBytes(Uri theUri) {
  ByteArrayOutputStream bais = new ByteArrayOutputStream();
  InputStream is = null;
  byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
  try {
   is = getApplicationContext().getContentResolver().openInputStream(theUri);

   int n;

   while ((n = is.read(byteChunk)) > 0) {
    bais.write(byteChunk, 0, n);
   }

  } catch (Exception e) {
   System.out.println("Failed while reading bytes from  url: " + e.getMessage());
   e.printStackTrace();
   // Perform any other exception handling that's appropriate.
   return byteChunk;
  } finally {
   try {
    is.close();
   } catch (Exception e) {

   }

  }

  return bais.toByteArray();
 }
 /*
  * the shared-data stuff
  */

 public void setPlayMPF(String myFile) {
  try {
   myFile = myFile.replace("lcl-", "");
   Uri myUri;

   if (myFile.startsWith("http")) {


    if (myFile.indexOf("src=") != -1) {
     String mnfile = URLDecoder.decode(myFile.substring(myFile.indexOf("src=") + 4, myFile.length()), "UTF-8");
     // URL url = new URL(myFile); //Some instantiated URL object
     // myUri = url.toURI();
     myFile = mnfile;
    }
    URL url = new URL(myFile);
    myUri = Uri.parse(url.toURI().toString());



   } else {
    File ffile = new File(mediaStorageDir, myFile);
    myUri = Uri.fromFile(ffile);
   }
   System.out.println("setPlayMPF: " + myFile + "    ::   " + myUri.toString());
   // showDaToast("setPlayMPF: " + myFile + "    ::   " + myUri.toString());
   Intent mIntent = new Intent(Intent.ACTION_VIEW, myUri);
   if (myFile.indexOf(".gif") != -1) {
    mIntent.setDataAndType(myUri, "image/gif");
   } else {
    mIntent.setDataAndType(myUri, "video/mp4");
   }
   startActivity(mIntent);
  } catch (Exception e) {
   showDaToast("MMOO: " + e.toString());
  }
 }

 public boolean onKeyDown(int keyCode, KeyEvent event) {
  if (keyCode == KeyEvent.KEYCODE_BACK) {
   if (mWebView.canGoBack()) {
    mWebView.goBack();
    return true;
   } else if (mWebView.getUrl().equals(strHomeUrl)) {
    return false;
   } else {
    mWebView.loadUrl(strHomeUrl);
    return true;
   }
  }
  return super.onKeyDown(keyCode, event);
 }

// generic function for getting picture from camero or gallery
 public void doStreamPic(String fromType) {
  if (fromType.equals("camera")) {
   Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
   startActivityForResult(pictureIntent, CAMERA_REQUEST);
   // pictureIntent.setRequestedOrientation(1);
  } else {
   Intent pickPhoto = new Intent(Intent.ACTION_PICK, (MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
   startActivityForResult(pickPhoto, GALLERY_REQUEST);
  }
 }

 public void doImgGet(final int idomdcom, String fname, final String strDIGfname) {
  currFileName = fname;
  try {
   this.runOnUiThread(new Runnable() {
   public void run() {
     final int theTestInt = idomdcom;
     final String theDIGTstr = strDIGfname;
     switch (theTestInt) {
      case 20:
       Intent pickBgPhoto = new Intent(getApplicationContext(), com.njfsoft_utils.artpad.ArtPad.class);
       pickBgPhoto.putExtra("apmode", "apmodeGallery");
       startActivityForResult(pickBgPhoto, 110);
       break;
      case 25:
       Intent pictureBgIntent = new Intent(getApplicationContext(), com.njfsoft_utils.artpad.ArtPad.class);
       pictureBgIntent.putExtra("apmode", "apmodeCamera");
       startActivityForResult(pictureBgIntent, 110);
       break;
      case 30:
       Intent nttCOapmMPF = new Intent(getApplicationContext(), CutOuts.class);
       nttCOapmMPF.putExtra("apmode", "mp4");
       nttCOapmMPF.putExtra("apfile", currFileName);
       nttCOapmMPF.putExtra("apmeta", theDIGTstr);
       startActivityForResult(nttCOapmMPF, SHARE_ACTIVITY_RES);
       break;
      case 35:
       Intent nttCOapmGIF = new Intent(getApplicationContext(), CutOuts.class);
       nttCOapmGIF.putExtra("apmode", "gif");
       nttCOapmGIF.putExtra("apfile", currFileName);
       nttCOapmGIF.putExtra("apmeta", theDIGTstr);
       startActivityForResult(nttCOapmGIF, SHARE_ACTIVITY_RES);
       break;
      case 40:
       Intent nttCOapmWB = new Intent(getApplicationContext(), CutOuts.class);
       nttCOapmWB.putExtra("apmode", "mp4");
       nttCOapmWB.putExtra("apfile", currFileName);
       nttCOapmWB.putExtra("apmeta", theDIGTstr);
       startActivityForResult(nttCOapmWB, SHARE_SOCIAL_RES);
       break;
      case 41:
       Intent nttCOapmTR = new Intent(getApplicationContext(), CutOuts.class);
       nttCOapmTR.putExtra("apmode", "trumpster");
       nttCOapmTR.putExtra("apfile", currFileName);
       nttCOapmTR.putExtra("apmeta", theDIGTstr);
       startActivityForResult(nttCOapmTR, SHARE_ACTIVITY_RES);
       break;
     }
    }
   });
  } catch (Exception e) {
   System.out.println("dev:ERROR:doTest:" + e.toString());
   e.printStackTrace();
  }
 }



 public void doArtPadBitmap() {
  /**/
  try {
   Intent toMain = new Intent(this, com.jwetherell.quick_response_code.DecoderActivity.class);
   // toMain.putExtra("encdBmp", currImgString);
   startActivityForResult(toMain, 11);
  } catch (Exception e) {
   System.out.println("doArtPadBitmap" + e.toString());
  }

 }


 public void doArtPadBitmap(final Bitmap theDoAPBitmap) {
  /*
          try {
              Intent toMain = new Intent(this, CaptureActivity.class);
              ByteArrayOutputStream baos = new ByteArrayOutputStream();
              theDoAPBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
              baos.close();
              byte[] bMapArray = baos.toByteArray();
              String encodedImage = Base64.encodeBytes(bMapArray);
              // toMain.putExtra("encdBmp", encodedImage);
              startActivityForResult(toMain, 11);
          } catch(Exception e) {
              System.out.println("doArtPadBitmap" + e.toString());
          }
  */
 }

 public void doImgLoad() {
  try {
   if (currImgString.equalsIgnoreCase("noQvalue")) {

   } else {
    mWebView.loadUrl("javascript:showStreamPic('" + currImgString + "');");
   }
  } catch (Exception e) {
   System.out.println("doImgLoad" + e.toString());
  }
 }
 public void doImgEdit() {
  try {
   if (currImgString.equalsIgnoreCase("noQvalue")) {

   } else {
    doArtPadBitmap();
    //  mWebView.loadUrl("javascript:showStreamPic('" + currImgString + "');");
   }
  } catch (Exception e) {
   System.out.println("doImgLoad" + e.toString());
  }
 }


 public Bitmap doBitmapFromAsset(String strBfile) {
  AssetManager assetManager = qBits.this.getAssets();
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
  //        encBitmap(scaleBitmap(doBitmapFromAsset(strBfile)));
 }


 public byte[] getTemporaryImage(Uri contentUri) {
  try {
   ExifInterface exif = new ExifInterface(contentUri.getPath());
   if (exif.hasThumbnail()) {
    return exif.getThumbnail();
   }
  } catch (IOException e) {

   System.out.println("getTemporaryImage: " + e.toString());
   return null;
  }
  return null;
 }


 public static Uri getImageContentUri(Context context, File imageFile) {
  String filePath = imageFile.getAbsolutePath();
  Cursor cursor = context.getContentResolver().query(
   MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
   new String[] {
    MediaStore.Images.Media._ID
   },
   MediaStore.Images.Media.DATA + "=? ",
   new String[] {
    filePath
   }, null);
  if (cursor != null && cursor.moveToFirst()) {
   int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
   cursor.close();
   return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + id);
  } else {
   if (imageFile.exists()) {
    ContentValues values = new ContentValues();
    values.put(MediaStore.Images.Media.DATA, filePath);
    return context.getContentResolver().insert(
     MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
   } else {
    return null;
   }
  }
 }



 public String getGifStr(String thefile) {
  String uString;
  uString = "noQvalue";
  try {
   File ffile = new File(mediaStorageDir, thefile);
   Uri daUri = getImageContentUri(getApplicationContext(), ffile);
   uString = Base64.encodeBytes(getLocalImageAsBytes(daUri));
   return uString;
  } catch (Exception e) {
   showDaToast("getGifStr.error: " + e.toString());
   e.printStackTrace();
   return uString;
  }


 }


 public String getThmbStr(String thefile) {
  String uString;
  uString = "noQvalue";
  try {
   File ffile = new File(mediaStorageDir, thefile);
   Uri daUri = getImageContentUri(getApplicationContext(), ffile);
   long id = ContentUris.parseId(daUri);
   byte[] byteArray = getTemporaryImage(daUri);
   if (byteArray != null) {
    uString = Base64.encodeBytes(byteArray);
    System.out.println("getThmbStr.exif-thumb: " + uString);
   } else {
    Bitmap aabitmap = MediaStore.Images.Thumbnails.getThumbnail(
     getContentResolver(), id,
     3,
     (BitmapFactory.Options) null);
    if (aabitmap != null) {
     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
     aabitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
     byte[] abyteArray = byteArrayOutputStream.toByteArray();
     uString = Base64.encodeBytes(abyteArray);
     System.out.println("getThmbStr.thumb: " + uString);
    }
   }
   return uString;
  } catch (Exception e) {
   System.out.println("getThmbStr.error: " + e.toString());
   e.printStackTrace();
   return uString;
  }
 }


// --
// end of to move media functions


















// to move. rewrite
// abstract synching functions for images and database records to/from app/remote-server
// see assets/js/x_aa-edit-synch

 public void setJsnPrs(String tmpJstr) {
  try {
   JSONObject jobj = new JSONObject(tmpJstr);
   JSONArray jarray = jobj.getJSONArray("knvp");
   String param = jarray.getJSONObject(0).getString("v");
   showDaToast("setJsnPrs " + "\n\n" + param);
  } catch (Exception e) {
   showDaToast("setJsnPrs Error" + e);
  }
 }

 public String getGzipAsString(InputStream instream) {
  StringBuffer gzipStrngBuffer = null;
  String fullString = null;
  GZIPInputStream gzipIStream = null;
  BufferedReader gzipBReader = null;
  int len = 0;
  int ttlen = 0;
  char chars[] = new char[1024];
  try {
   gzipIStream = new GZIPInputStream(instream);
   gzipBReader = new BufferedReader(new InputStreamReader(gzipIStream));
   gzipStrngBuffer = new StringBuffer();
   //Write chunks of characters to the StringBuffer 
   while ((len = gzipBReader.read(chars, 0, chars.length)) >= 0) {
    ttlen++;
    // System.out.println("chars.length: " + String.valueOf(chars.length) +  "chars: " + chars + "len: " + String.valueOf(len));
    gzipStrngBuffer.append(chars, 0, len);
   }
   chars = null;
   gzipIStream.close();
   gzipBReader.close();
   instream.close();
   fullString = gzipStrngBuffer.toString();
  } catch (Exception gzipException) {
   len = 0;
   ttlen = 0;
   BufferedReader normBR = new BufferedReader(new InputStreamReader(instream));
   StringBuffer normSTRB = new StringBuffer();
   try {
    while ((len = normBR.read(chars, 0, chars.length)) >= 0) {
     ttlen++;
     // System.out.println("chars.length: " + String.valueOf(chars.length) +  "chars: " + chars + "len: " + String.valueOf(len));
     normSTRB.append(chars, 0, len);
    }
    fullString = "ERROR; " + gzipException.toString() + " :: " + normSTRB.toString();

   } catch (Exception except) {
    fullString = "ERROR; " + except.toString() + " :: " + instream.toString();
   }
  }
  return fullString;
 }



 public String writeFile(String str) {
  String s2 = "noQvalue";
  try {
   long nowLong = System.currentTimeMillis() / 1000;
   String datestring = Long.toString(nowLong);
   int myint = Integer.parseInt(datestring);
   String dbqi = Integer.toString(myint);
   String hostfolder = "http://192.168.42.43/evenflow/webdroid/assets/quickorder/";
   dbqi = dbqi.substring(dbqi.length() - 2, dbqi.length());
   File ffile = new File(mediaStorageDir, dbqi + ".pcdbq");
   FileOutputStream fos = new FileOutputStream(ffile, true);
   BufferedOutputStream buffer = new BufferedOutputStream(fos);
   GZIPOutputStream gzip = new GZIPOutputStream(buffer);
   gzip.write(str.getBytes());
   gzip.close();
   buffer.close();
   fos.close();
   String fNewM = Environment.getExternalStorageDirectory().getPath() + File.separator + "quick-order" + File.separator + dbqi + ".pcdbq";
   HttpClient httpClient = new DefaultHttpClient();
   HttpContext localContext = new BasicHttpContext();
   HttpPost httpPost = new HttpPost("http://192.168.42.43/evenflow/webdroid/assets/quickorder/_p/do.php");
   String strOutput = "noQvalue";
   ArrayList < NameValuePair > nameValuePairs = new ArrayList < NameValuePair > ();
   nameValuePairs.add(new BasicNameValuePair("action", "synch"));
   nameValuePairs.add(new BasicNameValuePair("filename", fNewM));
   nameValuePairs.add(new BasicNameValuePair("dbqi", dbqi));
   MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
   for (int index = 0; index < nameValuePairs.size(); index++) {
    if (nameValuePairs.get(index).getName().equalsIgnoreCase("filename")) {
     // If the key equals to "image", we use FileBody to transfer the data
     entity.addPart(nameValuePairs.get(index).getName(), new FileBody(new File(nameValuePairs.get(index).getValue())));
    } else {
     // Normal string data
     entity.addPart(nameValuePairs.get(index).getName(), new StringBody(nameValuePairs.get(index).getValue()));
    }
   }
   httpPost.setEntity(entity);
   HttpResponse response = httpClient.execute(httpPost, localContext);
   s2 = convertResponseToString(response);
   showDaToast(s2);
   return s2;
  } catch (Exception e) {
   e.printStackTrace();
   s2 = e.toString();
   System.out.println("qBits:writeFile:error: " + e);
   return s2;
  }
 }

 public String writeAFile(String str) {
  System.out.println("writeFile function: 1737");
  String s2 = "noQvalue";
  try {
   // str = replaceString(str, "'", "");
   long nowLong = System.currentTimeMillis() / 1000;
   String datestring = Long.toString(nowLong);
   int myint = Integer.parseInt(datestring);
   String dbqi = Integer.toString(myint);
   String hostfolder = "http://192.168.42.43/evenflow/webdroid/assets/quickorder/";
   dbqi = dbqi.substring(dbqi.length() - 2, dbqi.length());
   File ffile = new File(mediaStorageDir, dbqi + ".pcdbq");
   FileOutputStream fos = new FileOutputStream(ffile, true);
   BufferedOutputStream buffer = new BufferedOutputStream(fos);
   GZIPOutputStream gzip = new GZIPOutputStream(buffer);
   gzip.write(str.getBytes());
   gzip.close();
   buffer.close();
   fos.close();
   File afile = new File(mediaStorageDir, dbqi + ".pcdbq");
   String uWFString = hostfolder + "_p/do.php?&action=synch&ttime=" + datestring;
   URL url = new URL(uWFString);
   // URLConnection urlconnection = url.openConnection();
   chttpreq = new ClientHttpRequest(url);
   if (chttpreq != null) {

    chttpreq.setParameter("filename", afile);
    chttpreq.setParameter("dbqi", dbqi);
    // InputStream instream = chttpreq.post(url, new Object[] { "filename", afile });
    InputStream instream = chttpreq.post();
    s2 = getGzipAsString(instream);
    showDaToast("response: " + s2);
   }
   return s2;
  } catch (Exception e) {
   System.out.println(e);
   e.printStackTrace();
   String eString = e.toString();
   s2 = eString;
   // showDaToast("wrietFile.error: \n" + eString);
   return s2;
  }
 }

 public String grabImageFromUrl(String fname, String url, String striid) {
  System.out.println("qBits:grabImageFromUrl:vars: " + fname + " : " + url + " : " + striid);
  Bitmap bitmap = null;
  String s2 = "noQvalue";
  try {
   bitmap = BitmapFactory.decodeStream((InputStream) new URL("http://192.168.42.43/evenflow/webdroid/assets/quickorder/images/pimgs/" + url).getContent());
   ByteArrayOutputStream baos = new ByteArrayOutputStream();
   bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
   baos.close();
   byte[] bMapArray = baos.toByteArray();
   JSONArray aresultSet = jarrFileSaved(bMapArray, url);
   JSONObject fretObject = new JSONObject();
   fretObject.put("in", url);
   fretObject.put("success", "true");
   fretObject.put("msg", "done");
   fretObject.put("id", striid);
   s2 = fretObject.toString();
   System.out.println("qBits:grabImageFromUrl:returns: " + s2);
   //       showDaToast("grabImageFromUrl: " + s2);
   return s2;
  } catch (Exception e) {
   e.printStackTrace();
   s2 = e.toString();
   System.out.println("qBits:grabImageFromUrl:error: " + s2);
   return s2;
  }
 }

 public String loadImgToUrl(String in , String strFn, String strIid) {
  String s2 = "noQvalue";
  System.out.println("qBits:loadImgToUrl:vars: " + in +" : " + strFn + " : " + strIid);
  try {
   HttpClient httpClient = new DefaultHttpClient();
   HttpContext localContext = new BasicHttpContext();
   HttpPost httpPost = new HttpPost("http://192.168.42.43/evenflow/webdroid/assets/quickorder/_p/file_imgupload.php");
   File afile = new File(mediaStorageDir, strFn);
   String filePath = afile.getAbsolutePath();
   String strOutput = "noQvalue";
   ArrayList < NameValuePair > nameValuePairs = new ArrayList < NameValuePair > ();
   nameValuePairs.add(new BasicNameValuePair("in", in ));
   nameValuePairs.add(new BasicNameValuePair("iid", strIid));
   nameValuePairs.add(new BasicNameValuePair("uploadfile", filePath));
   MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
   for (int index = 0; index < nameValuePairs.size(); index++) {
    if (nameValuePairs.get(index).getName().equalsIgnoreCase("uploadfile")) {
     // If the key equals to "image", we use FileBody to transfer the data
     entity.addPart(nameValuePairs.get(index).getName(), new FileBody(new File(nameValuePairs.get(index).getValue())));
    } else {
     // Normal string data
     entity.addPart(nameValuePairs.get(index).getName(), new StringBody(nameValuePairs.get(index).getValue()));
    }
   }
   httpPost.setEntity(entity);
   HttpResponse response = httpClient.execute(httpPost, localContext);
   s2 = convertResponseToString(response);
   System.out.println("qBits:loadImgToUrl:returns: " + s2);
   return s2;
  } catch (Exception e) {
   e.printStackTrace();
   s2 = e.toString();
   System.out.println("qBits:loadImgToUrl:error: " + s2);
   return s2;
  }
 }

 public String setImgSynch(String strFromWhere, String str, String strIid) {
  String s2 = "noQvalue";
  String compUrl = str;
  String in = str;
  System.out.println("qBits:setImgSynch:vars: " + strFromWhere + " : " + str + " : " + strIid);
  try {
   if (compUrl.lastIndexOf(File.separator) != -1) { in = compUrl.substring(compUrl.lastIndexOf(File.separator) + 1, compUrl.length());
   }

   if (strFromWhere.equals("local")) {
    s2 = loadImgToUrl( in , compUrl, strIid);
   } else {
    s2 = grabImageFromUrl( in , compUrl, strIid);
   }
   System.out.println("qBits:setImgSynch:returns: " + s2);
   return s2;
  } catch (Exception e) {
   e.printStackTrace();
   s2 = e.toString();
   System.out.println("qBits:setImgSynch:error: " + s2);
   return s2;
  }
 }
// -- end of to move. rewrite.
// abstract synching functions for database and images to/from app/remote-server


/*
* this is to delete
* basically long pressing on browser image gives you view/edit/download options on context menu
 @Override
 public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
  super.onCreateContextMenu(menu, v, menuInfo);
  System.out.println("onCreateContextMenu: ");
  WebView.HitTestResult result = ((WebView) v).getHitTestResult();
  System.out.println("HitTestResult:: " + result.getType() + " :: " + result.getExtra() + " :: " + result.toString());
  MenuItem.OnMenuItemClickListener handler = new MenuItem.OnMenuItemClickListener() {
   public boolean onMenuItemClick(MenuItem item) {
    return true;
   }
  };
  //  if (result.getType() == WebView.HitTestResult.IMAGE_TYPE ||  result.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
  // Menu options for an image.
  menu.setHeaderTitle(result.getExtra());
  menu.add(0, 1, 0, "Say It").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
   @Override
   public boolean onMenuItemClick(MenuItem menuItem) {
    WebView.HitTestResult result = mWebView.getHitTestResult();
    getSpeechToText();
    System.out.println("onMenuItemClick: " + result.getExtra());
    // shareStory(theStoryBundle);
    // mWebView.loadUrl(result.getExtra().toString());
    return true; //To change body of implemented methods use File | Settings | File Templates.
   }
  });
  menu.add(0, 2, 0, "View Image").setOnMenuItemClickListener(handler);
  menu.add(0, 3, 0, "Edit Image").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
   @Override
   public boolean onMenuItemClick(MenuItem menuItem) {
    WebView.HitTestResult result = mWebView.getHitTestResult();
    System.out.println("onMenuItemClick: " + result.getExtra() + " :: " + result.getType());
    // doEPPicture("web");
    // shareStory(theStoryBundle);
    // mWebView.loadUrl(result.getExtra().toString());
    return true; //To change body of implemented methods use File | Settings | File Templates.
   }
  });
 }
*/


}