package com.lovejjfg.androidmvp.view;

/**
 * Created by lovejjfg on 2016/3/6.
 *
 */
public interface LoginView {
    void showProgress(boolean isShow);

    void setUserNameError(String msg);

    void setPasswordError(String msg);

    void setPasswordFocus();

    void setUserNameFocus();

    void showSuccess();

    String getUserName();

    String getPassWord();

}
