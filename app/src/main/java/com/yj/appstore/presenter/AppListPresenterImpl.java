package com.yj.appstore.presenter;

import com.yj.appstore.Contract;
import com.yj.appstore.api.AppListService;
import com.yj.appstore.model.bean.App;
import com.yj.appstore.model.bean.CommonResponse;
import com.yj.appstore.network.NetClient;
import com.yj.appstore.network.NetCode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppListPresenterImpl implements Contract.AppListPresenter{
    private int page = 1;
    final int row = 10;
    private Contract.AppListView view;

    public AppListPresenterImpl(Contract.AppListView view) {
        this.view = view;
    }

    @Override
    public void refresh() {
        view.showLoading();
        page = 1;
        AppListService service = NetClient.getInstance().create(AppListService.class);
        Call<CommonResponse<List<App>>> call = service.getAppList(String.valueOf(page), String.valueOf(row));
        call.enqueue(new Callback<CommonResponse<List<App>>>() {
            @Override
            public void onResponse(Call<CommonResponse<List<App>>> call, Response<CommonResponse<List<App>>> response) {
                view.hideLoading();
                if (response.body() != null) {
                    if (response.body().getCode().equals(NetCode.success)) {
                        view.onRefreshSuccess(response.body().getData());
                    } else {
                        view.onRefreshFailure(response.body().getMsg());
                    }
                } else {
                    view.onRefreshFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<CommonResponse<List<App>>> call, Throwable t) {
                view.onRefreshFailure(t.getMessage());
            }
        });
    }

    @Override
    public void loadMore() {
    }
}
