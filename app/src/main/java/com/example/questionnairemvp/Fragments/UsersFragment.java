package com.example.questionnairemvp.Fragments;

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
import com.example.questionnairemvp.Recycler.RecyclerUsersAdapter;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerViewUsersFragment;
    private RecyclerUsersAdapter recyclerUsersAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        recyclerViewUsersFragment = (RecyclerView) view.findViewById(R.id.fragment_users_recycler_view);
        recyclerViewUsersFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewUsersFragment.setAdapter();
        return view;
    }
}
