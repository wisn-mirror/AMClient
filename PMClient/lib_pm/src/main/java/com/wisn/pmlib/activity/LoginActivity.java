package com.wisn.pmlib.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wisn.pmlib.R;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText ip;
    private EditText username;
    private EditText password;
    private Button login;
    private Button loginout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        checkAccount();
    }

    /**
     * 检测用户是否登陆
     */
    private void checkAccount() {
    }

    private void initView() {
        ip = (EditText) findViewById(R.id.ip);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        loginout = (Button) findViewById(R.id.loginout);
        login.setOnClickListener(this);
        loginout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                submit();
                break;
            case R.id.loginout:
                loginOut();
                break;
        }
    }



    /**
     * 已经登陆的View
     */
    public  void  alreadyLoginView(){
         ip.setVisibility(View.GONE);
         username.setVisibility(View.GONE);
         password.setVisibility(View.GONE);
        login.setVisibility(View.GONE);
        loginout.setVisibility(View.VISIBLE);
    }


    public  void  LoginView(){
        ip.setVisibility(View.VISIBLE);
        username.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);
        login.setVisibility(View.VISIBLE);
        loginout.setVisibility(View.GONE);
    }

    private void submit() {
        String ipString = ip.getText().toString().trim();
        if (TextUtils.isEmpty(ipString)) {
            Toast.makeText(this, "IP不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String usernameString = username.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordString = password.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "密码", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
    }
    /**
     * 登出
     */
    private void loginOut() {

    }
}
