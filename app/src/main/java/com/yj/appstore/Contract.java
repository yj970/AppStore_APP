package com.yj.appstore;

import android.app.Activity;

import com.yj.appstore.model.bean.App;
import com.yj.appstore.model.bean.AppInfo;
import com.yj.appstore.model.bean.Comment;

import java.util.List;

public class Contract {
    public interface LoginRegisterView {
        void showLoginAndRegister();
        void exit();

        void showLoading();

        void hideLoading();

        void loginSuccess();

        void loginFailure(String msg);
    }

    public interface LoginRegisterPresenter {
        void showLoginAndRegister();
        void exit();

        void login(String userName, String password);
    }

    public interface AppListView {
        void showLoading();
        void hideLoading();
        void onRefreshSuccess(List<App> data);
        void onRefreshFailure(String msg);
        void onLoadMoreSuccess(List<App> data);
        void onLoadMoreFailure(String msg);
    }

    public interface AppListPresenter {
        void refresh();
        void loadMore();
    }

    public interface AppInfoView {
        void showLoading();

        void hideLoading();

        void loadAppInfoSuccess(AppInfo appInfo);

        void loadAppInfoFailure(String msg);

        void showCommentLoading();

        void hideCommentLoading();

        void refreshCommentsSuccess(List<Comment> comments);

        void refreshCommentsFailure(String msg);

        void loadMoreCommentsSuccess(List<Comment> comments);

        void loadMoreCommentsFailure(String msg);

        void commitCommentSuccess();

        void commitCommentFailure(String msg);

        void showDownloading();

        void hideDownloading();

        void showDownloadFailure(String msg);

        void showDownloadProgress(int progress);

        void showDownloadBtnAndHideOpen();

        void showOpenBtnAndHideDownload();
    }
    public interface AppInfoPresenter{
        void refresh(Activity activity, String packageId);

        void refreshComments(String packageId);

        void loadMoreComments(String packageId);

        void commitComment(String packageId, String comment);

        void downloadFile(Activity activity);

        void checkInstall(Activity activity, String packageId);
    }
}
