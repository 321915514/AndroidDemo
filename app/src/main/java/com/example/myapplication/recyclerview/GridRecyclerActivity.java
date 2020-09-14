package com.example.myapplication.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.gridview.GridViewActivity;

public class GridRecyclerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_recycler);
        recyclerView = findViewById(R.id.rv_grid);
        // 网格显示
        recyclerView.setLayoutManager(new GridLayoutManager(GridRecyclerActivity.this,3));
        recyclerView.setAdapter(new GridAdapter(GridRecyclerActivity.this));

    }
}
