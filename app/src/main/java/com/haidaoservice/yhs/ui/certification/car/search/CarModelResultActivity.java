package com.haidaoservice.yhs.ui.certification.car.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ClassName: CarModelResultActivity
 * Description: 车型品牌型号列表页面
 * Creator: chenwei
 * Date: 2017/8/4 14:38
 * Version: 1.0
 */
public class CarModelResultActivity extends ToolbarActivity {
    @BindView(R.id.rlvResult)
    RecyclerView rlvResult;

    private CarSubModelAdapter mAdapter;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, CarModelResultActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("选择车型");
        //车型列表
        rlvResult.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CarSubModelAdapter(mContext);
        rlvResult.setAdapter(mAdapter);

        mockData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_search_model_result;
    }

    /**
     * 模拟数据
     */
    private void mockData() {
        List<CarSubModel> carSubModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            carSubModels.add(new CarSubModel("奥迪A" + (i + 1)));
        }
        mAdapter.addAll(carSubModels);
    }

}
