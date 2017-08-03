package com.movebeans.hd_android.ui.certification.enterprise;

import android.content.Context;
import android.content.Intent;

import com.movebeans.hd_android.R;
import com.movebeans.hd_android.base.ToolbarActivity;

/**
 * ClassName: AttachEnterpriseDetailActivity
 * Description: 企业资料
 * Creator: chenwei 
 * Date: 2017/8/3 16:28
 * Version: 1.0
 */
public class AttachEnterpriseDetailActivity extends ToolbarActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, AttachEnterpriseDetailActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("企业资料");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certification_attach_enterprise_detail;
    }
}
