package com.haidaoservice.yhs.ui.certification.car;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haidaoservice.yhs.R;
import com.haidaoservice.yhs.base.BaseRecyclerAdapter;
import com.haidaoservice.yhs.base.BaseViewHolder;
import com.haidaoservice.yhs.widget.ScrollGridLayoutManager;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.indexablerv.IndexableHeaderAdapter;

/**
 * ClassName: CarLinearLayoutAdapter
 * Description: 车型列表头部Adapter
 * Creator: chenwei
 * Date: 2017/8/4 10:01
 * Version: 1.0
 */
public class CarModelHeaderAdapter extends IndexableHeaderAdapter<String> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<CarModel> hotCars;
    private BaseRecyclerAdapter.OnItemClickListener mOnItemClickListener;

    public CarModelHeaderAdapter(Context context, String index, String indexTitle, List<CarModel> hostCars, BaseRecyclerAdapter.OnItemClickListener onItemClickListener) {
        super(index, indexTitle, Arrays.asList(""));
        this.mContext = context;
        this.mOnItemClickListener = onItemClickListener;
        this.hotCars = hostCars;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType() {
        return 1;
    }

    public CarModel getCar(int position) {
        return hotCars == null || hotCars.size() <= position || position < 0 ? null : hotCars.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.partial_car_models_header, parent, false);
        ContentVH vh = new ContentVH(view);
        CarModelGridAdapter modelGridAdapter = new CarModelGridAdapter(mContext);
        vh.rlvHotCar.setLayoutManager(new ScrollGridLayoutManager(mContext, 5));
        vh.rlvHotCar.setAdapter(modelGridAdapter);
        modelGridAdapter.setOnItemClickListener(mOnItemClickListener);
        modelGridAdapter.addAll(hotCars);
        return vh;
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, String entity) {
        ContentVH vh = (ContentVH) holder;
    }

    class ContentVH extends BaseViewHolder {
        @BindView(R.id.rlvHotCar)
        RecyclerView rlvHotCar;

        public ContentVH(View itemView) {
            super(itemView);
        }
    }
}
