package com.haidaoservice.yhs.ui.certification.enterprise;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

/**
 * ClassName: CertificationEnterpriseActivity
 * Description: say something
 * Creator: chenwei
 * Date: 2017/8/2 18:33
 * Version: 1.0
 */
public class CertificationEnterpriseActivity extends ToolbarActivity {
    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, CertificationEnterpriseActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("认证企业");
        tvRight.setText("提交认证");
        tvRight.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certification_enterprise;
    }
}
