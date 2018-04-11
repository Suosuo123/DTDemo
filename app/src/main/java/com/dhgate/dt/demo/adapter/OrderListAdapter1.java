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
import com.dhgate.dt.demo.widget.WinToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
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

        @Bind(R.id.checkbox)
        public CheckBox checkBox;

        @Bind(R.id.tv_name)
        public TextView tv_name;

        @Bind(R.id.tv_action1)
        public TextView tv_action1;

        @Bind(R.id.tv_action2)
        public TextView tv_action2;

        @Bind(R.id.tv_action3)
        public TextView tv_action3;

        @OnClick(R.id.tv_action2)
        public void payClick() {
            if (tv_action2.getText().equals("付款")) {
                WinToast.toast(mContext, "付款");
            } else if (tv_action2.getText().equals("送仓申请")) {
                WinToast.toast(mContext, "送仓申请");
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
        vh.tv_action1.setText(order1.getAction1());
        vh.tv_action2.setText(order1.getAction2());
        vh.tv_action3.setText(order1.getAction3());

        if (order1.getName().equals("未输入客户名称")) {
            vh.tv_name.setTextColor(mContext.getResources().getColor(R.color.text_gray1));
        } else {
            vh.tv_name.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        vh.tv_action3.setVisibility(TextUtils.isEmpty(order1.getAction3()) ? View.GONE : View.VISIBLE);

        return view;
    }

}
