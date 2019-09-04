package com.example.questionnairemvp.Retrofit2;

import com.example.questionnairemvp.ROOM.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface MyRetrofitServise {
    @GET("")
    Call<Users> listRepos (@Body Users users);
}
