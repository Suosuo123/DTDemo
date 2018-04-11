package com.dhgate.dt.demo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.adapter.OrderListAdapter1;
import com.dhgate.dt.demo.entity.Order1;
import com.dhgate.dt.demo.entity.Product;
import com.dhgate.dt.demo.widget.WinToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
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

    @Bind(R.id.lv_product)
    public ListView lv_product;

    @OnClick(R.id.tv_send)
    public void addToOrderClick() {
        if (lv_product.getCheckedItemCount() <= 0) {
            WinToast.toast(mActivity, "请选择订单");
            return;
        }
    }


    private OrderListAdapter1 mAdapter;
    private List<Order1> mList = new ArrayList<>();

    private int mTotalCount = 3;


    @Override
    protected void initView() {

        mTotalCount = getArguments().getInt("count", mTotalCount);

        mAdapter = new OrderListAdapter1(mActivity, lv_product);
        lv_product.setAdapter(mAdapter);
        lv_product.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);


        Order1 order1 = new Order1("未输入客户名称", "未付定金", "付款", "");
        Order1 order2 = new Order1("高捷零配件有限公司", "定金已付", "付款", "付款延期");
        Order1 order3 = new Order1("未输入客户名称", "备货中", "送仓申请", "送仓延期");
        Order1 order4 = new Order1("未输入客户名称", "备货中", "催交货", "");
        Order1 order5 = new Order1("未输入客户名称", "对方删除", "同意", "拒绝");
        Order1 order6 = new Order1("未输入客户名称", "对方删除", "拆单", "接单");
        Order1 order7 = new Order1("未输入客户名称", "对方删除", "同意", "拒绝");

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
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initData() {

    }

}