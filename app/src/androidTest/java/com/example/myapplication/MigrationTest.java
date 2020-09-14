package com.example.myapplication;

import androidx.room.testing.MigrationTestHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.database.AppDatabase;
import com.example.myapplication.database.DbManager;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class MigrationTest {

    @Rule
    public MigrationTestHelper helper;

    public MigrationTest(){
        helper = new MigrationTestHelper(InstrumentationRegistry.getInstrumentation(), Objects.requireNonNull(AppDatabase.class.getCanonicalName()),new FrameworkSQLiteOpenHelperFactory());
    }

    @Test
    public void migration1_2() throws Exception{
        SupportSQLiteDatabase db = helper.createDatabase("Migration",5);

        db.execSQL("select * from address");

        //db.close();

       // db = helper.runMigrationsAndValidate("Migration",5,false, DbManager.MIGRATION_1_2,DbManager.MIGRATION_2_3,DbManager.MIGRATION_3_4,DbManager.MIGRATION_4_5);


    }

}

