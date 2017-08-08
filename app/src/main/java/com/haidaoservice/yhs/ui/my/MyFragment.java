package com.haidaoservice.yhs.ui.my;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseFragment;
import com.haidaoservice.yhs.ui.certification.CertificationCenterActivity;
import com.haidaoservice.yhs.ui.user.view.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ClassName: MyFragment
 * Description: 首页>我的
 * Creator: chenwei
 * Date: 2017/8/4 13:14
 * Version: 1.0
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.tvActionTitle)
    TextView tvActionTitle;
    @BindView(R.id.ivActionLeft)
    ImageView ivActionLeft;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        tvActionTitle.setText("我的");
        ivActionLeft.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }


    @OnClick({R.id.rltCertificationCenter, R.id.rltLogin,R.id.rltSign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rltCertificationCenter:
                startActivity(CertificationCenterActivity.createIntent(mContext));
                break;
            case R.id.rltLogin:
                startActivity(LoginActivity.createIntent(mContext));
                break;
            case R.id.rltSign:
                startActivity(MyWalletActivity.createIntent(mContext));
                break;
        }
    }

}
