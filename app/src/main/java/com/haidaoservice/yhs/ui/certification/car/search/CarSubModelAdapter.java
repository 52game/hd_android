package com.haidaoservice.yhs.ui.certification.car.search;

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
 * ClassName: CarSubModelAdapter
 * Description: 车型子型号适配器
 * Creator: chenwei
 * Date: 2017/8/4 14:49
 * Version: 1.0
 */
public class CarSubModelAdapter extends BaseRecyclerAdapter<CarSubModel> {
    public CarSubModelAdapter(Context context) {
        super(context, NEITHER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_car_search_result, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, CarSubModel item, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvName.setText(item.getName());
    }

    protected class MyViewHolder extends BaseViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;

        public MyViewHolder(View view) {
            super(view);

        }
    }
}
