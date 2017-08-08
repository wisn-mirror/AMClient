package com.wisn.pmlib.activity.index;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 快速索引
 * @author Wisn
 * @time 2016/5/27 10:59
 */

public class QuickIndex extends View {
    public OnIndexChangeListener getOnIndexChangeListener() {
        return onIndexChangeListener;
    }

    /**
     * 添加字母的监听
     * @param onIndexChangeListener
     */
    public void setOnIndexChangeListener(OnIndexChangeListener onIndexChangeListener) {
        this.onIndexChangeListener = onIndexChangeListener;
    }

    public  OnIndexChangeListener  onIndexChangeListener;
    public  interface  OnIndexChangeListener{
        void startTouch();
        void change(String str, int index);
        void release();
    }
    private Paint paint;
    private int mWidth;
    private int mHeight;
    public String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private float helfWidth;
    private int evHeight;
    private float helfHeight;

    public QuickIndex(Context context) {
        this(context, null);
    }

    public QuickIndex(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndex(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(20);
//        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }
    int index=-1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float y = event.getY();
                 index=(int)y/evHeight;
                if(onIndexChangeListener!=null){
                    onIndexChangeListener.startTouch();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float ymove = event.getY();
                index=(int)ymove/evHeight;
                if(onIndexChangeListener!=null){
                    if(index<data.length){
                        onIndexChangeListener.change(data[index],index);
                    }
                }
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
            case MotionEvent.ACTION_UP:
                index=-1;
                if(onIndexChangeListener!=null){
                    onIndexChangeListener.release();
                }
                break;
        }
        invalidate();
        return true;//super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = this.getMeasuredHeight();
        mWidth = this.getMeasuredWidth();
        helfWidth = mWidth * 0.5f;
        evHeight = mHeight / data.length;
        helfHeight = evHeight * 0.5f;
        System.out.println("mHeight" + mHeight + "mWidth" + mWidth + "helfWidth" + helfWidth + "evHeight" + evHeight + "helfHeight" + helfHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < data.length; i++) {
            Rect rect = new Rect();
            paint.getTextBounds(data[i], 0, 1, rect);
            float x = helfWidth - rect.width() * 0.5f;
            float y = helfHeight  +(rect.height() * 0.5f) + evHeight * i;
          //  System.out.println("x:=" + x + "y:" + y);
            if(index==i){
                paint.setColor(Color.YELLOW);
            }else{
                paint.setColor(Color.RED);
            }
            canvas.drawText(data[i], x, y, paint);
        }

    }
}
