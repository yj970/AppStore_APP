package com.yj.appstore.model;

import com.yj.appstore.App;
import com.yj.appstore.api.AppService;
import com.yj.appstore.listener.CommonListener;
import com.yj.appstore.model.bean.AppInfo;
import com.yj.appstore.model.bean.Comment;
import com.yj.appstore.model.bean.CommonResponse;
import com.yj.appstore.network.CommonCallBack;
import com.yj.appstore.network.NetClient;

import java.util.List;

import retrofit2.Call;

public class AppInfoModel {
    private int page = 1;
    private final int row = 10;
    /**
     * 获取app详情
     * @param packageId 包名
     * @param commonListener
     */
    public void requestAppInfo(String packageId, final CommonListener<AppInfo> commonListener) {
        AppService service = NetClient.getInstance().create(AppService.class);
        Call<CommonResponse<AppInfo>> call = service.getAppInfo(packageId);
        call.enqueue(new CommonCallBack<AppInfo>() {
            @Override
            public void onSuccess(AppInfo appInfo) {
                commonListener.onSuccess(appInfo);
            }

            @Override
            public void onFailure(String msg) {
                commonListener.onFailure(msg);
            }
        });
    }

    /**
     * 刷新评论列表
     * @param packageId 包名
     * @param commonListener
     */
    public void requestComments(String packageId, final CommonListener<List<Comment>> commonListener) {
        page = 1;
        getComments(packageId, commonListener);
    }

    /**
     * 获取评论列表
     * @param packageId 包名
     * @param commonListener
     */
    private void getComments(String packageId, final CommonListener<List<Comment>> commonListener) {
        AppService service = NetClient.getInstance().create(AppService.class);
        Call<CommonResponse<List<Comment>>> call = service.getCommentList(packageId, String.valueOf(page), String.valueOf(row));
        call.enqueue(new CommonCallBack<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> comments) {
                commonListener.onSuccess(comments);
            }

            @Override
            public void onFailure(String msg) {
                commonListener.onFailure(msg);
            }
        });
    }

    /**
     * 加载更多评论列表
     * @param packageId 包名
     * @param commonListener
     */
    public void requestLoadMoreComments(String packageId, CommonListener<List<Comment>> commonListener) {
        page++;
        getComments(packageId, commonListener);
    }
}
