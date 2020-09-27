package com.example.myapplication.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventBusActivity extends AppCompatActivity {

    @BindView(R.id.bt_register)
    Button mRegister;
    @BindView(R.id.bt_intent)
    Button mIntent;
    @BindView(R.id.tv_message)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);


        mIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EventBusActivity2.class));
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
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 1)
    public void onGetMessage(MessageWrap wrap) {
        mTextView.setText(wrap.getMessage());
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}