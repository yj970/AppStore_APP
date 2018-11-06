package com.yj.appstore;

import com.yj.appstore.model.bean.App;

import java.util.List;

public class Contract {

    public interface LoginRegisterView {
        void showLoginAndRegister();
        void exit();
    }

    public interface LoginRegisterPresenter {
        void showLoginAndRegister();
        void exit();
    }

    public interface AppListView {
       void showLoading();
       void hideLoading();
        void onRefreshSuccess(List<App> data);

        void onRefreshFailure(String msg);
    }

    public interface AppListPresenter {
        void refresh();
        void loadMore();
    }
}
