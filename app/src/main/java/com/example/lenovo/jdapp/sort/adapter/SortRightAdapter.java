package com.example.lenovo.jdapp.sort.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.jdapp.R;
import com.example.lenovo.jdapp.sort.bean.ChildBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/18.
 */

public class SortRightAdapter extends RecyclerView.Adapter<SortRightAdapter.ViewHolder> {
    private List<ChildBean.DataBean> list;
    private Context context;

    public SortRightAdapter(List<ChildBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = View.inflate(context, R.layout.item_sort_right, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.sort_rightTitle.setText(list.get(position).getName());
        List<ChildBean.DataBean.ListBean> lists = list.get(position).getList();
        MyAdapter myAdapter = new MyAdapter(context,lists);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,5);
        holder.sort_rightRecyc.setLayoutManager(gridLayoutManager);
        holder.sort_rightRecyc.setAdapter(myAdapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView sort_rightTitle;
        private final RecyclerView sort_rightRecyc;

        public ViewHolder(View itemView) {
            super(itemView);
            sort_rightTitle = itemView.findViewById(R.id.sort_right_title);
            sort_rightRecyc = itemView.findViewById(R.id.sort_right_recyclerview);
        }
    }

}
