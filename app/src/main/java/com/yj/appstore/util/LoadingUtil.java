package com.yj.appstore.util;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingUtil {
    // 加载进度条
    ProgressDialog progressDialog;
    Context context;
    String defaultMsg;

    public LoadingUtil(Context context) {
        this.context = context;
        defaultMsg = "加载数据中...";
    }

    public void showLoading(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(true);
        }
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    public void showLoading(int resId) {
        showLoading(context.getResources().getString(resId));
    }

    public void showLoading() {
        showLoading(defaultMsg);
    }

    public void dismissLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
