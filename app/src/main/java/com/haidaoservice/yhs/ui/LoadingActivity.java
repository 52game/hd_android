package com.haidaoservice.yhs.ui;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.haidaoservice.lib.common.tool.NetworkUtil;
import com.haidaoservice.lib.view.dialog.DialogHelp;
import com.haidaoservice.yhs.MainActivity;
import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseActivity;

import icepick.State;

/**
 * Created by zhangfei on 2017/3/28.
 */

public class LoadingActivity extends BaseActivity {

    private final static int REQUEST_PERMISSION = 100;
    /**
     * 当有版本更新时再次请求写的权限
     **/
    private final static int REQUEST_PERMISSION_VERSION = 101;

    private final static int HANDLER_START_APP = 0;
    private final static int HANDLER_WAITING = 1;

    private final static long WAITING_TIME = 3000;

    @State
    boolean isRequest = false;
    @State
    boolean isResponse = false;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_WAITING:
                    isResponse = true;
                    sendEmptyMessage(HANDLER_START_APP);
                    break;
                case HANDLER_START_APP:
                    if (isRequest && isResponse) {
                        startActivity(MainActivity.createIntent(mContext));
                        finish();
                    }
                    break;
            }
        }
    };

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, LoadingActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        isResponse = true;
        isRequest = true;
        handler.sendEmptyMessageDelayed(HANDLER_START_APP, WAITING_TIME);
    }

    @Override
    protected void initIntentValue() {
        super.initIntentValue();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_loading;
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCamera = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (checkCamera != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            } else {
                update();
                handler.sendEmptyMessageDelayed(HANDLER_WAITING, WAITING_TIME);
            }
        } else {
            update();
            handler.sendEmptyMessageDelayed(HANDLER_WAITING, WAITING_TIME);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                update();
            } else {
                DialogHelp.getConfirmDialog(mContext, "您未授予权限,可能导致有些功能无法使用,是否授权?", "再次授权", "下次再说", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkPermission();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        update();
                    }

                }).setCancelable(false).show();
            }
        } else if (requestCode == REQUEST_PERMISSION_VERSION) {
            if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                update();
            } else {
                DialogHelp.getMessageDialog(mContext, "您未授予权限,程序将退出!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
            }
        }
    }

    private void update() {
        if (NetworkUtil.isNetAvailable(mContext)) {
            return;
        }
        DialogHelp.getConfirmDialog(mContext, "数据请求失败，请检查网络", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                update();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        }).setCancelable(false).show();
    }


}
