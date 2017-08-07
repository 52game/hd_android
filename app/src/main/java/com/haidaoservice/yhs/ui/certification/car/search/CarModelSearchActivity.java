package com.haidaoservice.yhs.ui.certification.car.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.haidaoservice.lib.common.tool.InputMethodUtils;
import com.haidaoservice.lib.view.dialog.ToastUtil;
import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseRecyclerAdapter;
import com.haidaoservice.yhs.base.ToolbarActivity;
import com.haidaoservice.yhs.ui.certification.car.CarBrand;
import com.haidaoservice.yhs.widget.ScrollGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ClassName: AttachSearchActivity
 * Description: 选择车型搜索
 * Creator: chenwei
 * Date: 2017/8/3 16:0
 * Veron: 1.0
 */
public class CarModelSearchActivity extends ToolbarActivity {

    @BindView(R.id.etSearchKey)
    EditText etSearchKey;
    @BindView(R.id.rlvResult)
    RecyclerView rlvResult;

    private CarSubModelAdapter mAdapter;

    private HeaderAndFooterWrapper mWrapperAdapter;

    private View headerView;
    RecyclerView rlvHeaderBrand;
    private CarSearchBrandGridAdapter mBrandGridAdapter;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, CarModelSearchActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        tvTitle.setText("选择车型");
        //初始化列表
        rlvResult.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CarSubModelAdapter(mContext);
        rlvResult.setAdapter(mAdapter);
        mWrapperAdapter = new HeaderAndFooterWrapper(mAdapter);
        headerView = LayoutInflater.from(mContext).inflate(R.layout.partial_car_models_result_header, null);
        rlvHeaderBrand = (RecyclerView) headerView.findViewById(R.id.rlvHeaderBrand);
        rlvHeaderBrand.setLayoutManager(new ScrollGridLayoutManager(mContext, 5));
        mBrandGridAdapter = new CarSearchBrandGridAdapter(mContext);
        rlvHeaderBrand.setAdapter(mBrandGridAdapter);
        mWrapperAdapter.addHeaderView(headerView);
        rlvResult.setAdapter(mWrapperAdapter);
        mBrandGridAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                startActivity(CarModelResultActivity.createIntent(mContext));
            }
        });
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                ToastUtil.show(mContext, mAdapter.getItem(position - mWrapperAdapter.getHeadersCount()).getName());
            }
        });
        //初始化搜索框
        etSearchKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    String key = etSearchKey.getText().toString();
                    if (!TextUtils.isEmpty(key)) {
                        mockSearchResult(etSearchKey.getText().toString());
                        InputMethodUtils.hide(etSearchKey);
                    } else {
                        etSearchKey.requestFocus();
                        ToastUtil.show(mContext, "请输入车型名称");
                        startActivity(CarModelResultActivity.createIntent(mContext));
                        return true;
                    }
                }
                return false;
            }
        });
        mockHotBrand();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private void mockHotBrand() {
        List<CarBrand> carBrands = new ArrayList<>();
        String[] brands = {"大众", "丰田", "现代", "起亚", "别克"};
        for (int i = 0; i < 5; i++) {
            CarBrand brand = new CarBrand("", brands[i]);
            carBrands.add(brand);
        }
        mBrandGridAdapter.addAll(carBrands);
    }

    private void mockSearchResult(String key) {
        mAdapter.clear();
        List<CarSubModel> carSubModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            carSubModels.add(new CarSubModel("奥迪A" + key + (i + 1)));
        }
        mAdapter.addAll(carSubModels);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_models_search;
    }
}
