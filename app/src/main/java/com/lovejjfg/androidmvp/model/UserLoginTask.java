package com.lovejjfg.androidmvp.model;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.lovejjfg.androidmvp.presenter.LoginPresenterImpl;


/**
 * Created by lovejjfg on 2016/3/6.
 *
 */

/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 */
public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

    private final String mEmail;
    private final String mPassword;
    private final LoginTask presenter;
    private final Handler handler;

    public UserLoginTask(LoginPresenterImpl loginPresenter, String email, String password) {
        mEmail = email;
        mPassword = password;
        presenter = loginPresenter;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.
        handler.post(new Runnable() {
            @Override
            public void run() {
                presenter.onLoginTaskStart();
            }
        });
        try {
            // Simulate network access.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return false;
        }

        // TODO: register the new account here.
        return true;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        if (success) {
            presenter.onLoginSuccess();
        } else {
            presenter.onLoginFailed("This password is incorrect");
        }
    }

    @Override
    protected void onCancelled() {
        presenter.onLoginCancelled();
    }
}
