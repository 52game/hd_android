package com.haidaoservice.yhs.ui.user.view.forget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;
import com.haidaoservice.yhs.ui.user.UserConstants;
import com.haidaoservice.lib.common.tool.VerificationUtils;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;

/**
 * Created by zhangfei on 2017/3/30.
 */

public class ForgetActivity extends ToolbarActivity<ForgetPresenter> implements ForgetContract.ForgetView {

    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etPasswordAgain)
    EditText etPasswordAgain;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnSendCode)
    Button btnSendCode;


    @State
    String phone;


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, ForgetActivity.class);
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
    protected void initToolbar() {
        super.initToolbar();
        tvTitle.setText("重置密码1");
    }

    @Override
    public int getLayoutId() {
        return R.layout.user_forget_activity_forget;
    }

    @OnClick({R.id.btnFinish, R.id.btnSendCode})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendCode: {
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showShortToast("手机号码不能为空");
                    return;
                }
                if (!VerificationUtils.matcherPhoneNum(phone)) {
                    showShortToast("请输入正确的手机号码");
                    return;
                }
                showDialog();
                mPresenter.getVCode(phone, UserConstants.CodeType.TYPE_RESET_PASS.value());
                break;
            }
            case R.id.btnFinish:
                phone = etPhone.getText().toString();
                String pass = etPassword.getText().toString();
                String sure = etPasswordAgain.getText().toString();
                String code = etCode.getText().toString();
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
    public void getSuccess() {
        hideDialog();
        showShortToast("获取成功");
    }

    @Override
    public void resetSuccess() {
        hideDialog();
        showShortToast("重置成功");
        finish();
    }

    @Override
    public void checkButtonDisabled() {
        btnSendCode.setEnabled(false);
    }

    @Override
    public void updateCountdown(int time) {
        btnSendCode.setText(getString(R.string.verify_send_code_disabled, String.valueOf(time)));
    }

    @Override
    public void resumeCheckButton() {
        btnSendCode.setEnabled(true);
        btnSendCode.setText("获取验证码");
    }
}
