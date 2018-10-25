package com.example.lenovo.jdapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.lenovo.jdapp.fragment.FindFragment;
import com.example.lenovo.jdapp.fragment.FirstFragment;
import com.example.lenovo.jdapp.fragment.MyFragment;
import com.example.lenovo.jdapp.fragment.ShoppingCartFragment;
import com.example.lenovo.jdapp.fragment.SortFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtFirst;
    private TextView txtScot;
    private TextView txtFind;
    private TextView txtShopping;
    private TextView txtMy;
    private ViewPager vpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     initView();

     setListener();


        final List<Fragment> list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SortFragment());
        list.add(new FindFragment());
        list.add(new ShoppingCartFragment());
        list.add(new MyFragment());

        vpFragment.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

    }

    private void setListener() {
        txtFirst.setOnClickListener(this);
        txtFind.setOnClickListener(this);
        txtScot.setOnClickListener(this);
        txtShopping.setOnClickListener(this);
        txtMy.setOnClickListener(this);
    }

    private void initView() {
        txtFirst = findViewById(R.id.txt_first);
        txtScot = findViewById(R.id.txt_scot);
        txtFind = findViewById(R.id.txt_find);
        txtShopping = findViewById(R.id.txt_shoppingcart);
        txtMy = findViewById(R.id.txt_my);
        vpFragment = findViewById(R.id.vp_fragment);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_first:
                vpFragment.setCurrentItem(0);
                break;
            case R.id.txt_scot:
                vpFragment.setCurrentItem(1);
                break;
            case R.id.txt_find:
                vpFragment.setCurrentItem(2);
                break;
            case R.id.txt_shoppingcart:
                vpFragment.setCurrentItem(3);
                break;
            case R.id.txt_my:
                vpFragment.setCurrentItem(4);
                break;
        }
    }
}
