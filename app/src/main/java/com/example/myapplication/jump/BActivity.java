package com.example.myapplication.jump;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class BActivity extends AppCompatActivity {
    private TextView textView;
    private Button mBtnFinish;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_b);
        textView = findViewById(R.id.tv_me);
        Bundle bundle = getIntent().getExtras();
        int number = bundle.getInt("number");
        String string= bundle.getString("name");
        textView.setText(number+","+string);
        Toast.makeText(getApplicationContext(),number+string,Toast.LENGTH_SHORT).show();
        mBtnFinish = findViewById(R.id.finish);
        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle1= new Bundle();
                bundle1.putString("title","我回来了");
                intent.putExtras(bundle1);
                setResult(AActivity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
