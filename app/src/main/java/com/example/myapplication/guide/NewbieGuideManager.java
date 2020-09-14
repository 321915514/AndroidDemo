package com.example.myapplication.guide;

import android.app.Activity;

import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.core.Builder;
import com.app.hubert.guide.core.Controller;
import com.app.hubert.guide.model.GuidePage;

public class NewbieGuideManager {
    private Controller controller;
    private Builder mNewbieGuideBuilder;
    public NewbieGuideManager(Activity activity){
        mNewbieGuideBuilder = NewbieGuide.with(activity).alwaysShow(true);
    }

    public void addGuidePage(OnGuideClickListener onGuideClickListener){


    }
}
