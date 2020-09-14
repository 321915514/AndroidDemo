package com.example.myapplication.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

//
//import android.content.Context;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.room.migration.Migration;
//import androidx.sqlite.db.SupportSQLiteDatabase;
//
public class DbManager extends RoomDatabase{

    private static RoomDatabase.MigrationContainer container = new RoomDatabase.MigrationContainer();






//    public static RoomDatabase.Builder upgradeVersion(){
////        List<Migration> list = new ArrayList<>();
////            list.add(MIGRATION_1_2);
////            list.add(MIGRATION_2_3);
////            list.add(MIGRATION_3_4);
////            list.add(MIGRATION_4_5);
////            list.add(MIGRATION_5_6);
////        switch (current){
////            case 1:
////                return list;
////            case 2:
////                list.remove(MIGRATION_1_2);
////                return list;
////            case 3:
////                list.remove(MIGRATION_1_2);
////
////
////        }
//        container.addMigrations(MIGRATION_1_2,MIGRATION_2_3,MIGRATION_3_4,MIGRATION_4_5,MIGRATION_5_6);
//
//
//    }









public static Migration MIGRATION_1_2 = new Migration(1,2) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        database.execSQL("alter table user add column height integer not null default 0");
    }
};

    public static Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table user rename to user_temp");
            database.execSQL("create table if not exists user(u_id integer not null primary key autoincrement,name varchar(100) not null ,age integer not null ,description text not null ,height integer not null )");
            database.execSQL("insert into user select * from user_temp");
            database.execSQL("drop table user_temp");
        }
    };

    public static Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("create table if not exists address (id integer not null primary key autoincrement,country varchar(50) not null,province varchar(20) not null,city varchar(20) not null) ");
        }
    };

    public static Migration MIGRATION_4_5 = new Migration(4,5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table user add column country text ");
            database.execSQL("alter table user add column province text  ");
            database.execSQL("alter table user add column city text");
            database.execSQL("alter table user add column id integer ");
        }
    };


    public static Migration MIGRATION_5_6 = new Migration(5,6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("create table if not exists consumer (con_id integer not null primary key autoincrement,username varchar(100) not null,password varchar(100) not null,address_id integer not null,foreign key(address_id) references address(id) on delete cascade) ");
            database.execSQL("create index address_id on consumer(address_id)");
        }
    };

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }


//
//
//
//
//
////     static Migration upgradeVersion(int currentVersion,int upgradeVersion){
////        return new Migration(currentVersion,upgradeVersion) {
////            @Override
////            public void migrate(@NonNull SupportSQLiteDatabase database) {
////                RoomDatabase.MigrationContainer container =new RoomDatabase.MigrationContainer();
////                switch (currentVersion){
////                    case 1:
////                        container.addMigrations(MIGRATION_1_2,MIGRATION_2_3,MIGRATION_3_4);
////                    case 2:
////                        container.addMigrations(MIGRATION_2_3,MIGRATION_3_4);
////                    case 3:
////                        container.addMigrations(MIGRATION_3_4);
////                    case 4:
////                        container.addMigrations(MIGRATION_4_5);
////
////                }
////            }
////        };
////
////
////    }
//
//
//    public static Migration MIGRATION_1_2 = new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("create table if not exists address ( id Integer primary key  autoincrement  , country text , province text ,city text )");
//        }
//    };
//
//    public static Migration MIGRATION_2_3 = new Migration(2,3) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("alter table address rename to address_temp");
//            database.execSQL("create table if not exists address (id Integer primary key autoincrement unique ,country text ,province text ,city text)");
//            //database.execSQL("create index index_address_id on address(id) ");
//            database.execSQL("insert into address (country,province,city) select country,province,city from address_temp");
//            database.execSQL("drop table address_temp");
//        }
//    };
//
//    public static Migration MIGRATION_3_4 = new Migration(3,4) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("create table if not exists consumer (id Integer not null  primary key autoincrement,username text not null,password text not null,address_id Integer not null,foreign key(address_id) references address(id)) ");
//        }
//    };
//
//    public static Migration MIGRATION_4_5 = new Migration(4,5) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("alter table consumer add column info text");
//        }
//    };
//
//
//
//
//
//
//
//
//
}
