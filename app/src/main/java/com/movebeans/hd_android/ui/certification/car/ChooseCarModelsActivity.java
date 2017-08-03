package com.movebeans.hd_android.ui.certification.car;

import android.content.Context;
import android.content.Intent;

import com.movebeans.hd_android.R;
import com.movebeans.hd_android.base.ToolbarActivity;

/**
 * ClassName: ChooseCarModelsActivity
 * Description: 选择车型页面
 * Creator: chenwei 
 * Date: 2017/8/3 17:09
 * Version: 1.0
 */
public class ChooseCarModelsActivity extends ToolbarActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, ChooseCarModelsActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("选择车型");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certification_car_models;
    }
}
