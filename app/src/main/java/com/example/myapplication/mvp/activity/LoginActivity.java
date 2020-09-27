package com.example.myapplication.mvp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.mvp.BaseActivity;
import com.example.myapplication.mvp.contract.LoginView;
import com.example.myapplication.mvp.entity.UserBean;
import com.example.myapplication.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.tv_result)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }



    private LoginPresenter mLoginPresenter = new LoginPresenter();

//    @Override
//    public void showResult(String data) {
//        textView.setText(data);
//    }

    @Override
    public void showResult(String data) {

    }

    @Override
    public void shoeError(int code, String data) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showToast(@NonNull String msg) {

    }

    @Override
    public void showProgressView() {

    }

    @Override
    public void dismissProgressView() {

    }

    @OnClick(R.id.bt_submit)
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.bt_submit:
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                UserBean user = new UserBean();
                user.setUsername(username);
                user.setPassword(password);
                textView.setText(mLoginPresenter.login(user));
                break;
        }
    }
}