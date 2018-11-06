package com.yj.appstore.api;

import com.yj.appstore.model.bean.App;
import com.yj.appstore.model.bean.CommonResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AppListService {
    @GET("app/queryList/{page}/{row}")
    Call<CommonResponse<List<App>>> getAppList(@Path("page") String page, @Path("row") String row);
}
