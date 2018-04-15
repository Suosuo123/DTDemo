package com.dhgate.dt.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.activity.CreateOrderActivity;
import com.dhgate.dt.demo.entity.Product;
import com.dhgate.dt.demo.entity.SendApplyProduct;
import com.dhgate.dt.demo.utils.log.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderProductListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;

    private List<Product> mList = new ArrayList<>();

    public OrderProductListAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void bindData(List<Product> list) {
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public void updateList(List<Product> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void removeOneItem(int position) {
        this.mList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int arg0) {
        return mList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public class ViewHolder {

        @BindView(R.id.index)
        public TextView tv_index;

        @BindView(R.id.iv_icon)
        public ImageView iv_icon;

        @BindView(R.id.tv_single_price)
        public TextView tv_single_price;

        @BindView(R.id.item_price_total)
        public TextView tv_item_price_total;

        @BindView(R.id.tv_name)
        public TextView tv_name;

        @BindView(R.id.tv_mode)
        public TextView tv_mode;

        @BindView(R.id.tv_count)
        public TextView tv_count;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {

        Product product = mList.get(position);

        View view = convertView;
        if (view == null) {
            ViewHolder vh = new ViewHolder();
            view = mInflater.inflate(R.layout.item_create_order_product_list, arg2, false);
            ButterKnife.bind(vh, view);
            view.setTag(vh);
        }
        ViewHolder vh = (ViewHolder) view.getTag();

        vh.tv_index.setText((position + 1) + "");
        vh.iv_icon.setImageResource(product.getIconResId());
        vh.tv_name.setText(product.getName());
        vh.tv_mode.setText(product.getMode());
        vh.tv_count.setText("x " + product.getCount());
        vh.tv_single_price.setText("¥ " + product.getSinglePrice() + "");
        vh.tv_item_price_total.setText("¥ " + product.getTotalPrice() + "");
        return view;
    }

}
