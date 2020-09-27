package com.example.myapplication.mvp;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.util.UtToast;

import java.lang.annotation.Target;

public class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mContext = this;
    }

    /**
     * show toast
     */
    public void shoeToast(String msg){
        UtToast.show(mContext,msg);
    }
}
