package com.yj.appstore.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class TitleFragment extends Fragment {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static TitleFragment newInstance(String title, boolean isShowBackBtn) {
        Bundle args = new Bundle();
        TitleFragment fragment = new TitleFragment();
        args.putString(Constant.TITLE, title);
        args.putBoolean(Constant.IS_SHOW_BACK_BTN, isShowBackBtn);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            String title = getArguments().getString(Constant.TITLE);
            tvTitle.setText(title);
            boolean isShowBackBtn = getArguments().getBoolean(Constant.IS_SHOW_BACK_BTN);
            if (isShowBackBtn) {
                ivBack.setVisibility(View.VISIBLE);
            } else {
                ivBack.setVisibility(View.INVISIBLE);
            }
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        getActivity().finish();
    }
}
