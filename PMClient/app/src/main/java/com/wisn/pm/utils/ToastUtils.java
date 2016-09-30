package com.wisn.pm.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * <b>Create Date:</b> 2016/9/18<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public class ToastUtils {
    private static  boolean  isShow=true;

    public  static void  show(final Context context, final String content){
        if(isShow){
           /* new Handler().post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context,content, Toast.LENGTH_SHORT).show();
                }
            });*/
            Toast.makeText(context,content, Toast.LENGTH_SHORT).show();
        }
    }
}
