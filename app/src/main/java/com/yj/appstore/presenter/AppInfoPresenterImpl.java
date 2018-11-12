package com.yj.appstore.presenter;

import com.yj.appstore.Contract;
import com.yj.appstore.listener.CommonListener;
import com.yj.appstore.model.AppInfoModel;
import com.yj.appstore.model.bean.AppInfo;
import com.yj.appstore.model.bean.Comment;


import java.util.List;

import retrofit2.Call;

public class AppInfoPresenterImpl implements Contract.AppInfoPresenter {
    private Contract.AppInfoView view;
    private AppInfoModel model;

    public AppInfoPresenterImpl(Contract.AppInfoView view) {
        this.view = view;
        model = new AppInfoModel();
    }

    /**
     * 获取app详情
     * @param packageId
     */
    @Override
    public void refresh(String packageId) {
        view.showLoading();
        model.requestAppInfo(packageId, new CommonListener<AppInfo>() {
            @Override
            public void onSuccess(AppInfo appInfo) {
                view.hideLoading();
                view.loadAppInfoSuccess(appInfo);
            }

            @Override
            public void onFailure(String msg) {
                view.hideLoading();
                view.loadAppInfoFailure(msg);
            }
        });
    }

    /**
     * 获取app评论列表
     * @param packageId
     */
    @Override
    public void refreshComments(String packageId) {
        view.showCommentLoading();
        model.requestComments(packageId, new CommonListener<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> comments) {
                view.hideCommentLoading();
                view.refreshCommentsSuccess(comments);
            }

            @Override
            public void onFailure(String msg) {
                view.hideCommentLoading();
                view.refreshCommentsFailure(msg);
            }
        });
    }

    /**
     * 加载更多评论列表
     * @param packageId
     */
    @Override
    public void loadMoreComments(String packageId) {
        view.showCommentLoading();
        model.requestLoadMoreComments(packageId, new CommonListener<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> comments) {
                view.hideCommentLoading();
                view.loadMoreCommentsSuccess(comments);
            }

            @Override
            public void onFailure(String msg) {
                view.hideCommentLoading();
                view.loadMoreCommentsFailure(msg);
            }
        });
    }

    @Override
    public void commitComment(String packageId, String comment) {
        view.showLoading();
        String token = model.getToken();
        if (token != null) {
            model.commitComment(packageId, comment, token, new CommonListener<Object>() {
                @Override
                public void onSuccess(Object o) {
                    view.hideLoading();
                    view.commitCommentSuccess();
                }

                @Override
                public void onFailure(String msg) {
                    view.hideLoading();
                    view.commitCommentFailure(msg);
                }
            });
        } else {
            view.commitCommentFailure("请登录！");
        }
    }
}
