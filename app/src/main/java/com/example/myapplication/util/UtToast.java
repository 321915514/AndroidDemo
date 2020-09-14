package com.example.myapplication.util;

import android.content.Context;
import android.widget.Toast;

public  class UtToast {
    public static void toast(Context context,String message,int timer){
        Toast.makeText(context,message,timer).show();
    }
}
