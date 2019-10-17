package com.example.questionnairemvp;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;
import com.example.questionnairemvp.Constants.Constants;
import com.example.questionnairemvp.ROOM.DataBaseQuestionnaire;
import com.example.questionnairemvp.ROOM.Users;
import com.example.questionnairemvp.Retrofit2.MyRetrofitServise;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;


public class AppQuestionnaire extends Application {

    private static AppQuestionnaire instance;
    private DataBaseQuestionnaire dataBaseQuestionnaire;
    private MyRetrofitServise myRetrofitServise;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constants.ConstantsGlobal.TAG, "Application");
        instance = this;
        dataBaseQuestionnaire = Room
                .databaseBuilder(this, DataBaseQuestionnaire.class,"questionnaire.db")
                /*.addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance()
                                        .getDataBaseQuestionnaire()
                                        .getDaoUsers()
                                        .insertUsers(Users.setFirstDataUsers());
                                Log.d(Constants.ConstantsGlobal.TAG, "onCreate: DataBase is created Users");
                            }
                        });

                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance()
                                        .dataBaseQuestionnaire
                                        .getDaoUserQuestionnaire()
                                        .insertUserQuestionnaire(UserQuestionnaire.setFirstData());
                                Log.d(Constants.ConstantsGlobal.TAG, "onCreate: DataBase is created UserQuestionnaire");
                            }
                        });
                    }
                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        Log.d(Constants.ConstantsGlobal.TAG, "onOpen: DataBase is opened");
                    }
                })*/
                .allowMainThreadQueries()
                .build();

    }

    public static AppQuestionnaire getInstance(){
        return instance;
    }

    public DataBaseQuestionnaire getDataBaseQuestionnaire(){
        return dataBaseQuestionnaire;
    }
    public MyRetrofitServise getMyRetrofitServise () {
        return myRetrofitServise;
    }
}
