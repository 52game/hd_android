package com.haidaoservice.yhs.ui.location;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.ui.location.adapter.CitysAdapter;
import com.haidaoservice.yhs.ui.location.entity.CityName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    // 城市数据
    private List<CityName> citys;
    // 城市ListView
    private ListView lvCitys;
    // 城市Adapter
    private CitysAdapter citysAdapter;
    // 字母导航数据
    private String[] navData;
    // 字母导航ListView
    private ListView lvNav;
    // 字母导航Adapter
    private ArrayAdapter<String> navAdapter;
    private  Cn2Spell spell;
    TextView tvNow;
    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, LocationActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        data();
        findView();
        initView();
    }
    public void initView(){
        // 创建Adapter
        citysAdapter = new CitysAdapter(this, citys);
        navAdapter = new ArrayAdapter<String>(this, R.layout.nav_item, navData);

        // 初始化Listview
        lvCitys = (ListView) findViewById(R.id.lv_location);
        lvNav = (ListView) findViewById(R.id.lv_location_nav);

        // 为ListView配置Adapter
        lvCitys.setAdapter(citysAdapter);
        lvNav.setAdapter(navAdapter);

        // 为右侧字母导航的ListView配置监听器
        InnerOnItemClickListener listener = new InnerOnItemClickListener();
        lvNav.setOnItemClickListener(listener);
    }
    private class InnerOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(
                AdapterView<?> parent, // 哪个AdapterView
                View view, // 哪个列表项对应的View对象
                int position, // 位置，从0开始顺序编号的数值
                long id // 不需要使用！Adapter中getItemId()的返回值
        ) {
            // 1. 根据参数position确定点击的字母
            String str = navData[position];
            // 2. 获取该字母在联系人ListView中应该出现的位置
            int ch = str.charAt(0);
            int pos = citysAdapter.getPositionForSection(ch);
            // 3. 快速滑动ListView
            lvCitys.setSelection(pos);
        }

    }
    public void findView(){
        tvNow=(TextView) findViewById(R.id.tv_location_now);
        tvNow.setFocusable(true);
    }
    public void data(){
        navData = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
        // 设置数据
        citys = new ArrayList<CityName>();
        citys.add(new CityName("南京", "nanjing"));
        citys.add(new CityName("北京", "beijing"));
        citys.add(new CityName("东京", "dongjing"));
        citys.add(new CityName("上海", "shanghai"));
        citys.add(new CityName("广东", "guangdong"));
        citys.add(new CityName("深圳", "chengzheng"));
        citys.add(new CityName("海南", "hainan"));
        citys.add(new CityName("青岛", "qingdao"));
        citys.add(new CityName("黑龙江", "heilongjiang"));
        citys.add(new CityName("乌鲁木齐", "wulumuqi"));
        citys.add(new CityName("泰州", "taizhou"));
        citys.add(new CityName("宿迁", "suqian"));
        citys.add(new CityName("盐城", "yancheng"));
        citys.add(new CityName("淮安", "huanan"));
        citys.add(new CityName("河南", "henan"));
        citys.add(new CityName("河北", "hebei"));
        citys.add(new CityName("山东", "shandong"));
        citys.add(new CityName("山西", "shanxi"));
        citys.add(new CityName("陕西", "shanxi"));
        citys.add(new CityName("湖南", "hunan"));
        citys.add(new CityName("湖北", "hubei"));
        citys.add(new CityName("福建", "fujian"));
        citys.add(new CityName("厦门", "xiameng"));
        citys.add(new CityName("哈尔滨", "haerbing"));
        citys.add(new CityName("天津", "tianjing"));
        citys.add(new CityName("合肥", "hefei"));
        citys.add(new CityName("兴化", "xinghua"));
        citys.add(new CityName("日照", "rizhao"));
        citys.add(new CityName("三亚", "sanya"));
        citys.add(new CityName("南宁", "nanning"));
        citys.add(new CityName("桂林", "guiling"));
        citys.add(new CityName("西安", "xian"));
        citys.add(new CityName("常州", "changzhou"));
        citys.add(new CityName("无锡", "wuxi"));
        citys.add(new CityName("苏州", "suzhou"));
        // 排序，要求List集合中的CityName实现Comparable接口
        Collections.sort(citys);
    }
}
