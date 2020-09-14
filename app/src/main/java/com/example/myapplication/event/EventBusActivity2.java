package com.example.myapplication.event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ToastActivity;
import com.example.myapplication.util.UtToast;

import org.greenrobot.eventbus.EventBus;

public class EventBusActivity2 extends AppCompatActivity {
    private EditText mEvent;
    private Button mPublish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus2);
        mEvent = findViewById(R.id.et_event);
        mPublish = findViewById(R.id.bt_event_publish);

        mPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publishContent();
            }
        });

    }
    private  void  publishContent(){
        String msg = mEvent.getText().toString();
        EventBus.getDefault().post(MessageWrap.getInstance(msg));
        UtToast.toast(getApplicationContext(),"publish msg"+msg, Toast.LENGTH_LONG);
    }
}