package com.example.lenovo.jdapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.jdapp.R;
import com.example.lenovo.jdapp.bean.Sudoku;

import java.util.List;

/**
 * Created by lenovo on 2018/10/17.
 */

public class SudokuAdapter extends BaseAdapter{

    private Context context;
    private List<Sudoku.DataBean> list;


    public SudokuAdapter(Context context, List<Sudoku.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view== null){

            holder = new ViewHolder();
            view=View.inflate(context,R.layout.item_first_sudoku,null);
            holder.imgLogo = view.findViewById(R.id.img_logo);
            holder.txtTitle = view.findViewById(R.id.txt_title);
            view.setTag(holder);
        }else {
         holder = (ViewHolder) view.getTag();
        }

        holder.txtTitle.setText(list.get(i).getName());
        Glide.with(context).load(list.get(i).getIcon()).into(holder.imgLogo);

        return view;
    }

    class ViewHolder{
        private ImageView imgLogo;
        private TextView txtTitle;
    }

}
