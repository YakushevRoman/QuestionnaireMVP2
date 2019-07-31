package com.example.questionnairemvp.Recycler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.example.questionnairemvp.Constants.Constants;
import com.example.questionnairemvp.MVP.QuestionnaireFragment.QuestionnaireFragment;
import com.example.questionnairemvp.R;
import com.example.questionnairemvp.ROOM.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecyclerUsersAdapter extends RecyclerView.Adapter<RecyclerUsersAdapter.UsersHolder> {

    private List <Users> usersList = new ArrayList<>();
    private Fragment fragment;

    public RecyclerUsersAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_fragment_users, viewGroup, false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder usersHolder, int i) {
        usersHolder.bindUsersHolder(usersList.get(i));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void setData (List <Users> data){
        usersList.clear();
        usersList.addAll(data);
        notifyDataSetChanged();
        Log.d(Constants.ConstantsGlobal.TAG, "setData: "+ data.size());
    }


    class UsersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button buttonUsersHolder;
        private Users usersHolder;

        UsersHolder(@NonNull View itemView) {
            super(itemView);
            buttonUsersHolder = itemView.findViewById(R.id.item_fragment__user);
            buttonUsersHolder.setOnClickListener(this);
        }

        void  bindUsersHolder(Users users){
            usersHolder = users;
            buttonUsersHolder.setText(users.getName());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), usersHolder.getName(), Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putLong(Constants.ConstantsRecyclerUsersAdapter.ID_FOR_IDNAME, usersHolder.getId());

            FragmentManager fragmentManager = fragment.getFragmentManager();
            Fragment fragmentQuestionnaireFragment = Objects.requireNonNull(fragmentManager).findFragmentById(R.id.fragment_container);
            if (fragmentQuestionnaireFragment != null){
                fragmentQuestionnaireFragment = new QuestionnaireFragment();
                fragmentQuestionnaireFragment.setArguments(bundle);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragmentQuestionnaireFragment)
                        .addToBackStack(null)
                        .commit();
            }
        }
    }
}
