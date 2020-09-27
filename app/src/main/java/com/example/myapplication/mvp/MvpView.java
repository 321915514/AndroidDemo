package com.example.myapplication.mvp;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

public interface MvpView {
    /**
     * 获取Activity实例
     *
     * @return
     */
    Activity getActivity();

    /**
     * 展示吐司
     *
     * @param msg 吐司文本
     */
    @UiThread
    void showToast(@NonNull String msg);

    /**
     * 显示进度View
     */
    @UiThread
    void showProgressView();

    /**
     * 隐藏进度View
     */
    @UiThread
    void dismissProgressView();
}
