package com.bwie.login1605k1012.view;

import android.content.Context;

/**
 * Created by eric on 2018/10/12.
 */

public interface IView<T> {

    void success(T t);

    void failed(Exception e);

    String getUsername();

    String getPassword();

    void setUsername(String username);

    void setPassword(String password);

    void check(boolean isChecked);

    void show();

    void dissmiss();

    void gotoMain();

    Context getContext();
}
