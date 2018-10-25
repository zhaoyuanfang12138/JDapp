package com.example.lenovo.jdapp.regist.presenter;


import android.content.Intent;
import android.text.TextUtils;

import com.example.lenovo.jdapp.bean.RegistBean;
import com.example.lenovo.jdapp.inter.ICallBack;
import com.example.lenovo.jdapp.login.view.LoginActivity;
import com.example.lenovo.jdapp.regist.model.RegistModel;
import com.example.lenovo.jdapp.regist.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/10/16.
 */

public class RegistPresenter {

    private RegistModel model;
    private IView iv;

    public void attach(IView iv){
        this.iv = iv;
        model = new RegistModel();
    }

    public void checkData(){
        if (TextUtils.isEmpty(iv.getMobile()) || TextUtils.isEmpty(iv.getPassword())) {
            iv.checkDate(false);
        } else {
            iv.checkDate(true);
        }
    }

    public void regist(String url){
        url = url.concat("?mobile=").concat(iv.getMobile()).concat("&password=").concat(iv.getPassword());
        Type type = new TypeToken<RegistBean>() {
        }.getType();

        model.Regist(url, new ICallBack() {
            @Override
            public void success(Object obj) {
                iv.success(obj);
                RegistBean bean = (RegistBean) obj;
                if (bean.getCode().equals("0")){
                    iv.getContext().startActivity(new Intent(iv.getContext(), LoginActivity.class));
                    iv.gotoLogin();
                }

            }

            @Override
            public void failed(Exception e) {
                iv.failed(e);
            }
        },type);

    }

    public void detach(){
        if (iv!=null){
            iv=null;
        }
    }


}
