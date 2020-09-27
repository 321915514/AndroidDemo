package com.example.myapplication.mvp;

import com.example.myapplication.model.Translation;
import com.example.myapplication.model.Translation1;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import retrofit2.http.GET;

public interface Api {
    void baseApiSubscribe(Observable observable, Observer observer);

}
