package com.movebeans.hd_android.ui.certification.car;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.movebeans.hd_android.R;


/**
 * ClassName: SideChoosePopupWindow
 * Description: 侧面弹出选择页面
 * Creator: chenwei
 * Date: 2017/8/2 17:22
 * Version: 1.0
 */
public class SideChoosePopupWindow extends PopupWindow {

    private Context mContext;

    private View contentView;

    private RecyclerView rlvChoose;

    public SideChoosePopupWindow(Context context) {
        super(context);
        this.mContext = context;
        contentView = LayoutInflater.from(context).inflate(R.layout.popup_side_choose, null, false);
        rlvChoose = (RecyclerView) contentView.findViewById(R.id.rlvChoose);
        //设置SelectPicPopupWindow的View
        this.setContentView(contentView);

        //设置SelectPicPopupWindow弹出窗体可点击
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(false);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        //点击其他地方消失
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = rlvChoose.getTop();
                int width = rlvChoose.getLeft();
                int y = (int) event.getY();
                int x = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height || x < width) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }
}
