package com.yj.appstore.model.bean;

public class App {
    private String appName;
    private String packageId;
    private String logoPicUrl;
    private String downloadUrl;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getLogoPicUrl() {
        return logoPicUrl;
    }

    public void setLogoPicUrl(String logoPicUrl) {
        this.logoPicUrl = logoPicUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
