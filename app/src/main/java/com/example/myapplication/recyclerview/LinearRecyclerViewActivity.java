package com.example.myapplication.recyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.util.UtToast;

public class LinearRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_recycler_view);
        recyclerView = findViewById(R.id.re_main);
        RecyclerView.LayoutManager layoutManager = new RecyclerView.LayoutManager() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) recyclerView.getLayoutParams();
                layoutParams.setMarginStart(30);
                return layoutParams;
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(LinearRecyclerViewActivity.this));
        recyclerView.setAdapter(new LinearAdapter(LinearRecyclerViewActivity.this));
        recyclerView.addItemDecoration(new MyDecoration());

        recyclerView.addOnScrollListener(OnScrollListener);
    }

    public RecyclerView.OnScrollListener  OnScrollListener = new RecyclerView.OnScrollListener(){
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            Log.e("TAG",""+recyclerView);
            UtToast.show(getApplicationContext(),""+newState);
        }
    };

    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect,View view,RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect,view,parent,state);
            outRect.set(0,0,0,getResources().getDimensionPixelSize(R.dimen.dividedheight));
        }
    }
}
