package com.example.myapplication.database.dao;

import android.util.Log;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.database.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("select * from user")
    List<User> getUsers();
    @Query("select * from user where u_id = :id")
    User findUserById(int id);
    @Insert
    Long addUser(User user);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateUser(User user);
    @Delete
    int deleteUserById(User user);




}
