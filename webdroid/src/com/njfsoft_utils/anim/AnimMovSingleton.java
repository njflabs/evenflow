package com.njfsoft_utils.anim;
import java.util.ArrayList;


import com.njfsoft_utils.anim.AnimFrameSingleton;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.Bitmap;
 


public class AnimMovSingleton  {
    private static AnimMovSingleton instance;
    String tagValA = "noQvalue";
	ArrayList<AnimFrameSingleton> amsArrAFS;
 	AnimationDrawable amsAnimation = null;
 	AnimationDrawable amsBgAnimation = null;
 	int iMovHeight;
 	int iMovWidth;
 	int iMovFPS;
	int iMovFDelay;
	int[] amsAnimInt = null;
	String iMovType = null;
	String iMovName = null;
	String iMovFileStr = null;
    protected AnimMovSingleton() {
    }




    public static AnimMovSingleton getInstance() { 
        if (instance == null) {
            instance = new AnimMovSingleton();
        }
        return instance;
    }

    public void clearAMS() {
       amsArrAFS = null;
 	amsAnimation = null;
 	amsBgAnimation = null;
 	 amsAnimInt = null;
	iMovType = null;
    }

    public void setMamsArrAFS(ArrayList<AnimFrameSingleton> tmpval) {
        this.amsArrAFS = tmpval;
    }

    public ArrayList<AnimFrameSingleton> getMamsArrAFS() {
        return amsArrAFS;
    }


    public void setMamsAnimation(AnimationDrawable tmpval) {
        this.amsAnimation = tmpval;
    }

    public void setMamsAnimInt(int[] tmpval) {
        this.amsAnimInt = tmpval;
	  
    }

    public void setMamsBgAnimation(AnimationDrawable tmpval) {
        this.amsBgAnimation = tmpval;
    }

    public void setMmovHeight(int tmpval) {
        this.iMovHeight = tmpval;
    }

    public void setMmovWidth(int tmpval) {
        this.iMovWidth = tmpval;
    }

    public void setMmovFPS(int tmpval) {
        this.iMovFPS = tmpval;
    }
    public void setMmovFDelay(int tmpval) {
        this.iMovFDelay = tmpval;
    }

    public void setIMovName(String tmpval) {
        this.iMovName = tmpval;
    }

    public void setIMovType(String tmpval) {
        this.iMovType = tmpval;
    }
    public void setIMovFileStr(String tmpval) {
        this.iMovFileStr = tmpval;
    }

    public AnimationDrawable getMamsAnimation() {
        return amsAnimation;
    }

    public int[] getMamsAnimInt() {
       return amsAnimInt;
    }


    public AnimationDrawable getMamsBgAnimation() {
        return amsBgAnimation;
    }

     public int getMmovHeight() {
        return iMovHeight;
    }

    public int getMmovWidth() {
        return iMovWidth;
    }
    public int getMmovFPS() {
        return iMovFPS;
    }
    public int getMmovFDelay() {
        return iMovFDelay;
    }
    public String getIMovName() {
        return iMovName;
    }
    public String getIMovType() {
        return iMovType;
    }
    public String getIMovFileStr() {
        return iMovFileStr;
    }

    @Override
    public String toString() {
        return tagValA;
    }
}
