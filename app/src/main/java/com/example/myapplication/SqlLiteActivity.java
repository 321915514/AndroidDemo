package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.database.entity.User;
import com.example.myapplication.util.UtToast;

import java.io.File;
import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class SqlLiteActivity extends AppCompatActivity {


    private SQLiteDatabase db;
    private Context mContext;
    private Button mShowData;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.sql_lite_layout);
        File file = new File(Environment.getStorageDirectory(),"lol_hero.db");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        db = SQLiteDatabase.openOrCreateDatabase("/data/data/"+SqlLiteActivity.this.getPackageName()+"/databases/lol_hero.db", null);
        db.execSQL("drop table user");
        db.execSQL("create table if not exists user(id integer not null  primary key autoincrement,name verchar(50) not null,age Integer not null )");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","剑圣");
        contentValues.put("age",890);
        db.insert("user",null,contentValues);


        Cursor cursor = db.query("user",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.move(i);
               final int id = cursor.getInt(i);
               final String name = cursor.getString(i);
                Toast.makeText(getApplicationContext(),id+":"+name,Toast.LENGTH_SHORT).show();
                mShowData = findViewById(R.id.show_data);
                textView = findViewById(R.id.sql_show);
                mShowData.setOnClickListener(view -> textView.setText(id+":"+name));
            }
        }
        cursor.close();
    }
}
