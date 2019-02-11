package com.yj.appstore.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.yj.appstore.R;
import com.yj.appstore.fragment.TitleFragment;

public class BaseTitleActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setTitle(String title) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transition = manager.beginTransaction();
        transition.replace(R.id.fl_title, TitleFragment.newInstance(title, true));
        transition.commitAllowingStateLoss();
    }

    public void setTitle(String title, boolean isShowBackBtn) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transition = manager.beginTransaction();
        transition.replace(R.id.fl_title, TitleFragment.newInstance(title, isShowBackBtn));
        transition.commitAllowingStateLoss();
    }
}
