package com.yj.appstore.util;

import android.widget.Toast;

import com.yj.appstore.App;

public class ToastUtil {
    public static void show(String msg) {
        if (msg == null) {
            return;
        }
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
