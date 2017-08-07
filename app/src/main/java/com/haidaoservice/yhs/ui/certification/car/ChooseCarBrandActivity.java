package com.haidaoservice.yhs.ui.certification.car;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseRecyclerAdapter;
import com.haidaoservice.yhs.base.ToolbarActivity;
import com.haidaoservice.lib.view.dialog.ToastUtil;
import com.haidaoservice.yhs.ui.certification.car.search.CarModelSearchActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

/**
 * ClassName: ChooseCarBrandActivity
 * Description: 选择车品牌页面
 * Creator: chenwei
 * Date: 2017/8/3 17:09
 * Version: 1.0
 */
public class ChooseCarBrandActivity extends ToolbarActivity {

    @BindView(R.id.indexableLayout)
    IndexableLayout indexableLayout;

    private CarLinearLayoutAdapter mLinearLayoutAdapter;

    private CarBrandHeaderAdapter mGridLayoutAdapter;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, ChooseCarBrandActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("选择车型");
        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
        mLinearLayoutAdapter = new CarLinearLayoutAdapter(mContext);
        indexableLayout.setAdapter(mLinearLayoutAdapter);
        mLinearLayoutAdapter.setDatas(mockCar(100));
        indexableLayout.setCompareMode(IndexableLayout.MODE_NONE);
        indexableLayout.setOverlayStyle_MaterialDesign(Color.RED);

        mLinearLayoutAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<CarBrand>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, CarBrand entity) {
                ToastUtil.show(mContext, entity.getName());
            }
        });

        mGridLayoutAdapter = new CarBrandHeaderAdapter(mContext, "热", "热门车型", mockCar(10), new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                ToastUtil.show(mContext, mGridLayoutAdapter.getCar(position).getName());
            }
        });
        indexableLayout.addHeaderAdapter(mGridLayoutAdapter);
    }

    private List<CarBrand> mockCar(int dataCount) {
        List<CarBrand> models = new ArrayList<>();
        models.add(new CarBrand("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3225718973,3662862966&fm=26&gp=0.jpg", "奥迪"));
        models.add(new CarBrand("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1908345536,170139177&fm=26&gp=0.jpg", "宝马"));
        Random random = new Random();
        for (int i = 0; i < dataCount-2; i++) {
            models.add(new CarBrand("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1908345536,170139177&fm=26&gp=0.jpg", (char) ('A' + random.nextInt(26)) + "其它"));
        }
        return models;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certification_car_brands;
    }

    @OnClick(R.id.tvSearch)
    public void onViewClicked() {
        startActivity(CarModelSearchActivity.createIntent(mContext));
        overridePendingTransition(0,0);
    }
}
