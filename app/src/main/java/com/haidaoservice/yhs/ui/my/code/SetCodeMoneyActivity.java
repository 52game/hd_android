package com.haidaoservice.yhs.ui.my.code;

import android.content.Context;
import android.content.Intent;
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

public class SetCodeMoneyActivity extends ToolbarActivity {
    public static final int RESULT_CODE = 1001;
    @BindView(R.id.et_set_code_money)
    EditText etMoney;
    @BindView(R.id.btn_set_code_sure)
    Button bt;
    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, SetCodeMoneyActivity.class);
        return intent;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_set_code_money;
    }

    @Override
    public void initView() {
        super.initView();
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
    }

    @OnClick({R.id.et_set_code_money,R.id.btn_set_code_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_set_code_money:

                break;
            case R.id.btn_set_code_sure:
                String account = etMoney.getText().toString();
                if (TextUtils.isEmpty(account)) {
                    showShortToast("充值金额不能为空");
                    return;
                }
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("result", account);
                //设置返回数据
                setResult(RESULT_CODE, intent);
                finish();
                break;


        }
    }
}
