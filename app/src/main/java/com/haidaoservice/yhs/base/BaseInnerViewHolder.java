package com.haidaoservice.yhs.base;

import android.view.View;

import butterknife.ButterKnife;


/**
 * ClassName: BaseInnerViewHolder
 * Description: 利用ButterKnife与内部类做额外布局的控件查找
 * Creator: chenwei
 * Date: 16/9/2 10:01
 * Version: 1.0
 */
public class BaseInnerViewHolder {

    public BaseInnerViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

}
