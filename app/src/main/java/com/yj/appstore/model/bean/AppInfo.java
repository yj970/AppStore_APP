package com.yj.appstore.model.bean;

public class AppInfo {
//         "appName":"app名字",
//                 "logoPicUrl":"图标logo路径",
//                 "appDescribes":"app简介",
//                 "screenShotPicUrl_1":"截图1路径",
//                 "screenShotPicUrl_2":"截图2路径",
//                 "screenShotPicUrl_3":"截图3路径",
//                 "downloadUrl":"app下载地址"

    private String appName;
    private String logoPicUrl;
    private String appDescribes;
    private String screenShotPicUrl_1;
    private String screenShotPicUrl_2;
    private String screenShotPicUrl_3;
    private String downloadUrl;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLogoPicUrl() {
        return logoPicUrl;
    }

    public void setLogoPicUrl(String logoPicUrl) {
        this.logoPicUrl = logoPicUrl;
    }

    public String getAppDescribes() {
        return appDescribes;
    }

    public void setAppDescribes(String appDescribes) {
        this.appDescribes = appDescribes;
    }

    public String getScreenShotPicUrl_1() {
        return screenShotPicUrl_1;
    }

    public void setScreenShotPicUrl_1(String screenShotPicUrl_1) {
        this.screenShotPicUrl_1 = screenShotPicUrl_1;
    }

    public String getScreenShotPicUrl_2() {
        return screenShotPicUrl_2;
    }

    public void setScreenShotPicUrl_2(String screenShotPicUrl_2) {
        this.screenShotPicUrl_2 = screenShotPicUrl_2;
    }

    public String getScreenShotPicUrl_3() {
        return screenShotPicUrl_3;
    }

    public void setScreenShotPicUrl_3(String screenShotPicUrl_3) {
        this.screenShotPicUrl_3 = screenShotPicUrl_3;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
