package com.example.myapplication.database;




import android.content.Context;
import android.hardware.camera2.DngCreator;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.database.dao.AddressDao;
import com.example.myapplication.database.dao.ConsumerDao;
import com.example.myapplication.database.dao.UserDao;
import com.example.myapplication.database.entity.Address;
import com.example.myapplication.database.entity.Consumer;
import com.example.myapplication.database.entity.User;

@Database(entities = {User.class,Address.class,Consumer.class},version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract AddressDao AddressDao();

    public abstract ConsumerDao ConsumerDao();

    private static AppDatabase INSTANCE;


    public static AppDatabase getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,AppDatabase.class,"AppDatabase").addMigrations(DbManager.MIGRATION_1_2,
                            DbManager.MIGRATION_2_3, DbManager.MIGRATION_3_4,DbManager.MIGRATION_4_5,DbManager.MIGRATION_5_6).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }




}


