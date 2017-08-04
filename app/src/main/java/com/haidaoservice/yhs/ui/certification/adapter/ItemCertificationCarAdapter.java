package com.haidaoservice.yhs.ui.certification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseRecyclerAdapter;
import com.haidaoservice.yhs.base.BaseViewHolder;
import com.haidaoservice.yhs.ui.certification.car.CarCertificationItem;
import com.haidaoservice.yhs.utils.GlideHelper;

import butterknife.BindView;

import static com.haidaoservice.yhs.ui.certification.SkillCertificationItem.APPROVE;
import static com.haidaoservice.yhs.ui.certification.SkillCertificationItem.OUT_OF_DATE;
import static com.haidaoservice.yhs.ui.certification.SkillCertificationItem.REJECT;
import static com.haidaoservice.yhs.ui.certification.SkillCertificationItem.REVIEW;

/**
 * ClassName: ItemCertificationCarAdapter
 * Description: 认证中心>车辆认证列表适配器
 * Creator: chenwei
 * Date: 2017/8/2 14:21
 * Version: 1.0
 */
public class ItemCertificationCarAdapter extends BaseRecyclerAdapter<CarCertificationItem> {


    public ItemCertificationCarAdapter(Context context) {
        super(context, NEITHER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_certification_car, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, CarCertificationItem item, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        GlideHelper.showImage(mContext,item.getIcon(),myViewHolder.ivIcon);
        myViewHolder.tvCarName.setText(item.getName());
        myViewHolder.tvCarLicence.setText(item.getLicence());
        switch (item.getStatus()) {
            case APPROVE:
                myViewHolder.tvStatus.setText("已认证");
                break;
            case REVIEW:
                myViewHolder.tvStatus.setText("审核中");
                break;
            case OUT_OF_DATE:
                myViewHolder.tvStatus.setText("已过期");
                break;
            case REJECT:
                myViewHolder.tvStatus.setText("审核未通过");
                break;
        }

    }

    protected class MyViewHolder extends BaseViewHolder {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvCarName)
        TextView tvCarName;
        @BindView(R.id.tvCarLicence)
        TextView tvCarLicence;
        @BindView(R.id.tvStatus)
        TextView tvStatus;

        public MyViewHolder(View view) {
            super(view);

        }
    }
}
