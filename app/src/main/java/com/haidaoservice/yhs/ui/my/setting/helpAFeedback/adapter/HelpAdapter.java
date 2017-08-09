package com.haidaoservice.yhs.ui.my.setting.helpAFeedback.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseRecyclerAdapter;
import com.haidaoservice.yhs.base.BaseViewHolder;

import butterknife.BindView;

/**
 * author:davidinchina on 2017/8/7 18:18
 * email:davicdinchina@gmail.com
 * version:1.0.0
 * des:帮助反馈页面列表适配器
 */
public class HelpAdapter extends BaseRecyclerAdapter<String> {
    public HelpAdapter(Context context, int mode) {
        super(context, mode);
    }

    public HelpAdapter(Context context) {
        super(context, NEITHER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new HelpHolder(mInflater.inflate(R.layout.item_help_list_item, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, String item, int position) {
        HelpHolder indexHolder = (HelpHolder) holder;
        indexHolder.setInfo(item);
    }

    class HelpHolder extends BaseViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        public HelpHolder(View itemView) {
            super(itemView);
        }

        public void setInfo(String object) {
            tvTitle.setText(object);
        }
    }
}
