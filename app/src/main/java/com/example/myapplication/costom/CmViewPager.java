package com.example.myapplication.costom;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CmViewPager  extends ViewPager {
    public CmViewPager(@NonNull Context context) {
        super(context);
    }

    public CmViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}