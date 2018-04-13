package com.dhgate.dt.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.entity.SendApplyProduct;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ExportBillProductListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;

    private List<SendApplyProduct> mList = new ArrayList<>();

    private ListView mListView;

    public ExportBillProductListAdapter(Context context, ListView listView) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mListView = listView;
    }

    public void bindData(List<SendApplyProduct> list) {
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public void updateList(List<SendApplyProduct> list) {
        this.mList = list;
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

        @Bind(R.id.checkbox)
        public CheckBox checkBox;

        @Bind(R.id.tv_single_price)
        public TextView tv_single_price;

        @Bind(R.id.item_price_total)
        public TextView tv_item_price_total;

        @Bind(R.id.tv_name)
        public TextView tv_name;

        @Bind(R.id.tv_count)
        public TextView tv_count;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        SendApplyProduct product = mList.get(position);

        View view = convertView;
        if (view == null) {
            ViewHolder vh = new ViewHolder();
            view = mInflater.inflate(R.layout.item_export_bill_product_list, arg2, false);
            ButterKnife.bind(vh, view);
            view.setTag(vh);
        }
        ViewHolder vh = (ViewHolder) view.getTag();

        vh.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListView.setItemChecked(position, ((CheckBox) v).isChecked());
                notifyDataSetChanged();
            }
        });
        vh.checkBox.setChecked(mListView.isItemChecked(position));

        vh.tv_name.setText(product.getName());
        vh.tv_count.setText("x " + product.getCount());
        vh.tv_single_price.setText("¥ " + product.getSinglePrice() + "");
        vh.tv_item_price_total.setText("¥ " + product.getTotalPrice() + "");

        return view;
    }

}
