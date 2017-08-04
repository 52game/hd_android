package com.haidaoservice.yhs.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.widget.EmptyLayout;
import com.haidaoservice.yhs.widget.RecyclerRefreshLayout;
import com.haidaoservice.lib.base.BasePresenter;
import com.haidaoservice.lib.base.PageBean;
import com.haidaoservice.lib.common.tool.InputMethodUtils;

import butterknife.BindView;

/**
 * Created by zhangfei on 2017/4/11.
 */

public abstract class BaseRecyclerActivity<T extends BasePresenter, E> extends BaseActivity<T>
        implements RecyclerRefreshLayout.SuperRefreshLayoutListener,
        BaseRecyclerAdapter.OnItemClickListener {

    private final String TAG = this.getClass().getSimpleName();
    protected BaseRecyclerAdapter<E> mAdapter;

    protected boolean isRefreshing;

    protected PageBean<T> mBean;

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    protected RecyclerRefreshLayout mRefreshLayout;
    @BindView(R.id.errorLayout)
    protected EmptyLayout mErrorLayout;
    @BindView(R.id.layoutListContainer)
    protected LinearLayout mLayoutListContainer;


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
        mBean = new PageBean<>();
        mAdapter = getRecyclerAdapter();
        initRecycler();
        mAdapter.setState(BaseRecyclerAdapter.STATE_HIDE, false);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mRefreshLayout.setSuperRefreshLayoutListener(this);
        mAdapter.setState(BaseRecyclerAdapter.STATE_HIDE, false);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_DRAGGING == newState && mContext != null
                        && BaseRecyclerActivity.this.getCurrentFocus() != null) {
                    InputMethodUtils.hide(BaseRecyclerActivity.this);
                }
            }
        });
        mRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        boolean isNeedEmptyView = isNeedEmptyView();
        if (isNeedEmptyView) {
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
            mRefreshLayout.setVisibility(View.GONE);
            if (isLoadingData()) {
                onRefreshing();
            }
        } else {
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            mRefreshLayout.setVisibility(View.VISIBLE);
            if (isLoadingData()) {
                mRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(true);
                        onRefreshing();
                    }
                });
            }
        }
    }

    protected void initRecycler() {
        mRecyclerView.setLayoutManager(getLayoutManager());
    }

    protected abstract BaseRecyclerAdapter<E> getRecyclerAdapter();

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    protected GridLayoutManager getLayoutManager(int spanCount) {
        return new GridLayoutManager(mContext, spanCount);
    }

    /**
     * 需要空的View
     *
     * @return isNeedEmptyView
     */
    protected boolean isNeedEmptyView() {
        return true;
    }

    /**
     * 是否进入页面就加载数据
     *
     * @return isNeedEmptyView
     */
    protected boolean isLoadingData() {
        return true;
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_base_recycler;
    }


    @Override
    public void onRefreshing() {
        if (isRefreshing)
            return;
        isRefreshing = true;
        mBean.setPage(0);
        requestData();
    }

    @Override
    public void onLoadMore() {
        mAdapter.setState(BaseRecyclerAdapter.STATE_LOADING, true);
        mBean.setPage(mBean.getPage() + 1);
        requestData();
    }

    protected void requestData() {

    }

    protected void onComplete() {
        mRefreshLayout.setVisibility(View.VISIBLE);
        mRefreshLayout.onComplete();
        isRefreshing = false;
    }

    @Override
    public void onItemClick(int position, long itemId) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
