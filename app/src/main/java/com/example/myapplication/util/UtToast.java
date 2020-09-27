package com.example.myapplication.util;

import android.content.Context;
import android.widget.Toast;

public  class UtToast {
    public static void show(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
