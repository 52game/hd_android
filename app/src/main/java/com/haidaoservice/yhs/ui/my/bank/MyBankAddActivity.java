package com.haidaoservice.yhs.ui.my.bank;

import android.content.Context;
import android.content.Intent;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

public class MyBankAddActivity extends ToolbarActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MyBankAddActivity.class);
        return intent;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_my_bank_add;
    }

    @Override
    public void initView() {

    }
}
