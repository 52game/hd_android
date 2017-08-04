package com.haidaoservice.yhs.ui.certification.enterprise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseActivity;
import com.haidaoservice.yhs.base.BaseRecyclerAdapter;
import com.haidaoservice.yhs.ui.certification.AttachSearchResultModel;
import com.haidaoservice.yhs.ui.certification.adapter.ItemCertificationAttachAdapter;
import com.haidaoservice.lib.common.tool.InputMethodUtils;
import com.haidaoservice.lib.view.dialog.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ClassName: AttachSearchActivity
 * Description: 挂靠企业搜索页面
 * Creator: chenwei
 * Date: 2017/8/3 16:40
 * Version: 1.0
 */
public class AttachSearchActivity extends BaseActivity {

    @BindView(R.id.etSearchKey)
    EditText etSearchKey;
    @BindView(R.id.rlvResult)
    RecyclerView rlvResult;

    private ItemCertificationAttachAdapter mAdapter;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, AttachSearchActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        //初始化列表
        mAdapter = new ItemCertificationAttachAdapter(mContext);
        rlvResult.setLayoutManager(new LinearLayoutManager(mContext));
        rlvResult.setAdapter(mAdapter);
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
                        ToastUtil.show(mContext, "请输入企业名称或法人代表");
                        return true;
                    }
                }
                return false;
            }
        });
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                AttachSearchResultModel item = mAdapter.getItem(position);
                if (item.getDisplayType() != AttachSearchResultModel.DISPLAY_TITLE) {
                    startActivity(AttachEnterpriseDetailActivity.createIntent(mContext));
                }
            }
        });
    }

    private void mockData(String key) {
        List<AttachSearchResultModel> modelList = new ArrayList<>();
        Random random = new Random();
        int enterpriseCount = random.nextInt(6);
        if (enterpriseCount > 0) {
            AttachSearchResultModel title1 = new AttachSearchResultModel();
            title1.setName("您可能要找的企业");
            title1.setDisplayType(AttachSearchResultModel.DISPLAY_TITLE);
            modelList.add(title1);
        }
        for (int i = 0; i < enterpriseCount; i++) {
            AttachSearchResultModel item = new AttachSearchResultModel();
            item.setName(String.format("%s-%d", key, i + 1));
            item.setLegalPerson("马云");
            item.setDisplayType(AttachSearchResultModel.DISPLAY_ENTERPRISE);
            modelList.add(item);
        }
        int personCount = random.nextInt(6);
        if (personCount > 0) {
            AttachSearchResultModel title2 = new AttachSearchResultModel();
            title2.setName("您可能要找的法人");
            title2.setDisplayType(AttachSearchResultModel.DISPLAY_TITLE);
            modelList.add(title2);
        }
        for (int i = 0; i < personCount; i++) {
            AttachSearchResultModel item = new AttachSearchResultModel();
            item.setName(String.format("%s-%d", key, i + 1));
            item.setDisplayType(AttachSearchResultModel.DISPLAY_LEGAL_PERSON);
            item.setLegalPerson("马化腾");
            modelList.add(item);
        }
        mAdapter.clear();
        mAdapter.addAll(modelList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certification_attach_search;
    }

    @OnClick(R.id.tvCancel)
    public void onViewClicked() {
        mAdapter.clear();
        etSearchKey.setText("");
        InputMethodUtils.show(etSearchKey);
    }
}
