package com.dhgate.dt.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.activity.MakeLogisticsFormActivity;
import com.dhgate.dt.demo.activity.OrderDetailActivity;
import com.dhgate.dt.demo.adapter.OrderListAdapter1;
import com.dhgate.dt.demo.entity.Order1;
import com.dhgate.dt.demo.entity.Product;
import com.dhgate.dt.demo.widget.WinToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderListFragment1 extends BaseFragment {


    public static OrderListFragment1 newInstance() {
        OrderListFragment1 f = new OrderListFragment1();
        Bundle bundle = new Bundle();
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_order_list1;
    }

    @BindView(R.id.lv_product)
    public ListView lv_product;

    @BindView(R.id.checkbox)
    public CheckBox checkbox;

    @OnClick(R.id.tv_send)
    public void addToOrderClick() {
        if (lv_product.getCheckedItemCount() <= 0) {
            WinToast.toast(mActivity, "请选择订单");
            return;
        }
        Intent intent = new Intent(mActivity, MakeLogisticsFormActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rel_select_all)
    public void selectAllClick() {
        if (mSelectAll) {
            selectAll(false);
        } else {
            selectAll(true);
        }
        mSelectAll = !mSelectAll;
        checkbox.setChecked(mSelectAll);
    }

    private void selectAll(boolean selectAll) {
        for (int i = 0; i < lv_product.getCount(); i++) {
            lv_product.setItemChecked(i, selectAll);
        }
        mAdapter.notifyDataSetChanged();
    }

    private boolean mSelectAll;

    private OrderListAdapter1 mAdapter;
    private List<Order1> mList = new ArrayList<>();

    private int mTotalCount = 3;


    @Override
    protected void initView() {

        mTotalCount = getArguments().getInt("count", mTotalCount);

        mAdapter = new OrderListAdapter1(mActivity, lv_product);
        lv_product.setAdapter(mAdapter);
        lv_product.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        Order1 order1 = new Order1("MOHAMED", "广州金行电子科技", "¥25475.00", "定金已付", "付款", "付款延期");
        Order1 order2 = new Order1("请填写收货人名称", "高捷零配件有限公司", "¥15000.00", "定金已付", "付款", "付款延期");
        Order1 order3 = new Order1("请填写收货人名称", "天翼贸易有限公司", "¥386670.00", "备货中", "送仓申请", "送仓延期");
        Order1 order4 = new Order1("请填写收货人名称", "瑞亭贸易有限公司", "¥764000.00", "备货中", "催交货", "");
        Order1 order5 = new Order1("请填写收货人名称", "华南日通有限公司", "¥304550.00", "对方删除", "同意", "拒绝");
        Order1 order6 = new Order1("请填写收货人名称", "华澳贸易有限公司", "¥43000.00", "对方删除", "拆单", "拆单");
        Order1 order7 = new Order1("请填写收货人名称", "成达信息科技公司", "¥50000.00", "对方删除", "同意", "拒绝");

        mList.add(order1);
        mList.add(order2);
        mList.add(order3);
        mList.add(order4);
        mList.add(order5);
        mList.add(order6);
        mList.add(order7);

        mAdapter.updateList(mList);

        lv_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

}