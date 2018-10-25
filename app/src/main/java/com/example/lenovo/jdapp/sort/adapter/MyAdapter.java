package com.example.lenovo.jdapp.sort.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.test.mock.MockContext;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.jdapp.R;
import com.example.lenovo.jdapp.sort.bean.ChildBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private Context mContext;
    private List<ChildBean.DataBean.ListBean> lists;

    public MyAdapter(Context mContext, List<ChildBean.DataBean.ListBean> lists) {
        this.mContext = mContext;
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = View.inflate(mContext, R.layout.sort_right_child,null);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          holder.content.setText(lists.get(position).getName());
Glide.with(mContext).load(lists.get(position).getIcon()).into(holder.img1);
    }



    @Override
    public int getItemCount() {
        return lists.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img1;
        private final TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            content = itemView.findViewById(R.id.content);
        }
    }

}
