package com.yj.appstore.presenter;

import com.yj.appstore.Contract;
import com.yj.appstore.event.RefreshEventBarEvent;
import com.yj.appstore.listener.CommonListener;
import com.yj.appstore.model.LoginAndRegisterModel;
import com.yj.appstore.model.bean.User;

import org.greenrobot.eventbus.EventBus;

public class LoginAndRegisterImpl implements Contract.LoginRegisterPresenter{
    private Contract.LoginRegisterView view;
    private LoginAndRegisterModel model;

    public LoginAndRegisterImpl(Contract.LoginRegisterView view) {
        this.view = view;
        model = new LoginAndRegisterModel();
    }

    @Override
    public void showLoginAndRegister() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void login(String userName, String password) {
        view.showLoading();
        model.login(userName, password, new CommonListener<User>() {
            @Override
            public void onSuccess(User user) {
                view.hideLoading();
                model.saveUserAndToken(user);
                EventBus.getDefault().post(new RefreshEventBarEvent());
                view.loginSuccess();
            }

            @Override
            public void onFailure(String msg) {
                view.hideLoading();
                view.loginFailure(msg);
            }
        });
    }
}
