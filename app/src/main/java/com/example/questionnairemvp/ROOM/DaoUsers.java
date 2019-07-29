package com.example.questionnairemvp.ROOM;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DaoUsers extends  DaoBase <Users>{

    @Query("Select * from Users")
    public List <Users> getAllUsers ();

    @Insert
    public void insertUsers (Users users);

    /*@Delete
    public void deleteUsers ();
    */
    @Delete
    public int deleteCountUsers ();



}
