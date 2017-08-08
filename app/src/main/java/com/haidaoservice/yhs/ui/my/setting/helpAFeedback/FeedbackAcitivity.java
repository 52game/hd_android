/*
*FeedbackAcitivity     2017-08-08
*Copyright(c) 2017 xuwei Co.Ltd. All right reserved.
*/
package com.haidaoservice.yhs.ui.my.setting.helpAFeedback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

import butterknife.OnClick;

/**
 * author:davidinchina on 2017/8/8 10:16
 * email:davicdinchina@gmail.com
 * version:1.0.0
 * des:问题反馈
 */
public class FeedbackAcitivity extends ToolbarActivity<HelpPresenter> implements HelpContact.HelpContactView {

    /**
     * @desc 不带参数构造意图
     * @author:davidinchina on 2017/8/8 10:16
     */
    public static Intent createContext(Context mContext) {
        Intent intent = new Intent(mContext, FeedbackAcitivity.class);
        return intent;
    }

    /**
     * @desc 带参构造意图
     * @author:davidinchina on 2017/8/8 10:16
     */
    public static Intent createContext(Context mContext, Bundle bundle) {
        Intent intent = new Intent(mContext, FeedbackAcitivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_layout_feedback_layout;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tvTitle.setText("问题反馈");
        tvRight.setText("提交");
        tvRight.setVisibility(View.VISIBLE);
        ivLeft.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.ivActionLeft, R.id.btnSubmit, R.id.tvActionRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivActionLeft:
                finish();
                break;
            case R.id.btnSubmit:
                showShortToast("提交反馈");
                finish();
                break;
            case R.id.tvActionRight:
                showShortToast("提交反馈");
                finish();
                break;
        }
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void showError(Throwable msg) {

    }

    @Override
    public void querySuccess(Object result) {

    }
}