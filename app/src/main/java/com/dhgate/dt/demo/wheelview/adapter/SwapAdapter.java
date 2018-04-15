package com.dhgate.dt.demo.wheelview.adapter;

import android.view.LayoutInflater;
import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.wheelview.wheel.WheelView;
import java.util.List;

/**
 * Created by you on 2017/10/13.
 */

public class SwapAdapter extends WheelView.WheelAdapter<ViewHolder> {

    private final List<WheelViewBean> wheelViewBeans;

    public SwapAdapter(List<WheelViewBean> wheelViewBeanList) {
        this.wheelViewBeans = wheelViewBeanList;
    }

    @Override
    public int getItemCount() {
        return wheelViewBeans.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(LayoutInflater inflater, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.wheelview_item, null, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WheelViewBean wheelViewBean = wheelViewBeans.get(position);
        if (wheelViewBean.icon == WheelViewBean.ICON_USD) {
            holder.iv_head.setImageResource(R.mipmap.swap_3);
        } else {
            holder.iv_head.setImageResource(R.mipmap.swap_4);
        }
    }
}
