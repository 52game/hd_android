package com.haidaoservice.lib.voice;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;


/**
 * Created by fan on 2016/6/23.
 */
public class AudioPopupWindow extends PopupWindow {

    private View parent;
    int popupWidth;
    int popupHeight;

    public AudioPopupWindow(Context context, View contentView, View parent) {
        super(context);
        this.parent = parent;
        //设置SelectPicPopupWindow的View
        this.setContentView(contentView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        //测量view 注意这里，如果没有测量  ，下面的popupHeight高度为-2  ,因为LinearLayout.LayoutParams.WRAP_CONTENT这句自适应造成的
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupWidth = contentView.getMeasuredWidth();    //  获取测量后的宽度
        popupHeight = contentView.getMeasuredHeight();  //获取测量后的高度

        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 重写onKeyListener,按返回键消失
        contentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    AudioPopupWindow.this.dismiss();
                    return true;
                }
                return false;
            }
        });

        //点击其他地方消失
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (AudioPopupWindow.this.isShowing()) {
                    AudioPopupWindow.this.dismiss();
                    return true;
                }
                return false;
            }
        });
        if (this.isShowing()) {
            return;
        }

    }

    public void showAtLocation() {

        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        //在控件上方显示
        this.showAtLocation(parent, Gravity.NO_GRAVITY, location[0] - (popupWidth - parent.getMeasuredWidth()) / 2, location[1] / 2);
    }

}
