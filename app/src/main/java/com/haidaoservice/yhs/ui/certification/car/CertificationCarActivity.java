package com.haidaoservice.yhs.ui.certification.car;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ClassName: CertificationCarActivity
 * Description: 添加车辆认证页面
 * Creator: chenwei
 * Date: 2017/8/2 15:19
 * Version: 1.0
 */
public class CertificationCarActivity extends ToolbarActivity {

    @BindView(R.id.tvLicenseArea)
    TextView tvLicenseArea;
    @BindView(R.id.etLicenseNumber)
    EditText etLicenseNumber;
    @BindView(R.id.tvModelsName)
    TextView tvModelsName;
    @BindView(R.id.tvColorName)
    TextView tvColorName;
    @BindView(R.id.etLength)
    EditText etLength;
    @BindView(R.id.etLoad)
    EditText etLoad;
    @BindView(R.id.etOwner)
    EditText etOwner;
    @BindView(R.id.etRegisterTime)
    EditText etRegisterTime;
    @BindView(R.id.tvCompanyName)
    TextView tvCompanyName;
    @BindView(R.id.ivPicture)
    ImageView ivPicture;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, CertificationCarActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("添加车辆");
        tvRight.setText("提交认证");
        tvRight.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certification_car;
    }

    @OnClick({R.id.lltModels, R.id.lltColor, R.id.lltCompany, R.id.tvActionRight, R.id.ivPicture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lltModels:
                startActivity(ChooseCarModelsActivity.createIntent(mContext));
                break;
            case R.id.lltColor:
                new SideChoosePopupWindow(mContext).showAsDropDown(mToolbar);
//                new SideChoosePopupWindow(mContext).showAtLocation(scrollView,Gravity.RIGHT,0,0);
                break;
            case R.id.lltCompany:
                break;
            case R.id.tvActionRight:
                break;
            case R.id.ivPicture:
                new LicenseTipPopupWindow(mContext).showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                break;
        }
    }
}
