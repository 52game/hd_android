package com.haidaoservice.yhs.ui.certification.car;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseViewHolder;
import com.haidaoservice.yhs.utils.GlideHelper;

import butterknife.BindView;
import me.yokeyword.indexablerv.IndexableAdapter;

/**
 * ClassName: CarLinearLayoutAdapter
 * Description: 车型列表Adapter
 * Creator: chenwei
 * Date: 2017/8/4 10:01
 * Version: 1.0
 */
public class CarLinearLayoutAdapter extends IndexableAdapter<CarModel> {
    private Context mContext;
    private LayoutInflater mInflater;

    public CarLinearLayoutAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_car_models_title, parent,false);
        return new IndexVH(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_car_models_horizontal, parent,false);
        return new ContentVH(view);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
        IndexVH vh = (IndexVH) holder;
        vh.tvTitle.setText(indexTitle);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, CarModel entity) {
        ContentVH vh = (ContentVH) holder;
        vh.tvName.setText(entity.getName());
        GlideHelper.showImage(mContext, entity.getLogo(), vh.ivLogo);
    }

    class IndexVH extends BaseViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        public IndexVH(View itemView) {
            super(itemView);
        }
    }

    class ContentVH extends BaseViewHolder {
        @BindView(R.id.ivLogo)
        ImageView ivLogo;
        @BindView(R.id.tvName)
        TextView tvName;

        public ContentVH(View itemView) {
            super(itemView);
        }
    }
}
