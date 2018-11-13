package com.yj.appstore.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface DownloadService {
    /**
     * 下载apk文件
     * @param url
     * @return
     */
    @Streaming
    @GET
    public Call<ResponseBody> downloadApk(@Url String url);
}
