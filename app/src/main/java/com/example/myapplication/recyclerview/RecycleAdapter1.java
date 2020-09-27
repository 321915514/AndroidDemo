package com.example.myapplication.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.security.KeyStore;
import java.util.List;
import java.util.Map;

public class RecycleAdapter1 extends RecyclerView.Adapter<RecycleAdapter1.ViewHolder>  {

    private List<Map<String,String>> list;
    private Context context;
    public RecycleAdapter1(List<Map<String,String>> list,Context context){
        this.list = list;
        this.context = context;
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item);
            textView = itemView.findViewById(R.id.tv_title);
            textView1 = itemView.findViewById(R.id.tv_author);
        }
    }
    public interface OnItemClickListener{
        void OnItemListener(int position);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecycleAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter1.ViewHolder holder, int position) {
        Glide.with(context).load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1600336005&di=1e912c589bf62804c61b1290fb083b72&src=http://hbimg.b0.upaiyun.com/912ac08017fbd2c549e096fd81ad90bc3287057a15d1b-jyRojM_fw658").into(holder.imageView);
        Map<String,String> item = list.get(position);
        for (Map.Entry<String,String> item1: item.entrySet()) {
            holder.textView1.setText(item1.getKey());
            holder.textView.setText(item1.getValue());
        }
        // 可点击
        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.OnItemListener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
