/*
*AddressAdapter     2017-08-08
*Copyright(c) 2017 xuwei Co.Ltd. All right reserved.
*/
package com.haidaoservice.yhs.ui.my.address.adapter;

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
 * author:davidinchina on 2017/8/8 15:12
 * email:davicdinchina@gmail.com
 * version:1.0.0
 * des: 地址适配器
 */
public class AddressAdapter extends BaseRecyclerAdapter<String> {
    public AddressAdapter(Context context, int mode) {
        super(context, mode);
    }

    public AddressAdapter(Context context) {
        super(context, ONLY_FOOTER);//允许显示加载更多
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new AddressAdapterHolder(mInflater.inflate(R.layout.item_address_layout_item, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, String item, int position) {
        AddressAdapterHolder holder2 = (AddressAdapterHolder) holder;
        holder2.setInfo(item);
    }

    class AddressAdapterHolder extends BaseViewHolder {
        @BindView(R.id.tvAddress)
        TextView tvAddress;

        public AddressAdapterHolder(View itemView) {
            super(itemView);
        }

        public void setInfo(String object) {
            tvAddress.setText(object);
        }
    }
}
