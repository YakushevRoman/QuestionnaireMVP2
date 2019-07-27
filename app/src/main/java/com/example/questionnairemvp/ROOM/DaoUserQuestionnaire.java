package com.example.questionnairemvp.ROOM;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DaoUserQuestionnaire {

    @Query("Select * From UserQuestionnaire")
    public List <UserQuestionnaire> getUserQuestionnaires ();

    @Insert
    public void insertUserQuestionnaire (UserQuestionnaire userQuestionnaire);

    /*@Delete
    public void deleteDaoUserQuestionnaire ();

    @Delete
    public  int deleteCountDaoUserQuestionnaire ();*/
}
