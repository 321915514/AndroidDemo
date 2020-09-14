package com.example.myapplication.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.RoomWarnings;

@Entity(tableName = "user")
public class User {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer u_id;
    @NonNull
    private String name;
    @NonNull
    private Integer age;
    @NonNull
    private String description;
    @NonNull
    private Integer height ;

    @Embedded
    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    private Address address;
    @Ignore
    public User(@NonNull String name, @NonNull Integer age) {
        this.name = name;
        this.age = age;
    }

    @Ignore
    private String country;
    @Ignore
    private String province;
    @Ignore
    private String city;

    public Address getAddress() {
        return address;
    }

    public void setAddress(String  country,String province,String city) {
         this.country= country;
         this.province = province;
         this.city = city;
    }

    public Integer getHeight() {
        return height;
    }


    public Integer getU_id() {
        return u_id;
    }
    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Ignore
    public User(){}

    public User( @NonNull String name, @NonNull Integer age, @NonNull String description, @NonNull Integer height, Address address) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.height = height;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
