package com.bwie.login1605k1012.model;

import com.bwie.login1605k1012.inter.ICallBack;
import com.bwie.login1605k1012.utils.HttpUtils;

import java.lang.reflect.Type;

/**
 * Created by eric on 2018/10/12.
 */

public class LoginModel {
    public void login(String url, ICallBack callBack, Type type) {
        HttpUtils.getInstance().get(url, callBack, type);
    }
}
