package com.yj.appstore.model;

import com.yj.appstore.Constant;
import com.yj.appstore.api.AppService;
import com.yj.appstore.api.UserService;
import com.yj.appstore.listener.CommonListener;
import com.yj.appstore.model.bean.App;
import com.yj.appstore.model.bean.CommonResponse;
import com.yj.appstore.model.bean.User;
import com.yj.appstore.network.CommonCallBack;
import com.yj.appstore.network.NetClient;
import com.yj.appstore.util.SpUtil;

import java.util.List;

import retrofit2.Call;

public class LoginAndRegisterModel extends BaseModel{
    /**
     * 登录
     * @param userName
     * @param password
     * @param commonListener
     */
    public void login(String userName, String password, final CommonListener<User> commonListener) {
        UserService service = NetClient.getInstance().create(UserService.class);
        Call<CommonResponse<User>> call = service.login(userName, password);
        call.enqueue(new CommonCallBack<User>() {
            @Override
            public void onSuccess(User user) {
                commonListener.onSuccess(user);
            }

            @Override
            public void onFailure(String msg) {
                commonListener.onFailure(msg);
            }
        });
    }

    /**
     * 保存用户信息
     * @param user
     */
    public void saveUserAndToken(User user) {
        SpUtil.saveData(Constant.USERNAME, user.getUserName());
        SpUtil.saveData(Constant.TOKEN, user.getToken());
    }
}
