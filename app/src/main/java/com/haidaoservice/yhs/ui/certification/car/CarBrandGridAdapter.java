package com.haidaoservice.yhs.ui.certification.car;

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

import butterknife.BindView;

/**
 * ClassName: CarBrandGridAdapter
 * Description: 车型列表网格列表Adapter
 * Creator: chenwei
 * Date: 2017/8/4 10:01
 * Version: 1.0
 */
public class CarBrandGridAdapter extends BaseRecyclerAdapter<CarBrand> {
    private Context mContext;

    public CarBrandGridAdapter(Context context) {
        super(context,NEITHER);
        this.mContext = context;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        View view = mInflater.inflate(R.layout.item_car_models_vertical, parent,false);
        return new ContentVH(view);
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, CarBrand item, int position) {
        ContentVH vh = (ContentVH) holder;
        vh.tvName.setText(item.getName());
        GlideHelper.showImage(mContext, item.getLogo(), vh.ivLogo);
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
