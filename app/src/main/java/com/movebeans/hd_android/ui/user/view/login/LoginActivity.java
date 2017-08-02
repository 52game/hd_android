package com.movebeans.hd_android.ui.user.view.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.movebeans.hd_android.R;
import com.movebeans.hd_android.base.ToolbarActivity;
import com.movebeans.hd_android.ui.location.LocationActivity;
import com.movebeans.hd_android.ui.user.User;
import com.movebeans.hd_android.ui.user.UserConstants;
import com.movebeans.hd_android.ui.user.UserSessionManager;
import com.movebeans.hd_android.ui.user.view.code.CodeActivity;
import com.movebeans.hd_android.ui.user.view.forget.ForgetActivity;
import com.movebeans.lib.common.tool.VerificationUtils;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;

/**
 * Created by zhangfei on 2017/3/24.
 */

public class LoginActivity extends ToolbarActivity<LoginPresenter> implements LoginContract.LoginView {


    @BindView(R.id.lay_login_container)
    LinearLayout layLoginContainer;
    @BindView(R.id.ll_login_layer)
    View llLoginLayer;
    @BindView(R.id.ll_login_options)
    LinearLayout llLoginOptions;
    @BindView(R.id.ll_login_pull)
    LinearLayout llLoginPull;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPassword)
    EditText etPassword;

    @State
    String phone;

    @State
    String pass;


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    public static Intent createIntent(Context context, String phone) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(UserConstants.Extra.EXTRA_STRING_PHONE, phone);
        return intent;
    }

    @Override
    public void initView() {
        if (!TextUtils.isEmpty(phone)) {
            etPhone.setText(phone);
        }
    }

    @Override
    protected void initIntentValue() {
        super.initIntentValue();
        phone = getIntent().getStringExtra(UserConstants.Extra.EXTRA_STRING_PHONE);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tvTitle.setText("登录");
    }

    @Override
    public int getLayoutId() {
        return R.layout.user_login_activity_login;
    }

    @OnClick({R.id.btnLogin, R.id.btnRegister, R.id.btnForget, R.id.ll_login_pull, R.id.ivWeibo, R.id.ivWx, R.id.ivQq, R.id.ll_login_layer})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String account = etPhone.getText().toString();
                String pass = etPassword.getText().toString();
                if (TextUtils.isEmpty(account)) {
                    showShortToast("手机号码不能为空");
                    return;
                }
                if (!VerificationUtils.matcherPhoneNum(account)) {
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
                this.pass = pass;
                showDialog("登录中...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        success(null);
                    }
                }, 1000);
                //mPresenter.login(account, pass);
                break;
            case R.id.btnRegister:
                startActivity(CodeActivity.createIntent(mContext));
                break;
            case R.id.btnForget:
                startActivity(ForgetActivity.createIntent(mContext));
                break;
            case R.id.ll_login_layer:
            case R.id.ll_login_pull:
                llLoginPull.animate().cancel();
                llLoginPull.animate().cancel();
                int height = llLoginOptions.getHeight();
                float progress = (llLoginLayer.getTag() != null && llLoginLayer.getTag() instanceof Float) ?
                        (float) llLoginLayer.getTag() : 1;
                int time = (int) (360 * progress);

                if (llLoginPull.getTag() != null) {
                    llLoginPull.setTag(null);
                    glide(height, progress, time);
                } else {
                    llLoginPull.setTag(true);
                    upGlide(height, progress, time);
                }
                break;
            case R.id.ivWeibo:
                showDialog("登录中...");
                //mPresenter.thirdLogin("", UserConstants.ThirdType.TYPE_SINA.value());
                break;
            case R.id.ivWx:
                showDialog("登录中...");
                //mPresenter.thirdLogin("", UserConstants.ThirdType.TYPE_WX.value());
                break;
            case R.id.ivQq:
                showDialog("登录中...");
                //mPresenter.thirdLogin("", UserConstants.ThirdType.TYPE_QQ.value());
                break;

        }
    }

    @Override
    public void showError(Throwable msg) {
        hideDialog();
        msg.printStackTrace();
    }


    @Override
    public void success(User user) {
        //user.setPass(pass);
        hideDialog();
//        UserSessionManager.getInstance().saveUserInfo(user);
//        UserSessionManager.getInstance().login();
        showShortToast("登录成功");
        startActivity(MainActivity.createIntent(mContext));
        finish();
    }

    @Override
    public void thirdSuccess(User user) {
        hideDialog();
        UserSessionManager.getInstance().saveUserInfo(user);
        UserSessionManager.getInstance().login();
        showShortToast("登录成功");
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.mRxManager.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * menu up glide
     *
     * @param height   height
     * @param progress progress
     * @param time     time
     */
    private void upGlide(int height, float progress, int time) {
        llLoginPull.animate()
                .translationYBy(height * progress)
                .translationY(0)
                .setDuration(time)
                .start();
        llLoginLayer.animate()
                .alphaBy(1 - progress)
                .alpha(1)
                .setDuration(time)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        llLoginLayer.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        if (animation instanceof ValueAnimator) {
                            llLoginLayer.setTag(((ValueAnimator) animation).getAnimatedValue());
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (animation instanceof ValueAnimator) {
                            llLoginLayer.setTag(((ValueAnimator) animation).getAnimatedValue());
                        }
                    }
                })
                .start();
    }

    /**
     * menu glide
     *
     * @param height   height
     * @param progress progress
     * @param time     time
     */
    private void glide(int height, float progress, int time) {
        llLoginPull.animate()
                .translationYBy(height - height * progress)
                .translationY(height)
                .setDuration(time)
                .start();
        llLoginLayer.animate()
                .alphaBy(1 * progress)
                .alpha(0)
                .setDuration(time)
                .setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        if (animation instanceof ValueAnimator) {
                            llLoginLayer.setTag(((ValueAnimator) animation).getAnimatedValue());
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (animation instanceof ValueAnimator) {
                            llLoginLayer.setTag(((ValueAnimator) animation).getAnimatedValue());
                        }
                        llLoginLayer.setVisibility(View.GONE);
                    }
                })
                .start();
    }
}
