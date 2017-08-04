package com.haidaoservice.yhs.ui.certification.skill;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.ui.certification.adapter.CertificateSkillAdapter;

import java.util.ArrayList;
import java.util.List;

public class CertificateSkillActivity extends AppCompatActivity {
    //listview 适配器 数据
    private ListView lv;
    private CertificateSkillAdapter adapter;
    private List<String>list=new ArrayList<>();

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, CertificateSkillActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate_skill);
        data();
        initView();
    }
    public void initView(){
        lv=(ListView) findViewById(R.id.lv_certificate_skill);
        adapter=new CertificateSkillAdapter(list,CertificateSkillActivity.this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    public  void  data(){
        list.add("开锁上岗证");
        list.add("育婴上岗证");
        list.add("保姆上岗证");
        list.add("驾驶证");
    }

}