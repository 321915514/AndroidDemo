package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private LocalBinder binder = new LocalBinder();
    private boolean quit = false;
    private int count = 0;


    public class LocalBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    private static String TAG = "MyService";
    private  String  msg = "myservice";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e(TAG,"myservice onBind");

        return binder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"myservice onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"myservice onCreate");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!quit){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }).start();
    }
    public int getCount(){
        return count;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        Log.e(TAG,"myservice onDestroy");
    }
}
