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

import butterknife.BindView;

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
}
