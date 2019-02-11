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
import com.yj.appstore.event.RefreshTitleBarEvent;
import com.yj.appstore.util.SpUtil;
import com.yj.appstore.view.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingFragment extends Fragment {

    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    Unbinder unbinder;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    // 刷新
    public void refreshTitleBar() {
        String userName = (String) SpUtil.getData(Constant.USERNAME);
        if (TextUtils.isEmpty(userName)) {
            tvUser.setVisibility(View.GONE);
            tvExit.setVisibility(View.GONE);
            tvLoginRegister.setVisibility(View.VISIBLE);
        } else {
            tvUser.setText("欢迎, "+userName);
            tvUser.setVisibility(View.VISIBLE);
            tvLoginRegister.setVisibility(View.GONE);
            tvExit.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe
    public void acceptRefreshEventBar(RefreshTitleBarEvent event) {
        refreshTitleBar();
    }

    @OnClick({R.id.tv_login_register, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_register:
                LoginActivity.startLoginActivity(getActivity());
                break;
            case R.id.tv_exit:
                SpUtil.saveData(Constant.TOKEN, "");
                SpUtil.saveData(Constant.USERNAME, "");
                EventBus.getDefault().post(new RefreshTitleBarEvent());
                break;
        }
    }
}
