package com.example.questionnairemvp.Fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.questionnairemvp.Constants.Constants;
import com.example.questionnairemvp.MVP.ModelUsersFragment;
import com.example.questionnairemvp.MVP.PresenterUsersFragment;
import com.example.questionnairemvp.R;
import com.example.questionnairemvp.ROOM.AppQuestionnaire;
import com.example.questionnairemvp.ROOM.DaoUsers;
import com.example.questionnairemvp.ROOM.DataBaseQuestionnaire;
import com.example.questionnairemvp.ROOM.Users;
import com.example.questionnairemvp.Recycler.RecyclerUsersAdapter;
import java.util.List;

public class UsersFragment extends Fragment {
    private PresenterUsersFragment presenterUsersFragment;
    //private RecyclerUsersAdapter recyclerUsersAdapter;
    ModelUsersFragment modelUsersFragment;
    DaoUsers dataBaseQuestionnaire;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constants.ConstantsGlobal.TAG, "onCreate UsersFragment");
        dataBaseQuestionnaire = AppQuestionnaire.getInstance().getDataBaseQuestionnaire().getDaoUsers();
        modelUsersFragment = new ModelUsersFragment(dataBaseQuestionnaire);
        presenterUsersFragment = new PresenterUsersFragment(modelUsersFragment);
        presenterUsersFragment.attachView(this);
        presenterUsersFragment.viewIsAlready();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        Log.d(Constants.ConstantsGlobal.TAG, "onCreateView UsersFragment");
        //

        //
        FloatingActionButton addFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fragment_users__Add_FloatingActionButton);
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterUsersFragment.addUsersPresenter();
            }
        });
        return view;
    }

    public void showList(List <Users> usersList){
        Log.d(Constants.ConstantsGlobal.TAG, "showList(List <Users> usersList)"+ usersList.size());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterUsersFragment.detachView();
    }
}
