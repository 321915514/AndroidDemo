package com.example.myapplication.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "address")
public class Address {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    @NonNull
    private String country;
    @NonNull
    private String province;
    @NonNull
    private String city;

    public Address() {
    }
    @Ignore
    public Address(@NonNull String country, @NonNull String province, @NonNull String city) {
        this.country = country;
        this.province = province;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
