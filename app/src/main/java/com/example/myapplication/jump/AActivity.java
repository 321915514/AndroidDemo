package com.example.myapplication.jump;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ButtonActivity;
import com.example.myapplication.R;

import java.sql.BatchUpdateException;

public class AActivity extends AppCompatActivity {
    private Button mBtnJump;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_a);
        // 显式跳转
        mBtnJump = findViewById(R.id.Jump);
        mBtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1
//                Intent intent = new Intent(AActivity.this,BActivity.class);
//                startActivity(intent);
//                // 2
//                Intent intent1 = new Intent();
//                intent1.setClass(AActivity.this,BActivity.class);
//                startActivity(intent1);
//                // 3
//                startActivity(new Intent().setClassName(AActivity.this,"com.example.myapplication.jump.BActivity"));
//                // 4
//                startActivity(new Intent().setComponent(new ComponentName(AActivity.this,"com.example.myapplication.jump.BActivity")));
                // 隐士跳转
                Intent intent = new Intent().setAction("com.android.test.BActivity");
                Bundle bundle = new Bundle();
                bundle.putString("name","Hello");
                bundle.putInt("number",90);
                intent.putExtras(bundle);
                //startActivity(intent);
                startActivityForResult(intent,0);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != 0){
            finish();
        }
        Toast.makeText(getApplicationContext(),data.getExtras().getString("title"),Toast.LENGTH_SHORT).show();
    }
}
