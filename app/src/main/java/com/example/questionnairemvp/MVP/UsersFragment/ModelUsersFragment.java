package com.example.questionnairemvp.MVP.UsersFragment;

import android.os.AsyncTask;
import android.util.Log;
import com.example.questionnairemvp.Constants.Constants;
import com.example.questionnairemvp.ROOM.DaoUsers;
import com.example.questionnairemvp.ROOM.Users;
import java.util.List;

public class ModelUsersFragment {
private DaoUsers dataBaseQuestionnaire;

    public ModelUsersFragment(DaoUsers dataBaseQuestionnaire) {
        this.dataBaseQuestionnaire = dataBaseQuestionnaire;
    }

    public void addUser (Users user, IAddUser addUser){
        AddUser add = new AddUser(addUser);
        add.execute(user);
    }

    public void loadUsers (ILoadUsers iLoadUsers){
        LoadUsers loadUsers = new LoadUsers(iLoadUsers);
        loadUsers.execute();
    }

    public void deleteUsers (Users users, IDeleteUser iDeleteUser ){
        DeleteUsers deleteUsers = new DeleteUsers(iDeleteUser);
        deleteUsers.execute(users);
    }

    interface IAddUser {
        public void onAddUser();
    }

    interface ILoadUsers {
        public void onLoadUsers (List <Users> usersList);
    }

    interface  IDeleteUser {
        public void onDeleteUsers (int count);
    }

    class AddUser extends AsyncTask <Users, Void, Void>{

        private final IAddUser iAddUser;

        public AddUser(IAddUser iAddUser) {
            this.iAddUser = iAddUser;
        }

        @Override
        protected Void doInBackground(Users... users) {
            Users user = users[0];
            dataBaseQuestionnaire.insertUsers(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (iAddUser != null){
                iAddUser.onAddUser();
            }
        }
    }
    class LoadUsers extends AsyncTask <Void, Void,List <Users>>{

        private final ILoadUsers iLoadUsers;

        public LoadUsers(ILoadUsers iLoadUsers) {
            this.iLoadUsers = iLoadUsers;
        }

        @Override
        protected List<Users> doInBackground(Void... voids) {
            List <Users> usersList = dataBaseQuestionnaire.getAllUsers();
            return usersList;
        }

        @Override
        protected void onPostExecute(List<Users> usersList) {
            super.onPostExecute(usersList);
            if (iLoadUsers != null){
                iLoadUsers.onLoadUsers(usersList);
                Log.d(Constants.ConstantsGlobal.TAG, "LoadUsers: "+ usersList.size());;
            }
        }
    }
    class DeleteUsers extends AsyncTask <Users, Void, Integer>{
        private final IDeleteUser iDeleteUser;

        public DeleteUsers(IDeleteUser iDeleteUser) {
            this.iDeleteUser = iDeleteUser;
        }

        @Override
        protected Integer doInBackground(Users... users) {
            Users user = users[0];
            Integer count = dataBaseQuestionnaire.deleteUserQuestionnaire(user);
            return count;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            iDeleteUser.onDeleteUsers(integer);
        }
    }
}
