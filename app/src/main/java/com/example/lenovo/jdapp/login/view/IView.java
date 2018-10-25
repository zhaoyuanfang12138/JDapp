package com.example.lenovo.jdapp.login.view;

import android.content.Context;

/**
 * Created by lenovo on 2018/10/16.
 */

public interface IView <T>{

    void success(T t);

    void failed(Exception e);

    String getMobile();

    String getPassword();

    void setMobile(String mobile);

    void setPassword(String password);

    void check(boolean isChecked);

    void show();

    void dissmiss();

    void gotoMain();

    Context getContext();

}
