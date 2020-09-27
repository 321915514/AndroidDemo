package com.example.myapplication;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.model.GuidePage;
import com.blankj.utilcode.util.LogUtils;
import com.example.myapplication.component.GetRequest_Interface;
import com.example.myapplication.event.EventBusActivity;
import com.example.myapplication.event.MessageWrap;
import com.example.myapplication.model.Translation;
import com.example.myapplication.model.Translation1;
import com.example.myapplication.mvp.activity.LoginActivity;
import com.example.myapplication.recyclerview.RecyclerViewActivity1;
import com.example.myapplication.util.UtToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.UI)
    Button mBtnUI;
    @BindView(R.id.interaction)
    Button mInteraction;
    @BindView(R.id.foreground)
    Button mForeGround;
    @BindView(R.id.database)
    Button mDatabase;
    @BindView(R.id.bt_native)
    Button mNative;
    @BindView(R.id.bt_register)
    Button mRegister;


    private final int REQUEST_CODE = 1;

    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION};
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_slide_tab)
    Button btSlideTab;
    @BindView(R.id.bt_recyclerview)
    Button mRecycleView;
    @BindView(R.id.bt_send_notification)
    Button btSendNotification;
    @BindView(R.id.bt_scan_wifi)
    Button btScanWifi;
    @BindView(R.id.event_receive)
    TextView textView;


    public void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(PERMISSIONS_STORAGE, REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            grantResults[0] = PackageManager.PERMISSION_GRANTED;
            UtToast.show(getApplicationContext(), "授权成功");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
        onClick onClick = new onClick();
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBtnUI.setOnClickListener(onClick);
        mInteraction.setOnClickListener(onClick);
        mForeGround.setOnClickListener(onClick);
        mDatabase.setOnClickListener(onClick);
        mNative.setOnClickListener(onClick);
        btLogin.setOnClickListener(onClick);

        mRegister.setOnClickListener(onClick);

        btSlideTab.setOnClickListener(onClick);

        mRecycleView.setOnClickListener(onClick);

        // rxjava
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("TAG", "onSubscribe" + d);
                this.disposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e("TAG", "onNext" + integer);
                if (integer == 2) {
                    disposable.dispose();
                    Log.e("TAG", "断开链接");
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("TAG", "onError" + e);
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "onComplete");
            }
        });

        Observable.just(3, 1, 2, 4).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }

        });
        Integer i = 10;
        // defer
//        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
//            @Override
//            public ObservableSource<? extends Integer> call() throws Exception {
//                return Observable.just(i);
//            }
//        });
//        observable.subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull Integer integer) {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Long aLong) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

//        Observable.interval(2,1,TimeUnit.SECONDS).doOnNext(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Throwable {
//                Log.e("TAG","第" + aLong + "次轮询");
//
//                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fy.iciba.com/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();
//                // 创建网络请求接口
//                GetRequest_Interface request_interface = retrofit.create(GetRequest_Interface.class);
//                // 封装
//                Observable<Translation> observable = request_interface.getCall();
//                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Translation>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Translation translation) {
//                        translation.show();
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.e("TAG","请求失败"+e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//            }
//        }).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull Long aLong) {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.e("error","处理error"+e);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                final List<String> list = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    list.add("事件" + integer + "拆分的事件" + j);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe();

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
            }
        }).concatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                List<String> list = new ArrayList<>();
                for (int j = 0; j < 2; j++) {
                    list.add("我是事件" + integer + "拆分的事件" + j);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe();

        Observable.just(1, 2, 3, 4, 5).buffer(3, 1).subscribe(new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<Integer> integers) {
                Log.e("TAG", "buffer size" + integers.size());
                for (Integer v : integers) {
                    Log.e("TAG", "事件" + v);
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable.zip(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);


            }
        }), Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                Thread.sleep(1000);
                emitter.onNext("B");
                Thread.sleep(1000);
                emitter.onNext("C");
                Thread.sleep(1000);
                emitter.onNext("D");
                Thread.sleep(1000);
                emitter.onComplete();


            }
        }).subscribeOn(Schedulers.newThread()), new BiFunction<Integer, String, String>() {

            @Override
            public String apply(Integer integer, String s) throws Throwable {
                return integer + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.e("TAG", s);
            }
        });

        // reduce()
        Observable.just(1, 2, 3, 4).reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Throwable {
                return integer * integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.e("TAG", "reduce" + "------------" + integer);
            }
        });

        // collect
