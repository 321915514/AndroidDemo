package com.example.myapplication.mvp.model;

import android.net.ConnectivityManager;

import com.example.myapplication.Rxjava.BenewApi;
import com.example.myapplication.component.GetRequest_Interface;
import com.example.myapplication.database.entity.User;
import com.example.myapplication.model.Translation;
import com.example.myapplication.mvp.Api;
import com.example.myapplication.mvp.BaseApi;
import com.example.myapplication.mvp.BaseObserver;
import com.example.myapplication.mvp.entity.UserBean;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginModelImpl implements LoginModel{
    @Override
    public String login(UserBean user) {
        Map<String,Object> map = new HashMap<>();
        BaseApi.getInstance().requestTranslation(map, new BaseObserver<Translation>() {
            @Override
            public String onSuccess(Translation translation) {
                return translation.show();
            }

            @Override
            public String onFailure() {
                return null;
            }
        });
        return null;
    }

}
