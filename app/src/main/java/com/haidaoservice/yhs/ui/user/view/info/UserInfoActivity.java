package com.haidaoservice.yhs.ui.user.view.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

/**
 * Created by zhangfei on 2017/4/10.
 */

public class UserInfoActivity extends ToolbarActivity {


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.user_info_activity_user_info;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tvTitle.setText("我的资料");
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
