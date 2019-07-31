package com.example.questionnairemvp.MVP.QuestionnaireFragment;

import android.os.AsyncTask;

import com.example.questionnairemvp.ROOM.DataBaseQuestionnaire;
import com.example.questionnairemvp.ROOM.UserQuestionnaire;

public class QuestionnaireModel {

    private final DataBaseQuestionnaire dataBaseQuestionnaire;

    public QuestionnaireModel(DataBaseQuestionnaire dataBaseQuestionnaire) {
        this.dataBaseQuestionnaire = dataBaseQuestionnaire;
    }

    interface IAddAnswer {
        public void addAnswer ();
    }

    public void addAnswer (UserQuestionnaire userQuestionnaire, IAddAnswer iAddAnswer){
        AddAnswer addAnswer = new AddAnswer(iAddAnswer);
        addAnswer.execute(userQuestionnaire);
    }

    private class AddAnswer extends AsyncTask <UserQuestionnaire, Void, Void>{

        private final IAddAnswer iAddAnswer;

        private AddAnswer(IAddAnswer iAddAnswer) {
            this.iAddAnswer = iAddAnswer;
        }

        @Override
        protected Void doInBackground(UserQuestionnaire... userQuestionnaires) {
            UserQuestionnaire userQuestionnaire = userQuestionnaires[0];
            dataBaseQuestionnaire
                    .getDaoUserQuestionnaire()
                    .insertUserQuestionnaire(userQuestionnaire);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (iAddAnswer != null){
                iAddAnswer.addAnswer();
            }
        }
    }
}
