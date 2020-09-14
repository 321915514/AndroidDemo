package com.example.myapplication.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class ContainerActivity extends AppCompatActivity implements AFragment.IOnMessageClick {
    private AFragment aFragment;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        textView = findViewById(R.id.tv_title);
        // 实力话
        aFragment = AFragment.newInstance("World");
        getSupportFragmentManager().beginTransaction().add(R.id.btn_container,aFragment,"a").commitAllowingStateLoss();

    }

//    public void setData(String s){
//        textView.setText(s);
//    }

    @Override
    public void onClick(String s) {
        textView.setText(s);
    }
}