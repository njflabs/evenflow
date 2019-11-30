package com.quickorder.js_interfaces;

import com.quickorder.QuickOrder;
import com.njfsoft_utils.core.StringUtils;
import com.njfsoft_utils.dbutil.UtilDbRecord;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;


import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
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



// import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;



/**
 * Created by IntelliJ IDEA.
 * User: boss
 * Date: 16-07-2013
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 */
public class JSI_quickOrder extends QuickOrder {
    final QuickOrder webMobia;
    public JSI_quickOrder(QuickOrder theWebMobia) {
        webMobia = theWebMobia;

    }

    /*
   * functions for setting and getting config values
    */
    public void setConfValString(String theString, String theVal) {
        webMobia.putConfValString(theString, theVal);
    }
    public void setConfValInt(String theString, String theVal) {
        webMobia.putConfValInt(theString, Integer.parseInt(theVal));
    }

    public String fetchConfValString(String theString) {
        String confStr = webMobia.getConfValString(theString);
        return confStr;
    }
    public String fetchConfValInt(String theString) {
        String confStr = String.valueOf(webMobia.getConfValInt(theString));
        return confStr;
    }
    public void setImgGet(String stiDCm, String strCBARgs, String sstrCBARgs) {
	webMobia.doImgGet(Integer.parseInt(stiDCm), strCBARgs, sstrCBARgs);
    }
    public void setMDcom(String stiDCm, String strCBARgs) {
	webMobia.doMDcom(Integer.parseInt(stiDCm), strCBARgs);
    }

 
    public void setStreamPic(String fromType) {
        webMobia.doStreamPic(fromType);
    }

    public void getUrlSlider() {
      //   webMobia.doUrlSlider();
    }

    /* Sends configuration for the loaded page */
    public void setPageLoad() {
        webMobia.doPageLoad();
    }

    public void doPagePopUp(String theUrl, String theHTML) {
        // showPagePopUp(theUrl, "noQvalue");
        webMobia.setPagePopUp(theUrl, theHTML);
    }
     public void doPageReload() {
        webMobia.doPageReload();
    }   
    public void setStoryShare(String sNwork, String  stitle, String  sdesc) {
        System.out.println("setStoryShare" + sNwork + ";;" + stitle + "::" + sdesc);
        webMobia.doStoryShare(sNwork, stitle, sdesc);
    }
    public void setNewStoryID() {
        webMobia.doNewStoryID();
    }
    public void setAssetBmp(String strBname) {
        webMobia.doAssetBmp(strBname);
    }
    public void setImgLoad() {
        webMobia.doImgLoad();
    }
    public void getPlayMPF(String strs) {
        webMobia.setPlayMPF(strs);
    }
    public void setImgEdit() {
        webMobia.doArtPadBitmap();
    }
    public String doGifStr(String strIU) {
        return webMobia.getGifStr(strIU);
    }

    public String doThmbStr(String strIU) {
        return webMobia.getThmbStr(strIU);
    }
    public String doWriteFile(String strIU) {
        return webMobia.writeFile(strIU);
    }

    public String doImgSynch(String strFWhere, String strIU, String strIID) {
        return webMobia.setImgSynch(strFWhere, strIU, strIID);
    }

    public void doJSIRecUpdate(int recid, String cvals)  {
     webMobia.dbMSQLA.doUpdateRecord(recid, cvals);
	}




