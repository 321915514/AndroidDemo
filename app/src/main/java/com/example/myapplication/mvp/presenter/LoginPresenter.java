package com.example.myapplication.mvp.presenter;

import android.util.Log;

import com.example.myapplication.database.entity.User;
import com.example.myapplication.mvp.BasePresenter;
import com.example.myapplication.mvp.contract.LoginView;
import com.example.myapplication.mvp.entity.UserBean;
import com.example.myapplication.mvp.model.LoginModel;
import com.example.myapplication.mvp.model.LoginModelImpl;

public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginModel loginModel = new LoginModelImpl();
    private LoginView loginView;

    public String login(UserBean user) {
       return loginModel.login(user);
    }


}
