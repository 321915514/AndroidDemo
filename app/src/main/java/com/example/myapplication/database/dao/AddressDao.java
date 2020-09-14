package com.example.myapplication.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.database.entity.Address;

import java.util.List;

@Dao
public interface AddressDao {
    @Query("select * from address")
    List<Address> selectAddress();
    @Insert
    Long insertAll(Address address);

    @Query("select id from address where city = :city")
    int findAddressIDByCity(String city);

    @Query("select * from address where id = :id")
    Address selectAddressByID(int id);
}
