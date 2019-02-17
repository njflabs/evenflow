package com.njfsoft_utils.anim;


import android.graphics.Bitmap;
/**
 * Created by IntelliJ IDEA.
 * User: boss
 * Date: 16-03-2014
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public class AnimFrameSingleton {
    String tagValA = "noQvalue";
    byte[] mBytes;
    Bitmap mBitmap;
    Bitmap mAnimBmp;
    int mAnimResInt;
    Bitmap mAnimBgBmp;

    int iPST;
    int iAnmFrameTS;
    int iAnmBgFrameTS;
    public AnimFrameSingleton() {
    }


    public void setMBytes(byte[] tmpval) {
        this.mBytes = tmpval;
    }

    public void setMBitmap(Bitmap tmpval) {
        this.mBitmap = tmpval;
    }

    public void setMAnimBmp(Bitmap tmpval) {
        this.mAnimBmp = tmpval;
    }

    public void setMAnimResInt(int tmpval) {
        this.mAnimResInt = tmpval;
            System.out.println("AnimFrameSingleton:setMAnimResInt: " + tmpval);
    }

    public void setMAnimBgBmp(Bitmap tmpval) {
        this.mAnimBgBmp= tmpval;
    }

    public void setIpst(int tmpval) {
        this.iPST = tmpval;
    }

    public void setIanmFrmTS(int tmpval) {
        this.iAnmFrameTS = tmpval;
    }
    public void setIanmBgFrmTS(int tmpval) {
        this.iAnmBgFrameTS = tmpval;
    }

    public byte[] getMBytes() {
        return mBytes;
    }
    public Bitmap getMBitmap() {
        return mBitmap;
    }
    public Bitmap getMAnimBmp() {
        return mAnimBmp;
    }

    public int getMAnimResInt() {
        return mAnimResInt;
    }

    public Bitmap getMAnimBgBmp() {
        return mAnimBgBmp;
    }

    public int getIpst() {
        return iPST;
    }
 
    public int getIanmFrmTS() {
        return iAnmFrameTS;
    }
    public int getIanmBgFrmTS() {
        return iAnmBgFrameTS;
    }
    @Override
    public String toString() {
        return tagValA;
    }
}