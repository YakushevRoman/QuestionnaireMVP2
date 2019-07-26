package com.example.questionnairemvp.ROOM;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.questionnairemvp.Constants.Constants;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppQuestionnaire extends Application {

    private static AppQuestionnaire instance;
    private DataBaseQuestionnaire dataBaseQuestionnaire;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constants.TAG, " AppQuestionnaire onCreate()");
        instance = this;
        dataBaseQuestionnaire = Room
                .databaseBuilder(this, DataBaseQuestionnaire.class,"questionnaire.db")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance()
                                        .getDataBaseQuestionnaire()
                                        .getDaoUsers()
                                        .insertUsers(Users.setFirstData());
                                Log.d(Constants.TAG, "onCreate: DataBase is opened");
                            }
                        });
                    }
                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        Log.d(Constants.TAG, "onOpen: DataBase is created");
                    }
                })
                .allowMainThreadQueries()
                .build();
    }

    public static AppQuestionnaire getInstance (){
        return instance;
    }

    public DataBaseQuestionnaire getDataBaseQuestionnaire(){
        return dataBaseQuestionnaire;
    }
}
