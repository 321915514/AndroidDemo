package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessageServices extends Service {
    public MessageServices() {
    }

    public static final int msg_SAY_HELLO = 1;

    private Messenger messenger = new Messenger(new MessageServices.inComingHandle());

    class inComingHandle extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case msg_SAY_HELLO:
                    Log.e("tag","thanks ,service receiver a message");
                    break;
            }
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("tag","onBind");
        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        Log.e("tag","onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e("tag","onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("tag","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
}
