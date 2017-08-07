package com.haidaoservice.yhs.ui.certification.skill;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

public class UnlockActivity extends ToolbarActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, UnlockActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("开锁上岗认证");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("提交认证");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_unlock;
    }
}
