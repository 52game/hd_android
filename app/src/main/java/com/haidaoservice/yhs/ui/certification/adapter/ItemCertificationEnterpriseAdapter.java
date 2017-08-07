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
import com.haidaoservice.yhs.ui.certification.EnterpriseCertificationItem;
import com.haidaoservice.yhs.utils.GlideHelper;

import butterknife.BindView;

import static com.haidaoservice.yhs.ui.certification.EnterpriseCertificationItem.TYPE_ENTERPRISE_CERTIFICATION;
import static com.haidaoservice.yhs.ui.certification.SkillCertificationItem.APPROVE;
import static com.haidaoservice.yhs.ui.certification.SkillCertificationItem.OUT_OF_DATE;
import static com.haidaoservice.yhs.ui.certification.SkillCertificationItem.REJECT;
import static com.haidaoservice.yhs.ui.certification.SkillCertificationItem.REVIEW;

/**
 * ClassName: ItemCertificationCarAdapter
 * Description: 认证中心>企业认证列表适配器
 * Creator: chenwei
 * Date: 2017/8/2 14:21
 * Version: 1.0
 */
public class ItemCertificationEnterpriseAdapter extends BaseRecyclerAdapter<EnterpriseCertificationItem> {


    public ItemCertificationEnterpriseAdapter(Context context) {
        super(context, NEITHER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_certification_enterprise, parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, EnterpriseCertificationItem item, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        GlideHelper.showImage(mContext,item.getIcon(),myViewHolder.ivIcon);
        myViewHolder.tvName.setText(item.getName());
        switch (item.getStatus()) {
            case APPROVE:
                myViewHolder.tvStatus.setText(item.getType()==TYPE_ENTERPRISE_CERTIFICATION?"已认证":"挂靠成功");
                break;
            case REVIEW:
                myViewHolder.tvStatus.setText(item.getType()==TYPE_ENTERPRISE_CERTIFICATION?"审核中":"等待审核");
                break;
            case OUT_OF_DATE:
                myViewHolder.tvStatus.setText(item.getType()==TYPE_ENTERPRISE_CERTIFICATION?"已过期":"已失效");
                break;
            case REJECT:
                myViewHolder.tvStatus.setText(item.getType()==TYPE_ENTERPRISE_CERTIFICATION?"审核未通过":"已拒绝");
                break;
        }

    }

    protected class MyViewHolder extends BaseViewHolder {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvStatus)
        TextView tvStatus;

        public MyViewHolder(View view) {
            super(view);

        }
    }
}
