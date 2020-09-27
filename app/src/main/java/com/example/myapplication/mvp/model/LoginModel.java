package com.example.myapplication.mvp.model;

import android.net.ConnectivityManager;

import com.example.myapplication.database.entity.User;
import com.example.myapplication.mvp.entity.UserBean;

public interface LoginModel {
    interface OnLoginFinishedListener{
        void onSuccess();
        void onFailure();
    }
    String login(UserBean user);
}
