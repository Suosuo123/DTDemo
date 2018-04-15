package com.dhgate.dt.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.entity.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ConfirmOrderProductListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;

    private List<Product> mList = new ArrayList<>();

    public ConfirmOrderProductListAdapter(Context context) {
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
    public boolean hasStableIds() {
        return true;
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
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        View view = convertView;
        if (view == null) {
            ViewHolder vh = new ViewHolder();
            view = mInflater.inflate(R.layout.item_confirm_order_product_list, arg2, false);
            ButterKnife.bind(vh, view);
            view.setTag(vh);
        }
        ViewHolder vh = (ViewHolder) view.getTag();
        vh.tv_index.setText((position + 1) + "");
        return view;
    }

}
