package com.example.myapplication.mvp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ViewProxy<V extends BaseView> implements InvocationHandler {

    private V view;

    public Object newProxyInstance(V view){
        this.view = view;
        return Proxy.newProxyInstance(view.getClass().getClassLoader(),view.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if(view == null) {
            return null;
        }
        Object temp = method.invoke(view,objects);
        return temp;
    }

    /**
     * 解绑
     */
    public void detachView(){
        view = null;
    }
}
