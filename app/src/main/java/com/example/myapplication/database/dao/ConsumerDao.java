package com.example.myapplication.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;

import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.database.entity.Consumer;

import java.util.List;



@Dao
public interface ConsumerDao {
    @Query("select * from consumer")
    List<Consumer> findConsumers();

    @Query("select * from consumer where address_id = (select id from address where address.city = :city)")
    List<Consumer> findConsumerByCity(String city);

    @Insert
    Long insertConsumer(Consumer consumer);

    @Delete
    int deleteConsumerByID(Consumer consumer);

}
