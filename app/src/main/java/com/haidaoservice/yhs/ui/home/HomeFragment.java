package com.haidaoservice.yhs.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseFragment;
import com.haidaoservice.yhs.ui.sign.SignActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ClassName: HomeFragment
 * Description: 首页
 * Creator: chenwei
 * Date: 2017/8/4 17:49
 * Version: 1.0
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.rlvHome)
    RecyclerView rlvHome;

    private ItemHomeAdapter homeAdapter;
    @Override
    public void initView() {
        rlvHome.setLayoutManager(new LinearLayoutManager(mContext));
        homeAdapter = new ItemHomeAdapter(mContext);
        rlvHome.setAdapter(homeAdapter);
        mockData();
    }

    private void mockData() {
        List<RecommandInfo> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new RecommandInfo());
        }
        homeAdapter.addAll(list);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @OnClick(R.id.tvSign)
    public void onViewClicked() {
        startActivity(SignActivity.createIntent(mContext));
    }
}
