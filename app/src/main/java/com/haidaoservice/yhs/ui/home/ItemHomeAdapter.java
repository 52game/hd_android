package com.haidaoservice.yhs.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseRecyclerAdapter;
import com.haidaoservice.yhs.base.BaseViewHolder;
import com.haidaoservice.yhs.utils.GlideHelper;
import com.haidaoservice.yhs.widget.StarBar;

import butterknife.BindView;

/**
 * ClassName: ItemCertificationCarAdapter
 * Description: 首页列表适配器
 * Creator: chenwei
 * Date: 2017/8/2 14:21
 * Version: 1.0
 */
public class ItemHomeAdapter extends BaseRecyclerAdapter<RecommandInfo> {


    public ItemHomeAdapter(Context context) {
        super(context, NEITHER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_home_recommand_list, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, RecommandInfo item, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        GlideHelper.showImage(mContext, item.getCover(), myViewHolder.ivHead);
    }

    class MyViewHolder extends BaseViewHolder {
        @BindView(R.id.tvCover)
        ImageView tvCover;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDes)
        TextView tvDes;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.ivSms)
        ImageView ivSms;
        @BindView(R.id.ivTel)
        ImageView ivTel;
        @BindView(R.id.ivHead)
        ImageView ivHead;
        @BindView(R.id.tvUsername)
        TextView tvUsername;
        @BindView(R.id.tvGrade)
        TextView tvGrade;
        @BindView(R.id.starBar)
        StarBar starBar;

        public MyViewHolder(View view) {
            super(view);

        }
    }
}
