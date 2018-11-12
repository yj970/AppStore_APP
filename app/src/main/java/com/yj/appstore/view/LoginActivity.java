package com.yj.appstore.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yj.appstore.Contract;
import com.yj.appstore.R;
import com.yj.appstore.presenter.LoginAndRegisterImpl;
import com.yj.appstore.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements Contract.LoginRegisterView{

    @BindView(R.id.et_login)
    EditText etLogin;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Contract.LoginRegisterPresenter presenter;

    public static void startLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginAndRegisterImpl(this);
    }

    @OnClick({R.id.btn_register, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                break;
            case R.id.btn_login:
                presenter.login(etLogin.getText().toString(), etPsw.getText().toString());
                break;
        }
    }

    @Override
    public void showLoginAndRegister() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loginSuccess() {
        ToastUtil.show("登录成功");
        finish();
    }

    @Override
    public void loginFailure(String msg) {
        ToastUtil.show("登录失败");
    }
}
