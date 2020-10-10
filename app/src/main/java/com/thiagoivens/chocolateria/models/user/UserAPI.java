package com.thiagoivens.chocolateria.models.user;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface UserAPI {

    @POST("user/")
    Call<User> createUser(@Body User user);

    @FormUrlEncoded
    @POST("token/")
    Call<User> login(@Field("username") String username, @Field("password") String password);
}
