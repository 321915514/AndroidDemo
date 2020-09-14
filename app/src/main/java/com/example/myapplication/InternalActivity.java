package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity {
    private Button mBtnSave,mBtnShow;
    private EditText mEditText;
    private final String FILE_NAME = "test.txt";
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        mBtnSave = findViewById(R.id.save);
        mBtnShow = findViewById(R.id.show);
        mEditText = findViewById(R.id.Edit_text);
        textView = findViewById(R.id.text_view);
        // view
        // 第一种
        //textView.animate().translationYBy(500f).setDuration(3000).start();
        // 第二种
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
//        valueAnimator.setDuration(2000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            // getAnimatedValue() 实际的值 0-100
//            // getAnimatedFraction() 动画的进度 0-1
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//
//            }
//        });
        // 第三种
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView,"translationY",0,500,800,100,900);
        objectAnimator.setDuration(2000);
        objectAnimator.start();

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(mEditText.getText().toString());
            }
        });
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InternalActivity.this,show(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void save(String str){
        try {
            File file = new File(Environment.getStorageDirectory(),"file_test");
            if(!file.exists()){
                file.mkdir();
            }
            File file1 = new File(file,"test.txt");
            if(!file1.exists()){
                file1.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            //FileOutputStream fileOutputStream = openFileOutput(FILE_NAME,MODE_PRIVATE);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String show(){
        FileInputStream fileInputStream=null;
        try {
            //fileInputStream = openFileInput(FILE_NAME);
            File file = new File(Environment.getStorageDirectory().getAbsolutePath()+File.separator+"file_test",FILE_NAME);
            fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            StringBuilder buffer = new StringBuilder();
            if((len = fileInputStream.read(bytes))>0){
                buffer.append(new String(bytes,0,len));
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
