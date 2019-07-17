package com.example.questionnairemvp.ROOM;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Users.class,UserQuestionnaire.class},version = 1,exportSchema = true)
public abstract class DataBaseQuestionnaire extends RoomDatabase {
    public abstract DaoUsers getDaoUsers();
    public abstract DaoUserQuestionnaire getDaoUserQuestionnaire();
}
