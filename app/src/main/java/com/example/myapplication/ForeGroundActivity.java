package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;


public class ForeGroundActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStartForeGround;
    private Button mStopForeGround;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fore_ground);
        mStartForeGround = findViewById(R.id.start_foreground);
        mStopForeGround = findViewById(R.id.stop_foreground);
        mStartForeGround.setOnClickListener(this);
        mStopForeGround.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,ForeGroundService.class);
        Bundle bundle = null;
        switch (view.getId()){
            case R.id.start_foreground:
                bundle = new Bundle();
                bundle.putInt("cmd",0);
                intent.putExtras(bundle);
                startService(intent);
                break;
            case R.id.stop_foreground:
                bundle = new Bundle();
                bundle.putInt("cmd",1);
                intent.putExtras(bundle);
                startService(intent);
                break;

        }
    }
}
