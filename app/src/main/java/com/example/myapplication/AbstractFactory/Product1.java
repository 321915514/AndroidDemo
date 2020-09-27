package com.example.myapplication.AbstractFactory;

import android.util.Log;

public class Product1 extends AbstractProductA {

    @Override
    public void doSomething() {
        Log.e("TGA","productA");
    }
}
