package com.haidaoservice.yhs.ui.user.view.modify.pass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;
import com.haidaoservice.yhs.ui.user.User;
import com.haidaoservice.yhs.ui.user.UserSessionManager;
import com.haidaoservice.yhs.ui.user.view.login.LoginActivity;
import com.haidaoservice.lib.common.tool.VerificationUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangfei on 2017/4/10.
 */

public class ModifyPassActivity extends ToolbarActivity<ModifyPassPresenter> implements ModifyPassContract.ModifyPassView {


    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etNewPassword)
    EditText etNewPassword;
    @BindView(R.id.etPasswordAgain)
    EditText etPasswordAgain;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, ModifyPassActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tvTitle.setText("修改密码");
    }

    @Override
    public int getLayoutId() {
        return R.layout.user_modify_activity_modify_pass;
    }


    @OnClick({R.id.btnModify})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnModify:
                String pass = etPassword.getText().toString();
                String newPass = etNewPassword.getText().toString();
                String sure = etPasswordAgain.getText().toString();
                User user = UserSessionManager.getInstance().getUserInfo();
                if (TextUtils.isEmpty(pass)) {
                    showShortToast("密码不能为空");
                    return;
                }
                if (!pass.equals(user.getPass())) {
                    showShortToast("请输入正确的密码");
                    return;
                }
                if (TextUtils.isEmpty(newPass)) {
                    showShortToast("新密码不能为空");
                    return;
                }
                if (!VerificationUtils.matcherPassword(newPass)) {
                    showShortToast("新密码不能小于6位");
                    return;
                }
                if (!newPass.equals(sure)) {
                    showShortToast("两次输入密码不一致");
                    return;
                }
                mPresenter.modifyPassword(pass, newPass);
                break;
        }
    }

    @Override
    public void showError(Throwable msg) {

    }

    @Override
    public void success() {
        showShortToast("修改密码成功");
        startActivity(LoginActivity.createIntent(mContext));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
