package com.example.questionnairemvp.ROOM;

import android.arch.persistence.room.Insert;

public interface DaoUserQuestionnaire {

    @Insert
    public void insertUserQuestionnaire (UserQuestionnaire userQuestionnaire);

}
