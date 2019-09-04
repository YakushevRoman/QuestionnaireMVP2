package com.example.questionnairemvp.MVP.UsersFragment;

import android.util.Log;
import com.example.questionnairemvp.Constants.Constants;
import com.example.questionnairemvp.ROOM.Users;
import java.util.List;

public class PresenterUsersFragment {
    private final ModelUsersFragment modelUsersFragment;
    private UsersFragment usersFragment;

    public PresenterUsersFragment(ModelUsersFragment modelUsersFragment) {
        this.modelUsersFragment = modelUsersFragment;
    }

    public void attachView (UsersFragment usersFragment){
        this.usersFragment = usersFragment;
    }

    public void detachView (){
        usersFragment = null;
    }

    public void viewIsAlready (){
        Log.d(Constants.ConstantsGlobal.TAG, "viewIsAlready (): ");
        loadUsersPresenter();
    }

    private void loadUsersPresenter(){
        Log.d(Constants.ConstantsGlobal.TAG, "loadUsersPresenter () ");
            modelUsersFragment.loadUsers(new ModelUsersFragment.ILoadUsers() {
                @Override
                public void onLoadUsers(final List<Users> usersList) {
                    Log.d(Constants.ConstantsGlobal.TAG, "loadUsersPresenter (): size"+ usersList.size());
                    usersFragment.showList(usersList);
                }
            });
    }

    public void addUsersPresenter(){
        Users users = new Users();
        users.setName("New users");
        Log.d(Constants.ConstantsGlobal.TAG, "addUsersPresenter() ");
        modelUsersFragment.addUser(users, new ModelUsersFragment.IAddUser() {
            @Override
            public void onAddUser() {
                loadUsersPresenter ();
            }
        });
    }

    public void deleteUserPresenter (){
        Users users = new Users();
        users.setName("New users");
        modelUsersFragment.deleteUsers(users, new ModelUsersFragment.IDeleteUser() {
            @Override
            public void onDeleteUsers(int count) {
                Log.d(Constants.ConstantsGlobal.TAG, "addUsersPresenter() " + count);
            }
        });
    }
}
