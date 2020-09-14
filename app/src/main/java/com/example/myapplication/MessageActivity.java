package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.nio.MappedByteBuffer;

public class MessageActivity extends AppCompatActivity  implements View.OnClickListener {
    private Button mBind,mUnBind,mSendMessage;
    private Messenger mService = null;
    private ServiceConnection connection;
    boolean mBound;
    private Messenger messenger = new Messenger(new receiveMessageHandler());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mBind = findViewById(R.id.bind);
        mUnBind = findViewById(R.id.Unbind);
        mSendMessage = findViewById(R.id.send_message);
        mUnBind.setOnClickListener(this);
        mBind.setOnClickListener(this);
        mSendMessage.setOnClickListener(this);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e("tag","onServiceConnected");
                mService = new Messenger(iBinder);
                mBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mBound = false;
            }
        };
    }

    class receiveMessageHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case MessageService.msg_SAY_HELLO:
                    Log.e("tag","ok~ i receive a message"+msg.getData().get("hi"));
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bind:
                bindService(new Intent(MessageActivity.this, MessageService.class),connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.send_message:
                Log.e("tag","sayHello");
                sayHello();
                break;
            case R.id.Unbind:
                Log.e("tag","unbindService");
                if (mBound){
                    unbindService(connection);
                    mBound = false;
                }
                break;

        }
    }

    private void sayHello() {
        if(!mBound) return;
        Message messenge = Message.obtain(null,MessageService.msg_SAY_HELLO,0,0);
        messenge.replyTo = messenger;
        try {
            mService.send(messenge);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
