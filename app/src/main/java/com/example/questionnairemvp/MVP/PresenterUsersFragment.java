package com.example.questionnairemvp.MVP;

import android.util.Log;

import com.example.questionnairemvp.Constants.Constants;
import com.example.questionnairemvp.Fragments.UsersFragment;
import com.example.questionnairemvp.ROOM.Users;

import java.util.List;

public class PresenterUsersFragment {
    private final ModelUsersFragment modelUsersFragment;
    private UsersFragment usersFragment;

    public PresenterUsersFragment(ModelUsersFragment modelUsersFragment) {
        this.modelUsersFragment = modelUsersFragment;
    }
    //
    public void attachView (UsersFragment usersFragment){
        this.usersFragment = usersFragment;
    }
    //
    public void detachView (){
        usersFragment = null;
    }
    //
    public void viewIsAlready (){
        loadUsersPresenter();
    }
    //
    public void loadUsersPresenter (){
        modelUsersFragment.loadUsers(new ModelUsersFragment.ILoadUsers() {
            @Override
            public void onLoadUsers(List<Users> usersList) {
                usersFragment.showList(usersList);
            }
        });
    }
    //
    public void addUsersPresenter(){
        // получение users
        Users users = null;
        modelUsersFragment.addUser(users, new ModelUsersFragment.IAddUser() {
            @Override
            public void onAddUser() {

            }
        });
    }
    //
    /*public void deleteUsersPresenter (){
        modelUsersFragment.deleteAllUsers(new ModelUsersFragment.IDeleteUsers() {
            @Override
            public void onDeleteUsers(int countDeleteUsers) {

            }
        });
    }*/

}
