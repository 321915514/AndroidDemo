package com.example.myapplication.AbstractFactory;

import android.util.Log;

public class Product2 extends AbstractProductA {

    @Override
    public void doSomething() {
        Log.e("TAG","productB");
    }
}
