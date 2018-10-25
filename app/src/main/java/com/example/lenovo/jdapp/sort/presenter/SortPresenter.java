package com.example.lenovo.jdapp.sort.presenter;

import com.example.lenovo.jdapp.fragment.SortFragment;
import com.example.lenovo.jdapp.inter.ICallBack;
import com.example.lenovo.jdapp.sort.bean.ChildBean;
import com.example.lenovo.jdapp.sort.view.IView;
import com.example.lenovo.jdapp.sort.bean.ShopBean;
import com.example.lenovo.jdapp.sort.model.SortModel;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lenovo on 2018/10/18.
 */

public class SortPresenter {

    private IView iv;
    private SortModel model;


    public void attach(IView iv){
        this.iv =  iv;
        model = new SortModel();
    }


    public void getleftshop(){
        Type type = new TypeToken<ShopBean>(){}.getType();

        model.getData("https://www.zhaoapi.cn/product/getCatagory", new ICallBack() {
            @Override
            public void success(Object obj) {
               ShopBean shopBean = (ShopBean) obj;
               if (shopBean!=null){
                   List<ShopBean.DataBean> data = shopBean.getData();
                   iv.success(data);
               }

            }

            @Override
            public void failed(Exception e) {
                 iv.failed(e);
            }
        },type);

    }


    public void setView(int cid,String url){
        String url2 = url+"?cid="+cid;

        Type type = new TypeToken<ChildBean>(){}.getType();

        model.getData(url2, new ICallBack() {
            @Override
            public void success(Object obj) {

                ChildBean childBean = (ChildBean) obj;
//                if(childBean!=null){
                    List<ChildBean.DataBean> data = childBean.getData();
                    iv.success1(data);
//                }
                
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
