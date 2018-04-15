package com.dhgate.dt.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.activity.PaymentActivity;
import com.dhgate.dt.demo.activity.SendApply1Activity;
import com.dhgate.dt.demo.entity.Order1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderListAdapter1 extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;

    private List<Order1> mList = new ArrayList<>();

    private ListView mListView;

    public OrderListAdapter1(Context context, ListView listView) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mListView = listView;
    }

    public void bindData(List<Order1> list) {
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public void updateList(List<Order1> list) {
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

        @BindView(R.id.checkbox)
        public CheckBox checkBox;

        @BindView(R.id.tv_name)
        public TextView tv_name;

        @BindView(R.id.tv_company)
        public TextView tv_company;

        @BindView(R.id.tv_money)
        public TextView tv_money;

        @BindView(R.id.tv_action1)
        public TextView tv_action1;

        @BindView(R.id.tv_action2)
        public TextView tv_action2;

        @BindView(R.id.tv_action3)
        public TextView tv_action3;

        @OnClick(R.id.tv_action2)
        public void payClick() {
            if (tv_action2.getText().equals("付款")) {
                Intent intent = new Intent(mContext, PaymentActivity.class);
                mContext.startActivity(intent);
            } else if (tv_action2.getText().equals("送仓申请")) {
                Intent intent = new Intent(mContext, SendApply1Activity.class);
                mContext.startActivity(intent);
            }
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        Order1 order1 = mList.get(position);

        View view = convertView;
        if (view == null) {
            ViewHolder vh = new ViewHolder();
            view = mInflater.inflate(R.layout.item_order_list_1, arg2, false);
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

        vh.tv_name.setText(order1.getName());
        vh.tv_company.setText(order1.getCompany());
        vh.tv_money.setText(order1.getMoney());
        vh.tv_action1.setText(order1.getAction1());
        vh.tv_action2.setText(order1.getAction2());
        vh.tv_action3.setText(order1.getAction3());

        if (order1.getName().equals("请填写收货人名称")) {
            vh.tv_name.setTextColor(mContext.getResources().getColor(R.color.text_gray1));
        } else {
            vh.tv_name.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        vh.tv_action3.setVisibility(TextUtils.isEmpty(order1.getAction3()) ? View.GONE : View.VISIBLE);

        return view;
    }

}
