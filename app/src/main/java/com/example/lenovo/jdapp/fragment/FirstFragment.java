package com.example.lenovo.jdapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.jdapp.R;
import com.example.lenovo.jdapp.adapter.SudokuAdapter;
import com.example.lenovo.jdapp.bean.FirstBean;
import com.example.lenovo.jdapp.bean.Sudoku;
import com.example.lenovo.jdapp.first.presenter.FirstPresenter;
import com.example.lenovo.jdapp.first.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/10/16.
 */

public class FirstFragment extends Fragment implements IView  {
    private ImageView imgArCode;
    private EditText edSearchBox;
    private ImageView imgMessage;
    private ViewPager vpBanner;
    private List<Sudoku.DataBean> list;
    private GridView gvCatagory;
    public static final int FLAG = 123;
    private SudokuAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FLAG) {
                int currentItem = vpBanner.getCurrentItem();
                if (currentItem < list.size() - 1) {
                    currentItem++;
                } else {
                    currentItem = 0;
                }
                vpBanner.setCurrentItem(currentItem);
                sendEmptyMessageDelayed(FLAG, 3000);
            }
        }
    };
    private FirstPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        imgArCode = v.findViewById(R.id.img_ar_code);
        edSearchBox = v.findViewById(R.id.ed_search_box);
        imgMessage = v.findViewById(R.id.ed_message);
        vpBanner = v.findViewById(R.id.vp_banner);
        gvCatagory = v.findViewById(R.id.gv_catagory);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    private void initData() {
        presenter = new FirstPresenter();
        presenter.attach(this);
        presenter.getBanner();
        presenter.getSudoku();
        list = new ArrayList<>();
        //sudoku适配器
        adapter = new SudokuAdapter(getActivity(), list);
        gvCatagory.setAdapter(adapter);

    }


    @Override
    public void success(final List<FirstBean.DataBean> dataBeans) {
        Log.i("23456", "success: " + dataBeans.get(0).getTitle());
        vpBanner.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return dataBeans.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                ImageView img = new ImageView(getActivity());
                Glide.with(getActivity()).load(dataBeans.get(position).getIcon()).into(img);
                container.addView(img);

                return img;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        handler.sendEmptyMessageDelayed(FLAG, 3000);

    }

    @Override
    public void getSudoku(List<Sudoku.DataBean> dataBeans) {
        if (list != null) {
            list.clear();
            list.addAll(dataBeans);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(getActivity(), "网络异常" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter == null) {
            presenter.detach();
        }
    }
}
