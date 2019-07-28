package com.example.questionnairemvp.Recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.questionnairemvp.R;
import com.example.questionnairemvp.ROOM.Users;

import java.util.List;

public class RecyclerUsersAdapter extends RecyclerView.Adapter<RecyclerUsersAdapter.UsersHolder> {

    private List <Users> usersList;
    private Context context;

    public RecyclerUsersAdapter(List<Users> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_fragment_users, viewGroup, false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder usersHolder, int i) {
        Users users = usersList.get(i);
        usersHolder.bindUsersHolder(users);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class UsersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button buttonUsersHolder;
        private Users usersHolder;

        public UsersHolder(@NonNull View itemView) {
            super(itemView);
            buttonUsersHolder = itemView.findViewById(R.id.item_fragment__user);
            buttonUsersHolder.setOnClickListener(this);
        }

        public void  bindUsersHolder (Users users){
            usersHolder = users;
            buttonUsersHolder.setText(users.getName().toString());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, usersHolder.getName().toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
