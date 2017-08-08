package com.haidaoservice.yhs.ui.my.code;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class IncomeCodeActivity extends BaseActivity {
    private static final int REQUEST_CODE_MONEY = 1000;
    @BindView(R.id.tv_income_code_back)
    TextView tvBack;
    @BindView(R.id.tv_income_code_level)
    TextView tvLevel;
    @BindView(R.id.tv_income_code_money)
    TextView tvMoney;
    @BindView(R.id.tv_income_code_save)
    TextView tvSave;
    @BindView(R.id.tv_income_code_recode)
    TextView tvRecord;
    @BindView(R.id.tv_income_code_money_show)
    TextView tvShow;
    @BindView(R.id.ll_income_code_payment)
    LinearLayout llPayment;
    @BindView(R.id.tv_income_code_payment_state)
    TextView tvState;

    //付款码的布局
    @BindView(R.id.ll_income_code)
    LinearLayout ll;
    //支付成功
    @BindView(R.id.ll_income_code_success)
    LinearLayout llSuccess;
    //设置金额数量
    String money="";
    //支付状态(初始 0 支付中 1  支付成功 2  支付失败 3)
    int state=0;
    //测试支付按钮
    @BindView(R.id.tv_income_test)
    TextView tvTest;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(state==1){
                        llPayment.setVisibility(View.VISIBLE);
                        tvState.setText("支付中...");
                    }
                    if(state==2){
                        ll.setVisibility(View.GONE);
                        llSuccess.setVisibility(View.VISIBLE);
                    }
                    if(state==3){
                        tvState.setText("支付失败");
                    }
                    break;

                default:
                    break;
            }

        };
    };

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, IncomeCodeActivity.class);
        return intent;
    }
    @Override
    public void initView() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_income_code;
    }
    @OnClick({R.id.tv_income_code_back, R.id.tv_income_code_money,R.id.tv_income_code_save,R.id.tv_income_code_recode,R.id.tv_income_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_income_code_back:
                finish();
                break;
            case R.id.tv_income_code_money:
                if(money.equals("")){
                    startActivityForResult(SetCodeMoneyActivity.createIntent(mContext),REQUEST_CODE_MONEY);
                }else{
                    tvMoney.setText("设置金额");
                    tvShow.setVisibility(View.GONE);
                    money="";
                }
                break;
            case R.id.tv_income_code_save:
                break;
            case R.id.tv_income_code_recode:
                break;
            case R.id.tv_income_test:
                if(state==0){
                    state=1;
                    moni();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_MONEY){
            if(resultCode==SetCodeMoneyActivity.RESULT_CODE){
                tvShow.setVisibility(View.VISIBLE);
                money=data.getExtras().getString("result");
                tvShow.setText("￥"+money);
                tvMoney.setText("清除金额");
            }
        }
    }
    //模拟支付,进入页面2s后有人开始支付,4s后支付成功
    public void moni(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                    try {
                        Thread.sleep(000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                Message msg = new Message();
                msg.what = 1;
                state=1;
                handler.sendMessage(msg);
            }
        });
        thread.start();
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {

                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                Message msg = new Message();
                msg.what = 1;
                state=2;
                handler.sendMessage(msg);
            }
        });
        thread2.start();

    }
}
