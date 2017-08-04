package com.haidaoservice.yhs.ui.user.view.code;

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
import com.haidaoservice.yhs.ui.user.view.forget.ForgetTwoActivity;
import com.haidaoservice.yhs.ui.user.view.register.RegisterActivity;
import com.haidaoservice.lib.common.tool.VerificationUtils;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;

/**
 * 验证码页面 -请求与验证
 * <p>
 * Created by zhangfei on 2017/3/29.
 */

public class CodeActivity extends ToolbarActivity<CodePresenter> implements CodeContract.CodeView {

    @State
    int type = UserConstants.CodeType.TYPE_REGISTER.value();
    @State
    String phone;
    @State
    String code;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnSendCode)
    Button btnSendCode;


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, CodeActivity.class);
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
        type = getIntent().getIntExtra(UserConstants.Extra.EXTRA_INTEGER_CODE_TYPE, UserConstants.CodeType.TYPE_REGISTER.value());
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        if (type == UserConstants.CodeType.TYPE_REGISTER.value())
            tvTitle.setText("注册");
        else
            tvTitle.setText("重置密码");
    }

    @Override
    public int getLayoutId() {
        return R.layout.user_code_activity_code;
    }

    @OnClick({R.id.btnSendCode, R.id.btnNext})
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
                mPresenter.getVCode(phone, type);
                break;
            }
            case R.id.btnNext: {
                phone = etPhone.getText().toString();
                code = etCode.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showShortToast("手机号码不能为空");
                    return;
                }
                if (!VerificationUtils.matcherPhoneNum(phone)) {
                    showShortToast("请输入正确的手机号码");
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    showShortToast("验证码不能为空");
                    return;
                }
                mPresenter.checkVCode(phone, type, code);
                break;
            }
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
    public void checkSuccess() {
        showShortToast("验证成功");
        if (type == UserConstants.CodeType.TYPE_REGISTER.value()) {
            startActivity(RegisterActivity.createIntent(mContext, phone));
        } else {
            startActivity(ForgetTwoActivity.createIntent(mContext, code));
        }

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
