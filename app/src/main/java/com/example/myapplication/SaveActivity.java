package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SaveActivity extends AppCompatActivity {
    private Button mBtnShare, mBtnInternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        mBtnShare = findViewById(R.id.Shared);
        mBtnInternal = findViewById(R.id.Internal);
        mBtnInternal.setOnClickListener(new OnClick());
        mBtnShare.setOnClickListener(new OnClick());

    }


    private class OnClick implements View.OnClickListener {
        Intent intent = null;

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.Shared:
                    intent = new Intent(SaveActivity.this,ShowActivity.class);
                    break;
                case R.id.Internal:
                    intent = new Intent(SaveActivity.this,InternalActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}

