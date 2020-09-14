package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.BatchUpdateException;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start;
    private Button stop;
    private Button mData;
    private ServiceConnection connection;
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        start = findViewById(R.id.start_service);
        stop = findViewById(R.id.Stop_service);
        mData = findViewById(R.id.getData);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        mData.setOnClickListener(this);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e("tag","绑定调用成功：onServiceConnected");
                MyService.LocalBinder binder = (MyService.LocalBinder) iBinder;
                myService = binder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
    }

    @Override
    public void onClick(View view) {
         Intent intent = new Intent(this,MyService.class);
        switch (view.getId()){
            case R.id.start_service:
                Log.e("tag","bindService");
                bindService(intent,connection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.getData:
                if(myService!= null){
                    Log.e("getdata","数据："+myService.getCount());
                }
                break;

            case R.id.Stop_service:
                Log.e("tag","unbindService");
                if(myService != null) {
                    myService = null;
                    unbindService(connection);
                }
                break;
        }
    }


}
