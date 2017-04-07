package com.wisn.pmlib.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wisn.pmlib.R;

/**
 * Created by wisn on 2017/4/1.
 */

public class SnackbarUtils {

    public static final   int Info = 1;
    public static final  int Confirm = 2;
    public static final  int Warning = 3;
    public static final  int Alert = 4;


    public static  int red = 0xfff44336;
    public static  int green = 0xff4caf50;
    public static  int blue = 0xff2195f3;
    public static  int orange = 0xffffc107;

    /**
     * 短显示Snackbar，自定义颜色
     * @param view
     * @param message
     * @param messageColor
     * @param backgroundColor
     * @return
     */
    public static Snackbar ShortSnackbar(View view, String message, int messageColor, int backgroundColor){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_SHORT);
        setSnackbarColor(snackbar,messageColor,backgroundColor);
        return snackbar;
    }

    /**
     * 长显示Snackbar，自定义颜色
     * @param view
     * @param message
     * @param messageColor
     * @param backgroundColor
     * @return
     */
    public static Snackbar LongSnackbar(View view, String message, int messageColor, int backgroundColor){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_LONG);
        setSnackbarColor(snackbar,messageColor,backgroundColor);
        return snackbar;
    }

    /**
     * 自定义时常显示Snackbar，自定义颜色
     * @param view
     * @param message
     * @param messageColor
     * @param backgroundColor
     * @return
     */
    public static Snackbar IndefiniteSnackbar(View view, String message,int duration,int messageColor, int backgroundColor){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        setSnackbarColor(snackbar,messageColor,backgroundColor);
        return snackbar;
    }

    /**
     * 短显示Snackbar，可选预设类型
     * @param view
     * @param message
     * @param type
     * @return
     */
    public static Snackbar ShortSnackbar(View view, String message, int type){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_SHORT);
        switchType(snackbar,type);
        return snackbar;
    }

    /**
     * 长显示Snackbar，可选预设类型
     * @param view
     * @param message
     * @param type
     * @return
     */
    public static Snackbar LongSnackbar(View view, String message,int type){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_LONG);
        switchType(snackbar,type);
        return snackbar;
    }

    /**
     * 自定义时常显示Snackbar，可选预设类型
     * @param view
     * @param message
     * @param type
     * @return
     */
    public static Snackbar IndefiniteSnackbar(View view, String message,int duration,int type){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        switchType(snackbar,type);
        return snackbar;
    }

    //选择预设类型
    private static void switchType(Snackbar snackbar,int type){
        switch (type){
            case Info:
                setSnackbarColor(snackbar,blue);
                break;
            case Confirm:
                setSnackbarColor(snackbar,green);
                break;
            case Warning:
                setSnackbarColor(snackbar,orange);
                break;
            case Alert:
                setSnackbarColor(snackbar, Color.YELLOW, red);
                break;
        }
    }



    /**
     * 自定义
     * @param snackbar
     * @param layoutId
     * @param index
     */
    public static void  SnackbarAddView(Snackbar snackbar, int layoutId, int index){
        View snackbarView=snackbar.getView();
        Snackbar.SnackbarLayout  snackbarView1 = (Snackbar.SnackbarLayout) snackbarView;
       //View newView= View.inflate(snackbarView1.getContext(),layoutId,null);
        View newView=LayoutInflater.from(snackbarView1.getContext()).inflate(layoutId,null);
        LinearLayout.LayoutParams  params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity= Gravity.CENTER_VERTICAL;
        snackbarView1.addView(newView,index);
    }

    /**
     * 设置消息字体颜色
     * @param snackbar
     * @param messagecolor
     * @param backgroundcolor
     */
    public static void setSnackbarColor(Snackbar snackbar,int backgroundcolor,int messagecolor){
        View view=snackbar.getView();
        if(view!=null){
            view.setBackgroundColor(backgroundcolor);
            TextView  messageView=((TextView) view.findViewById(R.id.snackbar_text));
            if(messageView!=null){
                messageView.setText(messagecolor);
            }
        }
    }

    /**
     * 设置背景颜色
     * @param snackbar
     * @param backgroundcolor
     */
    public static void setSnackbarColor(Snackbar snackbar,int backgroundcolor){
        View view=snackbar.getView();
        if(view!=null){
            view.setBackgroundColor(backgroundcolor);
        }
    }

/*

    Snackbar snackbar= SnackbarUtil.ShortSnackbar(coordinator,"妹子删了你发出的消息",SnackbarUtil.Warning).setActionTextColor(Color.RED).setAction("再次发送", new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SnackbarUtil.LongSnackbar(coordinator,"妹子已将你拉黑",SnackbarUtil.Alert).setActionTextColor(Color.WHITE).show();
        }
    });

 SnackbarUtil.SnackbarAddView(snackbar,R.layout.snackbar_addview,0);

 SnackbarUtil.SnackbarAddView(snackbar,R.layout.snackbar_addview2,2);

  snackbar.show();
*/

}
