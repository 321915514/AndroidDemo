package com.example.myapplication.mvp;

import com.example.myapplication.model.Translation;

import java.util.Map;
import java.util.TreeMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApi implements Api {


    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fy.iciba.com/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(new OkHttpClient.Builder().build()).build();
    // 接口
    Service api = retrofit.create(Service.class);

    public static BaseApi getInstance(){
        return BaseApiHolder.INSTANCE;
    }

    private static class BaseApiHolder{
        private static final BaseApi INSTANCE = new BaseApi();
    }

    @Override
    public void baseApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void requestTranslation(Map<String,Object> map, BaseObserver<Translation> observer){
        baseApiSubscribe(api.getCall(),observer);
    }

}
