package com.njfsoft_utils.shareutil;

 
import com.njfsoft_utils.shareutil.ShareDataResult;
/**
 * Created by IntelliJ IDEA.
 * User: boss
 * Date: 24-11-2012
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class JSI_Share  {

   
 



    
    public JSI_Share(ShareActivity theMain) {
        main = theMain;
        // loadActSingleton();
    }


    public void doTest(String stargs) {
        System.out.println("doTest: " + stargs);
    }


    public void getToggleWViewBtns(String sWB, String sSD) {
	 main.setToggleWViewBtns(Boolean.parseBoolean(sWB), Boolean.parseBoolean(sSD));
    }



	public String getData() { return main.getData(); }
	public String getCallingApp() { return main.getCallingApp(); } 
	public String getIHID() { return main.getIHID(); }
	public String getAPmode() { return main.getAPmode(); }
	public String getNworkID() { return main.getNworkID(); }
	public String getNwork() { return main.getNwork(); }
	public String getImgName() { return main.getImgName(); }
	public String getImgStr() { return main.getImgStr(); }
	public byte[] getImgBytes() { return main.getImgBytes(); }
	public String getTitle() { return main.getASTitle(); }
	public String getCaption() { return main.getCaption(); }
	public String getDesc() { return main.getDesc(); }
	public String getMsg() { return main.getMsg(); }


/*
    public String getShareImgStr() {
        try {
            String ret = main.getImgLoadStr();
		return ret;
        } catch (Exception e) {
            System.out.println("getJSComm: " + e);
		return "noQvalue";
        }

    }

    public String getImgLoadStr() {
        try {
 
                    String encodedImage = main.getData();
 			  return encodedImage;
        } catch(Exception e) {
            System.out.println("selfieLander:getImgLoadStr: " + e.toString());
		return "noQvalue";
        }
    }

    public void loadActSingleton() {
this.data = main.getData();
this.strCallingApp = main.getCallingApp();
this.strIHID = main.getIHID();
this.strAPmode = main.getAPmode();
this.strNworkID = main.getNworkID();
this.strNwork = main.getNwork();
this.strImgStr = main.getImgStr();
this.strImgBytes = main.getImgBytes();
this.strTitle = main.getTitle();
this.strCaption = main.getCaption();
this.strDesc = main.getDesc();
this.strMsg = main.getMsg();
}

*/
    ShareActivity main;
}


