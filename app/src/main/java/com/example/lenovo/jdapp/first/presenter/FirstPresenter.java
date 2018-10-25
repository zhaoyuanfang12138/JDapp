package com.example.lenovo.jdapp.first.presenter;

import com.example.lenovo.jdapp.bean.FirstBean;
import com.example.lenovo.jdapp.bean.Sudoku;
import com.example.lenovo.jdapp.first.model.FirstModel;
import com.example.lenovo.jdapp.fragment.FirstFragment;
import com.example.lenovo.jdapp.inter.ICallBack;
import com.example.lenovo.jdapp.first.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lenovo on 2018/10/17.
 */

public class FirstPresenter {

    private IView iv;
    private FirstModel model;

    public void attach(FirstFragment iv){
        this.iv = iv;
        model = new FirstModel();
    }

    //轮播
    public void getBanner(){
        Type type = new TypeToken<FirstBean>(){}.getType();
        model.getData("https://www.zhaoapi.cn/ad/getAd", new ICallBack() {
            @Override
            public void success(Object obj) {
                FirstBean firstBean = (FirstBean) obj;
                if (firstBean != null){
                    List<FirstBean.DataBean> data = firstBean.getData();
                    iv.success(data);
                }

            }

            @Override
            public void failed(Exception e) {
                iv.failed(e);
            }
        },type);

    }


    //九宫格
    public void getSudoku(){
        Type type = new TypeToken<Sudoku>() {
        }.getType();

        model.getData("https://www.zhaoapi.cn/product/getCatagory", new ICallBack() {
            @Override
            public void success(Object obj) {
                Sudoku sudoku = (Sudoku) obj;
                if (sudoku != null){
                    List<Sudoku.DataBean> data = sudoku.getData();
                    iv.getSudoku(data);
                }

            }

            @Override
            public void failed(Exception e) {
              iv.failed(e);
            }
        },type);

    }


    public void detach(){
        if (iv != null){
            iv = null;
        }
    }



}
