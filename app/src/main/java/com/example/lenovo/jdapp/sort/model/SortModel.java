package com.example.lenovo.jdapp.sort.model;

import com.example.lenovo.jdapp.inter.ICallBack;
import com.example.lenovo.jdapp.utils.HttpUtils;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/10/18.
 */

public class SortModel {

    public void getData(String url, ICallBack callBack, Type type){

        HttpUtils.getInstance().get(url,callBack,type);
    }

}
