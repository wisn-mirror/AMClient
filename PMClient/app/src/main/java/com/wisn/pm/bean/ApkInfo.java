package com.wisn.pm.bean;

import android.graphics.drawable.Drawable;

/**
 * <b>Create Date:</b> 2016/9/22<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public class ApkInfo {
    public Drawable  drawable;
    public String  apkName;

    public ApkInfo(Drawable drawable, String apkName) {
        this.drawable = drawable;
        this.apkName = apkName;
    }
}
