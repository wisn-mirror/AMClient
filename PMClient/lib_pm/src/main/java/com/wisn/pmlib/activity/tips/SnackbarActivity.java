package com.wisn.pmlib.activity.tips;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.base.BaseActivity;
import com.wisn.pmlib.utils.SnackbarUtils;

/**
 * Created by wisn on 2017/4/1.
 */

public class SnackbarActivity extends BaseActivity implements View.OnClickListener{

    private Button mSnackbar_action,snackbar_custom_view;
    private View mSnackbar_root;
    private View mSnackbar_bottom;
    private View mSnackbar_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        mSnackbar_root = findViewById(R.id.snackbar_root);
        mSnackbar_bottom = findViewById(R.id.snackbar_bottom);
        mSnackbar_top = findViewById(R.id.snackbar_top);
        mSnackbar_action = (Button) findViewById(R.id.snackbar_action);
        snackbar_custom_view = (Button) findViewById(R.id.snackbar_custom_view);
       // CoordinatorLayout  CoordinatorLayout=new CoordinatorLayout();
        mSnackbar_action.setOnClickListener(this);
        snackbar_custom_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==mSnackbar_action){
            Snackbar.make(mSnackbar_top,"这是massage", Snackbar.LENGTH_LONG).setAction("这是action", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(SnackbarActivity.this, "你点击了action", Toast.LENGTH_SHORT).show();
                    Snackbar.make(mSnackbar_top,"action", Snackbar.LENGTH_LONG).show();
                }
            }).setActionTextColor(getResources().getColor(R.color.white)).show();

        }else if(v==snackbar_custom_view){
            Snackbar snackbar= SnackbarUtils.ShortSnackbar(mSnackbar_top, "妹子删了你发出的消息", SnackbarUtils.Warning).setActionTextColor(
                    Color.RED).setAction("再次发送", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SnackbarActivity.this,"dddd",Toast.LENGTH_SHORT).show();
                            SnackbarUtils.LongSnackbar(mSnackbar_top,"妹子已将你拉黑",SnackbarUtils.blue).setActionTextColor(Color.WHITE).show();
                        }
                    },2000);
                }
            });
            snackbar.show();
//
//            SnackbarUtils.SnackbarAddView(snackbar,R.layout.snackbar_addview,0);
//
//            SnackbarUtils.SnackbarAddView(snackbar,R.layout.snackbar_addview2,2);
//
//            snackbar.show();
        }
    }
}
