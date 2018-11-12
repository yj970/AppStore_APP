package com.yj.appstore.api;

import com.yj.appstore.model.bean.App;
import com.yj.appstore.model.bean.AppInfo;
import com.yj.appstore.model.bean.Comment;
import com.yj.appstore.model.bean.CommonResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AppService {
    /**
     * 获取app列表
     * @param page
     * @param row
     * @return
     */
    @GET("app/queryList/{page}/{row}")
    Call<CommonResponse<List<App>>> getAppList(@Path("page") String page, @Path("row") String row);

    /**
     * 获取app详情
     * @param packageId
     * @return
     */
    @GET("app/query/{packageId}")
    Call<CommonResponse<AppInfo>> getAppInfo(@Path("packageId") String packageId);

    /**
     * 获取评论列表
     * @param packageId
     * @param page
     * @param row
     * @return
     */
    @GET("app/queryCommentList/{packageId}/{page}/{row}")
    Call<CommonResponse<List<Comment>>> getCommentList(@Path("packageId") String packageId, @Path("page") String page, @Path("row") String row);

    /**
     * 提交评论
     * @param packageId
     * @param comment
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("app/comment")
    Call<CommonResponse<Object>> commitComment(@Field("packageId") String packageId, @Field("comment") String comment, @Field("token") String token);
}
