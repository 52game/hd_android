package com.movebeans.hd_android.ui.certification.car;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.movebeans.hd_android.R;


/**
 * ClassName: LicenseTipPopupWindow
 * Description: 添加行驶证选择图片提示
 * Creator: chenwei 
 * Date: 2017/8/2 16:29
 * Version: 1.0
 */
public class LicenseTipPopupWindow extends PopupWindow {

    public final static int LICENSE_PICTURE_REQUEST_CODE = 1000066;

    private Context mContext;

    private View contentView;

    public LicenseTipPopupWindow(Context context) {
        super(context);
        this.mContext = context;
        contentView = LayoutInflater.from(context).inflate(R.layout.popup_license_tip, null, false);
        //设置SelectPicPopupWindow的View
        this.setContentView(contentView);

        //设置SelectPicPopupWindow弹出窗体可点击
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setTouchable(true);
        this.setOutsideTouchable(false);
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        contentView.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        contentView.findViewById(R.id.btnUpload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((Activity)mContext).startActivityForResult(LICENSE_PICTURE_REQUEST_CODE);
            }
        });
        backgroundAlpha((Activity) mContext, 0.5f);//0.0-1.0
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) mContext, 1f);
            }
        });

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}
