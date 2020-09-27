package com.example.myapplication.mvp;

import com.example.myapplication.model.Translation;
import com.example.myapplication.mvp.entity.UserBean;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import retrofit2.http.GET;

public interface Service {

    @GET("ajax.php?a=fy&f=auto&t=zh&w=hello%20world")
    Observable<Translation> getCall();

    @GET()
    Observable<UserBean> login();
}
