package com.example.myapplication.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.gridview.GridViewActivity;

public class RecyclerViewActivity extends AppCompatActivity {
    private Button mBtn,mBtnHor, mBtnGrid,mBtnCataract;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mBtn = findViewById(R.id.btn_Re);
        mBtnHor = findViewById(R.id.btn_hor);
        mBtnGrid =  findViewById(R.id.btn_Grid);
        mBtnCataract = findViewById(R.id.btn_cataract);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewActivity.this,LinearRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        mBtnHor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewActivity.this,HorRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        mBtnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewActivity.this, GridRecyclerActivity.class);
                startActivity(intent);
            }
        });
        mBtnCataract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewActivity.this, CataractRecyclerActivity.class);
                startActivity(intent);
            }
        });

    }
}
