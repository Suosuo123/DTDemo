package com.dhgate.dt.demo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.TextView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.activity.CreateOrderActivity;
import com.dhgate.dt.demo.adapter.CreateOrderProductListAdapter;
import com.dhgate.dt.demo.entity.Product;
import com.dhgate.dt.demo.utils.CommonUtils;
import com.dhgate.dt.demo.utils.log.LogUtils;
import com.dhgate.dt.demo.widget.swipeMenuListView.MySwipeMenuListView;
import com.dhgate.dt.demo.widget.swipeMenuListView.SwipeMenu;
import com.dhgate.dt.demo.widget.swipeMenuListView.SwipeMenuCreator;
import com.dhgate.dt.demo.widget.swipeMenuListView.SwipeMenuItem;
import com.dhgate.dt.demo.widget.swipeMenuListView.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class CreateOrderFragment extends BaseFragment {


    public static CreateOrderFragment newInstance(int count) {
        CreateOrderFragment f = new CreateOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count", count);
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_create_order;
    }

    @Bind(R.id.lv_product)
    public MySwipeMenuListView lv_product;

    @Bind(R.id.tv_money)
    public TextView tv_money;

    private CreateOrderProductListAdapter mAdapter;
    private List<Product> mList = new ArrayList<>();

    private int mTotalCount = 3;
    private int mSinglePrice = 28;
    private int mTotalPrice = 0;


    @Override
    protected void initView() {

        lv_product.setNestedpParent(((CreateOrderActivity) mActivity).viewPager);

        mTotalCount = getArguments().getInt("count", 3);
        mTotalPrice = mTotalCount * mSinglePrice;
        tv_money.setText("¥" + mTotalPrice);

        mAdapter = new CreateOrderProductListAdapter(mActivity);
        lv_product.setAdapter(mAdapter);

        for (int i = 0; i < mTotalCount; i++) {
            mList.add(new Product());
        }
        mAdapter.bindData(mList);


        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mActivity);
                deleteItem.setBackground(R.color.red1);
                deleteItem.setWidth(CommonUtils.dip2pixel(mActivity, 60));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.WHITE);
                //deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        lv_product.setMenuCreator(creator);

        // step 2. listener item click event
        lv_product.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:// delete
                        mAdapter.removeOneItem(position);
                        mTotalPrice = mSinglePrice * lv_product.getCount();
                        tv_money.setText("¥" + mTotalPrice);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    protected void initData() {

    }

}