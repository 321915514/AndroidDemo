package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Dialog.DialogActivity;
import com.example.myapplication.fragment.ContainerActivity;
import com.example.myapplication.gridview.GridViewActivity;
import com.example.myapplication.jump.AActivity;
import com.example.myapplication.listview.ListViewActivity;
import com.example.myapplication.recyclerview.RecycleActivity1;
import com.example.myapplication.recyclerview.RecyclerViewActivity;
import com.example.myapplication.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UIActivity extends AppCompatActivity {

    @BindView(R.id.Button_1)
    Button button;
    @BindView(R.id.Button_2)
    Button EditButton;
    @BindView(R.id.Redio_btn)
    Button RedioButton;
    @BindView(R.id.checkbox)
    Button checkBoxButton;
    @BindView(R.id.ImageView)
    Button mBtnImagView;
    @BindView(R.id.ListView)
    Button mBtnListView;
    @BindView(R.id.GridView)
    Button mbtnGridView;
    @BindView(R.id.RecyclerView)
    Button mBtnRecyclerView;
    @BindView(R.id.WebView)
    Button mBtnWebView;
    @BindView(R.id.Toast)
    Button mBtnToast;
    @BindView(R.id.Dialog)
    Button mBtnDialog;
    @BindView(R.id.ProgressBar)
    Button mBtnProgress;
    @BindView(R.id.CustomDialog)
    Button mBtnCustomDialog;
    @BindView(R.id.PopupWindow)
    Button mBtnPopupWindow;
    @BindView(R.id.Jump)
    Button mBtnJump;
    @BindView(R.id.fragment)
    Button mBtnFragment;
    @BindView(R.id.CmView)
    Button mBtnCmView;
    @BindView(R.id.HandlerActivity)
    Button mBtnHandler;
    @BindView(R.id.showActivity)
    Button mShow;
    @BindView(R.id.SqlLite)
    Button mSqlLite;
    @BindView(R.id.Location)
    Button mLocation;
    @BindView(R.id.ServiceActivity)
    Button mService;
    @BindView(R.id.startH5)
    Button mStartH5;
    @BindView(R.id.coordinatorLayout)
    Button coordinatorLayout;
    @BindView(R.id.bt_intent)
    Button btIntent;
    @BindView(R.id.bt_recyclerview)
    Button btRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        ButterKnife.bind(this);
        setListener();


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 跳转到ButtonActivity页面
//                Intent intent = new Intent(MainActivity.this,ButtonActivity.class);
//                startActivity(intent);
//            }
//        });
//        findViewById(R.id.Button_2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,EditActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public void setListener() {
        onClick onClick = new onClick();
        button.setOnClickListener(onClick);
        EditButton.setOnClickListener(onClick);
        RedioButton.setOnClickListener(onClick);
        checkBoxButton.setOnClickListener(onClick);
        mBtnImagView.setOnClickListener(onClick);
        mBtnListView.setOnClickListener(onClick);
        mbtnGridView.setOnClickListener(onClick);
        mBtnRecyclerView.setOnClickListener(onClick);
        mBtnWebView.setOnClickListener(onClick);
        mBtnToast.setOnClickListener(onClick);
        mBtnDialog.setOnClickListener(onClick);
        mBtnProgress.setOnClickListener(onClick);
        mBtnCustomDialog.setOnClickListener(onClick);
        mBtnPopupWindow.setOnClickListener(onClick);
        mBtnJump.setOnClickListener(onClick);
        mBtnFragment.setOnClickListener(onClick);
        mBtnCmView.setOnClickListener(onClick);
        mBtnHandler.setOnClickListener(onClick);
        mShow.setOnClickListener(onClick);
        mSqlLite.setOnClickListener(onClick);
        mLocation.setOnClickListener(onClick);
        mService.setOnClickListener(onClick);
        mStartH5.setOnClickListener(onClick);
        coordinatorLayout.setOnClickListener(onClick);
        btRecyclerview.setOnClickListener(onClick);
        btIntent.setOnClickListener((View view) -> {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("age", 23);
            bundle.putString("Hello", "world");
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private class onClick implements View.OnClickListener {
        Intent intent = null;

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.Button_1:
                    intent = new Intent(UIActivity.this, ButtonActivity.class);
                    break;
                case R.id.Button_2:
                    intent = new Intent(UIActivity.this, EditActivity.class);
                    break;
                case R.id.Redio_btn:
                    intent = new Intent(UIActivity.this, RedioActivity.class);
                    break;
                case R.id.checkbox:
                    intent = new Intent(UIActivity.this, CheckBoxActivity.class);
                    break;
                case R.id.ImageView:
                    intent = new Intent(UIActivity.this, ImageViewActivity.class);
                    break;
                case R.id.ListView:
                    intent = new Intent(UIActivity.this, ListViewActivity.class);
                    break;
                case R.id.GridView:
                    intent = new Intent(UIActivity.this, GridViewActivity.class);
                    break;
                case R.id.RecyclerView:
                    intent = new Intent(UIActivity.this, RecyclerViewActivity.class);
                    break;
                case R.id.WebView:
                    intent = new Intent(UIActivity.this, WebViewActivity.class);
                    break;
                case R.id.Toast:
                    intent = new Intent(UIActivity.this, ToastActivity.class);
                    break;
                case R.id.Dialog:
                    intent = new Intent(UIActivity.this, DialogActivity.class);
                    break;
                case R.id.ProgressBar:
                    intent = new Intent(UIActivity.this, ProgressActivity.class);
                    break;
                case R.id.CustomDialog:
                    intent = new Intent(UIActivity.this, CustomDialogActivity.class);
                    break;
                case R.id.PopupWindow:
                    intent = new Intent(UIActivity.this, PopupWindowActivity.class);
                    break;
                case R.id.Jump:
                    intent = new Intent(UIActivity.this, AActivity.class);
                    break;
                case R.id.fragment:
                    intent = new Intent(UIActivity.this, ContainerActivity.class);
                    break;
                case R.id.CmView:
                    intent = new Intent(UIActivity.this, CmViewActivity.class);
                    break;
                case R.id.HandlerActivity:
                    intent = new Intent(UIActivity.this, HandlerActivity.class);
                    break;
                case R.id.showActivity:
                    intent = new Intent(getApplicationContext(), SaveActivity.class);
                    break;
                case R.id.SqlLite:
                    intent = new Intent(getApplicationContext(), SqlLiteActivity.class);
                    break;
                case R.id.Location:
                    intent = new Intent(getApplicationContext(), LocationActivity.class);
                    break;
                case R.id.ServiceActivity:
                    intent = new Intent(getApplicationContext(), ServiceActivity.class);
                    break;
                case R.id.startH5:
                    intent = new Intent(getApplicationContext(), StartH5Activity.class);
                    break;
                case R.id.coordinatorLayout:
                    intent = new Intent(getApplicationContext(), coordinatorLayoutActivity.class);
                    break;
                case R.id.bt_recyclerview:
                    intent = new Intent(getApplicationContext(), RecycleActivity1.class);
                    intent.putExtra("title","列表");
                    intent.putExtra("share",true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
            }
            startActivity(intent);
        }
    }
}
