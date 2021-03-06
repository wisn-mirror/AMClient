package com.wisn.pmlib.activity.other;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.base.BaseActivity;
import com.wisn.pmlib.utils.BlurBitmap;


public class BlurActivity extends BaseActivity {
    /**
     * 原始图片控件
     */
    private ImageView mOriginImg;

    /**
     * 模糊后的图片控件
     */
    private ImageView mBluredImage;

    /**
     * 进度条SeekBar
     */
    private SeekBar mSeekBar;

    /**
     * 显示进度的文字
     */
    private TextView mProgressTv;

    /**
     * 透明度
     */
    private int mAlpha;

    /**
     * 原始图片
     */
    private Bitmap mTempBitmap;

    /**
     * 模糊后的图片
     */
    private Bitmap mFinalBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);

        // 初始化视图
        initViews();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取图片
                mTempBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.want);
                mFinalBitmap = BlurBitmap.blur(BlurActivity.this, mTempBitmap);
                BlurActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 填充模糊后的图像和原图
                        mBluredImage.setImageBitmap(mFinalBitmap);
                        mOriginImg.setImageBitmap(mTempBitmap);
                        mOriginImg.setAlpha((int) (255 - 0 * 2.55));
                        // 处理seekbar滑动事件
                        setSeekBar();
                    }
                });


            }
        }).start();

    }

    /**
     * 初始化视图
     */
    private void initViews() {
        mBluredImage = (ImageView) findViewById(R.id.activity_main_blured_img);
        mOriginImg = (ImageView) findViewById(R.id.activity_main_origin_img);
        mSeekBar = (SeekBar) findViewById(R.id.activity_main_seekbar);
        mProgressTv = (TextView) findViewById(R.id.activity_main_progress_tv);
    }

    /**
     * 处理seekbar滑动事件
     */
    private void setSeekBar() {
        mSeekBar.setMax(100);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                mOriginImg.setAlpha((int) (255 - mAlpha * 2.55));
                mProgressTv.setText(String.valueOf(mAlpha));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
