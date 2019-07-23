package com.example.questionnairemvp.Recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.questionnairemvp.R;
import com.example.questionnairemvp.ROOM.Users;

import java.util.List;

public class RecyclerUsers extends RecyclerView.Adapter<RecyclerUsers.UsersHolder> {

    private List <Users> usersList;

    public RecyclerUsers(List<Users> usersList) {
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recycler_view, viewGroup, false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder usersHolder, int i) {
        Users users = usersList.get(i);
        usersHolder.bindUsersHolder(i);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class UsersHolder extends RecyclerView.ViewHolder{
        private Button unhappy;
        private Button usual;
        private Button happy;

        public UsersHolder(@NonNull View itemView) {
            super(itemView);
            unhappy = itemView.findViewById(R.id.item_fragment_users_unhappy);
            usual = itemView.findViewById(R.id.item_fragment_users_usual);
            happy = itemView.findViewById(R.id.item_fragment_users_happy);
        }

        public void  bindUsersHolder (int id){

        }
    }

}
