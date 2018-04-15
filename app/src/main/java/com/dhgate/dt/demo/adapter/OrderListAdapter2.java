package com.dhgate.dt.demo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.entity.Order1;
import com.dhgate.dt.demo.entity.Order2;
import com.dhgate.dt.demo.widget.WinToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderListAdapter2 extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;

    private List<Order2> mList = new ArrayList<>();

    private ListView mListView;

    public OrderListAdapter2(Context context, ListView listView) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mListView = listView;
    }

    public void bindData(List<Order2> list) {
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public void updateList(List<Order2> list) {
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

        @BindView(R.id.tv_number)
        public TextView tv_number;

        @BindView(R.id.tv_date)
        public TextView tv_date;

        @BindView(R.id.tv_company)
        public TextView tv_company;

        @BindView(R.id.tv_money)
        public TextView tv_money;

        @BindView(R.id.tv_logistics_type)
        public TextView tv_logistics_type;

        @BindView(R.id.tv_action1)
        public TextView tv_action1;

        @OnClick(R.id.tv_action1)
        public void payClick() {

        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        Order2 order2 = mList.get(position);

        View view = convertView;
        if (view == null) {
            ViewHolder vh = new ViewHolder();
            view = mInflater.inflate(R.layout.item_order_list_2, arg2, false);
            ButterKnife.bind(vh, view);
            view.setTag(vh);
        }
        ViewHolder vh = (ViewHolder) view.getTag();

        vh.tv_number.setText(order2.getBillNumber());
        vh.tv_date.setText(order2.getDate());
        vh.tv_company.setText(order2.getCompany());
        vh.tv_money.setText(order2.getMoney());
        vh.tv_action1.setText(order2.getAction1());
        vh.tv_logistics_type.setText(order2.getTv_logistics_type());

        return view;
    }

}
