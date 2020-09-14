package com.example.myapplication.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.PointerIcon;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

public class CustomDialog extends Dialog implements View.OnClickListener {
    private TextView mTvTitle,mTvMessage,mTvCancel,mConfirm;

    private IOnCancelListener cancelListener;
    private IOnConfirmListener confirmListener;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }
    // 设置宽度
    // 获取屏幕宽度
    // 获取不到，醉了
//    WindowManager m = this.getWindow().getWindowManager();
//   // int width = m.getDefaultDisplay().getWidth();
//    int d = m.getDefaultDisplay().getWidth();
//    Point size = new Point();
//    WindowManager.LayoutParams p;
//
//   DisplayMetrics outMetrics = new DisplayMetrics();
//   // getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
//    int widthPixels = outMetrics.widthPixels;
//    int heightPixels = outMetrics.heightPixels;
//
  //  Log.i(TAG, "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels);
    //widthPixels = 1440, heightPixels = 2768
//    Resources resources = this.getWindow()
//    DisplayMetrics dm = resources.getDisplayMetrics();
//    float density1 = dm.density;
//    int width3 = dm.widthPixels;
//    int height3 = dm.heightPixels;
//
//    DisplayMetrics outMetrics = new DisplayMetrics();
//    getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
//    int widthPixel = outMetrics.widthPixels;
//    int heightPixel = outMetrics.heightPixels;



    public CustomDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public CustomDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public CustomDialog setCancel(String cancel,IOnCancelListener cancelListener) {
        this.cancel = cancel;
        this.cancelListener = cancelListener;
        return this;
    }

    public CustomDialog setConfirm(String confirm,IOnConfirmListener confirmListener) {
        this.confirm = confirm;
        this.confirmListener = confirmListener;
        return this;
    }

    private String title,message,cancel,confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_dialog);
        mTvTitle = findViewById(R.id.tv_title);
        mTvMessage = findViewById(R.id.tv_message);
        mTvCancel = findViewById(R.id.tv_cancel);
        mConfirm = findViewById(R.id.tv_confirm);
        if(!TextUtils.isEmpty(title)){
            mTvTitle.setText(title);
        }
        if(!TextUtils.isEmpty(message)){
            mTvMessage.setText(message);
        }
        if(!TextUtils.isEmpty(cancel)){
            mTvCancel.setText(cancel);
        }
        if(!TextUtils.isEmpty(confirm)){
            mConfirm.setText(confirm);
        }
        mTvCancel.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cancel:
                if(cancelListener!=null){
                    cancelListener.onCancel(this);
                }
                break;
            case R.id.tv_confirm:
                if(confirmListener!=null){
                    confirmListener.onConfirm(this);
                }
                break;
        }
    }


    public interface IOnCancelListener{
        void onCancel(CustomDialog dialog);
    }
    public interface IOnConfirmListener{
        void onConfirm(CustomDialog dialog);
    }
}
