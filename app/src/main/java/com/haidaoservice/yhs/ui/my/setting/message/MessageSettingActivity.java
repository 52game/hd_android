/*
*MessageSettingActivity     2017-08-07
*Copyright(c) 2017 xuwei Co.Ltd. All right reserved.
*/
package com.haidaoservice.yhs.ui.my.setting.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

import butterknife.OnClick;

/**
 * author:davidinchina on 2017/8/7 16:09
 * email:davicdinchina@gmail.com
 * version:1.0.0
 * des:
 */
public class MessageSettingActivity extends ToolbarActivity {

    /**
     * @desc 不带参数构造意图
     * @author:davidinchina on 2017/8/7 16:09
     */
    public static Intent createContext(Context mContext) {
        Intent intent = new Intent(mContext, MessageSettingActivity.class);
        return intent;
    }

    /**
     * @desc 带参构造意图
     * @author:davidinchina on 2017/8/7 16:09
     */
    public static Intent createContext(Context mContext, Bundle bundle) {
        Intent intent = new Intent(mContext, MessageSettingActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_message_setting;
    }
    @Override
    protected void initToolbar() {
        super.initToolbar();
        tvTitle.setText("消息管理");
        ivLeft.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.ivActionLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivActionLeft:
                finish();
                break;
        }
    }
    @Override
    public void initView() {
        super.initView();
    }
}