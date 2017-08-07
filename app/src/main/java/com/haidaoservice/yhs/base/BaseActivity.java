package com.haidaoservice.yhs.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.haidaoservice.lib.base.BasePresenter;
import com.haidaoservice.lib.base.BaseView;
import com.haidaoservice.lib.common.tool.TUtil;
import com.haidaoservice.lib.view.dialog.DialogHelp;
import com.haidaoservice.yhs.utils.ActivityController;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;

/**
 * Created by zhangfei on 2017/3/24.
 */

public abstract class
BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    public String TAG = this.getClass().getSimpleName();

    /**
     * 当前上下文
     */
    protected Context mContext;

    protected T mPresenter;

    private Dialog mDialog = null;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        if (mPresenter != null && this instanceof BaseView) mPresenter.attachV(this, this);
        initWindow();
        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        } else {
            initIntentValue();
        }
        int layoutId = getLayoutId();
        if (layoutId > 0) {
            View contentView = LayoutInflater.from(mContext).inflate(layoutId, null, false);
            initWidget(contentView);
            setContentView(contentView);
            /**采用注入方式初始化控件变量**/
            unbinder = ButterKnife.bind(this, contentView);
            initToolbar();
            initView();
        }
        ActivityController.addActivity(this);
    }

    protected void initWidget(View contentView) {

    }

    /**
     * 初始化Window属性
     */
    public void initWindow() {

    }

    /**
     * 初始化ToolBar
     **/
    protected void initToolbar() {

    }

    /**
     * 初始化页面传值
     */
    protected void initIntentValue() {

    }

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 获取布局文件的id
     *
     * @return
     */
    public abstract int getLayoutId();


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前页面的数据
        Icepick.saveInstanceState(this, outState);
    }

    protected void showLongToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showDialog() {
        showDialog("加载中...");
    }

    protected void showDialog(String msg) {
        if (mDialog == null) {
            mDialog = DialogHelp.getWaitDialog(mContext, msg);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setCancelable(true);
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mPresenter.unsubscribe();
                }
            });
            mDialog.show();
        } else {
            mDialog.show();
        }
    }

    protected void hideDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachVM();
        if (unbinder != null)
            unbinder.unbind();
    }
}
