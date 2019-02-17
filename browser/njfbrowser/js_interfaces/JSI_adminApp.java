package njfbrowser.js_interfaces;

import njfbrowser.main.adminApp;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 * Created by IntelliJ IDEA.
 * User: boss
 * Date: 16-07-2013
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 */
public class JSI_adminApp {
     adminApp webMobia;
    public JSI_adminApp(adminApp theWebMobia) {
        webMobia = theWebMobia;

    }


        public void doDB(String dbString) {
            // String decoding = new sun.misc.BASE64Decoder().decode(dbString);
            webMobia.getDynDBapp(dbString);
        }

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









}