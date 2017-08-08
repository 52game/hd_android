package com.haidaoservice.yhs.ui.my.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.haidaoservice.lib.view.dialog.DialogHelp;
import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseActivity;
import com.haidaoservice.yhs.base.ToolbarActivity;
import com.haidaoservice.yhs.ui.my.setting.commnent.CommentActivity;
import com.haidaoservice.yhs.ui.my.setting.helpAFeedback.HelpActivity;
import com.haidaoservice.yhs.ui.my.setting.message.MessageSettingActivity;
import com.haidaoservice.yhs.utils.ActivityController;

import butterknife.OnClick;

public class SettingActivity extends ToolbarActivity {

    @Override
    public void initView() {

    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tvTitle.setText("设置");
        ivLeft.setVisibility(View.VISIBLE);
    }

    public static Intent createIntent(Context mContext) {
        Intent intent = new Intent(mContext, SettingActivity.class);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }


    @OnClick({R.id.ivActionLeft, R.id.rlSettingMessage, R.id.rlClear, R.id.rlComment, R.id.rlFeedback, R.id.rlCheckVersion, R.id.rlAbout, R.id.flQuit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivActionLeft:
                finish();
                break;
            case R.id.rlSettingMessage:
                showShortToast("消息管理");
                startActivity(MessageSettingActivity.createContext(mContext));
                break;
            case R.id.rlClear:
                DialogHelp.getConfirmDialog(mContext, "确定清空缓存？", "确定", "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showShortToast("清理了0.00M的空间");
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                break;
            case R.id.rlComment:
//                showShortToast("给我们评价");
                startActivity(CommentActivity.createContext(mContext));
                break;
            case R.id.rlFeedback:
                startActivity(HelpActivity.createContext(mContext));
                break;
            case R.id.rlCheckVersion:
                //这里走检查更新接口
                showShortToast("当前已经是最新版本");
                break;
            case R.id.rlAbout:
                startActivity(AboutActivity.createContext(mContext));
                break;
            case R.id.flQuit:
                DialogHelp.getConfirmDialog(mContext, "确定退出当前账号？", "退出", "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityController.restartSystem((BaseActivity) mContext);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                break;
        }
    }

}
