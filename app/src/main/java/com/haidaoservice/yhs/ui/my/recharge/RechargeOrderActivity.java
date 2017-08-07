package com.haidaoservice.yhs.ui.my.recharge;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeOrderActivity extends ToolbarActivity {
    @BindView(R.id.ll_recharge_order_wx)
    LinearLayout llWx;
    @BindView(R.id.ll_recharge_order_zfb)
    LinearLayout llZfb;
    @BindView(R.id.ll_recharge_order_bank)
    LinearLayout llBank;
    @BindView(R.id.ll_recharge_order_more)
    LinearLayout llmore;
    @BindView(R.id.cb_recharge_order_wx)
    CheckBox cbWx;
    @BindView(R.id.cb_recharge_order_zfb)
    CheckBox cbZfb;
    @BindView(R.id.cb_recharge_order_bank)
    CheckBox cbBank;
    ;
    @BindView(R.id.btn_recharge_sure)
    Button btSure;
    //是否支付成功
    boolean isSuccess=false;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, RechargeOrderActivity.class);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge_order;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("支付订单");
    }

    @OnClick({R.id.ll_recharge_order_wx, R.id.ll_recharge_order_zfb, R.id.ll_recharge_order_bank, R.id.ll_recharge_order_more, R.id.btn_recharge_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_recharge_order_wx:
                isChecked(cbWx);
                break;
            case R.id.ll_recharge_order_zfb:
                isChecked(cbZfb);
                break;
            case R.id.ll_recharge_order_bank:
                isChecked(cbBank);
                break;
            case R.id.ll_recharge_order_more:
                llBank.setVisibility(View.VISIBLE);
                llmore.setVisibility(View.GONE);
                break;
            case R.id.btn_recharge_sure:
                if(!isSuccess){
                    startActivity(RechargeSuccessActivity.createIntent(mContext));
                    finish();
                }else{

                }
                break;
        }
    }
    public void isChecked(CheckBox cb){
        cbBank.setChecked(false);
        cbWx.setChecked(false);
        cbZfb.setChecked(false);
        cb.setChecked(true);
    }
}
