package com.haidaoservice.yhs.ui.certification;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;
import com.haidaoservice.yhs.ui.certification.adapter.ItemCertificationCarAdapter;
import com.haidaoservice.yhs.ui.certification.adapter.ItemCertificationEnterpriseAdapter;
import com.haidaoservice.yhs.ui.certification.adapter.ItemCertificationSkillAdapter;
import com.haidaoservice.yhs.ui.certification.car.CarCertificationItem;
import com.haidaoservice.yhs.ui.certification.car.CertificationCarActivity;
import com.haidaoservice.yhs.ui.certification.enterprise.AttachSearchActivity;
import com.haidaoservice.yhs.ui.certification.enterprise.CertificationEnterpriseActivity;
import com.haidaoservice.yhs.ui.certification.skill.CertificateSkillActivity;
import com.haidaoservice.yhs.widget.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

import static com.haidaoservice.yhs.ui.certification.EnterpriseCertificationItem.TYPE_ATTACH_ENTERPRISE;
import static com.haidaoservice.yhs.ui.certification.SkillCertificationItem.REJECT;

/**
 * ClassName: CertificationCenterActivity
 * Description: 认证中心页面
 * Creator: chenwei
 * Date: 2017/8/2 09:58
 * Version: 1.0
 */
public class CertificationCenterActivity extends ToolbarActivity {
    @BindView(R.id.rlvSkills)
    RecyclerView rlvSkills;
    @BindView(R.id.rlvCar)
    RecyclerView rlvCar;
    @BindView(R.id.rlvEnterprise)
    RecyclerView rlvEnterprise;
    @BindView(R.id.tvSkillCount)
    TextView tvSkillCount;
    @BindView(R.id.tvCarCount)
    TextView tvCarCount;
    @BindView(R.id.tvEnterpriseCount)
    TextView tvEnterpriseCount;

    private ItemCertificationSkillAdapter skillAdapter;

    private ItemCertificationCarAdapter carAdapter;

    private ItemCertificationEnterpriseAdapter enterpriseAdapter;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, CertificationCenterActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        tvTitle.setText("认证中心");
        //家政服务认证列表
        rlvSkills.setLayoutManager(new ScrollLinearLayoutManager(mContext));
        skillAdapter = new ItemCertificationSkillAdapter(mContext);
        rlvSkills.setAdapter(skillAdapter);
        //车辆认证列表
        rlvCar.setLayoutManager(new ScrollLinearLayoutManager(mContext));
        carAdapter = new ItemCertificationCarAdapter(mContext);
        rlvCar.setAdapter(carAdapter);
        //企业认证列表
        rlvEnterprise.setLayoutManager(new ScrollLinearLayoutManager(mContext));
        enterpriseAdapter = new ItemCertificationEnterpriseAdapter(mContext);
        rlvEnterprise.setAdapter(enterpriseAdapter);

        mockData();
    }

    /**
     * 模块页面数据
     * FIX ME 正式开发时删除
     */
    private void mockData() {
        ArrayList<SkillCertificationItem> certificationItems = new ArrayList<>();
        Random random = new Random();
        int count = random.nextInt(5);
        for (int i = 0; i < count; i++) {
            SkillCertificationItem item = new SkillCertificationItem();
            item.setIcon("");
            item.setName("家政服务认证" + (i + 1));
            item.setStatus(random.nextInt(REJECT) + 1);
            certificationItems.add(item);
        }
        skillAdapter.addAll(certificationItems);
        tvSkillCount.setText(Html.fromHtml(String.format("已认证<font color='#FF0000'>%d</font>个",count)));


        count = random.nextInt(5);
        ArrayList<CarCertificationItem> cars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            CarCertificationItem item = new CarCertificationItem();
            item.setIcon("");
            item.setName("宝马五系530Li" + (i + 1));
            item.setLicence("苏A12345");
            item.setStatus(random.nextInt(REJECT) + 1);
            cars.add(item);
        }
        carAdapter.addAll(cars);
        tvCarCount.setText(Html.fromHtml(String.format("已认证<font color='#FF0000'>%d</font>个",count)));

        count = random.nextInt(5);
        ArrayList<EnterpriseCertificationItem> enterprises = new ArrayList<>();
        for (int i = 0; i <count; i++) {
            EnterpriseCertificationItem item = new EnterpriseCertificationItem();
            item.setIcon("");
            item.setName("江苏海道个服责任有限公司" + (i + 1));
            item.setType(random.nextInt(TYPE_ATTACH_ENTERPRISE) + 1);
            item.setStatus(random.nextInt(REJECT) + 1);
            enterprises.add(item);
        }
        enterpriseAdapter.addAll(enterprises);
        tvEnterpriseCount.setText(Html.fromHtml(String.format("已认证<font color='#FF0000'>%d</font>个",count)));
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_certification_center;
    }

    @OnClick({R.id.tvAddSkill, R.id.tvAddCar, R.id.tvCertificationEnterprise, R.id.tvAttachEnterprise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvAddSkill:
                startActivity(CertificateSkillActivity.createIntent(mContext));
                break;
            case R.id.tvAddCar:
                startActivity(CertificationCarActivity.createIntent(mContext));
                break;
            case R.id.tvCertificationEnterprise:
                startActivity(CertificationEnterpriseActivity.createIntent(mContext));
                break;
            case R.id.tvAttachEnterprise:
                startActivity(AttachSearchActivity.createIntent(mContext));
                break;
        }
    }
}
