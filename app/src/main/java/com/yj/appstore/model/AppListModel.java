package com.yj.appstore.model;

import com.yj.appstore.api.AppService;
import com.yj.appstore.listener.CommonListener;
import com.yj.appstore.model.bean.App;
import com.yj.appstore.model.bean.CommonResponse;
import com.yj.appstore.network.CommonCallBack;
import com.yj.appstore.network.NetClient;

import java.util.List;

import retrofit2.Call;

public class AppListModel extends BaseModel{
    private int page = 1;//页码
    final int row = 10;//行数

    public void refresh(final CommonListener listListener) {
        page = 1;
        requestAppList(listListener);
    }

    public void loadMore(final CommonListener listListener) {
        page ++;
        requestAppList(listListener);
    }

    /**
     * 请求app列表
     * @param listListener
     */
    private void requestAppList(final CommonListener listListener) {
        AppService service = NetClient.getInstance().create(AppService.class);
        Call<CommonResponse<List<App>>> call = service.getAppList(String.valueOf(page), String.valueOf(row));
        call.enqueue(new CommonCallBack<List<App>>() {
            @Override
            public void onSuccess(List<App> list) {
                listListener.onSuccess(list);
            }

            @Override
            public void onFailure(String msg) {
                listListener.onFailure(msg);
            }
        });
    }
}