    public String getCurrPageVars(String tmpStrQstr) {
	  String cpv = webMobia.getCurrPageVars(tmpStrQstr);
            System.out.print("getCurrPageVars: " + cpv);

        return cpv;
    }




 



public String getNuDBselectQ(String tmpStrQstr) {
String strHtml = "noQvalue";
try {

JSONObject fulretObject = new JSONObject();
JSONArray resultSet = new JSONArray();
JSONObject fretObject = new JSONObject();
String tmpCB;
String tmpEl;
String tmpV; 
String tmpQFstring;
 


if(tmpStrQstr.startsWith("batch")) {
tmpQFstring = tmpStrQstr.substring(5, tmpStrQstr.length());
JSONArray array = new JSONArray(tmpQFstring);
System.out.println("batch of" + tmpQFstring);

for (int i = 0; i < array.length(); i++) {
    JSONObject row = array.getJSONObject(i);
    JSONObject retObject = new JSONObject();

    JSONObject vObject = new JSONObject();
    
    tmpCB = row.getString("f");
    tmpEl = row.getString("e");
    tmpV = row.getString("v");
    String retQstr = webMobia.dbMSQLA.setDBselectQ(tmpV); 
    retObject.put("f" , tmpCB); 
    retObject.put("v" , new JSONArray(retQstr)); 
    retObject.put("e" , tmpEl); 
    fretObject.put(tmpEl, retObject);
    resultSet.put(fretObject);
    System.out.println("batch of: " + tmpCB);
}
    fulretObject.put("status" , "fromandroid"); 
    fulretObject.put("data" , fretObject.toString());
 

} else {
    fulretObject.put("status" , "fromandroid"); 
    fulretObject.put("data" , webMobia.dbMSQLA.setDBselectQ(tmpStrQstr));

}
strHtml = fulretObject.toString();
return strHtml;
} catch(Exception ee) {
  System.out.println("getNuDBselectQ: " + ee);
  return strHtml;
}

}

public String getDBselectQ(String tmpStrQstr) {
String strHtml = "noQvalue";
if(tmpStrQstr.startsWith("batch")) {
String tmpCB;
String tmpEl;
String tmpV;
String tmpQFstring = tmpStrQstr.substring(5, tmpStrQstr.length());
System.out.println("batch of" + tmpQFstring);
try {
JSONObject fulretObject = new JSONObject();
JSONArray resultSet = new JSONArray();
JSONArray array = new JSONArray(tmpQFstring);
    JSONObject fretObject = new JSONObject();
for (int i = 0; i < array.length(); i++) {
    JSONObject row = array.getJSONObject(i);
    JSONObject retObject = new JSONObject();

    JSONObject vObject = new JSONObject();
    
    tmpCB = row.getString("f");
    tmpEl = row.getString("e");
    tmpV = row.getString("v");
    String retQstr = webMobia.dbMSQLA.setDBselectQ(tmpV); 
    retObject.put("f" , tmpCB); 
    retObject.put("v" , new JSONArray(retQstr)); 
    retObject.put("e" , tmpEl); 
    fretObject.put(tmpEl, retObject);
    resultSet.put(fretObject);
    System.out.println("batch of: " + tmpCB);
}
    strHtml = fretObject.toString();
} catch(Exception ee) {
  System.out.println("batch of error: " + ee);
  return strHtml;
}

} else {

        strHtml = webMobia.dbMSQLA.setDBselectQ(tmpStrQstr);
}
        return strHtml;
}




    public String getSQLdump() {
        // String strHtml = "<table width=\"100%\" style=\"border: 1px solid #EEE:\">";
		String strHtml = "";
//  (boolean isDistinct, String strTable, String[] dcolumns, String qstring, String[] qargs, String strGrpBy, String strHaving, String strOrderBy, String strQlimit)
        strHtml = webMobia.dbMSQLA.dbSelectQ(false, "users", null, "_id>?", new String[]{"0"}, null, null, "_id desc", "1000");
 
        return strHtml;
    }


    public String getRecordDelete(String theRID) {
        String retStr = "noQvalue";
        retStr = webMobia.dbMSQLA.doStrRecDelete(theRID);
        return retStr;
    }


/*
	public String getJSEpDBrecords(String strIsDistinct, String strTable, String strColumns, String qstring, String qargs, String strGrpBy, String strHaving, String strOrderBy, String strQlimit) {
				String[] parseT;
                        if (qargs.contains(":")) {
                           parseT = qargs.split(":");
				} else {
			         parseT = new String[]{qargs};
				}
      return webMobia.dbMSQLA.setJSDBrecords(false, null, null, qstring, parseT, strGrpBy, strHaving, strOrderBy, strQlimit).toString();
	}
*/

