package com.example.questionnairemvp.Fragments;

import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.questionnairemvp.R;
import com.example.questionnairemvp.ROOM.AppQuestionnaire;
import com.example.questionnairemvp.ROOM.DataBaseQuestionnaire;
import com.example.questionnairemvp.ROOM.Users;
import com.example.questionnairemvp.Recycler.RecyclerUsersAdapter;

import java.util.List;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerViewUsersFragment;
    private RecyclerUsersAdapter recyclerUsersAdapter;
    private List <Users> usersList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersList = AppQuestionnaire
                .getInstance()
                .getDataBaseQuestionnaire()
                .getDaoUsers()
                .getAllUsers();
        recyclerUsersAdapter = new RecyclerUsersAdapter(usersList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        recyclerViewUsersFragment = (RecyclerView) view.findViewById(R.id.fragment_users_recycler_view);
        recyclerViewUsersFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewUsersFragment.setAdapter(recyclerUsersAdapter);
        return view;
    }
}
