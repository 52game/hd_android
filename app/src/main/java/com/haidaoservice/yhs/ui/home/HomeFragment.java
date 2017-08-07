package com.haidaoservice.yhs.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseFragment;
import com.haidaoservice.yhs.ui.certification.car.search.HeaderAndFooterWrapper;
import com.haidaoservice.yhs.ui.sign.SignActivity;
import com.haidaoservice.yhs.widget.CustomHorizontalScrollView;
import com.haidaoservice.yhs.widget.advtextswitcher.AdvTextSwitcher;
import com.haidaoservice.yhs.widget.advtextswitcher.Switcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    private ViewHolder headerHolder;

    @Override
    public void initView() {
        rlvHome.setLayoutManager(new LinearLayoutManager(mContext));
        homeAdapter = new ItemHomeAdapter(mContext);


        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(homeAdapter);
        View headView = LayoutInflater.from(mContext).inflate(R.layout.partial_home_header, null);
        headerHolder = new ViewHolder(headView);
        mHeaderAndFooterWrapper.addHeaderView(headView);

        rlvHome.setAdapter(mHeaderAndFooterWrapper);

        mockData();
    }

    private void mockData() {
        mockHeaderData();
        List<RecommandInfo> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new RecommandInfo());
        }
        homeAdapter.addAll(list);
    }

    Switcher switcher = null;

    private void mockHeaderData() {
        //初始化上部菜单
        List<CustomHorizontalScrollView.TabInfo> tabs = new ArrayList<>();
        String[] topTitle = {"搬家服务", "水电维修", "货物找车", "高级管家", "幼教保育"};
        for (int i = 0; i < topTitle.length; i++) {
            tabs.add(new CustomHorizontalScrollView.TabInfo(topTitle[i], topTitle[i]));
        }
        headerHolder.tabContainer.setTabs(tabs);
        headerHolder.tabContainer.updateTabs();
        //初始化文字跑马灯
        String[] texts = {"Anne: Great!", "Cathy: I do not think so.Cathy: I do not think so.Cathy: I do not think so.Cathy: I do not think so.Cathy: I do not think so.Cathy: I do not think so.Cathy: I do not think so.Cathy: I do not think so.", "Jimmy: Cloning your repo...", "Aoi: This bug disappeared!"};
        headerHolder.switcher.setTexts(texts);
        if (switcher == null) {
            switcher = new Switcher(headerHolder.switcher, 5000);
            switcher.start();
        }
        //初始化具体服务
        String[] titles = {"江阴搬家", "同城货物", "我是土豪", "行程助手"};
        String[] subtitles = {"空调移动，同城搬家", "江阴本地有货来这找", "互联网最热项目", "一款智能计费工具"};
        for (int i = 0; i < 4; i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.partial_home_subservice, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            if (i != 0) {
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.regular_dimen_x5);
            }
            view.setLayoutParams(params);
            ((TextView)view.findViewById(R.id.tvTitle)).setText(titles[i]);
            ((TextView)view.findViewById(R.id.tvSubTitle)).setText(subtitles[i]);
            headerHolder.lltSpecificService.addView(view);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (switcher != null) {
            switcher.pause();
            switcher = null;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @OnClick(R.id.tvSign)
    public void onViewClicked() {
        startActivity(SignActivity.createIntent(mContext));
    }

    static class ViewHolder {
        @BindView(R.id.ivClear)
        ImageView ivClear;
        @BindView(R.id.tabContainer)
        CustomHorizontalScrollView tabContainer;
        @BindView(R.id.ivServiceIcon)
        ImageView ivServiceIcon;
        @BindView(R.id.ivFreightIcon)
        ImageView ivFreightIcon;
        @BindView(R.id.ivToolIcon)
        ImageView ivToolIcon;
        @BindView(R.id.ivMoneyIcon)
        ImageView ivMoneyIcon;
        @BindView(R.id.switcher)
        AdvTextSwitcher switcher;
        @BindView(R.id.lltSpecificService)
        LinearLayout lltSpecificService;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
