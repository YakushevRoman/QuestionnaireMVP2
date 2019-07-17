package com.example.questionnairemvp.ROOM;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface DaoUserQuestionnaire {

    @Insert
    public void insertUserQuestionnaire (UserQuestionnaire userQuestionnaire);

}
