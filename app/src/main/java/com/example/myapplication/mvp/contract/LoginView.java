package com.example.myapplication.mvp.contract;

import com.example.myapplication.mvp.BaseView;
import com.example.myapplication.mvp.MvpView;

public interface LoginView extends MvpView, BaseView {
    void showResult(String data);
    void shoeError(int code,String data);
}
