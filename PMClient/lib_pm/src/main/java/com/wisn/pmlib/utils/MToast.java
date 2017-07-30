package com.wisn.pmlib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wisn.pmlib.R;

/**
 * Created by wisn on 2017/6/20.
 */

public class MToast {
    //解决重复显示
    private static Toast myToast;

    /**
     * 自定义toast
     *
     * @param context
     * @param message
     */
    public static void showSimple(Context context, String message) {
        if (myToast == null) {
            myToast = new Toast(context);
        }
        //myToast.cancel();
        TextView view = (TextView) View.inflate(context,
                                                R.layout.item_custom_toast, null);
        view.setText(message);
        myToast.setDuration(Toast.LENGTH_SHORT);
        myToast.setView(view);
        myToast.setGravity(Gravity.CENTER, 0, 0);
        myToast.show();
    }

    /**
     * 封装toast 可以在子线程中直接使用
     *
     * @param context
     * @param message
     */
    public static void showOnThread(final Activity context, final String message) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (myToast == null) {
                    myToast = new Toast(context);
                }
                //myToast.cancel();
                TextView view = (TextView) View.inflate(context,
                                                        R.layout.item_custom_toast, null);
                view.setText(message);
                myToast.setDuration(Toast.LENGTH_SHORT);
                myToast.setView(view);
                myToast.setGravity(Gravity.CENTER, 0, 0);
                myToast.show();
            }
        });
    }
}
