package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.myapplication.model.RecycleItemModel;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class coordinatorLayoutActivity extends AppCompatActivity {

    //    @BindView(R.id.rv_show)
//    RecyclerView rvShow;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    //    @BindView(R.id.iv_bg)
//    ImageView ivBg;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_show)
    ImageView imageView;
    //    @BindView(R.id.iv_main)
//    ImageView imageViewMain;
    @BindView(R.id.cl_appbar)
    CollapsingToolbarLayout appBarLayout;
//    @BindView(R.id.cl_appbar)
//    CollapsingToolbarLayout clAppbar;
    @BindView(R.id.st_slide_tab)
    SlidingTabLayout stSlideTab;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    private RecycleFragment recycleFragment;
    private BlankFragment blankFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        ButterKnife.bind(this);
        Glide.with(getApplicationContext()).load("https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3238317745,514710292&fm=26&gp=0.jpg").into(imageView);

//        Log.e(getClass().getSimpleName(),""+height);
//        Glide.with(getApplicationContext()).load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1600753182&di=c174ae954060be904a9e88c82fbea2d2&src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg").into(imageView);
//        Glide.with(getApplicationContext()).load("https://www.baidu.com/img/PCfb_5bf082d29588c07f842ccde3f97243ea.png").into(ivBg);
//        Glide.with(getApplicationContext()).load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1600753182&di=c174ae954060be904a9e88c82fbea2d2&src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg").into(imageViewMain);
        ArrayList<String> list = new ArrayList<>();
        list.add("列表");
        list.add("简介");
        ArrayList<RecycleItemModel> list1 = new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            RecycleItemModel recycleItemModel = new RecycleItemModel();
            recycleItemModel.setUrl("https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1141259048,554497535&fm=26&gp=0.jpg");
            recycleItemModel.setTitle("标题"+i);
            recycleItemModel.setContent("内容"+i);
            recycleItemModel.setCount(10000+i);
            recycleItemModel.setDate("2020-9-"+i);
            list1.add(recycleItemModel);
        }
        String[] tables = list.toArray(new String[0]);
        blankFragment = BlankFragment.newInstance("1","2");
        recycleFragment = RecycleFragment.newInstance(list1);
        ArrayList<Fragment> list2 = new ArrayList<>();
        list2.add(recycleFragment);
        list2.add(blankFragment);
        stSlideTab.setViewPager(vpContent,tables,this,list2);


    }
}