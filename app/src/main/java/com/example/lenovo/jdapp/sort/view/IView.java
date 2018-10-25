package com.example.lenovo.jdapp.sort.view;

import com.example.lenovo.jdapp.sort.bean.ChildBean;
import com.example.lenovo.jdapp.sort.bean.ShopBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/18.
 */

public interface IView {

     void success(List<ShopBean.DataBean> data);

     void failed(Exception e);

    void success1(List<ChildBean.DataBean> childBean);

}
