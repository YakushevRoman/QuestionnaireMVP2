package com.example.questionnairemvp.ROOM;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Users {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "Users_id")
    private long id;
    @ColumnInfo (name = "Users_user")
    private String name;
}
