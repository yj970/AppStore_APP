package com.yj.appstore.network;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import com.yj.appstore.api.DownloadService;
import com.yj.appstore.util.L;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownloadUtil {
    private static final int DEFAULT_TIMEOUT = 15;
    static DownloadUtil downloadUtil;

    public static DownloadUtil getInstance() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }

    /**
     * 下载文件
     *
     * @param url
     */
    public  void downloadApk(final String url, final DownLoadListener downLoadListener) {
        final String apkFileName = getFileName(url);
        final Executor executor = new MainThreadExecutor(); // 用来切换主线程

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new DownloadInterceptor(executor, downLoadListener))
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)// 超时时间：15秒
                .build();
        final DownloadService api  = new Retrofit.Builder()
                .baseUrl(NetClient.baseUrl)
                .client(client)
                .build()
                .create(DownloadService.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<ResponseBody> result = api.downloadApk(url).execute();
                    writeResponseBodyToDisk(result.body(), apkFileName);
                    if (downLoadListener != null) {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                downLoadListener.onSuccess(getFileParentPath() + File.separator + apkFileName);
                            }
                        });
                    }
                } catch (final IOException e) {
                    if (downLoadListener != null) {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                downLoadListener.onFailure(e.getMessage());
                            }
                        });
                    }
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private class MainThreadExecutor implements Executor
    {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable r)
        {
            handler.post(r);
        }
    }

    /**
     * 获取文件名
     *
     * @param url
     * @return
     */
    private  String getFileName(String url) {
        String fileName = null;
        String[] s = url.split("/");
        fileName = s[s.length - 1];
        return fileName;
    }

    /**
     * 获取文件保存的父目录
     * @return
     */
    private String getFileParentPath() {
        return Environment.getExternalStorageDirectory().getPath() + File.separator + "yjAppStore";
    }

    /**
     * 保存apk文件
     *
     * @param body
     * @return
     */
    private  boolean writeResponseBodyToDisk(ResponseBody body, String fileName) {
        // 保存在根目录/yjAppStore里面
        File dir = new File(getFileParentPath());
        if (!dir.exists()) {
            boolean success = dir.mkdir();
            L.d(success + "");
        }
        try {
            File apkFile = new File(dir + File.separator + fileName);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(apkFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
