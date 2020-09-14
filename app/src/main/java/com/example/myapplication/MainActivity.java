package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.core.Controller;
import com.app.hubert.guide.listener.OnLayoutInflatedListener;
import com.app.hubert.guide.listener.OnPageChangedListener;
import com.app.hubert.guide.model.GuidePage;
import com.example.myapplication.component.GetRequest_Interface;
import com.example.myapplication.event.EventBusActivity;
import com.example.myapplication.model.Translation;
import com.example.myapplication.model.Translation1;
import com.example.myapplication.util.UtToast;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttReceivedMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCache;
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
    private Button mBtnUI, mInteraction, mForeGround,mDatabase,mNative, mRegister;




    private final int REQUEST_CODE = 1;

    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};



    public void init(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int permission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if(permission != PackageManager.PERMISSION_GRANTED){
                requestPermissions(PERMISSIONS_STORAGE,REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE){
            grantResults[0] = PackageManager.PERMISSION_GRANTED;
            UtToast.toast(getApplicationContext(),"授权成功",Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
        onClick onClick = new onClick();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnUI = findViewById(R.id.UI);
        mInteraction = findViewById(R.id.interaction);
        mForeGround = findViewById(R.id.foreground);
        mDatabase = findViewById(R.id.database);
        mRegister = findViewById(R.id.bt_register);

        mNative = findViewById(R.id.bt_native);

        mBtnUI.setOnClickListener(onClick);
        mInteraction.setOnClickListener(onClick);
        mForeGround.setOnClickListener(onClick);
        mDatabase.setOnClickListener(onClick);
        mNative.setOnClickListener(onClick);

        mRegister.setOnClickListener(onClick);

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
                Log.e("TAG","onSubscribe"+d);
                this.disposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e("TAG","onNext"+integer);
                if (integer == 2){
                    disposable.dispose();
                    Log.e("TAG","断开链接");
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("TAG","onError"+e);
            }

            @Override
            public void onComplete() {
                Log.e("TAG","onComplete");
            }
        });

        Observable.just(3,1,2,4).subscribe(new Observer<Integer>(){

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
                    list.add("事件"+integer+"拆分的事件"+j);
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
                    list.add("我是事件"+integer+"拆分的事件"+j);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe();

        Observable.just(1,2,3,4,5).buffer(3,1).subscribe(new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<Integer> integers) {
                Log.e("TAG","buffer size"+integers.size());
                for (Integer v:integers) {
                    Log.e("TAG","事件"+v);
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
                return integer+s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.e("TAG",s);
            }
        });

        // reduce()
        Observable.just(1,2,3,4).reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Throwable {
                return integer * integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.e("TAG","reduce"+"------------"+integer);
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
                if(memoryCache != null){
                    emitter.onNext(memoryCache);
                }else {
                    emitter.onComplete();
                }
            }
        });
        Observable disk = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                if(diskCache != null){
                    emitter.onNext(diskCache);
                }else {
                    emitter.onComplete();
                }
            }
        });

        Observable network = Observable.just("从网络获取");

        Observable.concat(memory,disk,network).firstElement().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.e("TAG",s);
            }
        });

        // retrofit实例
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fy.iciba.com/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(new OkHttpClient.Builder().build()).build();
        // 接口
        GetRequest_Interface getRequest_interface = retrofit.create(GetRequest_Interface.class);
        // 被观察者
        Observable<Translation>  observable = getRequest_interface.getCall().subscribeOn(Schedulers.io());
        Observable<Translation1>  observable1 = getRequest_interface.getCall_2().subscribeOn(Schedulers.io());
        // 发送
        Observable.zip(observable1, observable, (translation1, translation) -> translation1.show() + translation.show()).subscribe(s -> Log.e("TAG", "最终返回的数据" + s), throwable -> Log.e("TAG","失败"));



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
                Log.e("TAG","error"+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();

                runOnUiThread(()->{
                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                });
            }
        });

        // guide
        NewbieGuide.with(this).setLabel("guideMain").addGuidePage(GuidePage.newInstance().addHighLight(mBtnUI).setLayoutRes(R.layout.simple_guide_view)).alwaysShow(true).show();


        //












    }
    class onClick implements View.OnClickListener{
        Intent intent = null;
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.UI:
                    intent = new Intent(MainActivity.this,UIActivity.class);
                    break;
                case R.id.interaction:
                    intent = new Intent(MainActivity.this,MessageActivity.class);
                    break;
                case R.id.foreground:
                    intent = new Intent(MainActivity.this,ForeGroundActivity.class);
                    break;
                case R.id.database:
                    intent = new Intent(MainActivity.this,RoomDatabaseActivity.class);
                    break;
                case R.id.bt_native:
                    intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    break;

                case R.id.bt_register:
                    intent = new Intent(MainActivity.this, EventBusActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
