package com.example.questionnairemvp.MVP.QuestionnaireFragment;
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
import com.example.questionnairemvp.ROOM.DataBaseQuestionnaire;
import com.example.questionnairemvp.ROOM.UserQuestionnaire;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class QuestionnaireFragment extends Fragment {

    private QuestionnairePresenter questionnairePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startInitQuestionnaireFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_questionnare, container, false);

        questionnairePresenter.attachQuestionnaireFragment(this);

        Button unhappyButtonQuestionnaireFragment = view.findViewById(R.id.item_fragment_users_unhappy);
        Button usualButtonQuestionnaireFragment = view.findViewById(R.id.item_fragment_users_usual);
        Button happyButtonQuestionnaireFragment = view.findViewById(R.id.item_fragment_users_happy);

        unhappyButtonQuestionnaireFragment
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionnairePresenter.addAnswerQuestionnairePresenter(Constants.ConstantsQuestionnaireFragment.unhappyQuestionnaireFragment);
            }
        });

        usualButtonQuestionnaireFragment
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionnairePresenter.addAnswerQuestionnairePresenter(Constants.ConstantsQuestionnaireFragment.usualQuestionnaireFragment);
            }
        });

        happyButtonQuestionnaireFragment
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionnairePresenter.addAnswerQuestionnairePresenter(Constants.ConstantsQuestionnaireFragment.happyQuestionnaireFragment);
            }
        });
        return view;
    }

    private void startInitQuestionnaireFragment () {
        DataBaseQuestionnaire dataBaseQuestionnaire = AppQuestionnaire.getInstance().getDataBaseQuestionnaire();
        QuestionnaireModel questionnaireModel = new QuestionnaireModel(dataBaseQuestionnaire);
        questionnairePresenter = new QuestionnairePresenter(questionnaireModel);
    }

    public UserQuestionnaire getUserQuestionnaire(int answer){

        UserQuestionnaire userQuestionnaire = new UserQuestionnaire();
        userQuestionnaire.setId_name(getBundleQuestionnaireFragment());
        userQuestionnaire.setAnsver(answer);
        userQuestionnaire.setTime(getDataTime());

        return userQuestionnaire;
    }

    private long  getBundleQuestionnaireFragment(){
        Bundle bundleQuestionnaireFragment = getArguments();
        assert bundleQuestionnaireFragment != null;
        return bundleQuestionnaireFragment.getLong(Constants.ConstantsRecyclerUsersAdapter.ID_FOR_IDNAME);
    }

    private String  getDataTime (){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.getDefault());
        return dateFormat.format(date);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        questionnairePresenter.detachQuestionnaireFragment();
    }
}
