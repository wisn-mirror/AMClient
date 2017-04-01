package com.wisn.pmlib.activity.tips;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.base.BaseActivity;

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
                    Toast.makeText(SnackbarActivity.this, "你点击了action", Toast.LENGTH_SHORT).show();
                }
            }).setActionTextColor(getResources().getColor(R.color.white)).show();

        }else if(v==snackbar_custom_view){

        }
    }
}
