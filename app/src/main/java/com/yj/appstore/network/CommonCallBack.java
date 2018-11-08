package com.yj.appstore.network;


import com.yj.appstore.model.bean.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CommonCallBack<T> implements Callback<CommonResponse<T>> {

    @Override
    public void onResponse(Call<CommonResponse<T>> call, Response<CommonResponse<T>> response) {
        if (response.body() == null) {
            onFailure(response.errorBody().toString());
        } else {
            if (response.body().getCode().equals(NetCode.success)) {
                onSuccess(response.body().getData());
            } else {
                onFailure(response.body().getMsg());
            }
        }
    }

    @Override
    public void onFailure(Call<CommonResponse<T>> call, Throwable t) {
        onFailure(t.getMessage());
    }

    public abstract void onSuccess(T t);
    public abstract void onFailure(String msg);
}
