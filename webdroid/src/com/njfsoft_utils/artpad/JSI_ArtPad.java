package com.njfsoft_utils.artpad;

import com.njfsoft_utils.artpad.ArtPad;
 
public class JSI_ArtPad  {
    public JSI_ArtPad(ArtPad theArtPad) {
        artPad = theArtPad;


    }


    public void doTest(String stargs) {
        System.out.println("doTest: " + stargs);
    }


    public void doTextToBitmap(String theText) {
        try {


        // drawTextToBitmap(getApplicationContext(),3, theText);
        artPad.setTextToBitmap(theText);
        } catch (Exception e) {
        System.out.println("doApNewPopDialog: " + e);
    }
    }
    public void closePopUp() {
        // artPad.closeSharePopUp();
    }
 
    public void getAPJSComm(String strFID) {
        try {
            artPad.closeAPDialog();
            artPad.doAPJSComm(Integer.parseInt(strFID));
        } catch (Exception e) {
            System.out.println("getJSComm: " + e);
        }

    }



    public String getShareImgStr() {
        try {
            String ret = artPad.getImgLoadStr();
		return ret;
        } catch (Exception e) {
            System.out.println("getJSComm: " + e);
		return "noQvalue";
        }

    }


    ArtPad artPad;

}
