package com.example.questionnairemvp.ROOM;

import android.app.Application;
import android.arch.persistence.room.Room;

public class AppQuestionnaire extends Application {

    private AppQuestionnaire instance;
    private DataBaseQuestionnaire dataBaseQuestionnaire;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataBaseQuestionnaire = Room
                .databaseBuilder(this, DataBaseQuestionnaire.class,"questionnaire.db")
                .allowMainThreadQueries()
                .build();
    }

    public AppQuestionnaire getInstance (){
        return instance;
    }

    public DataBaseQuestionnaire getDataBaseQuestionnaire(){
        return dataBaseQuestionnaire;
    }
}
