package com.haidaoservice.yhs.ui.my;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;
import com.haidaoservice.yhs.ui.my.bank.MyBankActivity;
import com.haidaoservice.yhs.ui.my.code.IncomeCodeActivity;
import com.haidaoservice.yhs.ui.my.recharge.MyWalletRechargeActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MyWalletActivity extends ToolbarActivity {
    @BindView(R.id.tv_my_wallet_money)
    TextView tvMoney;
    @BindView(R.id.tv_my_wallet_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_my_wallet_income_code)
    TextView tvCode;
    @BindView(R.id.tv_my_wallet_withdrawals)
    TextView tvWithdrawals;
    @BindView(R.id.ll_my_wallet_bankcard)
    LinearLayout llBankCard;
    @BindView(R.id.ll_my_wallet_payment_safe)
    LinearLayout llPaymentSafe;
    @BindView(R.id.ll_my_wallet_gold)
    LinearLayout llGold;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MyWalletActivity.class);
        return intent;
    }
    @Override
    public void initView() {
        tvTitle.setText("我的钱包");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("明细");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_wallet;
    }
    @OnClick({R.id.tvActionRight, R.id.tv_my_wallet_recharge, R.id.tv_my_wallet_income_code,R.id.tv_my_wallet_withdrawals,R.id.ll_my_wallet_bankcard,R.id.ll_my_wallet_payment_safe,R.id.ll_my_wallet_gold})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvActionRight:
                break;
            case R.id.tv_my_wallet_recharge:
                startActivity(MyWalletRechargeActivity.createIntent(mContext));
                break;
            case R.id.tv_my_wallet_income_code:
                startActivity(IncomeCodeActivity.createIntent(mContext));
                break;
            case R.id.tv_my_wallet_withdrawals:
                break;
            case R.id.ll_my_wallet_bankcard:
                startActivity(MyBankActivity.createIntent(mContext));
                break;
            case R.id.ll_my_wallet_payment_safe:
                break;
            case R.id.ll_my_wallet_gold:
                break;
        }
    }
}
