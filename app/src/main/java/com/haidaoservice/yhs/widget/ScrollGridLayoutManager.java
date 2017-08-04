package com.haidaoservice.yhs.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * 禁用RecycleView滑动的GridLayoutManager
 */
public class ScrollGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;

    public ScrollGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public ScrollGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }


    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}