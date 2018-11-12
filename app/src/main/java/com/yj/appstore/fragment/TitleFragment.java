package com.yj.appstore.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yj.appstore.Constant;
import com.yj.appstore.R;
import com.yj.appstore.event.RefreshEventBarEvent;
import com.yj.appstore.util.SpUtil;
import com.yj.appstore.view.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TitleFragment extends Fragment{
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    public static TitleFragment newInstance(String title) {
        Bundle args = new Bundle();
        TitleFragment fragment = new TitleFragment();
        args.putString(Constant.TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_title, null, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            String title= getArguments().getString(Constant.TITLE);
            tvTitle.setText(title);
        }
        refreshTitleBar();
    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.tv_login, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                LoginActivity.startLoginActivity(getActivity());
                break;
            case R.id.tv_logout:
                SpUtil.saveData(Constant.TOKEN, "");
                SpUtil.saveData(Constant.USERNAME, "");
                refreshTitleBar();
                break;
        }
    }

    // 刷新
    public void refreshTitleBar() {
        String userName = (String) SpUtil.getData(Constant.USERNAME);
        if (TextUtils.isEmpty(userName)) {
            tvUser.setVisibility(View.GONE);
            tvLogout.setVisibility(View.GONE);
            tvLogin.setVisibility(View.VISIBLE);
        } else {
            tvUser.setText("欢迎, "+userName);
            tvUser.setVisibility(View.VISIBLE);
            tvLogin.setVisibility(View.GONE);
            tvLogout.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe
    public void acceptRefreshEventBar(RefreshEventBarEvent event) {
        refreshTitleBar();
    }

}
