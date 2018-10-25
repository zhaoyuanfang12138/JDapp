package com.example.lenovo.jdapp.regist.view;


import android.content.Context;

/**
 * Created by lenovo on 2018/10/16.
 */

public interface IView<T> {

    void success(T t);

    void failed(Exception e);

    String getMobile();

    String getPassword();

    void checkDate(boolean isChecked);

    Context getContext();

    void gotoLogin();

}
