package com.haidaoservice.yhs.ui.certification.car;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;
import com.haidaoservice.lib.common.tool.InputMethodUtils;
import com.haidaoservice.lib.view.dialog.ToastUtil;

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

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, CarModelSearchActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        tvTitle.setText("选择车型");
        //初始化列表
        //初始化搜索框
        etSearchKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    String key = etSearchKey.getText().toString();
                    if (!TextUtils.isEmpty(key)) {
                        mockData(etSearchKey.getText().toString());
                        InputMethodUtils.hide(etSearchKey);
                    } else {
                        etSearchKey.requestFocus();
                        ToastUtil.show(mContext, "请输入车型名称");
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }

    private void mockData(String key) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_models_search;
    }
}
