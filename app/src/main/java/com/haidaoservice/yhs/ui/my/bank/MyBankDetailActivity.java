package com.haidaoservice.yhs.ui.my.bank;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

public class MyBankDetailActivity extends ToolbarActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MyBankDetailActivity.class);
        return intent;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_my_bank_detail;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setTextSize(20);
        tvRight.setText("· · ·");
    }
}
