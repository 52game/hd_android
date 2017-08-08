/*
*AddressListActivity     2017-08-08
*Copyright(c) 2017 xuwei Co.Ltd. All right reserved.
*/
package com.haidaoservice.yhs.ui.my.address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseRecyclerActivity;
import com.haidaoservice.yhs.base.BaseRecyclerAdapter;
import com.haidaoservice.yhs.ui.my.address.adapter.AddressAdapter;
import com.haidaoservice.yhs.widget.EmptyLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * author:davidinchina on 2017/8/8 12:38
 * email:davicdinchina@gmail.com
 * version:1.0.0
 * des:地址设置
 */
public class AddressListActivity extends BaseRecyclerActivity {
    private int times = 0;
    private Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //各种数据的处理
                    if (times == 1 || times == 2) {
                        times++;
                        List<String> data = new ArrayList<>();
                        data.add("江苏省无锡市江阴市月城镇建国路21号");
                        data.add("江苏省无锡市江阴市月城镇南京路123号");
                        data.add("江苏省无锡市江阴市月城镇上海路533号");
                        data.add("江苏省无锡市江阴市月城镇北京路223号");
                        data.add("江苏省无锡市江阴市月城镇西安路733号");
                        handleResult(data, 15);//获取数据成功则这样回调，
                    } else if (times == 0) {
                        times++;
                        onError(EmptyLayout.NODATA, 0);//设置没有数据，可以点击重新加载
                    } else if (times == 3) {
                        times++;
                        onError(EmptyLayout.NODATA, BaseRecyclerAdapter.STATE_LOAD_ERROR);//设置没有数据，可以点击重新加载
                        if (times == 4) {
                            times = 0;//回到最初效果
                        }
                    }
                    break;
            }
        }
    };

    /**
     * @desc 不带参数构造意图
     * @author:davidinchina on 2017/8/8 12:38
     */
    public static Intent createContext(Context mContext) {
        Intent intent = new Intent(mContext, AddressListActivity.class);
        return intent;
    }

    /**
     * @desc 带参构造意图
     * @author:davidinchina on 2017/8/8 12:38
     */
    public static Intent createContext(Context mContext, Bundle bundle) {
        Intent intent = new Intent(mContext, AddressListActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void initView() {
        super.initView();//初始化刷新控件等
        //初始化页面控价
    }

    @Override
    public int getLayoutId() {
        //因为UI有其他控件，所以重写覆盖布局文件，不适用默认布局
        return R.layout.activity_address_layout;
    }

    @Override
    protected void requestData() {
        //模拟数据请求
        handle.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tvTitle.setText("地址设置");
        ivLeft.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.ivActionLeft, R.id.btnAddAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivActionLeft:
                finish();
                break;
            case R.id.btnAddAddress:
                showShortToast("添加新地址");
                break;
        }
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new AddressAdapter(mContext);
    }
}