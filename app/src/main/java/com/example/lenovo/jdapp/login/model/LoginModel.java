package com.example.lenovo.jdapp.login.model;

import com.example.lenovo.jdapp.inter.ICallBack;
import com.example.lenovo.jdapp.utils.HttpUtils;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/10/16.
 */

public class LoginModel {

    public void login(String url, ICallBack callBack, Type type) {
        HttpUtils.getInstance().get(url, callBack, type);
    }
}
