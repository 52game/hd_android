package com.haidaoservice.yhs.ui.my.bank;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyBankActivity extends ToolbarActivity {
    @BindView(R.id.lv_my_bank)
    ListView lv;

    MyBankAdapter bankAdapter;
    List<String>list=new ArrayList<>();

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MyBankActivity.class);
        return intent;
    }

    @Override
    public void initView(){
        tvTitle.setText("我的银行卡");
        moni();
        bankAdapter=new MyBankAdapter(list,mContext);
        lv.setAdapter(bankAdapter);

        View footview = View.inflate(mContext, R.layout.item_my_bank_add, null);
        lv.addFooterView(footview);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(MyBankDetailActivity.createIntent(mContext));
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_bank;
    }
    //模拟数据
    public void moni(){
        for(int i=0;i<3;i++){
            list.add("");
        }
    }
}
