package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class ProgressActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button button,mBtnProgressDialog1,mBtnProgressDialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        progressBar = findViewById(R.id.pb4);
        button = findViewById(R.id.pg_button);
        mBtnProgressDialog1 = findViewById(R.id.pg_Dialog1);
        mBtnProgressDialog2 = findViewById(R.id.pg_Dialog2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(0);
            }
        });
        mBtnProgressDialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(ProgressActivity.this);
                progressDialog.setTitle("提示");
                progressDialog.setMessage("正在加载");
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Toast.makeText(ProgressActivity.this,"cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        });
        mBtnProgressDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(ProgressActivity.this);
                progressDialog.setTitle("提示");
                progressDialog.setMessage("正在下载");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Toast.makeText(ProgressActivity.this,"cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        });


    }
    // 不推荐，内存泄漏
   Handler handler = new Handler(){
       @Override
       public void handleMessage(@NonNull Message msg) {
           super.handleMessage(msg);
           if(progressBar.getProgress()<100){
               handler.postDelayed(runnable,500);
           }else {
               Toast.makeText(ProgressActivity.this,"加载完成",Toast.LENGTH_SHORT).show();
           }
       }
   };
   Runnable runnable = new Runnable(){

       @Override
       public void run() {
           progressBar.setProgress(progressBar.getProgress()+5);
           handler.sendEmptyMessage(0);
       }
   };


}
