package com.wisn.pmlib.activity.svg;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wisn.pmlib.R;

public class SvgActivity extends AppCompatActivity {

    private ImageView mImageView1;
    private ImageView mImageView2;
    private boolean hearisStart=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        mImageView1 = (ImageView) findViewById(R.id.svgImage1);
        mImageView2 = (ImageView) findViewById(R.id.svgImage2);
        mImageView1.setImageResource(R.drawable.ic_camera_roll_black_24dp);
        mImageView2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                AnimatedVectorDrawable tickToCross = (AnimatedVectorDrawable) SvgActivity.this.getDrawable(R.drawable.heart_full_anim);
                AnimatedVectorDrawable crossToTick = (AnimatedVectorDrawable)  SvgActivity.this.getDrawable(R.drawable.heart_empty_anim);
                AnimatedVectorDrawable target=hearisStart?crossToTick:tickToCross;
                mImageView2.setImageDrawable(target);
                if(target!=null){
                    target.start();
                }
                hearisStart=!hearisStart;
            }
        });
    }
}
