package com.haidaoservice.yhs.ui.my.transaction;

import android.content.Context;
import android.content.Intent;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

public class TransactionDetailActivity extends ToolbarActivity {
    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, TransactionDetailActivity.class);
        return intent;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_transaction_detail;
    }

    @Override
    public void initView() {
        super.initView();
    }
}
