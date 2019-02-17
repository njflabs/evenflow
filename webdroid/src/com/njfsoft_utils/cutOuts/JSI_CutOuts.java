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
	//  main.doTest(stargs);
    }

 
     public String getShareImgStr() {
        try {
            String ret = main.getImgLoadStr();
		return ret;
        } catch (Exception e) {
            System.out.println("getJSComm: " + e);
		return "noQvalue";
        }

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
