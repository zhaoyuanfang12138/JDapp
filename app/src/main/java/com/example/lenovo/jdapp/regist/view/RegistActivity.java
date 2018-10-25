package com.example.lenovo.jdapp.regist.view;


import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jdapp.R;
import com.example.lenovo.jdapp.bean.RegistBean;
import com.example.lenovo.jdapp.regist.presenter.RegistPresenter;

public class RegistActivity extends AppCompatActivity implements IView<RegistBean>, View.OnClickListener {

    private TextView txtRegistBack;
    private TextView txtQiYeZhuCe;
    private EditText edRegistMobile;
    private EditText edRegistPassword;
    private Button btnRegistRegist;
    private TextView txtLianXikefu;
    private RegistPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        initView();

        initData();

        setlistener();

    }

    private void setlistener() {
        btnRegistRegist.setOnClickListener(this);
    }

    private void initData() {
        presenter = new RegistPresenter();
        presenter.attach(this);
    }

    private void initView() {
        txtRegistBack = findViewById(R.id.txt_regist_back);
        txtQiYeZhuCe = findViewById(R.id.txt_qiyezhuce);
        edRegistMobile = findViewById(R.id.txt_regist_mobile);
        edRegistPassword = findViewById(R.id.txt_regist_password);
        btnRegistRegist = findViewById(R.id.btn_regist_regist);
        txtLianXikefu = findViewById(R.id.txt_lianxikefu);
    }

    @Override
    public void success(RegistBean registBean) {

        Toast.makeText(this, registBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,"网络异常"+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getMobile() {
        return edRegistMobile.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return edRegistPassword.getText().toString();
    }

    @Override
    public void checkDate(boolean isChecked) {
        if (isChecked) {
            presenter.regist("https://www.zhaoapi.cn/user/reg");
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void gotoLogin() {
            finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_regist_regist:
                presenter.checkData();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
