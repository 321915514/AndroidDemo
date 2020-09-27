package com.example.myapplication.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.UIActivity;
import com.example.myapplication.util.UtToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity1 extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view1);
        ButterKnife.bind(this);
        List<Map<String,String>> list = new ArrayList<>();
        String s = "abcdwreweew";
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i <100 ; i++) {
            map.put(String.valueOf(i),i+s.substring(i/10));
            list.add(map);
        }
        RecycleAdapter1 adapter =new RecycleAdapter1(list,getApplicationContext());
        // 点击
        adapter.setOnItemClickListener(new RecycleAdapter1.OnItemClickListener() {
            @Override
            public void OnItemListener(int position) {
                startActivity(new Intent(getApplicationContext(), UIActivity.class));
            }
        });
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                UtToast.show(getApplicationContext(),"dx"+dx+":"+"dy"+dy);
            }
        });
    }



}