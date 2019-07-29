package com.example.questionnairemvp.Fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.questionnairemvp.Constants.Constants;
import com.example.questionnairemvp.MVP.ModelUsersFragment;
import com.example.questionnairemvp.MVP.PresenterUsersFragment;
import com.example.questionnairemvp.R;
import com.example.questionnairemvp.ROOM.AppQuestionnaire;
import com.example.questionnairemvp.ROOM.DataBaseQuestionnaire;
import com.example.questionnairemvp.ROOM.Users;
import com.example.questionnairemvp.Recycler.RecyclerUsersAdapter;
import java.util.List;

public class UsersFragment extends Fragment {
    private PresenterUsersFragment presenterUsersFragment;
    private ModelUsersFragment modelUsersFragment;
    private DataBaseQuestionnaire dataBaseQuestionnaire;
    private RecyclerUsersAdapter recyclerUsersAdapter;
    RecyclerView recyclerViewUsersFragment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        startInitUsersFragment();
        /*List<Users> usersList = AppQuestionnaire
                .getInstance()
                .getDataBaseQuestionnaire()
                .getDaoUsers()
                .getAllUsers();*/
        recyclerUsersAdapter = new RecyclerUsersAdapter(this);
        RecyclerView recyclerViewUsersFragment = view.findViewById(R.id.fragment_users_recycler_view);
        recyclerViewUsersFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewUsersFragment.setAdapter(recyclerUsersAdapter);
        recyclerViewUsersFragment.setHasFixedSize(true);

        return view;
    }



    private void startInitUsersFragment (){
        dataBaseQuestionnaire = AppQuestionnaire.getInstance().getDataBaseQuestionnaire();
        modelUsersFragment = new ModelUsersFragment(dataBaseQuestionnaire);
        presenterUsersFragment = new PresenterUsersFragment(modelUsersFragment);
        presenterUsersFragment.attachView(this);
        presenterUsersFragment.viewIsAlready();

        /*
        */
    }

    public void showList(List <Users> usersList){
        recyclerUsersAdapter.setData(usersList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
