package com.yj.appstore.listener;

import com.yj.appstore.model.bean.App;

import java.util.List;

public interface CommonListener<T> {
    void onSuccess(T t);
    void onFailure(String msg);
}
