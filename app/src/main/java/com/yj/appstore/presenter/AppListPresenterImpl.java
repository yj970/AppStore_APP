package com.yj.appstore.presenter;

import com.yj.appstore.Contract;

import com.yj.appstore.listener.CommonListener;
import com.yj.appstore.model.AppListModel;
import com.yj.appstore.model.bean.App;


import java.util.List;



public class AppListPresenterImpl implements Contract.AppListPresenter{
    private Contract.AppListView view;
    private AppListModel model;

    public AppListPresenterImpl(Contract.AppListView view) {
        this.view = view;
        model = new AppListModel();
    }

    @Override
    public void refresh() {
        view.showLoading();
        model.refresh(new CommonListener<List<App>>() {
            @Override
            public void onSuccess(List<App> list) {
                view.hideLoading();
                view.onRefreshSuccess(list);
            }

            @Override
            public void onFailure(String msg) {
                view.hideLoading();
                view.onRefreshFailure(msg);
            }
        });
    }

    @Override
    public void loadMore() {
        view.showLoading();
        model.loadMore(new CommonListener<List<App>>() {
            @Override
            public void onSuccess(List<App> list) {
                view.hideLoading();
                view.onLoadMoreSuccess(list);
            }

            @Override
            public void onFailure(String msg) {
                view.hideLoading();
                view.onLoadMoreFailure(msg);
            }
        });

    }
}
