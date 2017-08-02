package com.movebeans.hd_android.ui.sign;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.movebeans.hd_android.R;
import com.movebeans.hd_android.ui.sign.view.SignCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SignActivity extends AppCompatActivity implements View.OnClickListener{
    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, SignActivity.class);
        return intent;
    }
    TextView tvBack,tvRule,tvIsSign,tvGetGold,tvMonth,tvDay,tvWeek,tvDate;
    ImageView ivIsSign;
    SignCalendar sc;
    //是否签到
    boolean isSign=false;
    //日期
    String myDate;

    Dialog dialogSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initView();
        dialogShow();
        data();
    }
    public void  initView(){
        tvBack=(TextView) findViewById(R.id.tv_sign_back);
        tvRule=(TextView) findViewById(R.id.tv_sign_rule);
        tvIsSign=(TextView) findViewById(R.id.tv_sign_isSign);
        tvGetGold=(TextView) findViewById(R.id.tv_sign_getCold);
        tvMonth=(TextView) findViewById(R.id.tv_sign_month);
        tvDay=(TextView) findViewById(R.id.tv_sign_day);
        tvWeek=(TextView) findViewById(R.id.tv_sign_week);
        tvDate=(TextView) findViewById(R.id.tv_sign_date);
        ivIsSign=(ImageView) findViewById(R.id.iv_sign_isSign);
        sc=(SignCalendar) findViewById(R.id.sc_sign);
        tvBack.setOnClickListener(this);
        tvRule.setOnClickListener(this);
        ivIsSign.setOnClickListener(this);
        sc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_back:
                finish();
                break;
            case R.id.tv_sign_rule:
                break;
            case R.id.iv_sign_isSign:
                if (!isSign){
                    sc.setCalendarDayBgColor(myDate,0);
                    tvIsSign.setText("已签到");
                    tvGetGold.setText("");
                    isSign=true;
                    dialogSign.show();
                }
                break;

            default:
                break;
        }
    }
    public void dialogShow(){
        dialogSign = new Dialog(this);
        dialogSign.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view;
            view = LayoutInflater.from(this).inflate(R.layout.dialog_sign,
                    null);

        dialogSign.setContentView(view);
//        setDialogWindowAttr(dialog);
    }
//    public static void setDialogWindowAttr(Dialog dlg){
//        Window window = dlg.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.gravity = Gravity.CENTER;
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
//        lp.height = (int) ((WindowManager.LayoutParams.MATCH_PARENT)/2);
//         dlg.getWindow().setAttributes(lp);
//        }


    public void data(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        myDate = formatter.format(curDate);

        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(mWay)){
            mWay ="天";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }
        tvMonth.setText(mMonth+"月");
        tvDay.setText(mDay+"日");
        tvWeek.setText("星期"+mWay);
        tvDate.setText(mMonth+"月"+mDay+"日");

    }

}
