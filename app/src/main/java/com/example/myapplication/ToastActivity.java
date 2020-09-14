package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class ToastActivity extends AppCompatActivity {
    private Button mBtnToast1,mBtnToast2,mBtnToast3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        mBtnToast1 = findViewById(R.id.toast_1);
        mBtnToast2 = findViewById(R.id.toast_2);
        mBtnToast3 = findViewById(R.id.toast_3);
        onClick onClick = new onClick();
        mBtnToast1.setOnClickListener(onClick);
        mBtnToast2.setOnClickListener(onClick);
        mBtnToast3.setOnClickListener(onClick);


    }
    class onClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.toast_1:
                    Toast.makeText(getApplicationContext(),"Toast",Toast.LENGTH_LONG).show();
                    break;
                case R.id.toast_2:
                    Toast toast = Toast.makeText(getApplicationContext(),"center",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    break;
                case R.id.toast_3:
                    // 没效果，不知为啥
                    Toast toast1 = new Toast(getApplicationContext());
                    LayoutInflater inflater = LayoutInflater.from(ToastActivity.this);
                    View view1 = inflater.inflate(R.layout.latout_toast,null);
                    TextView textView = view1.findViewById(R.id.toast_image);
                    ImageView imageView = view1.findViewById(R.id.toast_text);
                    textView.setText("Hello World");
                    imageView.setImageResource(R.drawable.screen1);
                    toast1.setView(view1);
                    toast1.show();

                    break;
            }
        }
    }
}
