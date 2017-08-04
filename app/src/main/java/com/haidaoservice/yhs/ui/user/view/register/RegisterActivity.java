package com.haidaoservice.yhs.ui.user.view.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;
import com.haidaoservice.yhs.ui.user.UserConstants;
import com.haidaoservice.yhs.ui.user.view.login.LoginActivity;
import com.haidaoservice.lib.common.tool.VerificationUtils;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;

/**
 * Created by zhangfei on 2017/3/27.
 */

public class RegisterActivity extends ToolbarActivity<RegisterPresenter> implements RegisterContract.RegisterView {


    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etPasswordAgain)
    EditText etPasswordAgain;

    @State
    String phone;

    public static Intent createIntent(Context context, String phone) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra(UserConstants.Extra.EXTRA_STRING_PHONE, phone);
        return intent;
    }

    @Override
    public void initView() {
        tvPhone.setText(getString(R.string.activity_password_phone_text, phone));
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tvTitle.setText("注册");

    }

    @Override
    protected void initIntentValue() {
        super.initIntentValue();
        phone = getIntent().getStringExtra(UserConstants.Extra.EXTRA_STRING_PHONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.user_register_activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.btnFinish})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFinish:
                String pass = etPassword.getText().toString();
                String sure = etPasswordAgain.getText().toString();
                if (TextUtils.isEmpty(pass)) {
                    showShortToast("密码不能为空");
                    return;
                }
                if (!VerificationUtils.matcherPassword(pass)) {
                    showShortToast("密码不能小于6位");
                    return;
                }
                if (!pass.equals(sure)) {
                    showShortToast("两次输入密码不一致");
                    return;
                }

                showDialog("注册中...");
                mPresenter.register(phone, pass);
                break;
        }
    }

    @Override
    public void showError(Throwable msg) {
        hideDialog();
    }

    @Override
    public void success() {
        hideDialog();
        showShortToast("注册成功");
        startActivity(LoginActivity.createIntent(mContext, phone).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
