package com.example.lenovo.jdapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.jdapp.R;

import com.example.lenovo.jdapp.sort.adapter.SortLeftAdapter;

import com.example.lenovo.jdapp.sort.adapter.SortRightAdapter;
import com.example.lenovo.jdapp.sort.bean.ChildBean;
import com.example.lenovo.jdapp.sort.bean.ShopBean;
import com.example.lenovo.jdapp.sort.presenter.SortPresenter;
import com.example.lenovo.jdapp.sort.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/10/16.
 */

public class SortFragment extends Fragment implements IView {

    private RecyclerView left;
    private RecyclerView right;
    private SortPresenter presenter;
    private List<ShopBean.DataBean> list;
    private List<ChildBean.DataBean> list1;
    private String urll = "https://www.zhaoapi.cn/product/getProductCatagory";
    private SortRightAdapter rightAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sort,container,false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        left = v.findViewById(R.id.recyclerleft);
        right = v.findViewById(R.id.recyclerright);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

    }

    private void initData() {
        presenter = new SortPresenter();
        presenter.attach(this);
        presenter.getleftshop();
        presenter.setView(1,"https://www.zhaoapi.cn/product/getProductCatagory");

        list = new ArrayList<>();
        //添加适配器
        SortLeftAdapter adapter = new SortLeftAdapter(getActivity(),list);
           //线性布局管理器
           RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getActivity());
          //添加分割线
        left.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        left.setLayoutManager(layoutManager);

        left.setAdapter(adapter);
        adapter.setOnItemClickListener(new SortLeftAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View itemView, int position) {
                   int cid = list.get(position).getCid();
                   presenter.setView(cid,urll);
            }
        });


//线性布局管理器
        RecyclerView.LayoutManager layoutManager1 =  new LinearLayoutManager(getActivity());
        list1= new ArrayList<>();
        right.setLayoutManager(layoutManager1);
        rightAdapter = new SortRightAdapter(list1,getActivity());
        right.setAdapter(rightAdapter);




    }

    @Override
    public void success(List<ShopBean.DataBean> data) {

        for (int i=0; i <data.size();i++){
            list.add(data.get(i));
        }

    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(getActivity(), "网络异常" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }





    @Override
    public void success1(List<ChildBean.DataBean> childBean) {

        if (childBean!=null){
            list1.clear();
            list1.addAll(childBean);
            Toast.makeText(getActivity(), "shuju"+list1.size(), Toast.LENGTH_SHORT).show();
            rightAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
