package com.colin.demo.mvp_demo.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.colin.demo.R;
import com.colin.demo.mvp_demo.presenter.UserLoginPresenter;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements IUserLoginView {
    private EditText mUserNameEdit,mPasswordEdit;
    private Button mLoginBtn, mClearBtn;
    private ProgressBar mProgressBar;
    private UserLoginPresenter mUserLoginPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUserNameEdit = findViewById(R.id.et_username);
        mPasswordEdit = findViewById(R.id.et_password);
        mLoginBtn = findViewById(R.id.btn_login);
        mClearBtn = findViewById(R.id.btn_clear);

        mProgressBar = findViewById(R.id.login_progress);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();

            }
        });
        mClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.clear();

            }
        });

        mUserLoginPresenter=new UserLoginPresenter(this);
    }

    @Override
    public String getUserName() {
        return mUserNameEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordEdit.getText().toString();
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void toMainActivity() {
        showToast("登录成功,toMainActivity");

    }

    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        showToast("登录失败");

    }

    @Override
    public void clearUserName() {
        mUserNameEdit.setText("");

    }

    @Override
    public void clearPassword() {
        mPasswordEdit.setText("");
    }




}
