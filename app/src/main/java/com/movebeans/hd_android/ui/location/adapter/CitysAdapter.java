package com.movebeans.hd_android.ui.location.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.movebeans.hd_android.R;
import com.movebeans.hd_android.ui.location.entity.CityName;

import java.util.List;
import java.util.Locale;

/**
 * Created by tr on 2017/8/2.
 */

public class CitysAdapter extends BaseAdapter implements SectionIndexer {
    private Context context;
    private List<CityName> Citys;

    public CitysAdapter(Context context, List<CityName> Citys) {
        super();
        this.context = context;
        this.Citys = Citys;
    }

    @Override
    public int getCount() {
        return Citys.size();
    }
    class Holder {
        TextView tvName;
        TextView tvPinyin;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityName cityName = Citys.get(position);
        Holder holder;
        if(convertView == null) {
            holder=new Holder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_location, null);
            holder.tvName=(TextView) convertView.findViewById(R.id.tv_location_item_name);
            holder.tvPinyin=(TextView) convertView.findViewById(R.id.tv_location_item_pinyin);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

//        TextView tvName = (TextView) convertView.findViewById(R.id.tv_location_item_name);
//        TextView tvPinyin = (TextView) convertView.findViewById(R.id.tv_location_item_pinyin);
        holder.tvName.setText(cityName.getName());

        holder.tvPinyin.setText(cityName.getPinyin().toUpperCase(Locale.CHINA).substring(0, 1));

        // 显示部分分类字母
        // 根据position获取当前数据的分类字母
        int section = getSectionForPosition(position);
        // 根据分类字母获取该字母应该出现的位置
        int pos = getPositionForSection(section);
        // 3.判断字母应该出现的位置和position是否一致
        if(pos == position) {
            // 如果一致，则显示
            holder.tvPinyin.setVisibility(View.VISIBLE);
        } else {
            // 如果不一致，则隐藏
            holder.tvPinyin.setVisibility(View.GONE);
        }

        // 4. 返回模板的View对象
        return convertView;
    }

    // position：位置，例如0、1、2
    // section：分类字母，例如'A'、'B'

    @Override
    public int getPositionForSection(int section) {
        // 获取参数section分类字母应该出现的位置
        for (int i = 0; i < Citys.size(); i++) {
            int currentSection = getSectionForPosition(i);
            if(currentSection == section) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        // 获取参数position位置上的分类字母
        return Citys.get(position).getPinyin().toUpperCase(Locale.CHINA).charAt(0);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

}
