package com.lovejjfg.androidmvp.model;

/**
 * Created by lovejjfg on 2016/3/6.
 */
public interface LoginTask {
    void onLoginSuccess();

    void onLoginFailed(String msg);

    void onLoginCancelled();

    void onLoginTaskStart();
}
