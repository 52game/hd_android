package com.haidaoservice.yhs.ui.certification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseRecyclerAdapter;
import com.haidaoservice.yhs.base.BaseViewHolder;
import com.haidaoservice.yhs.ui.certification.AttachSearchResultModel;

import butterknife.BindView;

/**
 * ClassName: ItemCertificationAttachAdapter
 * Description: 挂靠企业搜索结果适配器
 * Creator: chenwei
 * Date: 2017/8/3 13:56
 * Version: 1.0
 */
public class ItemCertificationAttachAdapter extends BaseRecyclerAdapter<AttachSearchResultModel> {

    public ItemCertificationAttachAdapter(Context context) {
        super(context, NEITHER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        RecyclerView.ViewHolder holder = null;
        if (type == AttachSearchResultModel.DISPLAY_TITLE) {
            View convertView = mInflater.inflate(R.layout.item_certification_attach_search_title, parent,false);
            holder = new TitleViewHolder(convertView);
        } else if (type == AttachSearchResultModel.DISPLAY_ENTERPRISE) {
            View convertView = mInflater.inflate(R.layout.item_certification_attach_search_enterprise, parent,false);
            holder = new ResultViewHolder(convertView);
        } else if (type == AttachSearchResultModel.DISPLAY_LEGAL_PERSON) {
            View convertView = mInflater.inflate(R.layout.item_certification_attach_search_legal, parent,false);
            holder = new ResultViewHolder(convertView);
        }
        return holder;
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, AttachSearchResultModel item, int position) {
        if (holder instanceof ResultViewHolder) {
            ResultViewHolder myHolder = (ResultViewHolder) holder;
            myHolder.tvName.setText(item.getName());
            myHolder.tvLegalPerson.setText(item.getLegalPerson());
        } else {
            TitleViewHolder myHolder = (TitleViewHolder) holder;
            myHolder.tvTitle.setText(item.getName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getDisplayType();
    }

    protected class ResultViewHolder extends BaseViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvLegalPerson)
        TextView tvLegalPerson;

        public ResultViewHolder(View view) {
            super(view);

        }
    }

    protected class TitleViewHolder extends BaseViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        public TitleViewHolder(View view) {
            super(view);
        }
    }
}
