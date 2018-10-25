package com.example.lenovo.jdapp.sort.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.jdapp.R;
import com.example.lenovo.jdapp.sort.bean.ShopBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/18.
 */

public class SortLeftAdapter extends RecyclerView.Adapter<SortLeftAdapter.Holder>{

    private Context context;
    private List<ShopBean.DataBean> list;

    public SortLeftAdapter(Context context, List<ShopBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    //接口回调
    public interface OnItemClickListener{
        void OnItemClick(View itemView,int position);
    }

    private OnItemClickListener itemClickListener;

   public void setOnItemClickListener(OnItemClickListener itemClickListener){
       this.itemClickListener=itemClickListener;
   }



    @NonNull
    @Override
    public SortLeftAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = View.inflate(context,R.layout.item_sort_left,null);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SortLeftAdapter.Holder holder, final int position) {

        holder.txtLeft.setText(list.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.OnItemClick(view,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        private final TextView txtLeft;

        public Holder(View itemView) {
            super(itemView);
            txtLeft = itemView.findViewById(R.id.txt_left_title);
        }
    }

}


