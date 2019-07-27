package com.example.questionnairemvp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.questionnairemvp.Constants.Constants;
import com.example.questionnairemvp.R;
import com.example.questionnairemvp.ROOM.AppQuestionnaire;
import com.example.questionnairemvp.ROOM.DaoUserQuestionnaire;
import com.example.questionnairemvp.ROOM.UserQuestionnaire;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class QuestionnaireFragment extends Fragment {

    private DaoUserQuestionnaire daoUserQuestionnaire;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daoUserQuestionnaire = AppQuestionnaire.getInstance().getDataBaseQuestionnaire().getDaoUserQuestionnaire();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_questionnare, container, false);

        Button unhappyButtonQuestionnaireFragment = view.findViewById(R.id.item_fragment_users_unhappy);
        Button usualButtonQuestionnaireFragment = view.findViewById(R.id.item_fragment_users_usual);
        Button happyButtonQuestionnaireFragment = view.findViewById(R.id.item_fragment_users_happy);

        unhappyButtonQuestionnaireFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUserQuestionnaire(Constants.ConstantsQuestionnaireFragment.unhappyQuestionnaireFragment, getDataTime());
            }
        });

        usualButtonQuestionnaireFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUserQuestionnaire(Constants.ConstantsQuestionnaireFragment.usualQuestionnaireFragment, getDataTime());
            }
        });

        happyButtonQuestionnaireFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUserQuestionnaire(Constants.ConstantsQuestionnaireFragment.happyQuestionnaireFragment, getDataTime());
            }
        });
        return view;
    }

    private String  getDataTime (){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.getDefault());
        return dateFormat.format(date);
    }

    private void insertUserQuestionnaire(int answer, String time){
        UserQuestionnaire userQuestionnaire = new UserQuestionnaire();
        userQuestionnaire.setAnsver(answer);
        userQuestionnaire.setTime(time);
        daoUserQuestionnaire.insertUserQuestionnaire(userQuestionnaire);
    }
}
