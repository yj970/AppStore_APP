package com.yj.appstore.model;

import com.yj.appstore.Constant;
import com.yj.appstore.util.SpUtil;

public class BaseModel {

    /**
     * 获取token
     * @return
     */
    public String getToken() {
        return (String) SpUtil.getData(Constant.TOKEN);
    }
}
