package com.haidaoservice.yhs.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haidaoservice.yhs.R;

import java.util.List;

/**
 * ClassName: CustomHorizontalScrollView
 * Description: say something
 * Creator: chenwei 
 * Date: 2017/8/7 10:10
 * Version: 1.0
 */
public class CustomHorizontalScrollView extends HorizontalScrollView {
    private List<TabInfo> mTabInfos;

    LinearLayout childLayout;

    LayoutInflater mInflater;

    public CustomHorizontalScrollView(Context context) {
        this(context, null);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        childLayout = new LinearLayout(context);
        childLayout.setOrientation(LinearLayout.HORIZONTAL);
        childLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(childLayout);

        mInflater = LayoutInflater.from(context);
    }

    public void setTabs(List<TabInfo> infoList) {
        this.mTabInfos = infoList;
    }

    private ScrollTabListener onScrollTabListener;

    public void setOnScrollTabListener(ScrollTabListener onScrollTabListener) {
        this.onScrollTabListener = onScrollTabListener;
    }

    public ScrollTabListener getOnScrollTabListener() {
        return onScrollTabListener;
    }

    public void clearTabs() {
        childLayout.removeAllViews();
    }

    public LinearLayout getChildLayout() {
        return childLayout;
    }

    public View getTab(int index) {
        if (childLayout != null && childLayout.getChildCount() > index) {
            return childLayout.getChildAt(index);
        }
        return null;
    }

    /**
     * 更新Tab显示
     **/
    public void updateTabs() {
        childLayout.removeAllViews();
        if (mTabInfos != null && mTabInfos.size() > 0) {
            int height = getResources().getDimensionPixelSize(R.dimen.regular_dimen_x30);
            int paddingLeft = getResources().getDimensionPixelSize(R.dimen.regular_dimen_x15);
            LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
            int textWidth = 0;
            int index = 0;
            for (TabInfo info : mTabInfos) {
                LinearLayout parent = (LinearLayout) mInflater.inflate(R.layout.partial_tabs, null, false);
                TextView view = (TextView) parent.getChildAt(0);
                view.setLayoutParams(viewParams);
                view.setPadding(paddingLeft, 0, paddingLeft, 0);
                view.setText(info.title);
                view.setTag(info.tag);
                view.setOnClickListener(tabClick);
                if (onScrollTabListener != null) {
                    onScrollTabListener.onTabStartAdd(view,info.tag,index);
                }
                childLayout.addView(parent);
                int viewWidth = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                int viewHeight = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                view.measure(viewWidth, viewHeight);
                int factWidth = view.getMeasuredWidth();
                textWidth += factWidth;
                index++;
            }
            int screenWidth = getMeasuredWidth();
            if (textWidth < screenWidth) {
                int count = childLayout.getChildCount();
                float precentWidth = screenWidth * 1.0f / count;
                for (int i = 0; i < count; i++) {
                    View textView = ((LinearLayout) childLayout.getChildAt(i)).getChildAt(0);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
                    params.width = (int) precentWidth;
                    textView.setPadding(0, 0, 0, 0);
                    textView.setLayoutParams(params);
                }
            }
            setVisibility(View.VISIBLE);
        }
        else{
            setVisibility(View.GONE);
        }
    }

    private OnClickListener tabClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onScrollTabListener != null) {
                onScrollTabListener.onTabClick(v, v.getTag());
            }
        }
    };

    public static class TabInfo {
        public String title;
        public Object tag;

        public TabInfo(String title, Object tag) {
            this.title = title;
            this.tag = tag;
        }
    }

    public interface ScrollTabListener {
        /**
         * Tab点击回调
         **/
        public void onTabClick(View view, Object tag);

        /**
         * Tab添加时回调
         **/
        public void onTabStartAdd(View view, Object tag, int position);
    }

}
