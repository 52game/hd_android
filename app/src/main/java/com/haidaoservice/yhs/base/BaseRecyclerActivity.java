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

import java.util.List;

import butterknife.BindView;

import static com.haidaoservice.yhs.base.BaseRecyclerAdapter.BOTH_HEADER_FOOTER;
import static com.haidaoservice.yhs.base.BaseRecyclerAdapter.NEITHER;
import static com.haidaoservice.yhs.base.BaseRecyclerAdapter.ONLY_FOOTER;
import static com.haidaoservice.yhs.base.BaseRecyclerAdapter.ONLY_HEADER;

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
    @Nullable
    @BindView(R.id.ivActionLeft)
    public ImageView ivLeft;

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
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mRefreshLayout.setSuperRefreshLayoutListener(this);//下拉刷新，上拉加载监听
        mAdapter.setState(BaseRecyclerAdapter.STATE_HIDE, false);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_DRAGGING == newState && mContext != null
                        && BaseRecyclerActivity.this.getCurrentFocus() != null) {
                    InputMethodUtils.hide(BaseRecyclerActivity.this);//隐藏输入框
                }
            }
        });
        //设置刷新颜色
        mRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里是错误提示的点击重新加载
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                mRefreshLayout.setVisibility(View.GONE);
                if (isLoadingData()) {
                    onRefreshing();
                }
            }
        });
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

    //可以得到其他布局类型，GridLayout
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
    public void onRefreshing() {//下拉刷新回调
        if (isRefreshing)
            return;
        isRefreshing = true;
        mBean.setPage(PageBean.PageStart);
        requestData();
    }

    @Override
    public void onLoadMore() {//上拉加载回调
        if (mAdapter.BEHAVIOR_MODE == BOTH_HEADER_FOOTER
                || mAdapter.BEHAVIOR_MODE == ONLY_FOOTER) {//有加载，则根据数据大小设置footer
            mAdapter.setState(BaseRecyclerAdapter.STATE_LOADING, true);
        }
        mBean.setPage(mBean.getPage() + 1);
        requestData();
    }

    //开始数据请求,必须覆盖请求方法
    protected abstract void requestData();

    /**
     * @author davidinchina
     * cerate at 2017/8/8 下午3:24
     * @description 处理数据请求结果
     * list：获取到的数据集合
     * total：服务器端返回的数据总量，不分页获取数据则为0
     */
    protected void handleResult(List<E> list, int total) {
        mBean.setTotal(total);//更新数据总量
        if (mBean.getPage() == PageBean.PageStart) {
            //第一页，则清空已有数据
            mAdapter.clear();
        }
        mAdapter.addAll(list);
        onComplete();
    }

    /**
     * @author davidinchina
     * cerate at 2017/8/8 下午2:32
     * @description 刷新或者加载成功，由数据请求回调调用
     */
    protected void onComplete() {
        mRefreshLayout.setVisibility(View.VISIBLE);
        if (mBean.getTotal() <= mAdapter.getCount() && (mAdapter.BEHAVIOR_MODE == BOTH_HEADER_FOOTER
                || mAdapter.BEHAVIOR_MODE == ONLY_FOOTER)) {//有加载，则根据数据大小设置footer
            mAdapter.setState(BaseRecyclerAdapter.STATE_NO_MORE, true);
        }
        mRefreshLayout.onComplete(mBean.getTotal() > mAdapter.getCount());
        mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);//隐藏错误提示内容
        isRefreshing = false;

    }

    /**
     * @author davidinchina
     * cerate at 2017/8/8 下午2:45
     * @description 错误提示，根据类型提示，或是网络错误，或是没有数据，或是未登录其他
     * firstType 第一页错误，整体提示错误类型
     * otherType 其它页错误，footer提示错误类型
     */
    protected void onError(int firstType, int otherType) {
        if (mBean.getPage() == PageBean.PageStart || mAdapter.BEHAVIOR_MODE == NEITHER
                || mAdapter.BEHAVIOR_MODE == ONLY_HEADER) {//第一页内容错误或者不分页获取数据错误
            mRefreshLayout.setVisibility(View.GONE);//隐藏数据列表
            mRefreshLayout.onComplete(mBean.getTotal() > mAdapter.getCount());
            mErrorLayout.setErrorType(firstType);//显示错误提示项
        } else {
            mRefreshLayout.setVisibility(View.VISIBLE);//显示数据列表
            mAdapter.setState(otherType, true);
            mRefreshLayout.onComplete(mBean.getTotal() > mAdapter.getCount());
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);//隐藏错误提示项
        }
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
