package com.haidaoservice.yhs.ui.my;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseFragment;
import com.haidaoservice.yhs.ui.certification.CertificationCenterActivity;

import com.haidaoservice.yhs.ui.my.address.AddressListActivity;
import com.haidaoservice.yhs.ui.my.setting.SettingActivity;
import com.haidaoservice.yhs.ui.my.setting.personal.PersonalActivity;
import com.haidaoservice.yhs.ui.sign.SignActivity;
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
    @BindView(R.id.tvActionRight)
    TextView tvActionRight;
    @BindView(R.id.ivHead)
    ImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;

    @Override
    public void initView() {
        tvActionTitle.setText("我的");
        ivActionLeft.setVisibility(View.GONE);
        tvActionRight.setText("设置");
        tvActionRight.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }


    @OnClick({R.id.rltCertificationCenter, R.id.rltLogin, R.id.rltSign, R.id.tvActionRight,
            R.id.linInfo, R.id.rlWallet, R.id.rlAddress, R.id.rlHistory, R.id.rlCollection, R.id.rlSkills})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlSkills:
                //技能认证
                startActivity(CertificationCenterActivity.createIntent(mContext));
                break;
            case R.id.rltCertificationCenter:
                showShortToast("注册服务");
                break;
            case R.id.rltLogin:
                startActivity(LoginActivity.createIntent(mContext));
                break;
            case R.id.rltSign:
                startActivity(MyWalletActivity.createIntent(mContext));
                break;
            case R.id.tvActionRight:
                startActivity(SettingActivity.createIntent(mContext));
                break;
            case R.id.linInfo:
                showShortToast("修改个人信息");
                startActivity(PersonalActivity.createContext(mContext));
                break;
            case R.id.rlWallet:
                startActivity(MyWalletActivity.createIntent(mContext));
                break;
            case R.id.rlAddress:
//                showShortToast("地址设置");
                startActivity(AddressListActivity.createContext(mContext));
                break;
            case R.id.rlHistory:
                showShortToast("历史记录");
                break;
            case R.id.rlCollection:
                showShortToast("我的收藏");
                break;
        }
    }
}
