package com.example.myapplication.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class CataractAdapter extends RecyclerView.Adapter<CataractAdapter.LinearViewHolder> {

    private Context context;
    public CataractAdapter(Context context){
        this.context=context;
    }

    @Override
    public CataractAdapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CataractAdapter.LinearViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_cataract_item,parent,false));
    }

    @Override
    public void onBindViewHolder(CataractAdapter.LinearViewHolder holder, final int position) {
        if(position%2!=0){
            holder.imageView.setImageResource(R.drawable.screen1);
        }else {
            holder.imageView.setImageResource(R.drawable.screen2);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"pos: "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public LinearViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_cataract);
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }
}
