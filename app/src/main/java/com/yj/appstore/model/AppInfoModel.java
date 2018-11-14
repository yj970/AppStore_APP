package com.yj.appstore.model;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;

import com.yj.appstore.App;
import com.yj.appstore.api.AppService;
import com.yj.appstore.listener.CommonListener;
import com.yj.appstore.model.bean.AppInfo;
import com.yj.appstore.model.bean.Comment;
import com.yj.appstore.model.bean.CommonResponse;
import com.yj.appstore.network.CommonCallBack;
import com.yj.appstore.network.DownLoadListener;
import com.yj.appstore.network.DownloadUtil;
import com.yj.appstore.network.NetClient;

import java.io.File;
import java.util.List;

import retrofit2.Call;

public class AppInfoModel extends BaseModel{
    private int page = 1;
    private final int row = 10;
    private AppInfo appInfo;

    /**
     * 获取app详情
     * @param packageId 包名
     * @param commonListener
     */
    public void requestAppInfo(String packageId, final CommonListener<AppInfo> commonListener) {
        AppService service = NetClient.getInstance().create(AppService.class);
        Call<CommonResponse<AppInfo>> call = service.getAppInfo(packageId);
        call.enqueue(new CommonCallBack<AppInfo>() {
            @Override
            public void onSuccess(AppInfo appInfo) {
                AppInfoModel.this.appInfo = appInfo;
                commonListener.onSuccess(appInfo);
            }

            @Override
            public void onFailure(String msg) {
                commonListener.onFailure(msg);
            }
        });
    }

    /**
     * 刷新评论列表
     * @param packageId 包名
     * @param commonListener
     */
    public void requestComments(String packageId, final CommonListener<List<Comment>> commonListener) {
        page = 1;
        getComments(packageId, commonListener);
    }

    /**
     * 获取评论列表
     * @param packageId 包名
     * @param commonListener
     */
    private void getComments(String packageId, final CommonListener<List<Comment>> commonListener) {
        AppService service = NetClient.getInstance().create(AppService.class);
        Call<CommonResponse<List<Comment>>> call = service.getCommentList(packageId, String.valueOf(page), String.valueOf(row));
        call.enqueue(new CommonCallBack<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> comments) {
                commonListener.onSuccess(comments);
            }

            @Override
            public void onFailure(String msg) {
                commonListener.onFailure(msg);
            }
        });
    }

    /**
     * 加载更多评论列表
     * @param packageId 包名
     * @param commonListener
     */
    public void requestLoadMoreComments(String packageId, CommonListener<List<Comment>> commonListener) {
        page++;
        getComments(packageId, commonListener);
    }

    /**
     * 提交评论
     * @param packageId
     * @param comment
     * @param token
     * @param commonListener
     */
    public void commitComment(String packageId, String comment, String token, final CommonListener<Object> commonListener) {
        AppService service = NetClient.getInstance().create(AppService.class);
        Call<CommonResponse<Object>> call = service.commitComment(packageId, comment, token);
        call.enqueue(new CommonCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                commonListener.onSuccess(o);
            }

            @Override
            public void onFailure(String msg) {
                commonListener.onFailure(msg);
            }
        });
    }

    /**
     * 下载apk文件
     * @param loadListener
     */
    public void downloadFile(DownLoadListener loadListener) {
        String url = NetClient.baseUrl+appInfo.getDownloadUrl();
        DownloadUtil.getInstance().downloadApk(url, loadListener);
    }

    /**
     * 安装apk
     * @param activity
     * @param filePath
     * todo 兼容N版本
     */
    public void installApk(Activity activity, String filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }

    /**
     * 检查该app是否已安装
     * @param packageId
     * @return
     */
    public boolean checkIsInstall(Activity activity, String packageId) {
        List<PackageInfo> packageInfos = activity.getPackageManager().getInstalledPackages(0);
        for (PackageInfo info : packageInfos) {
            if (info.packageName.equals(packageId)) {
                return true;
            }
        }
        return false;
    }
}
