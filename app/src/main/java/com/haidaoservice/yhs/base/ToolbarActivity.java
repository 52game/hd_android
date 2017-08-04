package com.haidaoservice.yhs.base;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.lib.base.BasePresenter;
import com.haidaoservice.lib.common.tool.InputMethodUtils;

import butterknife.BindView;

/**
 * Created by zhangfei on 2017/3/27.
 */

public abstract class ToolbarActivity<T extends BasePresenter> extends BaseActivity<T> {


    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @Nullable
    @BindView(R.id.tvActionTitle)
    public TextView tvTitle;
    @Nullable
    @BindView(R.id.tvActionRight)
    public TextView tvRight;
    @Nullable
    @BindView(R.id.ivActionRight)
    public ImageView ivRight;
    @Nullable
    @BindView(R.id.ivActionLeft)
    public ImageView ivLeft;


    /**
     * 初始化Toolbar
     */
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_36dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                InputMethodUtils.hide((Activity) mContext);
            }
        });
    }

    /**
     * 初始化Window属性
     */
    public void initWindow() {
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

    }

    @Override
    public void initView() {

    }

}
