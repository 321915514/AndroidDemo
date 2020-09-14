package com.example.myapplication.event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity {
    private Button mEventRegister,mIntent;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        mEventRegister = findViewById(R.id.bt_register);
        mIntent = findViewById(R.id.bt_intent);
        mTextView = findViewById(R.id.tv_message);
        EventBus.getDefault().register(this);


        mIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EventBusActivity2.class));
            }
        });
//        mEventRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EventBus.getDefault().register(this);
//            }
//        });
    }
    // 保证了订阅者和发布者都是用的同一个实例
    @Subscribe(threadMode = ThreadMode.MAIN,priority = 1 )
    public void onGetMessage(MessageWrap wrap){
        mTextView.setText(wrap.getMessage());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}