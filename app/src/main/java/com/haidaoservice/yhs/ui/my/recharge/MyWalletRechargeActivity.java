package com.haidaoservice.yhs.ui.my.recharge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class MyWalletRechargeActivity extends ToolbarActivity {
    @BindView(R.id.et_recharge_money)
    EditText etMoney;
    @BindView(R.id.btn_recharge)
    Button bt;
    BroadcastReceiver receiver;
    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MyWalletRechargeActivity.class);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_wallet_recharge;
    }

    @Override
    public void initView() {
        tvTitle.setText("充值");
        etMoney.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);
        //字符过滤,只可以输入小数点后两位
        etMoney.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(source.equals(".") && dest.toString().length() == 0){
                    return "0.";
                }
                if(dest.toString().contains(".")){
                    int index = dest.toString().indexOf(".");
                    int mlength = dest.toString().substring(index).length();
                    if(mlength == 3){
                        return "";
                    }
                }
                return null;
            }
        }});


        Timer timer = new Timer();

        timer.schedule(new TimerTask()   {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager)etMoney.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(etMoney, 0);
            }
        }, 500);
        receiver=new Receiver();//广播接受者实例
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("finish_recharge");
        registerReceiver(receiver,intentFilter);
    }

    @OnClick({R.id.et_recharge_money,R.id.btn_recharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_recharge_money:

                break;
            case R.id.btn_recharge:
                String account = etMoney.getText().toString();
                if (TextUtils.isEmpty(account)) {
                    showShortToast("充值金额不能为空");
                    return;
                }
                startActivity(RechargeOrderActivity.createIntent(mContext));
                break;


        }
    }
    public class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if(action.equals("finish_recharge")){
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
