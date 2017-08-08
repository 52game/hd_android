package com.haidaoservice.yhs.base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
 * Created by zhangfei on 2017/4/1.
 */

public abstract class BaseRecyclerViewFragment<T extends BasePresenter, E> extends BaseFragment<T>
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
                if (RecyclerView.SCROLL_STATE_DRAGGING == newState && getActivity() != null
                        && getActivity().getCurrentFocus() != null) {
                    InputMethodUtils.hide(getActivity());
                }
            }
        });
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
            onRefreshing();
        } else {
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            mRefreshLayout.setVisibility(View.VISIBLE);
            mRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mRefreshLayout.setRefreshing(true);
                    onRefreshing();
                }
            });
        }
    }

    protected void initRecycler() {
        mRecyclerView.setLayoutManager(getLayoutManager());
    }


    protected abstract BaseRecyclerAdapter<E> getRecyclerAdapter();

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    protected GridLayoutManager getLayoutManager(int spanCount) {
        return new GridLayoutManager(mContext, spanCount);
    }

    /**
     * 是否进入页面就加载数据
     *
     * @return isNeedEmptyView
     */
    protected boolean isLoadingData() {
        return true;
    }

    /**
     * 需要空的View
     *
     * @return isNeedEmptyView
     */
    protected boolean isNeedEmptyView() {
        return true;
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_base_recycler;
    }


    @Override
    public void onRefreshing() {
        if (isRefreshing)
            return;
        isRefreshing = true;
        mBean.setPage(PageBean.PageStart);
        requestData();
    }

    @Override
    public void onLoadMore() {
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
     */
    protected void handleResult(List<E> list, int total) {
        mBean.setTotal(total);//更新数据总量
        if (mBean.getPage() == PageBean.PageStart) {
            //第一页
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
}
