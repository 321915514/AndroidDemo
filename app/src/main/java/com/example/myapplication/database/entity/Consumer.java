package com.example.myapplication.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(tableName = "consumer",indices = @Index(name = "address_id",value = "address_id"), foreignKeys = @ForeignKey(entity = Address.class,parentColumns = "id",childColumns = "address_id",onDelete = ForeignKey.CASCADE))
public class Consumer {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "con_id")
    private Integer id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private Integer address_id;

    public Consumer() {
    }
    @Ignore
    public Consumer(@NonNull String username, @NonNull String password, @NonNull Integer address_id) {
        this.username = username;
        this.password = password;
        this.address_id = address_id;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    @Ignore
    private String home;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
