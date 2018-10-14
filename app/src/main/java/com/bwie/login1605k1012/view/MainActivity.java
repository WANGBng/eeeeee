package com.bwie.login1605k1012.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.login1605k1012.HomeActivity;
import com.bwie.login1605k1012.R;
import com.bwie.login1605k1012.bean.LoginBean;
import com.bwie.login1605k1012.presenter.LoginPresenter;
import com.bwie.login1605k1012.view.IView;

public class MainActivity extends AppCompatActivity implements IView<LoginBean>, View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    private LoginPresenter presenter;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        setListener();
    }

    private void setListener() {
        btnLogin.setOnClickListener(this);
    }

    private void initData() {
        presenter = new LoginPresenter();
        presenter.attach(this);
        presenter.isFirst();

        pd = new ProgressDialog(this);
        pd.setMessage("正在登录，请稍候...");
    }

    private void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    @Override
    public void success(LoginBean loginBean) {
        if (loginBean != null) {
            Toast.makeText(this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "网络异常" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void setUsername(String username) {
        etUsername.setText(username);
    }

    @Override
    public void setPassword(String password) {
        etPassword.setText(password);
    }

    @Override
    public void check(boolean isChecked) {
        if (isChecked) {
            // 显示进度条
            presenter.login("http://www.xieast.com/api/user/login.php");
        }
    }

    @Override
    public void show() {
        pd.show();
    }

    @Override
    public void dissmiss() {
        pd.dismiss();
    }

    @Override
    public void gotoMain() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                presenter.check();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
