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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
