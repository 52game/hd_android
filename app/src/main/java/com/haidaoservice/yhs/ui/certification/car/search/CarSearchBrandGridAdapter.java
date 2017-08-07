package com.haidaoservice.yhs.ui.certification.car.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseRecyclerAdapter;
import com.haidaoservice.yhs.base.BaseViewHolder;
import com.haidaoservice.yhs.ui.certification.car.CarBrand;

import butterknife.BindView;

/**
 * ClassName: CarSearchBrandGridAdapter
 * Description: 搜索车型中列表头部品牌列表
 * Creator: chenwei 
 * Date: 2017/8/4 15:36
 * Version: 1.0
 */
public class CarSearchBrandGridAdapter extends BaseRecyclerAdapter<CarBrand> {
    private Context mContext;

    public CarSearchBrandGridAdapter(Context context) {
        super(context,NEITHER);
        this.mContext = context;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        View view = mInflater.inflate(R.layout.item_car_search_hot_word, parent,false);
        return new ContentVH(view);
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, CarBrand item, int position) {
        ContentVH vh = (ContentVH) holder;
        vh.tvName.setText(item.getName());
    }

    class ContentVH extends BaseViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;

        public ContentVH(View itemView) {
            super(itemView);
        }
    }
}
