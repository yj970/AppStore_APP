package com.yj.appstore.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.yj.appstore.util.LoadingUtil;

public class BaseActivity extends AppCompatActivity{
    public LoadingUtil loadingUtil; // 加载中控件
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingUtil = new LoadingUtil(this);
    }
}
