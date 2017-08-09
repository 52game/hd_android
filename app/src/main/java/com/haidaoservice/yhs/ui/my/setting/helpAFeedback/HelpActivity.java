/*
*HelpActivity     2017-08-07
*Copyright(c) 2017 xuwei Co.Ltd. All right reserved.
*/
package com.haidaoservice.yhs.ui.my.setting.helpAFeedback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;
import com.haidaoservice.yhs.ui.my.setting.helpAFeedback.adapter.HelpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:davidinchina on 2017/8/7 17:31
 * email:davicdinchina@gmail.com
 * version:1.0.0
 * des:
 */
public class HelpActivity extends ToolbarActivity<HelpPresenter> implements HelpContact.HelpContactView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private HelpAdapter helpAdapter;

    /**
     * @desc 不带参数构造意图
     * @author:davidinchina on 2017/8/7 17:31
     */
    public static Intent createContext(Context mContext) {

        Intent intent = new Intent(mContext, HelpActivity.class);
        return intent;
    }

    /**
     * @desc 带参构造意图
     * @author:davidinchina on 2017/8/7 17:31
     */
    public static Intent createContext(Context mContext, Bundle bundle) {
        Intent intent = new Intent(mContext, HelpActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_feedback_layout;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tvTitle.setText("帮助与反馈");
        ivLeft.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.ivActionLeft, R.id.flFeedBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivActionLeft:
                finish();
                break;
            case R.id.flFeedBack:
                startActivity(FeedbackAcitivity.createContext(mContext));
                break;
        }
    }

    @Override
    public void initView() {
        super.initView();
        initRecycle();
        initData();
    }

    /**
     * @param
     * @return
     * @author davidinchina
     * cerate at 2017/8/8 上午9:34
     * @description 设置recycleView
     */
    public void initRecycle() {
        recyclerView.setLayoutManager(getLayoutManager());
        helpAdapter = new HelpAdapter(mContext);
        helpAdapter.addAll(new ArrayList<String>());
        recyclerView.setAdapter(helpAdapter);
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    /**
     * @author davidinchina
     * cerate at 2017/8/8 上午9:31
     * @description 数据设置
     */
    public void initData() {
        List<String> data = new ArrayList<>();
        data.add("公告：为何无法发布信息？");
        data.add("电话呗持续骚扰？看这里！");
        data.add("怎样注册成为用户？");
        data.add("发布的信息置顶怎么扣费？");
        data.add("未通过审核的信息退钱么？");
        helpAdapter.addAll(data);
    }

    @Override
    public void showError(Throwable msg) {

    }

    @Override
    public void querySuccess(Object result) {

    }
}