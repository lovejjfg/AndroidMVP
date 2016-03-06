package com.lovejjfg.androidmvp.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.lovejjfg.androidmvp.R;
import com.lovejjfg.androidmvp.model.LoginTask;
import com.lovejjfg.androidmvp.model.UserLoginTask;
import com.lovejjfg.androidmvp.view.LoginView;


/**
 * Created by lovejjfg on 2016/3/6.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginTask {

    private final LoginView view;
    private final Activity activity;
    private boolean isRun;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        this.activity = (Activity) view;
    }

    @Override
    public void onLoginClick() {
        // Reset errors.
        view.setPasswordError(null);
        view.setUserNameError(null);
        // Store values at the time of the login attempt.
        String password = view.getPassWord();
        String userName = view.getUserName();
        // Check for a valid email address.
        if (TextUtils.isEmpty(userName)) {
            view.setUserNameError(activity.getString(R.string.error_field_required));
            view.setUserNameFocus();
            return;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            view.setPasswordError(activity.getString(R.string.error_invalid_password));
            view.setPasswordFocus();
            return;
        }


        UserLoginTask mLoginTask = new UserLoginTask(this, userName, password);
        if (!isRun) {
            mLoginTask.execute((Void) null);
        }


    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void onLoginSuccess() {
        view.showProgress(false);
        view.showSuccess();
        isRun = false;
    }

    @Override
    public void onLoginFailed(String msg) {
        view.showProgress(false);
        view.setPasswordError(msg);
        isRun = false;

    }

    @Override
    public void onLoginCancelled() {
        view.showProgress(false);
        isRun = false;
    }

    @Override
    public void onLoginTaskStart() {
        isRun = true;
        view.showProgress(true);
    }
}
