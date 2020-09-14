package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.sql.BatchUpdateException;

public class ForeGroundService extends Service {

    private static final int NOTIFATION_ID = 0x001;

    private boolean isRemove = false;

    public ForeGroundService() {
    }

    private void createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationChannel.DEFAULT_CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.screen1));
        //禁止点击删除按钮删除
        builder.setAutoCancel(false);
        // 禁止滑动删除
        builder.setOngoing(true);
        // 时间显示
        builder.setShowWhen(true);
        // 通知栏的标题内容
        builder.setContentTitle("i am a foreground service");
        // 创建通知
        Notification notification = builder.build();
        // 设置前台服务
        startForeground(NOTIFATION_ID,notification);


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            if (bundle.get("cmd") != null) {
                int i = (int) bundle.get("cmd");
                if(i ==0){
                    if(!isRemove){
                        createNotification();
                    }
                    isRemove = true;
                }else {
                    // 移除服务
                    if(isRemove){
                        stopForeground(true);
                    }
                    isRemove = false;
                }
            }
        }
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(isRemove){
            stopForeground(true);
        }
        isRemove = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
