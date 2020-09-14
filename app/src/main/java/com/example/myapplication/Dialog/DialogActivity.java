package com.example.myapplication.Dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class DialogActivity extends AppCompatActivity {
    private Button mBtnDialog1,mBtnDialog2,mBtnDialog3,mBtnDialog4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        mBtnDialog1 = findViewById(R.id.btn_Dialog1);
        mBtnDialog2 = findViewById(R.id.btn_Dialog2);
        mBtnDialog3 = findViewById(R.id.btn_Dialog3);
        mBtnDialog4 = findViewById(R.id.btn_Dialog4);
        OnClick onClick = new OnClick();
        mBtnDialog1.setOnClickListener(onClick);
        mBtnDialog2.setOnClickListener(onClick);
        mBtnDialog3.setOnClickListener(onClick);
        mBtnDialog4.setOnClickListener(onClick);
    }
    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_Dialog1:
                    final AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                    builder.setTitle("Hello").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(DialogActivity.this,"yes",Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(DialogActivity.this,"no",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    break;
                case R.id.btn_Dialog2:
                    // 单选
                    final String[] array = new String[]{"男","女"};
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(DialogActivity.this);
                    builder1.setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(DialogActivity.this,array[i],Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false).show();
                    break;
                case R.id.btn_Dialog3:
                    // 多选
                     final String[] strings = new String[]{"唱歌","跳舞","写代码"};
                     final boolean[] booleans = new boolean[]{false,false,true};
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(DialogActivity.this);
                    builder2.setTitle("选择兴趣").setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            Toast.makeText(DialogActivity.this,strings[i]+":"+b,Toast.LENGTH_SHORT).show();
                        }
                    }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //
                        }
                    }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //
                        }
                    }).show();
                    break;
                case R.id.btn_Dialog4:
                    // 自定义样式
                    final AlertDialog.Builder builder3 = new AlertDialog.Builder(DialogActivity.this);

                    View view1 = LayoutInflater.from(DialogActivity.this).inflate(R.layout.layout_login,null);
                    final EditText username = view1.findViewById(R.id.username);
                    final EditText password = view1.findViewById(R.id.password);
                    Button submit = view1.findViewById(R.id.login);
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(DialogActivity.this,username.getText()+":"+password.getText(),Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder3.setTitle("login").setView(view1).show();
                    break;
            }
        }
    }
}
