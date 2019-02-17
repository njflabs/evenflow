package com.njfsoft_utils.shareutil;

public class ShareDataResult {

    private static ShareDataResult instance;
      private String data = null;
	private String strCallingApp; 
	private String strIHID;
	private String strAPmode;
	private String strNworkID;
	private String strNwork;
	private String strImgName;
	private String strImgStr;
	private byte[] strImgBytes;
	private String strTitle;
	private String strCaption;
	private String strDesc;
	private String strMsg;
 

    protected ShareDataResult() {

    }

    public static ShareDataResult getInstance() { 
        if (instance == null) {
            instance = new ShareDataResult();
        }
        return instance;
    }

	public String getData() { return data; }
	public String getCallingApp() { return strCallingApp; } 
	public String getIHID() { return strIHID; }
	public String getAPmode() { return strAPmode; }
	public String getNworkID() { return strNworkID; }
	public String getNwork() { return strNwork; }
	public String getImgName() { return strImgName; }
	public String getImgStr() { return strImgStr; }
	public byte[] getImgBytes() { return strImgBytes; }
	public String getTitle() { return strTitle; }
	public String getCaption() { return strCaption; }
	public String getDesc() { return strDesc; }
	public String getMsg() { return strMsg; }

	public void setData(String strTmp) { this.data = strTmp; }
	public void setCallingApp(String strTmp) { this.strCallingApp = strTmp; } 
	public void setIHID(String strTmp) { this.strIHID = strTmp; }
	public void setAPmode(String strTmp) { this.strAPmode = strTmp; }
	public void setNworkID(String strTmp) { this.strNworkID = strTmp; }
	public void setNwork(String strTmp) { this.strNwork = strTmp; }
	public void setImgName(String strTmp) { this.strImgName = strTmp; }
	public void setImgStr(String strTmp) { this.strImgStr = strTmp; }
	public void setImgBytes(byte[] strTmp) { this.strImgBytes = strTmp; }
	public void setTitle(String strTmp) { this.strTitle = strTmp; }
	public void setCaption(String strTmp) { this.strCaption = strTmp; }
	public void setDesc(String strTmp) { this.strDesc = strTmp; }
	public void setMsg(String strTmp) { this.strMsg = strTmp; }

}
