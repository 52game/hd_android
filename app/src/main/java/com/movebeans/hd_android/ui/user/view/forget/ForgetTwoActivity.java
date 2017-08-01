package com.movebeans.hd_android.ui.user.view.forget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.movebeans.hd_android.R;
import com.movebeans.hd_android.base.ToolbarActivity;
import com.movebeans.hd_android.ui.user.UserConstants;
import com.movebeans.lib.common.tool.VerificationUtils;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;

/**
 * Created by zhangfei on 2017/4/24.
 */

public class ForgetTwoActivity extends ToolbarActivity<ForgetTwoPresenter> implements ForgetContract.ForgetTwoView {

    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etPasswordAgain)
    EditText etPasswordAgain;


    @State
    String phone;

    @State
    String code;


    public static Intent createIntent(Context context, String code) {
        Intent intent = new Intent(context, ForgetTwoActivity.class);
        intent.putExtra(UserConstants.Extra.EXTRA_STRING_CODE, code);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    protected void initIntentValue() {
        super.initIntentValue();
        code = getIntent().getStringExtra(UserConstants.Extra.EXTRA_STRING_CODE);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tvTitle.setText("重置密码1");
    }

    @Override
    public int getLayoutId() {
        return R.layout.user_forget_activity_two_forget;
    }

    @OnClick({R.id.btnFinish, R.id.btnSendCode})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFinish:
                String pass = etPassword.getText().toString();
                String sure = etPasswordAgain.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showShortToast("手机号码不能为空");
                    return;
                }
                if (!VerificationUtils.matcherPhoneNum(phone)) {
                    showShortToast("请输入正确的手机号码");
                    return;
                }
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
                if (TextUtils.isEmpty(code)) {
                    showShortToast("验证码不能为空");
                    return;
                }
                showDialog();
                mPresenter.forgetPassword(phone, pass, code);
                break;
        }
    }

    @Override
    public void showError(Throwable msg) {
        hideDialog();
    }

    @Override
    public void resetSuccess() {
        hideDialog();
        showShortToast("重置成功");
        finish();
    }
}
