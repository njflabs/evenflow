package com.njfsoft_utils.cutOuts;
import com.qbits.R;

import com.njfsoft_utils.cutOuts.CutOuts;
import com.njfsoft_utils.anim.AnimFrameSingleton;
import com.njfsoft_utils.anim.AnimMovSingleton;
/**
 * Created by IntelliJ IDEA.
 * User: boss
 * Date: 24-11-2012
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class JSI_CutOuts  {


    public JSI_CutOuts(CutOuts theMain) {
        main = theMain;


    }


    public void doTest(String stargs) {
        System.out.println("doTest: " + stargs);
	 // main.doTest(stargs);
    }

     public int getMovArrSize() {
        try {
            int ret = main.getMovArrSize();
		return ret;
        } catch (Exception e) {
            System.out.println("getMovArrSize: " + e);
		return 0;
        }

    } 

     public String getMovImgString(int tIMSi) {
        try {
            String ret = main.getMovImgString(tIMSi);
		return ret;
        } catch (Exception e) {
            System.out.println("getMovImgString: " + e);
		return "noQvalue";
        }

    }



 public String getCUConfBundle() {
        try {
            String ret = main.sendCUConfBundle();
		return ret;
        } catch (Exception e) {
            System.out.println("getCUConfBundle: " + e);
		return "noQvalue";
        }

    }


    public void setCUConfValStr(String theString, String theVal) {
        main.putCUConfValStr(theString, theVal);
    }

     public String getEFFramesObj() {
        try {
		String ret = "bla";

				AnimMovSingleton tmpAMS = AnimMovSingleton.getInstance();
				if(tmpAMS != null) {
 				ret = "sum: " + tmpAMS.getMamsArrAFS().size();
				}

		return ret;
        } catch (Exception e) {
            System.out.println("getJSComm: " + e);
		return "noQvalue";
        }

    } 

    CutOuts main;

}
