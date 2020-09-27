package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.model.RecycleItemModel;
import com.example.myapplication.recyclerview.RecycleAdapter1;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<RecycleItemModel> list;
    private Context context;

    private OnItemClickListener onitemClickListener;

    public interface OnItemClickListener{
         void ItemClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
    }

    public  MyAdapter(List<RecycleItemModel> list, Context context){
        this.list = list;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        TextView content;
        TextView count;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
            count = itemView.findViewById(R.id.tv_count);
            date = itemView.findViewById(R.id.tv_date);

        }

    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_item_cm,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getUrl()).into(holder.imageView);
        holder.title.setText(list.get(position).getTitle());
        holder.content.setText(list.get(position).getContent());
        holder.count.setText(String.valueOf(list.get(position).getCount()));
        holder.date.setText(list.get(position).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onitemClickListener.ItemClickListener(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
