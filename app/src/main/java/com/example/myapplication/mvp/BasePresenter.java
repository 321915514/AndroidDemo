package com.example.myapplication.mvp;

/**
 * 基础Presenter
 * @param <V>
 */
public abstract class BasePresenter<V extends BaseView> implements Presenter<V> {
    protected V mView;

    // 代理
    protected ViewProxy<V> viewProxy;

    public V getView(){
        return mView;
    }
    /**
     * 判断view是否添加
     */
    public boolean isAttached(){
        return mView!=null;
    }
    /**
     * attach view
     */
    public void attachView(V view){
        // 通过反射获取view
        mView = (V) new ViewProxy<V>().newProxyInstance(view);
    }

    @Override
    public void detachView(V view) {
        if(viewProxy != null){
            viewProxy.detachView();
        }
    }

    @Override
    public void destroy() {

    }
}
