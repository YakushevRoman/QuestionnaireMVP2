package com.example.questionnairemvp.MVP.QuestionnaireFragment;

import android.util.Log;

import com.example.questionnairemvp.Constants.Constants;
import com.example.questionnairemvp.ROOM.UserQuestionnaire;

public class QuestionnairePresenter {

    private final QuestionnaireModel questionnaireModel;
    private QuestionnaireFragment questionnaireFragment;

    public QuestionnairePresenter(QuestionnaireModel questionnaireModel) {
        this.questionnaireModel = questionnaireModel;
    }

    public void attachQuestionnaireFragment (QuestionnaireFragment questionnaireFragment){
        this.questionnaireFragment = questionnaireFragment;
    }

    public void detachQuestionnaireFragment(){
        questionnaireFragment = null;
    }

    public void addAnswerQuestionnairePresenter(int answer){
        UserQuestionnaire userQuestionnaire =  questionnaireFragment.getUserQuestionnaire(answer);
        questionnaireModel.addAnswer(userQuestionnaire, new QuestionnaireModel.IAddAnswer() {
            @Override
            public void addAnswer() {
                Log.d(Constants.ConstantsGlobal.TAG, "addAnswer (): ");
            }
        });
    }

}
