package com.yj.appstore.network;

public interface DownLoadListener {
    void onSuccess(String filePath);
    void onFailure(String msg);
    void onProgress(int progress);
}
