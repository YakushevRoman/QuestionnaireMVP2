package com.example.questionnairemvp.MVP.UsersFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.questionnairemvp.R;
import com.example.questionnairemvp.ROOM.AppQuestionnaire;
import com.example.questionnairemvp.ROOM.DaoUsers;
import com.example.questionnairemvp.ROOM.Users;
import com.example.questionnairemvp.Recycler.RecyclerUsersAdapter;
import java.util.List;

public class UsersFragment extends Fragment {
    private PresenterUsersFragment presenterUsersFragment;
    private RecyclerUsersAdapter recyclerUsersAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
            startInitializationUsersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        //
        RecyclerView recyclerView = view.findViewById(R.id.fragment_users_recycler_view);
        initializationRecyclerView(recyclerView);
        //
        FloatingActionButton addFloatingActionButton = view.findViewById(R.id.fragment_users__Add_FloatingActionButton);
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterUsersFragment.addUsersPresenter();
            }
        });
        //
        return view;
    }

    private void startInitializationUsersFragment(){
        //
        DaoUsers dataBaseQuestionnaire = AppQuestionnaire.getInstance().getDataBaseQuestionnaire().getDaoUsers();
        ModelUsersFragment modelUsersFragment = new ModelUsersFragment(dataBaseQuestionnaire);
        recyclerUsersAdapter = new RecyclerUsersAdapter(this);
        presenterUsersFragment = new PresenterUsersFragment(modelUsersFragment);
        presenterUsersFragment.attachView(this);
        presenterUsersFragment.viewIsAlready();
    }

    private void initializationRecyclerView (RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerUsersAdapter);
        recyclerView.setHasFixedSize(true);
    }

    public void showList(List <Users> usersList){
        recyclerUsersAdapter.setData(usersList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //
        presenterUsersFragment.detachView();
    }
}
