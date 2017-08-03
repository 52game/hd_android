package com.movebeans.hd_android.ui.certification.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.movebeans.hd_android.R;

import java.util.List;

/**
 * Created by tr on 2017/8/3.
 */

public class CertificateSkillAdapter extends BaseAdapter{
    private List<String>list;
    private Context context;

    public CertificateSkillAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class Holder {
        TextView tvName;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null) {
            holder=new Holder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_certificate_skill, null);
            holder.tvName=(TextView) convertView.findViewById(R.id.tv_skill_item_name);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tvName.setText(list.get(position));
        return convertView;
    }
}
