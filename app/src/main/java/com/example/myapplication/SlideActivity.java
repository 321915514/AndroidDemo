package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ScreenUtils;
import com.example.myapplication.costom.BasePageAdapter;
import com.example.myapplication.fragment.TestFragment;
import com.example.myapplication.ui.login.LoginFragment;
import com.example.myapplication.util.UtToast;
import com.example.myapplication.util.UtUIHelper;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideActivity extends AppCompatActivity {

    @BindView(R.id.st_slide_tab)
    SlidingTabLayout stSlideTab;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;

    private BlankFragment blankFragment;
    private ScrollingFragment scrollingFragment;
    private LoginFragment loginFragment;
    private TestFragment testFragment;

//    private BasePageAdapter basePageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        blankFragment = new BlankFragment();
        scrollingFragment = ScrollingFragment.getInstance();
        loginFragment = new LoginFragment();
        testFragment = TestFragment.getInstance("牛逼");
        ButterKnife.bind(this);
        List<String> list  = new ArrayList<>();
        String[] titles = {"简介","滚动","登录"};
        for (int i = 0; i < titles.length; i++) {
            list.add(titles[i]);
        }
        list.add("垃圾");
        String[] strings = list.toArray(new String[0]);
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(blankFragment);
        mFragments.add(scrollingFragment);
        mFragments.add(loginFragment);
        mFragments.add(testFragment);

//        basePageAdapter = new BasePageAdapter(getSupportFragmentManager(),mFragments,titles);
        //mainViewpager.setAdapter(basePageAdapter);
        mainViewpager.addOnPageChangeListener(onPageChangeListener);
        stSlideTab.setViewPager(mainViewpager,strings,this,mFragments);

        //stSlideTab.setTabWidth((float) UtUIHelper.getScreenWidth(this)/mFragments.size());
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) stSlideTab.getLayoutParams();
//        layoutParams.width = ScreenUtils.getScreenWidth();
//        layoutParams.height = UtUIHelper.dipToPx(this,50);
//        stSlideTab.setLayoutParams(layoutParams);
        stSlideTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                UtToast.show(getApplicationContext(),""+position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int count = stSlideTab.getTabCount();
            for (int i = 0; i < count; i++) {
                if(position == i){
                    stSlideTab.setTextSelectColor(Color.RED);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}