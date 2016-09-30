package com.wisn.pm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wisn.pm.R;

/**
 * <b>Create Date:</b> 2016/9/30<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public class SetPinActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText pinPassword;
    private EditText pinPassword2;
    private Button setPin_bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpin);
        initView();
    }

    @Override
    public void onClick(View view) {
        if(view==setPin_bt){
            //设置pin
            submit();
        }
    }

    private void initView() {
        pinPassword = (EditText) findViewById(R.id.pinPassword);
        pinPassword2 = (EditText) findViewById(R.id.pinPassword2);
        setPin_bt = (Button) findViewById(R.id.setPin_bt);
        setPin_bt.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String pinPasswordString = pinPassword.getText().toString().trim();
        if (TextUtils.isEmpty(pinPasswordString)) {
            Toast.makeText(this, "Input Pin Password", Toast.LENGTH_SHORT).show();
            return;
        }

        String pinPassword2String = pinPassword2.getText().toString().trim();
        if (TextUtils.isEmpty(pinPassword2String)) {
            Toast.makeText(this, "Input Pin Password Again", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