//        Observable.just(1,2,3,4).collect(new Callable<ArrayList<Integer>>() {
//
//            @Override
//            public ArrayList<Integer> call() throws Exception {
//                return new ArrayList<>();
//            }
//        },);


        String memoryCache = null;

        String diskCache = "磁盘缓存";

        Observable memory = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                if (memoryCache != null) {
                    emitter.onNext(memoryCache);
                } else {
                    emitter.onComplete();
                }
            }
        });
        Observable disk = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                if (diskCache != null) {
                    emitter.onNext(diskCache);
                } else {
                    emitter.onComplete();
                }
            }
        });

        Observable network = Observable.just("从网络获取");

        Observable.concat(memory, disk, network).firstElement().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.e("TAG", s);
            }
        });

        // retrofit实例
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fy.iciba.com/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(new OkHttpClient.Builder().build()).build();
        // 接口
        GetRequest_Interface getRequest_interface = retrofit.create(GetRequest_Interface.class);
        // 被观察者
        Observable<Translation> observable = getRequest_interface.getCall().subscribeOn(Schedulers.io());
        Observable<Translation1> observable1 = getRequest_interface.getCall_2().subscribeOn(Schedulers.io());
        // 发送
        // s -> Log.e("TAG", "最终返回的数据" + s), throwable -> Log.e("TAG", "失败")
        Observable.zip(observable1, observable, (translation1, translation) -> translation1.show() + translation.show()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        // okhttp 请求
        // 创建机场
        OkHttpClient client = new OkHttpClient();
        // builder模式
        Request request = new Request.Builder().url("https://www.baidu.com").build();

        // 创建call

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "error" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();

                runOnUiThread(() -> {
//                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    LogUtils.d(result);
                });
            }
        });

        // guide
        //NewbieGuide.with(this).setLabel("guideMain").addGuidePage(GuidePage.newInstance().addHighLight(mBtnUI).setLayoutRes(R.layout.simple_guide_view)).alwaysShow(true).show();


        // 位置


        // wifi scan
        btScanWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        if(intent.getAction().equals(wifiManager.SCAN_RESULTS_AVAILABLE_ACTION)){
                            List results = wifiManager.getScanResults();
                            if(request != null){
                                Log.e("TAG",""+results.size());
                            }
                        }

                    }
                };
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
                getApplicationContext().registerReceiver(wifiScanReceiver, intentFilter);

                boolean success = wifiManager.startScan();
                if (!success) {
                    scanFailure();
                }
            }
        });



        btSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });


    }

    private WifiManager wifiManager;

    void scanSuccess() {
        List<ScanResult> results = wifiManager.getScanResults();
        for (ScanResult item : results) {
            Log.e("TAG", item.BSSID + ":" + item.SSID);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true,priority = 1)
    public void handlermessage(MessageWrap wrap){
        EventBus.getDefault().post("垃圾");
        textView.setText(wrap.getMessage());
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true,priority = 1)
    public void handlermessage(String wrap){
        textView.setText(wrap);
    }

    void scanFailure() {
        Log.e("TAG", "error wifi");
    }

    void sendNotification() {
        // PendingIntent.FLAG_UPDATE_CURRENT
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), UIActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK), PendingIntent.FLAG_UPDATE_CURRENT);

        //
        /**
         * notification
         */
        // notificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 创建notification 对象
        String channelId = "channel1";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("123", "123", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("描述");
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300});
            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(getApplicationContext(), channelId)
                    .setCategory(Notification.CATEGORY_MESSAGE)
                    .setSmallIcon(R.drawable.jpush_notification_icon)
                    .setContentTitle("通知")
                    // 全屏通知
                    //.setFullScreenIntent(pendingIntent,true)
                    .setStyle(new Notification.BigTextStyle().bigText("much longer text that cannot fit one line , so good this notification ,i like it's"))
                    .setContentText("hello world")
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true).build();
            notificationManager.notify(0, notification);
            // setContentIntent(pendingIntent)

            // 添加震动


        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK){
            if(data.getExtras()!= null){
                Bundle bundle = data.getExtras();
                int age = bundle.getInt("age");
                String des = bundle.getString("Hello");
                UtToast.show(getApplicationContext(),""+age+des);
            }
        }
    }

    class onClick implements View.OnClickListener {
        Intent intent = null;

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.UI:
                    intent = new Intent(MainActivity.this, UIActivity.class);
                    startActivityForResult(intent,123);


                    break;
                case R.id.interaction:
                    intent = new Intent(MainActivity.this, MessageActivity.class);
                    break;
                case R.id.foreground:
                    intent = new Intent(MainActivity.this, ForeGroundActivity.class);
                    break;
                case R.id.database:
                    intent = new Intent(MainActivity.this, RoomDatabaseActivity.class);
                    break;
                case R.id.bt_native:
                    intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    break;

                case R.id.bt_register:
                    intent = new Intent(MainActivity.this, EventBusActivity.class);
                    break;
                case R.id.bt_login:
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    break;
                case R.id.bt_slide_tab:
                    intent = new Intent(getApplicationContext(), SlideActivity.class);
                    break;
                case R.id.bt_recyclerview:
                    intent = new Intent(getApplicationContext(), RecyclerViewActivity1.class);
                    break;
            }
            startActivity(intent);
        }


    }
}
