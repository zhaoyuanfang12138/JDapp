package com.example.lenovo.jdapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.jdapp.R;
import com.example.lenovo.jdapp.login.view.LoginActivity;

/**
 * Created by lenovo on 2018/10/16.
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private TextView txtLogin;
    private TextView txtRegist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my,container,false);
        txtLogin = v.findViewById(R.id.txt_login);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
          txtLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                break;

        }
    }
}
