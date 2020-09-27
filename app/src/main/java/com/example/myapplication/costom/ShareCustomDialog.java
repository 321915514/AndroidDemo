package com.example.myapplication.costom;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ShareCustomDialog extends Dialog implements View.OnClickListener {

    private TextView textView;

    public ShareCustomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onClick(View view) {

    }
}
