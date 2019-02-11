package com.yj.appstore.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yj.appstore.R;

public class AppCommentFragment extends Fragment{

    public static AppCommentFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AppCommentFragment fragment = new AppCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appcomment, container, false);
    }
}
