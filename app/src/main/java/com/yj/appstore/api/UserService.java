package com.yj.appstore.api;

import com.yj.appstore.model.bean.App;
import com.yj.appstore.model.bean.CommonResponse;
import com.yj.appstore.model.bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @FormUrlEncoded
    @POST("user/login")
    Call<CommonResponse<User>> login(@Field("userName") String userName, @Field("psw") String password);
}
