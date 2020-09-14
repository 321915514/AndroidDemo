package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StartH5Activity extends AppCompatActivity {
    private ImageView mClose;
    private ProgressBar mWebProgBar;
    private FrameLayout mFlContainer;

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_h5);
        mClose = findViewById(R.id.web_close_button);
        mWebProgBar = findViewById(R.id.webProgBar);
        mFlContainer = findViewById(R.id.fl_container);
        mWebView = findViewById(R.id.h5_content);

        mWebView.loadUrl("https://m.baidu.com");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Observable.create(new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
//                        emitter.onNext("https://m.baidu.com");
//                        Log.e("TAG","emiter");
//                    }
//                }).subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(@NonNull String s) {
//                        // 场
//                        OkHttpClient okHttpClient = new OkHttpClient();
//
//                        // request
//                        Request request = new Request.Builder().url(s).build();
//
//                        // call
//                        Call call = okHttpClient.newCall(request);
//
//                        call.enqueue(new Callback() {
//                            @Override
//                            public void onFailure(Call call, IOException e) {
//                                Log.e("TAG","error"+e);
//                            }
//
//                            @Override
//                            public void onResponse(Call call, Response response) throws IOException {
//
//                                assert response.body() != null;
//                                String data = response.body().string();
//                                Log.e("TAG",data);
//
//                                        //Log.e("TAG",response.body().string());
//                                runOnUiThread(()->{
//                                    mWebView.loadData(data, "text/html", "utf-8");
//                                });
//                            }
//                        });
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
            }
//        }).start();


}
