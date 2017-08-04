package com.haidaoservice.yhs;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.haidaoservice.yhs.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tvHome)
    TextView tvHome;
    @BindView(R.id.tvServiceOrder)
    TextView tvServiceOrder;
    @BindView(R.id.tvMsg)
    TextView tvMsg;
    @BindView(R.id.tvMy)
    TextView tvMy;
    @BindView(R.id.tvContent)
    FrameLayout tvContent;
    @BindView(R.id.tvPublish)
    TextView tvPublish;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @OnClick({R.id.tvHome, R.id.tvServiceOrder, R.id.tvMsg, R.id.tvMy, R.id.tvPublish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvHome:
                break;
            case R.id.tvServiceOrder:
                break;
            case R.id.tvMsg:
                break;
            case R.id.tvMy:
                break;
            case R.id.tvPublish:
                break;
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
