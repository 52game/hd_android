package com.haidaoservice.yhs.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseActivity;
import com.haidaoservice.yhs.base.BaseFragment;
import com.haidaoservice.yhs.ui.home.HomeFragment;
import com.haidaoservice.yhs.ui.my.MyFragment;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tvHome)
    TextView tvHome;
    @BindView(R.id.tvServiceOrder)
    TextView tvServiceOrder;
    @BindView(R.id.tvMsg)
    TextView tvMsg;
    @BindView(R.id.tvMy)
    TextView tvMy;
    @BindView(R.id.fltContent)
    FrameLayout fltContent;
    @BindView(R.id.tvPublish)
    TextView tvPublish;

    HomeFragment mHomeFragment;

    MyFragment mMyFragment;

    private BaseFragment showFragment;
    @State
    boolean isExit = false;
    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @OnClick({R.id.tvHome, R.id.tvServiceOrder, R.id.tvMsg, R.id.tvMy, R.id.tvPublish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvHome:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    replaceFragment(mHomeFragment, true);
                } else {
                    replaceFragment(mHomeFragment, false);
                }
                break;
            case R.id.tvServiceOrder:
                break;
            case R.id.tvMsg:
                break;
            case R.id.tvMy:
                if (mMyFragment == null) {
                    mMyFragment = new MyFragment();
                    replaceFragment(mMyFragment, true);
                } else {
                    replaceFragment(mMyFragment, false);
                }
                break;
            case R.id.tvPublish:
                break;
        }
    }

    @Override
    public void initView() {
        findViewById(R.id.tvHome).performClick();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void replaceFragment(BaseFragment fragment, boolean isAdd) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (showFragment != null) {
            transaction.hide(showFragment);
        }
        if (isAdd) {
            transaction.add(R.id.fltContent, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();
        showFragment = fragment;
    }
    @Override
    public void onBackPressed() {
        if (isExit) {
            finish();
        } else {
            showShortToast("再按一次退出应用");
            isExit = true;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    isExit = false;
                }
            }, 2500);
        }
    }
}
