package com.example.questionnairemvp.ROOM;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.questionnairemvp.Constants.Constants;

@Entity
public class UserQuestionnaire {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "UserQuestionnaire_id")
    private long id;

    @ColumnInfo (name = "UserQuestionnaire_answer")
    private int ansver;

    @ColumnInfo (name = "UserQuestionnaire_time")
    private String time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAnsver() {
        return ansver;
    }

    public void setAnsver(int ansver) {
        this.ansver = ansver;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static UserQuestionnaire setFirstData (){
        UserQuestionnaire userQuestionnaire = new UserQuestionnaire();
        userQuestionnaire.ansver = Constants.ConstantsQuestionnaireFragment.unhappyQuestionnaireFragment;
        userQuestionnaire.time = "11:11:11";
        return userQuestionnaire;
    }
}
