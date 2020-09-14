package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShowActivity extends AppCompatActivity {
    String msg = "Android";
    private Button mBtnSave,mBtnShow;
    private EditText mEditText;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Log.d(msg,"create");
        mBtnSave = findViewById(R.id.save);
        mBtnShow = findViewById(R.id.show);
        mEditText = findViewById(R.id.Edit_text);
        mSharedPreferences = getSharedPreferences("date",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditor.putString("name",mEditText.getText().toString());
                mEditor.apply();
            }
        });
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),mSharedPreferences.getString("name",""),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg,"onstart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg,"Onstop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg,"pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg,"resume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(msg,"destroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(msg,"restart");
    }
}
