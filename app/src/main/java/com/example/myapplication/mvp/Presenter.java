package com.example.myapplication.mvp;

public interface Presenter<V extends BaseView>{
    /**
     * 绑定
     */
    void attachView(V view);
    /**
     * 解绑
     */
    void detachView(V view);
    /**
     * 销毁
     */
    void destroy();
}
