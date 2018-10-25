package com.example.lenovo.jdapp.login.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.lenovo.jdapp.bean.LoginBean;
import com.example.lenovo.jdapp.inter.ICallBack;
import com.example.lenovo.jdapp.login.model.LoginModel;
import com.example.lenovo.jdapp.login.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/10/16.
 */

public class LoginPresenter {

    private IView iv;
    private LoginModel loginModel;

    public void attach(IView iv){
        this.iv = iv;
        loginModel = new LoginModel();
    }

    public void detach(){
        if (iv!=null){
            iv=null;
        }
    }

    //验证
    public void check(){
        if (TextUtils.isEmpty(iv.getMobile()) || TextUtils.isEmpty(iv.getPassword())){
            iv.check(false);
        }else {
            iv.check(true);
        }
    }


    public void isFirst(){
        SharedPreferences sp = iv.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String mobile = sp.getString("mobile", "");
        String password = sp.getString("password", "");
        //不是第一次登录
        if(!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(password)){
            iv.setMobile(mobile);
            iv.setPassword(password);
        }

    }


    public void login(String url){
        iv.show();
        final String mobile = iv.getMobile();
        final String password = iv.getPassword();


        url = url.concat("?mobile=").concat(mobile).concat("&password=").concat(password);
        Type type = new TypeToken<LoginBean>(){}.getType();

        loginModel.login(url, new ICallBack() {
            @Override
            public void success(Object obj) {
                iv.dissmiss();
                iv.success(obj);

                SharedPreferences sp = iv.getContext().getSharedPreferences("config",Context.MODE_PRIVATE);
                sp.edit().putString("username",mobile)
                        .putString("password",password)
                        .commit();

                 LoginBean loginBean = (LoginBean) obj;
                if (loginBean.getCode().equals("0")){
                    iv.gotoMain();
                }

            }

            @Override
            public void failed(Exception e) {
               iv.dissmiss();
               iv.failed(e);

            }
        },type);

    }


}
