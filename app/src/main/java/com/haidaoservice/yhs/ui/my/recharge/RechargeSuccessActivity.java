package com.haidaoservice.yhs.ui.my.recharge;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeSuccessActivity extends ToolbarActivity {
    @BindView(R.id.btn_recharge_success_sure)
    Button btnSure;
    @BindView(R.id.btn_recharge_success_continue)
    Button btnContinue;
    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, RechargeSuccessActivity.class);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge_success;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("支付订单");
    }
    @OnClick({R.id.btn_recharge_success_sure,R.id.btn_recharge_success_continue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_recharge_success_sure:
                Intent intent = new Intent();
                intent.setAction("finish_recharge");
                sendBroadcast(intent);
                finish();
                break;
            case R.id.btn_recharge_success_continue:
                finish();
                break;

        }
    }
}
