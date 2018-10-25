package com.example.lenovo.jdapp.login.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jdapp.MainActivity;
import com.example.lenovo.jdapp.R;
import com.example.lenovo.jdapp.bean.LoginBean;
import com.example.lenovo.jdapp.login.presenter.LoginPresenter;
import com.example.lenovo.jdapp.regist.view.RegistActivity;

public class LoginActivity extends AppCompatActivity implements IView<LoginBean>, View.OnClickListener {

    private TextView txtRegist;
    private EditText edMobile;
    private EditText edPassword;
    private Button btnLogin;
    private LoginPresenter presenter;
    private ProgressDialog pd;

    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        setlistener();

        initData();

    }

    private void initData() {
        presenter = new LoginPresenter();
        presenter.attach(this);
        presenter.isFirst();

        pd = new ProgressDialog(this);
        pd.setMessage("正在登录,请稍后...");

    }

    private void setlistener() {
       btnLogin.setOnClickListener(this);
       txtRegist.setOnClickListener(this);
    }

    private void initView() {
        edMobile = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        btnLogin = findViewById(R.id.btn_login);
        txtRegist = findViewById(R.id.txt_regist);

    }

    @Override
    public void success(LoginBean loginBean) {
         if (loginBean != null){
             Toast.makeText(this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
         }

    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "网络异常" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getMobile() {
        return edMobile.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return edPassword.getText().toString();
    }

    @Override
    public void setMobile(String mobile) {
           edMobile.setText(mobile);
    }

    @Override
    public void setPassword(String password) {
            edPassword.setText(password);
    }

    @Override
    public void check(boolean isChecked) {
            if (isChecked){
                //显示进度条
                presenter.login("https://www.zhaoapi.cn/user/login");
            }
    }

    @Override
    public void show() {
          pd.show();
    }

    @Override
    public void dissmiss() {
           pd.dismiss();
    }

    @Override
    public void gotoMain() {
           Intent intent = new Intent(this,MainActivity.class);
           startActivity(intent);
           finish();
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.detach();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                presenter.check();
                break;

            case R.id.txt_regist:

        Intent intent = new Intent(LoginActivity.this,RegistActivity.class);
        startActivity(intent);
        break;
    }
    }
}
