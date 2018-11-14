package com.yj.appstore.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yj.appstore.Constant;
import com.yj.appstore.Contract;
import com.yj.appstore.R;
import com.yj.appstore.adapter.CommentAdapter;
import com.yj.appstore.model.bean.AppInfo;
import com.yj.appstore.model.bean.Comment;
import com.yj.appstore.network.NetClient;
import com.yj.appstore.presenter.AppInfoPresenterImpl;
import com.yj.appstore.util.ToastUtil;
import com.yj.appstore.v.CommentPopWindow;
import com.yj.appstore.v.recycle.YJRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppInfoActivity extends BaseTitleActivity implements Contract.AppInfoView, SwipeRefreshLayout.OnRefreshListener, YJRecyclerView.LoadMoreListener {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_packageId)
    TextView tvPackageId;
    @BindView(R.id.btn_download)
    Button btnDownload;
    @BindView(R.id.rv)
    YJRecyclerView rv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.btn_open)
    Button btnOpen;
    private String packageId;
    private Contract.AppInfoPresenter appInfoPresenter;
    private CommentAdapter adapter;
    private CommentPopWindow commentPopWindow;
    private ProgressDialog downloadView;

    public static void startAppInfoActivity(Context context, String packageId) {
        Intent intent = new Intent(context, AppInfoActivity.class);
        intent.putExtra(Constant.PACKAGEID, packageId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        ButterKnife.bind(this);

        if (getIntent().hasExtra(Constant.PACKAGEID)) {
            packageId = getIntent().getStringExtra(Constant.PACKAGEID);
        }

        setTitle("应用");
        srl.setOnRefreshListener(this);

        adapter = new CommentAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        rv.setLoadMoreListener(this);

        downloadView = new ProgressDialog(this);
        downloadView.setMessage("下载中...");
        downloadView.setCanceledOnTouchOutside(false);

        commentPopWindow = new CommentPopWindow(this);

        appInfoPresenter = new AppInfoPresenterImpl(this);
        appInfoPresenter.refresh(this, packageId);
        appInfoPresenter.refreshComments(packageId);

        setListener();
    }

    private void setListener() {
        commentPopWindow.setListener(new CommentPopWindow.ICommentPopWindowListener() {
            @Override
            public void onClickComment(String comment) {
                appInfoPresenter.commitComment(packageId, comment);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        appInfoPresenter.checkInstall(this, packageId);
    }

    @Override
    public void showLoading() {
        loadingUtil.showLoading();
    }

    @Override
    public void hideLoading() {
        loadingUtil.dismissLoading();
    }

    @Override
    public void loadAppInfoSuccess(AppInfo appInfo) {
        tvName.setText(appInfo.getAppName());
        tvPackageId.setText(packageId);
        Glide.with(this).load(NetClient.baseUrl + appInfo.getLogoPicUrl()).into(ivLogo);
    }

    @Override
    public void loadAppInfoFailure(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void showCommentLoading() {
        srl.setRefreshing(true);
    }

    @Override
    public void hideCommentLoading() {
        srl.setRefreshing(false);
    }

    @Override
    public void refreshCommentsSuccess(List<Comment> comments) {
        adapter.setData(comments);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshCommentsFailure(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void loadMoreCommentsSuccess(List<Comment> comments) {
        adapter.addData(comments);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreCommentsFailure(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void commitCommentSuccess() {
        ToastUtil.show("评论成功！");
        commentPopWindow.dismiss();
        appInfoPresenter.refreshComments(packageId);
    }

    @Override
    public void commitCommentFailure(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void showDownloading() {
        downloadView.show();
    }

    @Override
    public void hideDownloading() {
        downloadView.dismiss();
    }

    @Override
    public void showDownloadFailure(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void showDownloadProgress(int progress) {
        downloadView.setMessage("下载中.."+progress+"%");
    }

    @Override
    public void showDownloadBtnAndHideOpen() {
        btnDownload.setVisibility(View.VISIBLE);
        btnOpen.setVisibility(View.GONE);
    }

    @Override
    public void showOpenBtnAndHideDownload() {
        btnDownload.setVisibility(View.GONE);
        btnOpen.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        appInfoPresenter.refreshComments(packageId);
    }

    @Override
    public void onLoadMore() {
        appInfoPresenter.loadMoreComments(packageId);
    }

    @OnClick(R.id.tv_comment)
    public void onViewClicked() {
        commentPopWindow.show();
    }

    @OnClick(R.id.btn_download)
    public void onDownloadClicked() {
        appInfoPresenter.downloadFile(this);
    }

    @OnClick(R.id.btn_open)
    public void onOpenClicked() {
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(packageId);
        startActivity(LaunchIntent);
    }
}
