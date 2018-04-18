package com.dhgate.dt.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.activity.ExportManagementActivity;
import com.dhgate.dt.demo.activity.OrderDetailActivity;
import com.dhgate.dt.demo.adapter.OrderListAdapter2;
import com.dhgate.dt.demo.entity.Order2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListFragment2 extends BaseFragment {

    public static OrderListFragment2 newInstance() {
        OrderListFragment2 f = new OrderListFragment2();
        Bundle bundle = new Bundle();
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_order_list2;
    }

    @BindView(R.id.lv_product)
    public ListView lv_product;


    private OrderListAdapter2 mAdapter;
    private List<Order2> mList = new ArrayList<>();

    private int mTotalCount = 3;


    @Override
    protected void initView() {


        mTotalCount = getArguments().getInt("count", mTotalCount);

        mAdapter = new OrderListAdapter2(mActivity, lv_product);
        lv_product.setAdapter(mAdapter);
        lv_product.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        Order2 order1 = new Order2("发票号/入仓号：001", "高捷零配件有限公司", "出库日期/到仓日期：2018/04/05", "¥336000.00", "查看", "自送");
        Order2 order2 = new Order2("发票号/入仓号：002", "百汇自动配件有限公司", "出库日期/到仓日期：2018/04/01", "¥738000.00", "查看", "陆运");
        Order2 order3 = new Order2("发票号/入仓号：003", "杰利皮具贸易有限公司", "出库日期/到仓日期：2018/03/03", "¥9830.00", "查看", "海运");
        Order2 order4 = new Order2("发票号/入仓号：004", "朗发家具贸易有限公司", "出库日期/到仓日期：2018/03/01", "¥900240.00", "查看", "空运");

        mList.add(order1);
        mList.add(order2);
        mList.add(order3);
        mList.add(order4);

        mAdapter.updateList(mList);

        lv_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

}