	public String getJSEpDBrecords(String strIsDistinct, String strTable, String strColumns, String qstring, String qargs, String strGrpBy, String strHaving, String strOrderBy, String strQlimit) {
				String[] parseT;
                        if (qargs.contains(":")) {
                           parseT = qargs.split(":");
				} else {
			         parseT = new String[]{qargs};
				}
      return webMobia.dbMSQLA.setJSDBrecords(false, null, null, qstring, parseT, strGrpBy, strHaving, strOrderBy, strQlimit).toString();
	}

	public void getAllRecordsDelete() {
      webMobia.dbMSQLA.doAllRecordsDelete();
	}

	public void doRecInsert(int rt, String va, String vb, String vc, String da) {
      webMobia.doRecInsert(rt, va, vb, vc, da);
	}

    public void getALocalUrl(String strLUrl) {
	webMobia.doLoadLclUrl(strLUrl);
 
    }

     public void setPageThread(int ptype, int pid, String purl) {
	webMobia.setPageThread(ptype, pid, purl);
 
    }

     public void getPageThread() {
	webMobia.getPageThread();
 
    }
     public void getFormatSMS(String strRKey, String strMsgArgs, String strPhoneNums, String strMsg) {
	webMobia.runFormatSMS(strRKey, strMsgArgs, strPhoneNums, strMsg);
 
    }

	public void getSmsDetails() {
	webMobia.runSmsDetails();
	}

	public void rinsert() {
	webMobia.rinsert();
	}


public void getPopPageThread() {
webMobia.getPopPageThread();
}
public String getStoryType() {
return String.valueOf(webMobia.currStoryType);
}
public String getStoryID() {
return String.valueOf(webMobia.currStoryID);
}

public void getJsnPrs(String tmpJstr) {

try {

    webMobia.setJsnPrs(tmpJstr);

} catch (Exception e) {
    showDaToast("getJsnPrs Error" + "\n\n" + tmpJstr + "\n\n" + e);
}

}


public void getWVScrollTop() {
webMobia.setWVScrollTop();
}

public void getWVScrollPoint(int tx, int ty) {
webMobia.setWVScrollPoint(tx, ty);
}

public void getOrderClean(String onum, String strOtitle, String strOttl, String strOtype) {
webMobia.setOrderClean(onum,strOtitle,strOttl,strOtype);
}

public String getCurrAqrStr() {
return webMobia.currAQRstring;
}

public void getCutOuts() {
webMobia.setCutOuts();
}

public void doShareFromPage(String tmpStrBnce) {
webMobia.shareFromPage(tmpStrBnce);
}
 



public static String bytesToHex(byte[] bytes) {
    char[] hexArray = "0123456789ABCDEF".toCharArray();
    char[] hexChars = new char[bytes.length * 2];
    for ( int j = 0; j < bytes.length; j++ ) {
        int v = bytes[j] & 0xFF;
        hexChars[j * 2] = hexArray[v >>> 4];
        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars);
}


  public String sign(String message, String secret) {
    try {
	String ss = "j4dN6FRh9addxwzzlIJQN4erYBfXvA2eSasasas";
      Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKeySpec = new SecretKeySpec(ss.getBytes(), "HmacSHA256");
      sha256_HMAC.init(secretKeySpec);
	String fnlStr =  bytesToHex(sha256_HMAC.doFinal(message.getBytes()));
	// webMobia.showDaToast("sign: " + fnlStr);
	return fnlStr;

//      return new String(bytesToHex(sha256_HMAC.doFinal(message.getBytes())));
    } catch (Exception e) {
      throw new RuntimeException("Unable to sign message.", e);
    }
  }


public void doWhatsApp(String strNumber, String msgStr) {
webMobia.sendWhatsApp(strNumber, msgStr);
}

public void setFix(String msgStr) {
webMobia.doFix(msgStr);
}

}