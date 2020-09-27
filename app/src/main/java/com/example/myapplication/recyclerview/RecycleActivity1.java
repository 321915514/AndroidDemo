package com.example.myapplication.recyclerview;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.RecycleItemModel1;
import com.example.myapplication.util.UtToast;
import com.siberiadante.customdialoglib.BottomPopupWindow;
import com.siberiadante.customdialoglib.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleActivity1 extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_share)
    Button btShare;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private boolean  share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle1);
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null) {
            String title = getIntent().getExtras().getString("title");
            share = getIntent().getExtras().getBoolean("share");
            tvTitle.setText(title);
            btShare.setOnClickListener(this);
        }
        btShare.setVisibility(share ? View.VISIBLE : View.GONE);

        List<RecycleItemModel1> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new RecycleItemModel1("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1473836766,4030812874&fm=26&gp=0.jpg","title"+i,"des"+i));
        }

        rvList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Adapter1 adapter1 = new Adapter1(R.layout.recycle_item,getApplicationContext(),list);

        rvList.setAdapter(adapter1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_share:
                new BottomPopupWindow(this).builder().addSheetItem("分享", Color.GRAY, new BottomPopupWindow.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int i) {
                        UtToast.show(getApplicationContext(),""+i);
                    }
                }).setCancelable(true).show();
        }
    }
}