package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Dialog.DialogActivity;
import com.example.myapplication.fragment.ContainerActivity;
import com.example.myapplication.jump.AActivity;
import com.example.myapplication.listview.ListViewActivity;
import com.example.myapplication.gridview.GridViewActivity;
import com.example.myapplication.recyclerview.RecyclerViewActivity;
import com.example.myapplication.webview.WebViewActivity;

public class UIActivity extends AppCompatActivity {

    private Button button;
    private Button EditButton;
    private Button RedioButton;
    private Button checkBoxButton;
    private Button mBtnImagView;
    private Button mBtnListView;
    private Button mbtnGridView;
    private Button mBtnRecyclerView;
    private Button mBtnWebView;
    private Button mBtnToast;
    private Button mBtnDialog,mBtnCustomDialog;
    private Button mBtnProgress,mBtnPopupWindow,mBtnJump,mBtnFragment,mBtnCmView,mBtnHandler,mShow,mSqlLite,mLocation,mService,mStartH5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        button = findViewById(R.id.Button_1);
        EditButton = findViewById(R.id.Button_2);
        RedioButton = findViewById(R.id.Redio_btn);
        checkBoxButton = findViewById(R.id.checkbox);
        mBtnImagView = findViewById(R.id.ImageView);
        mBtnListView = findViewById(R.id.ListView);
        mbtnGridView = findViewById(R.id.GridView);
        mBtnRecyclerView = findViewById(R.id.RecyclerView);
        mBtnWebView = findViewById(R.id.WebView);
        mBtnToast = findViewById(R.id.Toast);
        mBtnDialog = findViewById(R.id.Dialog);
        mBtnProgress = findViewById(R.id.ProgressBar);
        mBtnCustomDialog = findViewById(R.id.CustomDialog);
        mBtnPopupWindow = findViewById(R.id.PopupWindow);
        mBtnJump = findViewById(R.id.Jump);
        mBtnFragment = findViewById(R.id.fragment);
        mBtnCmView = findViewById(R.id.CmView);
        mBtnHandler = findViewById(R.id.HandlerActivity);
        mShow = findViewById(R.id.showActivity);
        mSqlLite = findViewById(R.id.SqlLite);
        mLocation = findViewById(R.id.Location);
        mService = findViewById(R.id.ServiceActivity);
        mStartH5 = findViewById(R.id.startH5);
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
    public void setListener(){
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
    }
    private class onClick implements View.OnClickListener{
        Intent intent=null;
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Button_1:
                    intent = new Intent(UIActivity.this,ButtonActivity.class);
                    break;
                case R.id.Button_2:
                    intent = new Intent(UIActivity.this,EditActivity.class);
                    break;
                case R.id.Redio_btn:
                    intent = new Intent(UIActivity.this, RedioActivity.class);
                    break;
                case R.id.checkbox:
                    intent = new Intent(UIActivity.this, CheckBoxActivity.class);
                    break;
                case R.id.ImageView:
                    intent = new Intent(UIActivity.this,ImageViewActivity.class);
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
                    intent = new Intent(getApplicationContext(),SaveActivity.class);
                    break;
                case R.id.SqlLite:
                    intent = new Intent(getApplicationContext(),SqlLiteActivity.class);
                    break;
                case R.id.Location:
                    intent = new Intent(getApplicationContext(),LocationActivity.class);
                    break;
                case R.id.ServiceActivity:
                    intent = new Intent(getApplicationContext(),ServiceActivity.class);
                    break;
                case R.id.startH5:
                    intent = new Intent(getApplicationContext(),StartH5Activity.class);
                    break;

            }
            startActivity(intent);
        }
    }
}
