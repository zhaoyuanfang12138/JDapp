package com.example.lenovo.jdapp.first.view;

import com.example.lenovo.jdapp.bean.FirstBean;
import com.example.lenovo.jdapp.bean.Sudoku;

import java.util.List;

/**
 * Created by lenovo on 2018/10/17.
 */

public interface IView {

    void success(List<FirstBean.DataBean> list);

   void getSudoku(List<Sudoku.DataBean> list);

    void failed(Exception e);

}
